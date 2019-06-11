package com.iessanvincente.weddingplanning.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.iessanvincente.weddingplanning.R;
import com.iessanvincente.weddingplanning.domain.ClientDto;
import com.iessanvincente.weddingplanning.domain.EventDto;
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
public class EventsActivity extends AppCompatActivity {
	private static final String TAG = "EventsActivity";
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
	private Intent actualIntent;
	private SharedPreferences settings;
	private SharedPreferences.Editor editor;
	private APICalls apiCalls = new APICalls();
	private ClientDto clientDto;
	private String userToken;

	@Override
	protected void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_events );
		ButterKnife.bind( this );

		// Get SharedPreferences
		settings = getSharedPreferences( "PREF_CLI", Context.MODE_PRIVATE );
		userToken = settings.getString( "token", "" );

		// Set token for calls
		apiCalls.setUserToken( userToken );

		// Save the intent intlo a private variable
		actualIntent = getIntent();

		getClientInfo();
		getSupportActionBar().setTitle( getResources().getString( R.string.activity_events, clientDto.displayName() ) );

	}

	/**
	 * Set a menu on activity
	 *
	 * @param menu
	 * @return
	 */
	@Override
	public boolean onCreateOptionsMenu( Menu menu ) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate( R.menu.menu_events, menu );
		return true;
	}

	/**
	 * Manage item touched on menu
	 *
	 * @param item item touched
	 * @return
	 */
	@Override
	public boolean onOptionsItemSelected( MenuItem item ) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if ( id == R.id.new_event ) {
			Intent intent = new Intent( getApplicationContext(), EventInfoActivity.class );
			intent.putExtra( "client", clientDto );
			startActivity( intent );
			overridePendingTransition( R.anim.push_left_in, R.anim.push_left_out );
			finish();
			return true;
		}

		return super.onOptionsItemSelected( item );
	}

	/**
	 * Back to MainActivity
	 *
	 * @return
	 */
	@Override
	public boolean onSupportNavigateUp( ) {
		Log.d( TAG, "Go to MainActivity" );
		Intent intent = new Intent( getApplicationContext(), MainActivity.class );
		intent.putExtra( "client", actualIntent.getSerializableExtra( "client" ) );
		startActivity( intent );
		overridePendingTransition( R.anim.push_left_in, R.anim.push_left_out );
		finish();
		return true;
	}

	/**
	 * Set events on recyclerviews
	 */
	private void setEvents( ) {
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

		if ( nextEvents.size() > 0 ) {
			setConfigRecyclerViewEvents( recyclerViewNextEvents, nextEvents );
		} else {
			recyclerViewNextEvents.setVisibility( View.GONE );
			titleNextEvents.setVisibility( View.GONE );
		}

		if ( prevEvents.size() > 0 ) {
			setConfigRecyclerViewEvents( recyclerViewPrevEvents, prevEvents );
		} else {
			recyclerViewPrevEvents.setVisibility( View.GONE );
			titlePrevEvents.setVisibility( View.GONE );
		}

		if ( nextEvents.size() == 0 && prevEvents.size() == 0 ) {
			titleNoEvents.setVisibility( View.VISIBLE );
		}
	}

	/**
	 * Get info client from intent
	 */
	private void getClientInfo( ) {
		clientDto = (ClientDto) actualIntent.getSerializableExtra( "client" );
		handledRecyclerViews();
	}

	/**
	 * Handled RecyclerViews
	 */
	private void handledRecyclerViews( ) {
		if ( clientDto.getEvents().size() == 0 ) {
			recyclerViewNextEvents.setVisibility( View.GONE );
			titleNextEvents.setVisibility( View.GONE );
			recyclerViewPrevEvents.setVisibility( View.GONE );
			titlePrevEvents.setVisibility( View.GONE );
			titleNoEvents.setVisibility( View.VISIBLE );
		} else {
			setEvents();
		}
	}

	/**
	 * Set up the RecyclerView configuration
	 *
	 * @param recyclerView to config
	 * @param eventDtoSet  to push into recyclerView
	 */
	private void setConfigRecyclerViewEvents( RecyclerView recyclerView, Set<EventDto> eventDtoSet ) {
		LinearLayoutManager layoutManager
				= new LinearLayoutManager( EventsActivity.this, RecyclerView.VERTICAL, false );
		layoutManager.setOrientation( RecyclerView.VERTICAL );
		recyclerView.setLayoutManager( layoutManager );
		recyclerView.addItemDecoration( new DividerItemDecoration( getApplicationContext(), DividerItemDecoration.VERTICAL ) );
		EventsRecyclerView adapter = new EventsRecyclerView( getApplicationContext(), eventDtoSet );
		adapter.setClickListener( ( view, position ) -> {
			Log.d( TAG, "Go to EventsInfoActivity" );
			Intent intent = new Intent( getApplicationContext(), EventInfoActivity.class );
			intent.putExtra( "client", clientDto );
			intent.putExtra( "event", adapter.getItem( position ) );
			startActivity( intent );
			overridePendingTransition( R.anim.push_left_in, R.anim.push_left_out );
			finish();
		} );
		recyclerView.setAdapter( adapter );
	}
}
