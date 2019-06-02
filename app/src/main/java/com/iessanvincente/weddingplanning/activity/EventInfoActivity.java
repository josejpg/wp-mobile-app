package com.iessanvincente.weddingplanning.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.checkbox.MaterialCheckBox;
import com.iessanvincente.weddingplanning.R;
import com.iessanvincente.weddingplanning.domain.ClientDto;
import com.iessanvincente.weddingplanning.domain.EventDto;
import com.iessanvincente.weddingplanning.domain.ProviderDto;
import com.iessanvincente.weddingplanning.entity.EventosEntity;
import com.iessanvincente.weddingplanning.helper.MappingHelper;
import com.iessanvincente.weddingplanning.interfaces.ResponseEventCallbackInterface;
import com.iessanvincente.weddingplanning.response.ResponseEvent;
import com.iessanvincente.weddingplanning.utils.APICalls;
import com.iessanvincente.weddingplanning.utils.ClientsRecyclerView;
import com.iessanvincente.weddingplanning.utils.ProviderRecyclerView;
import com.iessanvincente.weddingplanning.utils.Utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EventInfoActivity extends AppCompatActivity {

	private static final String TAG = "EventInfoActivity";

	@BindView( R.id.listClientsEvent )
	RecyclerView recyclerViewClientsEvent;
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
	private Intent actualIntent;
	private SharedPreferences settings;
	private APICalls apiCalls = new APICalls();
	private ClientDto clientDto = null;
	private EventDto eventDto = null;
	private String userToken;

	@Override
	protected void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_event_info );
		ButterKnife.bind( this );

		// Get SharedPreferences
		settings = getSharedPreferences( "PREF_CLI", Context.MODE_PRIVATE );
		userToken = settings.getString( "token", "" );

		// Set token for calls
		apiCalls.setUserToken( userToken );

		// Save the intent intlo a private variable
		actualIntent = getIntent();

		_updateButton.setOnClickListener( v -> updateEvent() );

		getClientInfo();
		getEventInfo();
		if ( eventDto.getClients().size() > 0 ) {
			setConfigRecyclerViewClientsEvents( recyclerViewClientsEvent, eventDto.getClients() );
		}
		if ( eventDto.getProviders().size() > 0 ) {
			setConfigRecyclerViewProvidersEvents( recyclerViewProvidersEvent, eventDto.getProviders() );
		}
		getSupportActionBar().setTitle( eventDto.getEvent() );
	}

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
	 * Set new data to event
	 */
	private void updateEvent( ) {
		Log.d( TAG, "Update client" );

		// Call to validate data form, if is false call to onLoginFailed.
		if ( !validateForm() ) {
			onFailedUpdate( getResources().getString( R.string.toast_client_login_failed ) );
			return;
		}

		// Disable login button while is calling to API
		_updateButton.setEnabled( false );

		// Shows a progress dialog with a message
		final ProgressDialog progressDialog = new ProgressDialog( EventInfoActivity.this,
				R.style.AppTheme_Light_Dialog );
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
				progressDialog.dismiss();
				onSuccessUpdate( responseEvent.getEvent() );
			}

			@Override
			public void onError( String message ) {
				progressDialog.dismiss();
				onFailedUpdate( message );
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
		eventDto = (EventDto) actualIntent.getSerializableExtra( "event" );
		_eventText.setText( eventDto.getEvent() );
		_descriptionText.setText( eventDto.getDescription() );
		_dateText.setText( Utils.getDateTimeAsString( eventDto.getDate() ) );
		_activeCheck.setChecked( eventDto.getActive() );
	}

	private void onSuccessUpdate( EventosEntity eventosEntity ) {
		_updateButton.setEnabled( true );
		eventDto = MappingHelper.getEventDtoFromEventosEntity( eventosEntity );
		Toast.makeText( getBaseContext(), getResources().getString( R.string.toast_event_update_successful ), Toast.LENGTH_LONG ).show();
	}

	private void onFailedUpdate( String message ) {
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
		List<ClientDto> clientDtoList = new ArrayList<>( clientDtoSet );
		LinearLayoutManager layoutManager = new LinearLayoutManager( EventInfoActivity.this, RecyclerView.VERTICAL, false );
		layoutManager.setOrientation( RecyclerView.VERTICAL );
		recyclerView.setLayoutManager( layoutManager );
		recyclerView.addItemDecoration( new DividerItemDecoration( getApplicationContext(), DividerItemDecoration.VERTICAL ) );
		ClientsRecyclerView adapter = new ClientsRecyclerView( getApplicationContext(), clientDtoSet );
		adapter.setClickListener( ( view, position ) -> {
			Log.d( TAG, "Remove client" );
			clientDtoList.remove( position );
			eventDto.setClients( new HashSet<>( clientDtoList ) );
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
		List<ProviderDto> providerDtoList = new ArrayList<>( providerDtoSet );
		LinearLayoutManager layoutManager
				= new LinearLayoutManager( EventInfoActivity.this, RecyclerView.VERTICAL, false );
		layoutManager.setOrientation( RecyclerView.VERTICAL );
		recyclerView.setLayoutManager( layoutManager );
		recyclerView.addItemDecoration( new DividerItemDecoration( EventInfoActivity.this, DividerItemDecoration.VERTICAL ) );
		ProviderRecyclerView adapter = new ProviderRecyclerView( EventInfoActivity.this, providerDtoSet );
		adapter.setClickListener( ( view, position ) -> {
			Log.d( TAG, "Remove provider" );
			providerDtoList.remove( position );
			eventDto.setProviders( new HashSet<>( providerDtoList ) );
			setConfigRecyclerViewProvidersEvents( recyclerView, eventDto.getProviders() );
		} );
		recyclerView.setAdapter( adapter );
	}
}