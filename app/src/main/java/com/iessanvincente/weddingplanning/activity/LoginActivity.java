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

import com.iessanvincente.weddingplanning.R;
import com.iessanvincente.weddingplanning.helper.MappingHelper;
import com.iessanvincente.weddingplanning.interfaces.ResponseClientCallbackInterface;
import com.iessanvincente.weddingplanning.response.ResponseClient;
import com.iessanvincente.weddingplanning.utils.APICalls;

import butterknife.BindView;
import butterknife.ButterKnife;

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
	private APICalls apiCalls = new APICalls();

	/**
	 * Get token from SharedPreferences and if ins't empty call to API with it to get client data
	 * and autoloogin.
	 * Set listener to buttons.
	 *
	 * @param savedInstanceState manage state of the instance
	 */
	@Override
	public void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );

		setContentView( R.layout.activity_login );
		ButterKnife.bind( this );

		// Set context for api calls
		apiCalls.setContext( getApplicationContext() );

		settings = getSharedPreferences( "PREF_CLI", Context.MODE_PRIVATE );
		userToken = settings.getString( "token", "" );

		if ( userToken != null && !userToken.isEmpty() ) {
			// Set token for api calls
			apiCalls.setUserToken( userToken );
			apiCalls.setContext( getApplicationContext() );
			onTokenSaved();
		}

		// Login button on click call to login method
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
				R.style.AppTheme_Light_Dialog );
		progressDialog.setIndeterminate( true );
		progressDialog.setMessage( getResources().getString( R.string.progressDialog_login ) );
		progressDialog.show();

		String email = _emailText.getText().toString();
		String password = _passwordText.getText().toString();

		apiCalls.getLoginClient(
				email,
				password, new ResponseClientCallbackInterface() {
					@Override
					public void onSuccess( ResponseClient responseClient ) {
						Log.d( TAG, "onSuccess" );
						progressDialog.dismiss();
						onLoginSuccess( responseClient );
					}

					@Override
					public void onError( String message ) {
						Log.d( TAG + " onError", message );
						progressDialog.dismiss();
						onLoginFailed( message );
					}
				}
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
					R.style.AppTheme_Light_Dialog );
			progressDialog.setIndeterminate( true );
			progressDialog.setMessage( getResources().getString( R.string.progressDialog_login ) );
			progressDialog.show();

			apiCalls.getLoginClientByToken(
					new ResponseClientCallbackInterface() {
						@Override
						public void onSuccess( ResponseClient responseClient ) {
							Log.d( TAG, "onSuccess by Token" );
							progressDialog.dismiss();
							onLoginSuccess( responseClient );
						}

						@Override
						public void onError( String message ) {
							Log.d( TAG + " onError by Token", message );
							progressDialog.dismiss();
							editor = settings.edit();
							editor.remove( "token" );
							editor.apply();
							_loginButton.setEnabled( true );
						}
					}
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
		intent.putExtra( "client", MappingHelper.getClientDtoFromClientesEntity( responseClient.getClient() ) );
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

		if ( password.isEmpty() || password.length() < 6 ) {
			_passwordText.setError( getResources().getString( R.string.validation_password ) );
			isOK = false;
		} else {
			_passwordText.setError( null );
		}

		return isOK;
	}
}
