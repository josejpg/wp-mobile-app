package com.iessanvincente.weddingplanning.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.iessanvincente.weddingplanning.R;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity
		implements NavigationView.OnNavigationItemSelectedListener {

	private SharedPreferences settings;
	private SharedPreferences.Editor editor;

	@Override
	protected void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );
		settings = getSharedPreferences( "PREF_CLI", Context.MODE_PRIVATE );
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

		if ( id == R.id.nav_profile ) {
			// Handle the camera action
		} else if ( id == R.id.nav_event ) {

		} else if ( id == R.id.nav_message ) {

		} else if ( id == R.id.nav_logout ) {
			editor = settings.edit();
			editor.remove( "token" );
			editor.apply();
			Intent intent = new Intent( getApplicationContext(), MainActivity.class );
			startActivity(intent);
		}

		DrawerLayout drawer = findViewById( R.id.drawer_layout );
		drawer.closeDrawer( GravityCompat.START );
		return true;
	}
}
