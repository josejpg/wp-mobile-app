package com.iessanvincente.weddingplanning.activity;

import android.app.ProgressDialog;
import android.content.Context;
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
import com.google.gson.reflect.TypeToken;
import com.iessanvincente.weddingplanning.R;
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

public class LoginActivity extends AppCompatActivity {
	private static final String TAG = "LoginActivity";
	private static final int REQUEST_SIGNUP = 0;
	@BindView( R.id.input_email )
	EditText _emailText;
	@BindView( R.id.input_password )
	EditText _passwordText;
	@BindView( R.id.btn_login )
	Button _loginButton;
	@BindView( R.id.link_signup )
	TextView _signupLink;
	private SharedPreferences settings;
	private SharedPreferences.Editor editor;
	private String userToken = "";

	@Override
	public void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );
		try {
			settings = getSharedPreferences( "PREF_CLI", Context.MODE_PRIVATE );
			userToken = settings.getString( "token", "" );
			Log.d( "userToken", userToken );
		} catch (Exception e) {

		}
		setContentView( R.layout.activity_login );
		ButterKnife.bind( this );

		if ( !userToken.isEmpty() ) {
			onTokenSaved();
		}
		_loginButton.setOnClickListener( v -> login() );

		_signupLink.setOnClickListener( v -> {
			// Start the Signup activity
			Intent intent = new Intent( getApplicationContext(), SignupActivity.class );
			startActivityForResult( intent, REQUEST_SIGNUP );
			finish();
			overridePendingTransition( R.anim.push_left_in, R.anim.push_left_out );
		} );
	}

	public void login( ) {
		Log.d( TAG, "Login" );

		if ( !validate() ) {
			onLoginFailed( "Falló el inicio de sesión" );
			return;
		}

		_loginButton.setEnabled( false );

		final ProgressDialog progressDialog = new ProgressDialog( LoginActivity.this,
				R.style.AppTheme_Dark_Dialog );
		progressDialog.setIndeterminate( true );
		progressDialog.setMessage( "Iniciando sesión..." );
		progressDialog.show();

		String email = _emailText.getText().toString();
		String password = _passwordText.getText().toString();

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
								progressDialog.dismiss();
								onLoginSuccess( responseClient );
							} else {
								Log.d( TAG, "Error on register" );
								progressDialog.dismiss();
								onLoginFailed( responseClient.getError() );
							}
						} else {
							Log.d( TAG, "Error with code " + response.code() );
							progressDialog.dismiss();
							onLoginFailed( responseClient.getError() );
						}
					} catch (IOException e) {
						Log.d( TAG, e.getMessage() );
						progressDialog.dismiss();
						onLoginFailed( "Falló el inicio de sesión" );
						e.printStackTrace();
					}
				} else {
					Log.d( TAG, "Null body" );
					progressDialog.dismiss();
					onLoginFailed( "Falló el inicio de sesión" );
				}
			}

			@Override
			public void onFailure( Call call, Throwable t ) {
				Log.d( TAG + " onFailure", t.getMessage() );
				progressDialog.dismiss();
				onLoginFailed( "Falló el inicio de sesión" );
			}
		};
		service.login(
				email,
				password,
				callback
		);
	}

	@Override
	public void onBackPressed( ) {
		// Disable going back to the MainActivity
		moveTaskToBack( true );
	}

	public void onTokenSaved( ) {
		Log.d( TAG, "Login by Token" );
		if ( !userToken.isEmpty() ) {
			_loginButton.setEnabled( false );

			final ProgressDialog progressDialog = new ProgressDialog( LoginActivity.this,
					R.style.AppTheme_Dark_Dialog );
			progressDialog.setIndeterminate( true );
			progressDialog.setMessage( "Iniciando sesión..." );
			progressDialog.show();

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
									progressDialog.dismiss();
									onLoginSuccess( responseClient );
								} else {
									progressDialog.dismiss();
									editor = settings.edit();
									editor.remove( "token" );
									editor.apply();
									_loginButton.setEnabled( true );
								}
							} else {
								progressDialog.dismiss();
								editor = settings.edit();
								editor.remove( "token" );
								editor.apply();
								_loginButton.setEnabled( true );
							}
						} catch (IOException e) {
							Log.d( TAG, e.getMessage() );
							progressDialog.dismiss();
							editor = settings.edit();
							editor.remove( "token" );
							editor.apply();
							_loginButton.setEnabled( true );
							e.printStackTrace();
						}
					} else {
						Log.d( TAG, "Null body" );
						progressDialog.dismiss();
						editor = settings.edit();
						editor.remove( "token" );
						editor.apply();
						_loginButton.setEnabled( true );
					}
				}

				@Override
				public void onFailure( Call call, Throwable t ) {
					Log.d( TAG + " onFailure", t.getMessage() );
					progressDialog.dismiss();
					editor = settings.edit();
					editor.remove( "token" );
					editor.apply();
					_loginButton.setEnabled( true );
				}
			};
			service.loginByToken(
					userToken,
					callback
			);
		}
	}

	public void onLoginSuccess( ResponseClient responseClient ) {
		_loginButton.setEnabled( true );
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
	}

	public void onLoginFailed( String message ) {
		Toast.makeText( getBaseContext(), message, Toast.LENGTH_LONG ).show();
		_loginButton.setEnabled( true );
	}

	public boolean validate( ) {
		boolean valid = true;

		String email = _emailText.getText().toString();
		String password = _passwordText.getText().toString();

		if ( email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher( email ).matches() ) {
			_emailText.setError( "Introducir un email válido" );
			valid = false;
		} else {
			_emailText.setError( null );
		}

		if ( password.isEmpty() || password.length() < 4 || password.length() > 10 ) {
			_passwordText.setError( "Entre 4 y 10 caracteres alfanuméricos" );
			valid = false;
		} else {
			_passwordText.setError( null );
		}

		return valid;
	}
}
