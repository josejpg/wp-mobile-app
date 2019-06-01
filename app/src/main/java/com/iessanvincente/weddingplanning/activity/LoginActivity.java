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
import com.iessanvincente.weddingplanning.R;
import com.iessanvincente.weddingplanning.response.ResponseClient;
import com.iessanvincente.weddingplanning.service.ClientService;

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

	/**
	 * Get token from SharedPreferences and if ins't empty call to API with it to get client data
	 * and autoloogin.
	 * Set listener to buttons.
	 *
	 * @param savedInstanceState
	 */
	@Override
	public void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );

		setContentView( R.layout.activity_login );
		ButterKnife.bind( this );

		try {
			settings = getSharedPreferences( "PREF_CLI", Context.MODE_PRIVATE );
			userToken = settings.getString( "token", "" );
		} catch (Exception e) {
		}
		if ( !userToken.isEmpty() ) {
			onTokenSaved();
		}

		// login button on click call to login method
		_loginButton.setOnClickListener( v -> login() );

		// Signup button on click go to SignupActivity
		_signupLink.setOnClickListener( v -> {
			// Start the Signup activity
			Intent intent = new Intent( getApplicationContext(), SignupActivity.class );
			startActivityForResult( intent, REQUEST_SIGNUP );
			finish();
			overridePendingTransition( R.anim.push_left_in, R.anim.push_left_out );
		} );
	}

	/**
	 * Login client with data form
	 */
	public void login( ) {
		Log.d( TAG, "Login" );

		// Call to validate data form, if is false call to onLoginFailed.
		if ( !validateForm() ) {
			onLoginFailed( getResources().getString( R.string.toast_client_login_failed ) );
			return;
		}

		// Disable login button while is calling to API
		_loginButton.setEnabled( false );

		// Shows a progress dialog with a message
		final ProgressDialog progressDialog = new ProgressDialog( LoginActivity.this,
				R.style.AppTheme_Dark_Dialog );
		progressDialog.setIndeterminate( true );
		progressDialog.setMessage( getResources().getString( R.string.progressDialog_login ) );
		progressDialog.show();

		String email = _emailText.getText().toString();
		String password = _passwordText.getText().toString();

		ClientService service = new ClientService();
		Callback<ResponseBody> callback = new Callback<ResponseBody>() {
			/**
			 * If API response OK this method check data.
			 * @param call
			 * @param response
			 */
			@Override
			public void onResponse( Call<ResponseBody> call, Response<ResponseBody> response ) {

				Gson gson = new Gson();
				ResponseClient responseClient = null;

				// If isn't body in response call to onLoginFailed
				// else get body and check it
				if ( response.body() != null ) {
					try {
						// Parse boty in ResponseClient model
						responseClient = gson.fromJson( response.body().string(), ResponseClient.class );

						// If the response isn't successful call to onLoginFailed
						if ( response.isSuccessful() ) {
							// If response getOk is true call to onLoginSuccess
							// else call to onLoginFailed
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
						onLoginFailed( getResources().getString( R.string.toast_client_login_failed ) );
						e.printStackTrace();
					}
				} else {
					Log.d( TAG, "Null body" );
					progressDialog.dismiss();
					onLoginFailed( getResources().getString( R.string.toast_client_login_failed ) );
				}
			}

			/**
			 *  IF API call failed this method call to onLoginFailed and stop the progress dialog.
			 * @param call
			 * @param t
			 */
			@Override
			public void onFailure( Call call, Throwable t ) {
				Log.d( TAG + " onFailure", t.getMessage() );
				progressDialog.dismiss();
				onLoginFailed( getResources().getString( R.string.toast_client_login_failed ) );
			}
		};
		// Call to method in service
		service.login(
				email,
				password,
				callback
		);
	}

	/**
	 * Block back to MainActivity
	 */
	@Override
	public void onBackPressed( ) {
		// Disable going back to the MainActivity
		moveTaskToBack( true );
	}

	/**
	 * Login client with token
	 */
	public void onTokenSaved( ) {
		Log.d( TAG, "Login by Token" );
		if ( !userToken.isEmpty() ) {
			_loginButton.setEnabled( false );

			final ProgressDialog progressDialog = new ProgressDialog( LoginActivity.this,
					R.style.AppTheme_Dark_Dialog );
			progressDialog.setIndeterminate( true );
			progressDialog.setMessage( getResources().getString( R.string.progressDialog_login ) );
			progressDialog.show();

			ClientService service = new ClientService();
			Callback<ResponseBody> callback = new Callback<ResponseBody>() {
				@Override
				public void onResponse( Call<ResponseBody> call, Response<ResponseBody> response ) {

					Gson gson = new Gson();
					ResponseClient responseClient = null;
					if ( response.body() != null ) {
						try {
							responseClient = gson.fromJson( response.body().string(), ResponseClient.class );
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

	/**
	 * Set the token and user data into SharedPreferences
	 * Start the MainActivity
	 *
	 * @param responseClient with all of data client
	 */
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
		overridePendingTransition( R.anim.push_left_in, R.anim.push_left_out );
	}

	/**
	 * Show a message with error text
	 *
	 * @param message this string will be show on app
	 */
	public void onLoginFailed( String message ) {
		Toast.makeText( getBaseContext(), message, Toast.LENGTH_LONG ).show();
		_loginButton.setEnabled( true );
	}

	/**
	 * Check if data form is OK.
	 *
	 * @return form status
	 */
	public boolean validateForm( ) {
		boolean isOK = true;

		String email = _emailText.getText().toString();
		String password = _passwordText.getText().toString();

		if ( email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher( email ).matches() ) {
			_emailText.setError( getResources().getString( R.string.validation_email ) );
			isOK = false;
		} else {
			_emailText.setError( null );
		}

		if ( password.isEmpty() || password.length() < 4 || password.length() > 10 ) {
			_passwordText.setError( getResources().getString( R.string.validation_password ) );
			isOK = false;
		} else {
			_passwordText.setError( null );
		}

		return isOK;
	}
}
