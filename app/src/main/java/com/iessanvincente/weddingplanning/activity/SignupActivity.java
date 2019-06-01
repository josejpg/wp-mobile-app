package com.iessanvincente.weddingplanning.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.iessanvincente.weddingplanning.R;
import com.iessanvincente.weddingplanning.entity.ClientesEntity;
import com.iessanvincente.weddingplanning.interfaces.ResponseClientCallbackInterface;
import com.iessanvincente.weddingplanning.response.ResponseClient;
import com.iessanvincente.weddingplanning.service.ClientService;
import com.iessanvincente.weddingplanning.utils.APICalls;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author Jose J. Pardines
 */
public class SignupActivity extends AppCompatActivity {
	private static final String TAG = "SignupActivity";

	@BindView( R.id.input_name )
	EditText _nameText;
	@BindView( R.id.input_lastName )
	EditText _lastNameText;
	@BindView( R.id.input_email )
	EditText _emailText;
	@BindView( R.id.input_mobile )
	EditText _mobileText;
	@BindView( R.id.input_password )
	EditText _passwordText;
	@BindView( R.id.input_reEnterPassword )
	EditText _reEnterPasswordText;
	@BindView( R.id.btn_signup )
	Button _signupButton;
	@BindView( R.id.link_login )
	TextView _loginLink;

	private SharedPreferences settings;
	private SharedPreferences.Editor editor;
	private APICalls apiCalls = new APICalls();

	/**
	 *
	 * Set listener to buttons.
	 *
	 * @param savedInstanceState
	 */
	@Override
	public void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_signup );
		ButterKnife.bind( this );

		apiCalls.setContext( getApplicationContext() );

		// Signup button on click call to
		_signupButton.setOnClickListener( v -> signup() );

		// Login link on click go to LoginActivity
		_loginLink.setOnClickListener( v -> {
			// Finish the registration screen and return to the Login activity
			Intent intent = new Intent( getApplicationContext(), LoginActivity.class );
			startActivity( intent );
			finish();
			overridePendingTransition( R.anim.push_left_in, R.anim.push_left_out );
		} );
	}

	/**
	 * Register a new client with data form
	 */
	public void signup( ) {
		Log.d( TAG, "Signup" );

		// Call to validate data form, if is false call to onSignupFailed.
		if ( !validateForm() ) {
			onSignupFailed( getResources().getString( R.string.toast_client_signup_failed ) );
			return;
		}

		// Disable signup button while is calling to API
		_signupButton.setEnabled( false );

		// Shows a progress dialog with a message
		final ProgressDialog progressDialog = new ProgressDialog( SignupActivity.this,
				R.style.AppTheme_Light_Dialog );
		progressDialog.setIndeterminate( true );
		progressDialog.setMessage( getResources().getString( R.string.progressDialog_signup ) );
		progressDialog.show();

		ClientesEntity clientesEntity = new ClientesEntity();
		clientesEntity.setNombre( _nameText.getText().toString() );
		clientesEntity.setApellidos( _lastNameText.getText().toString() );
		clientesEntity.setEmail( _emailText.getText().toString() );
		clientesEntity.setMovil( _mobileText.getText().toString() );
		clientesEntity.setPassword( _passwordText.getText().toString() );

		apiCalls.setNewClient(
				clientesEntity,
				new ResponseClientCallbackInterface() {
					@Override
					public void onSuccess( ResponseClient responseClient ) {
						Log.d( TAG, "onSuccess" );
						progressDialog.dismiss();
						onSignupSuccess( responseClient );
					}

					@Override
					public void onError( String message ) {
						Log.d( TAG + " onError", message );
						progressDialog.dismiss();
						onSignupFailed( message );
					}
				}
		);
	}

	/**
	 * Set the token and user data into SharedPreferences
	 * Start the MainActivity
	 *
	 * @param responseClient with all of data client
	 */
	public void onSignupSuccess( ResponseClient responseClient ) {
		_signupButton.setEnabled( true );
		// Set next activity with Client
		// Save token in SharedPreferences
		Intent intent = new Intent( getApplicationContext(), MainActivity.class );
		intent.putExtra( "client", responseClient.getClient() );
		settings = getSharedPreferences( "PREF_CLI", 0 );
		editor = settings.edit();
		editor.putString( "token", responseClient.getToken() );
		editor.apply();
		startActivityForResult( intent, RESULT_OK );
		finish();
		overridePendingTransition( R.anim.push_left_in, R.anim.push_left_out );
	}

	/**
	 * Show a message with error text
	 *
	 * @param message this string will be show on app
	 */
	public void onSignupFailed( String message ) {
		Toast.makeText( getBaseContext(), message, Toast.LENGTH_LONG ).show();

		_signupButton.setEnabled( true );
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
		String reEnterPassword = _reEnterPasswordText.getText().toString();


		if ( email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher( email ).matches() ) {
			_emailText.setError( getResources().getString( R.string.validation_email ) );
			isOk = false;
		} else {
			_emailText.setError( null );
		}

		if ( password.isEmpty() || password.length() < 4 || password.length() > 10 ) {
			_passwordText.setError( getResources().getString( R.string.validation_password ) );
			isOk = false;
		} else {
			_passwordText.setError( null );
		}

		if ( reEnterPassword.isEmpty() || reEnterPassword.length() < 4 || reEnterPassword.length() > 10 || !( reEnterPassword.equals( password ) ) ) {
			_reEnterPasswordText.setError( getResources().getString( R.string.validation_password_match ) );
			isOk = false;
		} else {
			_reEnterPasswordText.setError( null );
		}

		return isOk;
	}
}