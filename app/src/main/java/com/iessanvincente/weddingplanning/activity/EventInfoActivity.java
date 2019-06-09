package com.iessanvincente.weddingplanning.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.ArraySet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.checkbox.MaterialCheckBox;
import com.iessanvincente.weddingplanning.R;
import com.iessanvincente.weddingplanning.domain.ClientDto;
import com.iessanvincente.weddingplanning.domain.EventDto;
import com.iessanvincente.weddingplanning.domain.ProviderDto;
import com.iessanvincente.weddingplanning.entity.ClientesEntity;
import com.iessanvincente.weddingplanning.entity.EventosEntity;
import com.iessanvincente.weddingplanning.helper.MappingHelper;
import com.iessanvincente.weddingplanning.interfaces.ClientsDtoCallbackInterface;
import com.iessanvincente.weddingplanning.interfaces.ResponseEventCallbackInterface;
import com.iessanvincente.weddingplanning.response.ResponseEvent;
import com.iessanvincente.weddingplanning.utils.APICalls;
import com.iessanvincente.weddingplanning.utils.ClientsRecyclerView;
import com.iessanvincente.weddingplanning.utils.ProviderRecyclerView;
import com.iessanvincente.weddingplanning.utils.Utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EventInfoActivity extends AppCompatActivity {

	private static final String TAG = "EventInfoActivity";
	private final Calendar calendar = Calendar.getInstance();
	@BindView( R.id.titleClientsEvent )
	TextView recyclerViewClientsEventTitle;
	@BindView( R.id.listClientsEvent )
	RecyclerView recyclerViewClientsEvent;
	@BindView( R.id.titleProvidersEvent )
	TextView recyclerViewProvidersEventTitle;
	@BindView( R.id.listProvidersEvent )
	RecyclerView recyclerViewProvidersEvent;
	@BindView( R.id.input_event )
	EditText _eventText;
	@BindView( R.id.input_description )
	EditText _descriptionText;
	@BindView( R.id.input_date )
	EditText _dateText;
	@BindView( R.id.input_active )
	MaterialCheckBox _activeCheck;
	@BindView( R.id.btn_update_event )
	Button _updateButton;
	private ProgressDialog progressDialog;
	private Intent actualIntent;
	private SharedPreferences settings;
	private APICalls apiCalls = new APICalls();
	private ClientDto clientDto = null;
	private EventDto eventDto = null;
	private String userToken;
	private Boolean isUpdate = false;

	@Override
	protected void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_event_info );
		ButterKnife.bind( this );
		progressDialog = new ProgressDialog( EventInfoActivity.this, R.style.AppTheme_Light_Dialog );
		// Get SharedPreferences
		settings = getSharedPreferences( "PREF_CLI", Context.MODE_PRIVATE );
		userToken = settings.getString( "token", "" );

		// Set token for calls
		apiCalls.setUserToken( userToken );

		// Save the intent intlo a private variable
		actualIntent = getIntent();

		_updateButton.setOnClickListener( v -> {
			if ( isUpdate ) {
				updateEvent();
			} else {
				createEvent();
			}
		} );

		getClientInfo();
		getEventInfo();

		// Set a title
		getSupportActionBar().setTitle( ( eventDto != null ) ? eventDto.getEvent() : getResources().getString( R.string.activity_event_info_new ) );
	}

	/**
	 * Back to EventsActivity
	 *
	 * @return
	 */
	@Override
	public boolean onSupportNavigateUp( ) {
		Log.d( TAG, "Go to EventsActivity" );
		Intent intent = new Intent( getApplicationContext(), EventsActivity.class );
		intent.putExtra( "client", actualIntent.getSerializableExtra( "client" ) );
		startActivity( intent );
		overridePendingTransition( R.anim.push_left_in, R.anim.push_left_out );
		finish();
		return true;
	}

	/**
	 * Set new data to event
	 */
	private void updateEvent( ) {
		Log.d( TAG, "Update event" );

		// Call to validate data form, if is false call to onLoginFailed.
		if ( !validateForm() ) {
			onFailedUpdate( getResources().getString( R.string.toast_event_update_failed ) );
			return;
		}

		// Disable login button while is calling to API
		_updateButton.setEnabled( false );

		// Shows a progress dialog with a message
		progressDialog.setIndeterminate( true );
		progressDialog.setMessage( getResources().getString( R.string.progressDialog_updating_event ) );
		progressDialog.show();

		EventosEntity eventosEntity = MappingHelper.getEventosEntityFromEventDto( eventDto );
		eventosEntity.setNombre( _eventText.getText().toString() );
		eventosEntity.setDescripcion( _descriptionText.getText().toString() );
		eventosEntity.setFecha( Utils.getTimeFromDateTime( _dateText.getText().toString() ) );
		eventosEntity.setActivo( _activeCheck.isChecked() ? 1 : 0 );

		apiCalls.setUpdateEvent( eventosEntity, new ResponseEventCallbackInterface() {
			@Override
			public void onSuccess( ResponseEvent responseEvent ) {
				onSuccessUpdate( responseEvent.getEvent() );
			}

			@Override
			public void onError( String message ) {
				onFailedUpdate( message );
			}
		} );
	}


	/**
	 * Set new event
	 */
	private void createEvent( ) {
		Log.d( TAG, "Create event" );

		// Call to validate data form, if is false call to onLoginFailed.
		if ( !validateForm() ) {
			onFailedUpdate( getResources().getString( R.string.toast_event_create_failed ) );
			return;
		}

		// Disable login button while is calling to API
		_updateButton.setEnabled( false );

		Set<ClientesEntity> clientesEntitySet = new ArraySet<>();
		clientesEntitySet.add( MappingHelper.getClientesEntityFromClientDto( clientDto ) );

		// Shows a progress dialog with a message
		progressDialog.setIndeterminate( true );
		progressDialog.setMessage( getResources().getString( R.string.progressDialog_creating_event ) );
		progressDialog.show();

		EventosEntity eventosEntity = new EventosEntity();
		eventosEntity.setNombre( _eventText.getText().toString() );
		eventosEntity.setDescripcion( _descriptionText.getText().toString() );
		eventosEntity.setFecha( Utils.getTimeFromDateTime( _dateText.getText().toString() ) );
		eventosEntity.setActivo( _activeCheck.isChecked() ? 1 : 0 );
		eventosEntity.setClientes( clientesEntitySet );

		apiCalls.setNewEvent( eventosEntity, new ResponseEventCallbackInterface() {
			@Override
			public void onSuccess( ResponseEvent responseEvent ) {
				onSuccessCreate();
			}

			@Override
			public void onError( String message ) {
				onFailedCreate( message );
			}
		} );
	}

	/**
	 * Get client data and set into clientDto
	 */
	private void getClientInfo( ) {
		clientDto = (ClientDto) actualIntent.getSerializableExtra( "client" );
	}


	/**
	 * Get event data and set into eventDto and form
	 */
	private void getEventInfo( ) {
		if ( actualIntent.getSerializableExtra( "event" ) != null ) {
			eventDto = (EventDto) actualIntent.getSerializableExtra( "event" );
			_eventText.setText( eventDto.getEvent() );
			_descriptionText.setText( eventDto.getDescription() );
			_dateText.setText( Utils.getDateTimeAsString( eventDto.getDate() ) );
			_activeCheck.setChecked( eventDto.getActive() );
			if ( eventDto.getClients().size() > 0 ) {
				setConfigRecyclerViewClientsEvents( recyclerViewClientsEvent, eventDto.getClients() );
			}
			if( eventDto.getProviders().size() > 0 ) {
				setConfigRecyclerViewProvidersEvents( recyclerViewProvidersEvent, eventDto.getProviders() );
			}
			isUpdate = true;
		}

		handledInfoEvent();
	}

	/**
	 * Handled a success update
	 *
	 * @param eventosEntity event data
	 */
	private void onSuccessUpdate( EventosEntity eventosEntity ) {
		_updateButton.setEnabled( true );
		eventDto = MappingHelper.getEventDtoFromEventosEntity( eventosEntity );
		for ( EventDto eventDtoClientDto : clientDto.getEvents() ) {
			if ( eventDtoClientDto.getId().equals( eventDto.getId() ) ) {
				clientDto.getEvents().remove( eventDtoClientDto );
				clientDto.getEvents().add( eventDto );
				break;
			}
		}
		Toast.makeText( getBaseContext(), getResources().getString( R.string.toast_event_update_successful ), Toast.LENGTH_LONG ).show();
		progressDialog.dismiss();
	}

	/**
	 * Handled a failed update
	 *
	 * @param message with error
	 */
	private void onFailedUpdate( String message ) {
		_updateButton.setEnabled( true );
		Toast.makeText( getBaseContext(), message, Toast.LENGTH_LONG ).show();
		progressDialog.dismiss();
	}

	/**
	 * Handled a success create
	 */
	private void onSuccessCreate( ) {
		Toast.makeText( getBaseContext(), getResources().getString( R.string.toast_event_create_successful ), Toast.LENGTH_LONG ).show();
		apiCalls.getEventsByClient( clientDto, new ClientsDtoCallbackInterface() {
			@Override
			public void onSuccess( ClientDto _clientDto ) {
				Log.d( TAG, "onSuccess getEventsByClient" );
				clientDto = _clientDto;
				progressDialog.dismiss();
				Log.d( TAG, "Go to EventsActivity" );
				Intent intent = new Intent( getApplicationContext(), EventsActivity.class );
				intent.putExtra( "client", actualIntent.getSerializableExtra( "client" ) );
				startActivity( intent );
				overridePendingTransition( R.anim.push_left_in, R.anim.push_left_out );
				finish();
			}

			@Override
			public void onError( String message ) {
				Log.d( TAG + " onFailure getEventsByClient", message );
				progressDialog.dismiss();
			}
		} );
	}

	/**
	 * Handled a failed create
	 *
	 * @param message with error
	 */
	private void onFailedCreate( String message ) {
		_updateButton.setEnabled( true );
		Toast.makeText( getBaseContext(), message, Toast.LENGTH_LONG ).show();
	}

	/**
	 * Check if data form is OK.
	 *
	 * @return form status
	 */
	public boolean validateForm( ) {
		boolean isOk = true;

		String event = _eventText.getText().toString();
		String description = _descriptionText.getText().toString();
		String date = _dateText.getText().toString();
		String patterRegex = "^[0-3][0-9]/[0-1][0-9]/[0-9][0-9][0-9][0-9] [0-2][0-9]:[0-6][0-9]$";

		if ( event.isEmpty() ) {
			_eventText.setError( getResources().getString( R.string.validation_event ) );
			isOk = false;
		} else {
			_eventText.setError( null );
		}

		if ( description.isEmpty() ) {
			_descriptionText.setError( getResources().getString( R.string.validation_description ) );
			isOk = false;
		} else {
			_descriptionText.setError( null );
		}

		if ( date.isEmpty() || !date.matches( patterRegex ) ) {
			_dateText.setError( getResources().getString( R.string.validation_date ) );
			isOk = false;
		} else {
			_dateText.setError( null );
		}

		return isOk;
	}

	/**
	 * Set up the RecyclerView configuration
	 *
	 * @param recyclerView to config
	 * @param clientDtoSet to push into recyclerView
	 */
	private void setConfigRecyclerViewClientsEvents( RecyclerView recyclerView, Set<ClientDto> clientDtoSet ) {
		LinearLayoutManager layoutManager = new LinearLayoutManager( EventInfoActivity.this, RecyclerView.VERTICAL, false );
		layoutManager.setOrientation( RecyclerView.VERTICAL );
		recyclerView.setLayoutManager( layoutManager );
		ClientsRecyclerView adapter = new ClientsRecyclerView( getApplicationContext(), clientDtoSet, clientDto, eventDto.getDate() );
		adapter.setClickListener( ( view, position ) -> {
			Log.d( TAG, "Remove client" );
			eventDto.getClients().remove( adapter.getItem( position ) );
			setConfigRecyclerViewClientsEvents( recyclerView, eventDto.getClients() );
		} );
		recyclerView.setAdapter( adapter );
	}

	/**
	 * Set up the RecyclerView configuration
	 *
	 * @param recyclerView   to config
	 * @param providerDtoSet to push into recyclerView
	 */
	private void setConfigRecyclerViewProvidersEvents( RecyclerView recyclerView, Set<ProviderDto> providerDtoSet ) {
		LinearLayoutManager layoutManager
				= new LinearLayoutManager( EventInfoActivity.this, RecyclerView.VERTICAL, false );
		layoutManager.setOrientation( RecyclerView.VERTICAL );
		recyclerView.setLayoutManager( layoutManager );
		ProviderRecyclerView adapter = new ProviderRecyclerView( EventInfoActivity.this, providerDtoSet, eventDto.getDate() );
		adapter.setClickListener( ( view, position ) -> {
			Log.d( TAG, "Remove provider" );
			eventDto.getProviders().remove( adapter.getItem( position ) );
			setConfigRecyclerViewProvidersEvents( recyclerView, eventDto.getProviders() );
		} );
		recyclerView.setAdapter( adapter );
	}

	private void handledInfoEvent(){
		// If is a past event cant be updated
		if ( eventDto != null &&
				eventDto.getDate() != null &&
				( eventDto.getDate() * 1000 ) <= calendar.getTimeInMillis() ) {
			_updateButton.setVisibility( View.GONE );
			_activeCheck.setClickable( false );
			_eventText.setFocusable( false );
			_eventText.setClickable( false );
			_descriptionText.setFocusable( false );
			_descriptionText.setClickable( false );
			_dateText.setFocusable( false );
			_dateText.setClickable( false );
		} else {
			Calendar calendar = Calendar.getInstance();
			_dateText.setOnClickListener( v -> Utils.showDatePickerDialog( _dateText, getSupportFragmentManager(), ( eventDto != null ) ? eventDto.getDate() : calendar.getTimeInMillis() / 1000, true ) );
		}

		// Set a list of clients
		if ( eventDto != null &&
				eventDto.getClients().size() > 0 ) {
			setConfigRecyclerViewClientsEvents( recyclerViewClientsEvent, eventDto.getClients() );
			recyclerViewClientsEventTitle.setVisibility( View.VISIBLE );
			recyclerViewClientsEvent.setVisibility( View.VISIBLE );
		}

		// Set a list of providers
		if ( eventDto != null &&
				eventDto.getProviders().size() > 0 ) {
			setConfigRecyclerViewProvidersEvents( recyclerViewProvidersEvent, eventDto.getProviders() );
			recyclerViewProvidersEventTitle.setVisibility( View.VISIBLE );
			recyclerViewProvidersEvent.setVisibility( View.VISIBLE );
		}
	}
}
