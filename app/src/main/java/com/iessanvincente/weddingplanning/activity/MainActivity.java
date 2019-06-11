package com.iessanvincente.weddingplanning.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.iessanvincente.weddingplanning.R;
import com.iessanvincente.weddingplanning.domain.ClientDto;
import com.iessanvincente.weddingplanning.interfaces.ClientsDtoCallbackInterface;
import com.iessanvincente.weddingplanning.utils.APICalls;

import butterknife.ButterKnife;

/**
 * @author Jose J. Pardines
 */
public class MainActivity extends AppCompatActivity
		implements NavigationView.OnNavigationItemSelectedListener {
	private static final String TAG = "MainActivity";

	private Intent actualIntent;
	private SharedPreferences settings;
	private SharedPreferences.Editor editor;
	private APICalls apiCalls = new APICalls();
	private ClientDto clientDto;
	private String userToken;


	/**
	 * Get info from intent and call to API for a events
	 */
	private void getClientInfo( ) {
		// Shows a progress dialog with a message
		final ProgressDialog progressDialog = new ProgressDialog( MainActivity.this,
				R.style.AppTheme_Light_Dialog );
		progressDialog.setIndeterminate( true );
		progressDialog.setMessage( getResources().getString( R.string.progressDialog_loading_events ) );
		progressDialog.show();
		clientDto = (ClientDto) actualIntent.getSerializableExtra( "client" );
		apiCalls.getEventsByClient( clientDto, new ClientsDtoCallbackInterface() {
			@Override
			public void onSuccess( ClientDto _clientDto ) {
				Log.d( TAG, "onSuccess getEventsByClient" );
				clientDto.setEvents( _clientDto.getEvents() );
				progressDialog.dismiss();
			}

			@Override
			public void onError( String message ) {
				Log.d( TAG + " onFailure getEventsByClient", message );
				progressDialog.dismiss();
			}
		} );
	}

	@Override
	protected void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );

		setContentView( R.layout.activity_main );
		ButterKnife.bind( this );
		Toolbar toolbar = findViewById( R.id.toolbar );
		setSupportActionBar( toolbar );
		DrawerLayout drawer = findViewById( R.id.drawer_layout );
		NavigationView navigationView = findViewById( R.id.nav_view );
		ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
				this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close );
		drawer.addDrawerListener( toggle );
		toggle.syncState();
		navigationView.setNavigationItemSelectedListener( this );

		// Get SharedPreferences
		settings = getSharedPreferences( "PREF_CLI", Context.MODE_PRIVATE );
		userToken = settings.getString( "token", "" );

		// Set token for calls
		apiCalls.setUserToken( userToken );
		apiCalls.setContext( getApplicationContext() );

		// Save the intent intlo a private variable
		actualIntent = getIntent();

		getClientInfo();
		getSupportActionBar().setTitle( clientDto.displayName() );
	}

	@Override
	public void onBackPressed( ) {
		DrawerLayout drawer = findViewById( R.id.drawer_layout );
		if ( drawer.isDrawerOpen( GravityCompat.START ) ) {
			drawer.closeDrawer( GravityCompat.START );
		} else {
			super.onBackPressed();
		}
	}

	@SuppressWarnings( "StatementWithEmptyBody" )
	@Override
	public boolean onNavigationItemSelected( MenuItem item ) {
		// Handle navigation view item clicks here.
		int id = item.getItemId();

		if ( id == R.id.nav_profile ) { // Go to ProfileActivity
			Log.d( TAG, "Go to ProfileActivity" );
			Intent intent = new Intent( getApplicationContext(), ProfileActivity.class );
			intent.putExtra( "client", actualIntent.getSerializableExtra( "client" ) );
			startActivity( intent );
			overridePendingTransition( R.anim.push_left_in, R.anim.push_left_out );
			finish();
		} else if ( id == R.id.nav_event ) {
			Log.d( TAG, "Go to EventsActivity" );
			Intent intent = new Intent( getApplicationContext(), EventsActivity.class );
			intent.putExtra( "client", actualIntent.getSerializableExtra( "client" ) );
			startActivity( intent );
			overridePendingTransition( R.anim.push_left_in, R.anim.push_left_out );
			finish();
		} else if ( id == R.id.nav_services ) {
			Log.d( TAG, "Go to ServicesActivity" );
			Intent intent = new Intent( getApplicationContext(), ServicesActivity.class );
			intent.putExtra( "client", actualIntent.getSerializableExtra( "client" ) );
			startActivity( intent );
			overridePendingTransition( R.anim.push_left_in, R.anim.push_left_out );
			finish();
		} else if ( id == R.id.nav_message ) {
			Log.d( TAG, "Go to ChatsActivity" );
			Intent intent = new Intent( getApplicationContext(), ChatsActivity.class );
			intent.putExtra( "client", actualIntent.getSerializableExtra( "client" ) );
			startActivity( intent );
			overridePendingTransition( R.anim.push_left_in, R.anim.push_left_out );
			finish();
		} else if ( id == R.id.nav_logout ) { // Remove token an data user and go to LoginActivity
			Log.d( TAG, "Logout and go to LoginActivity" );
			editor = settings.edit();
			editor.remove( "token" );
			editor.remove( "client" );
			editor.apply();
			Intent intent = new Intent( getApplicationContext(), LoginActivity.class );
			startActivity( intent );
			finish();
			overridePendingTransition( R.anim.push_left_in, R.anim.push_left_out );
		}

		DrawerLayout drawer = findViewById( R.id.drawer_layout );
		drawer.closeDrawer( GravityCompat.START );
		return true;
	}

}
