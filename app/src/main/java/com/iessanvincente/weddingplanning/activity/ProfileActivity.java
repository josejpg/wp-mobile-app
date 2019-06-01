package com.iessanvincente.weddingplanning.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.iessanvincente.weddingplanning.R;
import com.iessanvincente.weddingplanning.domain.ClientDto;
import com.iessanvincente.weddingplanning.entity.ClientesEntity;
import com.iessanvincente.weddingplanning.helper.MappingHelper;
import com.iessanvincente.weddingplanning.interfaces.ClientesEntityCallbackInterface;
import com.iessanvincente.weddingplanning.utils.APICalls;

public class ProfileActivity extends AppCompatActivity {

	private static final String TAG = "ProfileActivity";

	ScrollView _scrollViewProfile;
	LinearLayout _linearForm;
	LinearLayout _linearPassword;
	EditText _emailText;
	EditText _nameText;
	EditText _lastNameText;
	EditText _phoneText;
	EditText _mobileText;
	EditText _addressText;
	EditText _postalCodeText;
	EditText _townText;
	EditText _birthdateText;
	EditText _provinceText;
	EditText _passwordText;
	EditText _rePasswordText;
	Button _showPasswordButton;
	Button _updateButton;

	private Gson gson = new Gson();
	private Intent actualIntent;
	private SharedPreferences settings;
	private String userToken;
	private ClientDto clientDto = new ClientDto();
	private APICalls apiCalls = new APICalls();

	@Override
	protected void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_profile );

		_scrollViewProfile = (ScrollView) this.findViewById( R.id.scrollView_profile );
		_linearForm = (LinearLayout) _scrollViewProfile.findViewById(  R.id.linear_form );
		_linearPassword = (LinearLayout) _scrollViewProfile.findViewById(  R.id.linear_password );
		_emailText = (EditText) _linearForm.findViewById(  R.id.input_email );
		_nameText = (EditText) _linearForm.findViewById(  R.id.input_name );
		_lastNameText = (EditText) _linearForm.findViewById(  R.id.input_lastName );
		_phoneText = (EditText) _linearForm.findViewById(  R.id.input_phone );
		_mobileText = (EditText) _linearForm.findViewById(  R.id.input_mobile );
		_addressText = (EditText) _linearForm.findViewById(  R.id.input_address );
		_postalCodeText = (EditText) _linearForm.findViewById(  R.id.input_postalCode );
		_townText = (EditText) _linearForm.findViewById(  R.id.input_town );
		_provinceText = (EditText) _linearForm.findViewById(  R.id.input_province );
		_birthdateText = (EditText) _linearForm.findViewById(  R.id.input_birthDate );
		_passwordText = (EditText) _linearForm.findViewById(  R.id.input_password );
		_rePasswordText = (EditText) _linearForm.findViewById(  R.id.input_reEnterPassword );
		_showPasswordButton = (Button) _linearForm.findViewById(  R.id.btn_show_password );
		_updateButton = (Button) _linearForm.findViewById(  R.id.btn_update );

		// Get SharedPreferences
		settings = getSharedPreferences( "PREF_CLI", Context.MODE_PRIVATE );
		userToken = settings.getString( "token", "" );

		// Save the intent intlo a private variable
		actualIntent = getIntent();

		// Set context for api calls
		apiCalls.setContext( getApplicationContext() );

		// Set token for api calls
		apiCalls.setUserToken( userToken );

		// Get data client
		clientDto = (ClientDto) actualIntent.getSerializableExtra( "client" );

		// Set on click action for button
		_showPasswordButton.setOnClickListener( v -> {
			if ( _linearPassword.getVisibility() == View.GONE ) {
				_linearPassword.setVisibility( View.VISIBLE );
				_showPasswordButton.setText( R.string.btnHidePassword );
			} else {
				_linearPassword.setVisibility( View.GONE );
				_showPasswordButton.setText( R.string.btnShowPassword );
				_passwordText.setText( "" );
				_rePasswordText.setText( "" );
			}
		} );

		// Set on click action for button
		_updateButton.setOnClickListener( v -> {
			updateClient();
		} );

		// Get data client
		getInfoClient();

	}

	@Override
	public boolean onSupportNavigateUp(){
		Log.d( TAG, "Go to MainActivity" );
		Intent intent = new Intent( getApplicationContext(), MainActivity.class );
		intent.putExtra( "client", actualIntent.getSerializableExtra( "client" ) );
		startActivity( intent );
		overridePendingTransition( R.anim.push_left_in, R.anim.push_left_out );
		finish();
		return true;
	}

	/**
	 * Set client data in form
	 */
	private void getInfoClient( ) {
		try {
			_emailText.setText( clientDto.getEmail() );
			_nameText.setText( clientDto.getName() );
			_lastNameText.setText( clientDto.getLastName() );
			_phoneText.setText( clientDto.getPhone() );
			_mobileText.setText( clientDto.getMobile() );
			_addressText.setText( clientDto.getAddress() );
			_postalCodeText.setText( clientDto.getPostalCode() );
			_townText.setText( clientDto.getTown() );
			_provinceText.setText( clientDto.getProvince() );
			_birthdateText.setText( Long.toString( clientDto.getBirthDate() ) );
		}catch ( Exception e ){
			e.printStackTrace();
		}
	}

	private void updateClient( ) {
		Log.d( TAG, "Update client" );

		// Call to validate data form, if is false call to onLoginFailed.
		if ( !validateForm() ) {
			onFailedUpdate( getResources().getString( R.string.toast_client_login_failed ) );
			return;
		}

		// Disable login button while is calling to API
		_updateButton.setEnabled( false );
		_showPasswordButton.setEnabled( false );

		// Shows a progress dialog with a message
		final ProgressDialog progressDialog = new ProgressDialog( ProfileActivity.this,
				R.style.AppTheme_Light_Dialog );
		progressDialog.setIndeterminate( true );
		progressDialog.setMessage( getResources().getString( R.string.progressDialog_login ) );
		progressDialog.show();

		ClientesEntity clientesEntity = MappingHelper.getClientesEntityFromClientDto( clientDto );
		clientesEntity.setNombre( _nameText.getText().toString() );
		clientesEntity.setApellidos( _lastNameText.getText().toString() );
		clientesEntity.setTelefono( _phoneText.getText().toString() );
		clientesEntity.setMovil( _mobileText.getText().toString() );
		clientesEntity.setDireccion( _addressText.getText().toString() );
		clientesEntity.setCp( _postalCodeText.getText().toString() );
		clientesEntity.setPoblacion( _townText.getText().toString() );
		clientesEntity.setProvincia( _provinceText.getText().toString() );
		if ( !_passwordText.getText().toString().isEmpty() ) {
			clientesEntity.setPassword( _passwordText.getText().toString() );
		}

		apiCalls.setUpdateClient( clientesEntity, new ClientesEntityCallbackInterface() {
			@Override
			public void onSuccess( ClientesEntity clientesEntity ) {
				Log.d( TAG, "onSuccess setUpdateClient" );
				progressDialog.dismiss();
				onSuccessUpdate( clientesEntity );
			}

			@Override
			public void onError( String message ) {
				Log.d( TAG + " onFailure setUpdateClient", message );
				progressDialog.dismiss();
				onFailedUpdate( message );
			}
		} );
	}

	private void onSuccessUpdate( ClientesEntity clientesEntity) {
		_updateButton.setEnabled( true );
		_showPasswordButton.setEnabled( true );
		clientDto = MappingHelper.getClientDtoFromClientesEntity( clientesEntity );
		Toast.makeText( getBaseContext(), getResources().getString( R.string.toast_client_update_successful ), Toast.LENGTH_LONG ).show();
	}

	private void onFailedUpdate( String message ) {
		_updateButton.setEnabled( true );
		_showPasswordButton.setEnabled( true );
		Toast.makeText( getBaseContext(), message, Toast.LENGTH_LONG ).show();
	}

	/**
	 * Check if data form is OK.
	 *
	 * @return form status
	 */
	public boolean validateForm( ) {
		boolean isOk = true;

		String email = _emailText.getText().toString();
		String password = _passwordText.getText().toString();
		String reEnterPassword = _rePasswordText.getText().toString();


		if ( email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher( email ).matches() ) {
			_emailText.setError( getResources().getString( R.string.validation_email ) );
			isOk = false;
		} else {
			_emailText.setError( null );
		}

		if ( !password.isEmpty() && _linearPassword.getVisibility() == View.VISIBLE ) {
			if ( password.length() < 4 || password.length() > 10 ) {
				_passwordText.setError( getResources().getString( R.string.validation_password ) );
				isOk = false;
			} else {
				_passwordText.setError( null );
			}

			if ( reEnterPassword.isEmpty() || reEnterPassword.length() < 4 || reEnterPassword.length() > 10 || !( reEnterPassword.equals( password ) ) ) {
				_rePasswordText.setError( getResources().getString( R.string.validation_password_match ) );
				isOk = false;
			} else {
				_rePasswordText.setError( null );
			}
		}

		return isOk;
	}
}
