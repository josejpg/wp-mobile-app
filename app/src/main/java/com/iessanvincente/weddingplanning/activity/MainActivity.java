package com.iessanvincente.weddingplanning.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;
import com.iessanvincente.weddingplanning.R;
import com.iessanvincente.weddingplanning.domain.ClientDto;
import com.iessanvincente.weddingplanning.domain.EventDto;
import com.iessanvincente.weddingplanning.interfaces.ClientsDtoCallbackInterface;
import com.iessanvincente.weddingplanning.utils.APICalls;
import com.iessanvincente.weddingplanning.utils.EventsRecyclerView;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashSet;
import java.util.Set;

import butterknife.BindView;
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

	@BindView( R.id.listNextEvents )
	RecyclerView recyclerViewNextEvents;
	@BindView( R.id.listPrevEvents )
	RecyclerView recyclerViewPrevEvents;
	@BindView( R.id.titleNextEvents )
	TextView titleNextEvents;
	@BindView( R.id.titlePrevEvents )
	TextView titlePrevEvents;
	@BindView( R.id.titleNoEvents )
	TextView titleNoEvents;

	private void getClientInfo( ) {
		clientDto = (ClientDto) actualIntent.getSerializableExtra( "client" );

		apiCalls.getEvenetsByClient( clientDto, new ClientsDtoCallbackInterface() {
			@Override
			public void onSuccess( ClientDto _clientDto ) {
				Log.d( TAG, "onSuccess getEvenetsByClient" );
				clientDto = _clientDto;
				Set<EventDto> nextEvents = new HashSet<>();
				Set<EventDto> prevEvents = new HashSet<>();
				for ( EventDto eventDto : clientDto.getEvents() ) {
					if ( LocalDateTime.ofEpochSecond( eventDto.getDate(), 0, ZoneOffset.UTC ).isAfter( LocalDateTime.now() ) ) {
						nextEvents.add( eventDto );
					} else if ( LocalDateTime.ofEpochSecond( eventDto.getDate(), 0, ZoneOffset.UTC ).isBefore( LocalDateTime.now() ) ) {
						prevEvents.add( eventDto );
					} else {
						prevEvents.add( eventDto );
					}
				}

				if( nextEvents.size() > 0 ) {
					setConfigRecyclerViewEvents( recyclerViewNextEvents, nextEvents );
				}else{
					recyclerViewNextEvents.setVisibility( View.GONE );
					titleNextEvents.setVisibility( View.GONE );
				}

				if( prevEvents.size() > 0 ) {
					setConfigRecyclerViewEvents( recyclerViewPrevEvents, prevEvents );
				}else{
					recyclerViewPrevEvents.setVisibility( View.GONE );
					titlePrevEvents.setVisibility( View.GONE );
				}

				if( nextEvents.size() == 0 && prevEvents.size() == 0 ){
					titleNoEvents.setVisibility( View.VISIBLE );
				}
			}

			@Override
			public void onError( String message ) {
				Log.d( TAG + " onFailure getEvenetsByClient", message );
				recyclerViewNextEvents.setVisibility( View.GONE );
				titleNextEvents.setVisibility( View.GONE );
				recyclerViewPrevEvents.setVisibility( View.GONE );
				titlePrevEvents.setVisibility( View.GONE );
				titleNoEvents.setVisibility( View.VISIBLE );
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

	/**
	 * Set up the RecyclerView configuration
	 * @param recyclerView to config
	 * @param eventDtoSet to push into recyclerView
	 */
	private void setConfigRecyclerViewEvents( RecyclerView recyclerView, Set<EventDto> eventDtoSet ){
		LinearLayoutManager layoutManager
				= new LinearLayoutManager( MainActivity.this, RecyclerView.VERTICAL, false );
		layoutManager.setOrientation( RecyclerView.VERTICAL );
		recyclerView.setLayoutManager( layoutManager );
		recyclerView.addItemDecoration( new DividerItemDecoration( getApplicationContext(), DividerItemDecoration.VERTICAL ) );
		EventsRecyclerView adapter = new EventsRecyclerView( getApplicationContext(), eventDtoSet );
		recyclerView.setAdapter( adapter );
	}
}
