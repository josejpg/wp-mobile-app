package com.iessanvincente.weddingplanning.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.iessanvincente.weddingplanning.R;
import com.iessanvincente.weddingplanning.entity.ClientesEntity;
import com.iessanvincente.weddingplanning.response.ResponseClient;
import com.iessanvincente.weddingplanning.service.ClientService;

import java.io.IOException;
import java.lang.reflect.Type;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

	@Override
	public void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_signup );
		ButterKnife.bind( this );

		_signupButton.setOnClickListener( v -> signup() );

		_loginLink.setOnClickListener( v -> {
			// Finish the registration screen and return to the Login activity
			Intent intent = new Intent( getApplicationContext(), LoginActivity.class );
			startActivity( intent );
			finish();
			overridePendingTransition( R.anim.push_left_in, R.anim.push_left_out );
		} );
	}

	public void signup( ) {
		Log.d( TAG, "Signup" );

		if ( !validate() ) {
			onSignupFailed( "Falló al registrar cliente" );
			return;
		}

		_signupButton.setEnabled( false );

		final ProgressDialog progressDialog = new ProgressDialog( SignupActivity.this,
				R.style.AppTheme_Dark_Dialog );
		progressDialog.setIndeterminate( true );
		progressDialog.setMessage( "Creando cuenta..." );
		progressDialog.show();
		ClientesEntity clientesEntity = new ClientesEntity();
		clientesEntity.setNombre( _nameText.getText().toString() );
		clientesEntity.setApellidos( _lastNameText.getText().toString() );
		clientesEntity.setEmail( _emailText.getText().toString() );
		clientesEntity.setMovil( _mobileText.getText().toString() );
		clientesEntity.setPassword( _passwordText.getText().toString() );

		ClientService service = new ClientService();
		Callback<ResponseBody> callback = new Callback<ResponseBody>() {
			@Override
			public void onResponse( Call<ResponseBody> call, Response<ResponseBody> response ) {

				Gson gson = new Gson();
				Type clientType = new TypeToken<ResponseClient>() {
				}.getType();
				ResponseClient responseClient = null;
				if ( response.body() != null ) {
					try {
						responseClient = gson.fromJson( response.body().string(), clientType );
						if ( response.isSuccessful() ) {
							if ( responseClient.getOk() ) {
								Log.d( TAG, "Login OK" );
								progressDialog.dismiss();
								onSignupSuccess( responseClient );
							} else {
								Log.d( TAG, "Error on register" );
								progressDialog.dismiss();
								onSignupFailed( responseClient.getError() );
							}
						} else {
							Log.d( TAG, "Error with code " + response.code() );
							progressDialog.dismiss();
							onSignupFailed( responseClient.getError() );
						}
					} catch (IOException e) {
						Log.d( TAG, e.getMessage() );
						progressDialog.dismiss();
						onSignupFailed( "Falló al registrar cliente" );
						e.printStackTrace();
					}
				} else {
					Log.d( TAG, "Null body" );
					Log.d( TAG, response.toString() );
					progressDialog.dismiss();
					onSignupFailed( "Falló al registrar cliente" );
				}
			}

			@Override
			public void onFailure( Call call, Throwable t ) {
				Log.d( TAG + " onFailure", t.getMessage() );
				progressDialog.dismiss();
				onSignupFailed( "Falló al registrar cliente" );
			}
		};
		service.registerClient(
				clientesEntity,
				callback
		);
	}


	public void onSignupSuccess( ResponseClient responseClient ) {
		_signupButton.setEnabled( true );
		Intent intent = new Intent( getApplicationContext(), MainActivity.class );
		intent.putExtra( "client", responseClient.getClient() );
		intent.putExtra( "token", responseClient.getToken() );
		startActivityForResult( intent, RESULT_OK );
		finish();
	}

	public void onSignupFailed( String message ) {
		Toast.makeText( getBaseContext(), message, Toast.LENGTH_LONG ).show();

		_signupButton.setEnabled( true );
	}

	public boolean validate( ) {
		boolean valid = true;

		String email = _emailText.getText().toString();
		String mobile = _mobileText.getText().toString();
		String password = _passwordText.getText().toString();
		String reEnterPassword = _reEnterPasswordText.getText().toString();


		if ( email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher( email ).matches() ) {
			_emailText.setError( "Introducir una dirección válida de email" );
			valid = false;
		} else {
			_emailText.setError( null );
		}

		if ( mobile.isEmpty() || mobile.length() != 9 ) {
			_mobileText.setError( "Introducir un número válido" );
			valid = false;
		} else {
			_mobileText.setError( null );
		}

		if ( password.isEmpty() || password.length() < 4 || password.length() > 10 ) {
			_passwordText.setError( "Entre 4 y 10 caracteres alfanuméricos" );
			valid = false;
		} else {
			_passwordText.setError( null );
		}

		if ( reEnterPassword.isEmpty() || reEnterPassword.length() < 4 || reEnterPassword.length() > 10 || !( reEnterPassword.equals( password ) ) ) {
			_reEnterPasswordText.setError( "Las contraseñas no coinciden" );
			valid = false;
		} else {
			_reEnterPasswordText.setError( null );
		}

		return valid;
	}
}