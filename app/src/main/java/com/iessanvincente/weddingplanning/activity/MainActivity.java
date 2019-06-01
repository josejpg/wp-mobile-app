package com.iessanvincente.weddingplanning.activity;

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
import com.google.gson.Gson;
import com.iessanvincente.weddingplanning.R;
import com.iessanvincente.weddingplanning.domain.ClientDto;
import com.iessanvincente.weddingplanning.domain.EventDto;
import com.iessanvincente.weddingplanning.entity.EventosEntity;
import com.iessanvincente.weddingplanning.helper.MappingHelper;
import com.iessanvincente.weddingplanning.interfaces.ClientsDtoCallbackInterface;
import com.iessanvincente.weddingplanning.response.ResponseClient;
import com.iessanvincente.weddingplanning.response.ResponseEvent;
import com.iessanvincente.weddingplanning.service.ClientService;
import com.iessanvincente.weddingplanning.service.EventService;
import com.iessanvincente.weddingplanning.utils.APICalls;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

	private void getClientInfo( ) {
		clientDto = (ClientDto) actualIntent.getSerializableExtra( "client" );

		apiCalls.getEvenetsByClient( clientDto, new ClientsDtoCallbackInterface() {
			@Override
			public void onSuccess( ClientDto _clientDto ) {
				Log.d( TAG, "onSuccess getEvenetsByClient" );
				clientDto = _clientDto;
			}

			@Override
			public void onError( String message ) {
				Log.d( TAG + " onFailure getEvenetsByClient", message );
			}
		} );
	}

	@Override
	protected void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );

		setContentView( R.layout.activity_main );
		Toolbar toolbar = findViewById( R.id.toolbar );
		setSupportActionBar( toolbar );
		DrawerLayout drawer = findViewById( R.id.drawer_layout );
		NavigationView navigationView = findViewById( R.id.nav_view );
		ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
				this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close );
		drawer.addDrawerListener( toggle );
		toggle.syncState();
		navigationView.setNavigationItemSelectedListener( this );

		// Set token for calls
		apiCalls.setUserToken( userToken );

		// Get SharedPreferences
		settings = getSharedPreferences( "PREF_CLI", Context.MODE_PRIVATE );
		userToken = settings.getString( "token", "" );

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
		} else if ( id == R.id.nav_message ) {

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
