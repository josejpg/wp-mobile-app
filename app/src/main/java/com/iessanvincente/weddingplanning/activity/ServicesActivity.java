package com.iessanvincente.weddingplanning.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.iessanvincente.weddingplanning.R;
import com.iessanvincente.weddingplanning.domain.ClientDto;
import com.iessanvincente.weddingplanning.domain.EventDto;
import com.iessanvincente.weddingplanning.domain.ProviderDto;
import com.iessanvincente.weddingplanning.domain.ServiceDto;
import com.iessanvincente.weddingplanning.entity.ProveedoresEntity;
import com.iessanvincente.weddingplanning.entity.ServiciosEntity;
import com.iessanvincente.weddingplanning.helper.MappingHelper;
import com.iessanvincente.weddingplanning.interfaces.ResponseProviderCallbackInterface;
import com.iessanvincente.weddingplanning.interfaces.ResponseServiceCallbackInterface;
import com.iessanvincente.weddingplanning.response.ResponseProvider;
import com.iessanvincente.weddingplanning.response.ResponseService;
import com.iessanvincente.weddingplanning.utils.APICalls;
import com.iessanvincente.weddingplanning.utils.EventsRecyclerView;
import com.iessanvincente.weddingplanning.utils.ProviderServiceRecyclerView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ServicesActivity extends AppCompatActivity {
	private static final String TAG = "ServicesActivity";
	@BindView( R.id.listServices )
	Spinner _listServices;
	@BindView( R.id.btn_find_providers )
	MaterialButton _btnSearch;
	@BindView( R.id.linear_providers_service )
	LinearLayout _linearProviderService;
	@BindView( R.id.listProviderService )
	RecyclerView _recyclerViewProviderService;
	private Intent actualIntent;
	private SharedPreferences settings;
	private APICalls apiCalls = new APICalls();
	private ClientDto clientDto = new ClientDto();
	private Set<ProviderDto> providerDtoSet = new HashSet<>(  );
	private Set<ServiceDto> serviceDtoSet = new HashSet<>(  );
	private ServiceDto serviceSelected = new ServiceDto();
	private String userToken = "";
	private ProgressDialog progressDialog;

	@Override
	protected void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_services );
		ButterKnife.bind( this );
		progressDialog = new ProgressDialog( ServicesActivity.this, R.style.AppTheme_Light_Dialog );

		// Get SharedPreferences
		settings = getSharedPreferences( "PREF_CLI", Context.MODE_PRIVATE );
		userToken = settings.getString( "token", "" );

		// Set token for calls
		apiCalls.setUserToken( userToken );
		apiCalls.setContext( getApplicationContext() );

		// Save the intent intlo a private variable
		actualIntent = getIntent();

		_btnSearch.setEnabled( false );

		getClientInfo();
		getServices();
		setListeners();
		getSupportActionBar().setTitle( getResources().getString( R.string.activity_services ) );
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
	 * Get info client from intent
	 */
	private void getClientInfo( ) {
		clientDto = (ClientDto) actualIntent.getSerializableExtra( "client" );
	}

	/**
	 * Get all of services
	 */
	private void getServices( ) {
		// Shows a progress dialog with a message
		progressDialog.setIndeterminate( true );
		progressDialog.setMessage( getResources().getString( R.string.progressDialog_loading_services ) );
		progressDialog.show();
		serviceDtoSet = new HashSet<>();
		setServicesOnSpinner();
		apiCalls.getServices( new ResponseServiceCallbackInterface() {
			@Override
			public void onSuccess( ResponseService responseService ) {
				onSuccessLoading( responseService.getServices() );
			}

			@Override
			public void onError( String message ) {
				onFailedLoading( message );
			}
		} );
	}

	/**
	 * Handled a success loading
	 *
	 * @param serviciosEntitySet list of services
	 */
	private void onSuccessLoading( Set<ServiciosEntity> serviciosEntitySet ) {
		for ( ServiciosEntity serviciosEntity : serviciosEntitySet ) {
			serviceDtoSet.add( MappingHelper.geServiceDtoFromServiciosEntity( serviciosEntity ) );
		}
		setServicesOnSpinner();
		progressDialog.dismiss();
	}

	/**
	 * Handled a failed loading
	 *
	 * @param message with error
	 */
	private void onFailedLoading( String message ) {
		Toast.makeText( getBaseContext(), message, Toast.LENGTH_LONG ).show();
		progressDialog.dismiss();
	}

	/**
	 * Get all of services
	 */
	private void getProvidersByService( String serviceID ) {
		if ( serviceID != null && !serviceID.isEmpty() ) {
			// Shows a progress dialog with a message
			progressDialog.setIndeterminate( true );
			progressDialog.setMessage( getResources().getString( R.string.progressDialog_loading_providers ) );
			progressDialog.show();
			apiCalls.getProviderByServiceId( serviceID, new ResponseProviderCallbackInterface() {
				@Override
				public void onSuccess( ResponseProvider responseProvider ) {
					onSuccessGetProviders( responseProvider.getProviders() );
				}

				@Override
				public void onError( String message ) {
					onFailedGetProviders( message );
				}
			} );
		}
	}

	/**
	 * Handled a success when get providers by service
	 *
	 * @param proveedoresEntitySet list of providers
	 */
	private void onSuccessGetProviders( Set<ProveedoresEntity> proveedoresEntitySet ) {
		for ( ProveedoresEntity proveedoresEntity : proveedoresEntitySet ) {
			providerDtoSet.add( MappingHelper.getProviderDtoFromProveedoresEntity( proveedoresEntity ) );
		}
		setConfigRecyclerViewProviders( _recyclerViewProviderService, providerDtoSet );
		progressDialog.dismiss();
	}

	/**
	 * Handled a failed when get providers by service
	 *
	 * @param message with error
	 */
	private void onFailedGetProviders( String message ) {
		Toast.makeText( getBaseContext(), message, Toast.LENGTH_LONG ).show();
		progressDialog.dismiss();
	}

	/**
	 * Set service list on spiner
	 */
	public void setServicesOnSpinner( ) {
		ArrayList<ServiceDto> serviceDtoArrayList = new ArrayList<>( serviceDtoSet );
		serviceDtoArrayList.add( 0, new ServiceDto() );
		ArrayAdapter<ServiceDto> adapter = new ArrayAdapter<>( this, android.R.layout.simple_spinner_dropdown_item, serviceDtoArrayList );
		adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
		_listServices.setAdapter( adapter );
	}

	/**
	 * Set listeners on activity
	 */
	public void setListeners( ) {
		_listServices.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected( AdapterView<?> parent, View view, int position, long id ) {
				List<ServiceDto> serviceDtoList = new ArrayList<>( serviceDtoSet );
				if( position > 0 ) {
					serviceSelected = serviceDtoList.get( position );
				}
				providerDtoSet.clear();
				if( serviceSelected.getId() != null && serviceSelected.getId() > 0 ){
					_btnSearch.setEnabled( true );
				}else{
					_btnSearch.setEnabled( false );
				}
				_linearProviderService.setVisibility( View.GONE );
			}

			@Override
			public void onNothingSelected( AdapterView<?> parent ) {
				_btnSearch.setEnabled( false );
				_linearProviderService.setVisibility( View.GONE );
				providerDtoSet.clear();
			}
		} );

		_btnSearch.setOnClickListener( v -> {
			getProvidersByService( serviceSelected.getId().toString() );
		} );
	}

	/**
	 * Set up the RecyclerView configuration
	 *
	 * @param recyclerView to config
	 * @param providerDtoSet  to push into recyclerView
	 */
	private void setConfigRecyclerViewProviders( RecyclerView recyclerView, Set<ProviderDto> providerDtoSet ) {
		LinearLayoutManager layoutManager
				= new LinearLayoutManager( ServicesActivity.this, RecyclerView.VERTICAL, false );
		layoutManager.setOrientation( RecyclerView.VERTICAL );
		recyclerView.setLayoutManager( layoutManager );
		recyclerView.addItemDecoration( new DividerItemDecoration( getApplicationContext(), DividerItemDecoration.VERTICAL ) );
		ProviderServiceRecyclerView adapter = new ProviderServiceRecyclerView( getApplicationContext(), providerDtoSet );
		adapter.setClickListener( ( view, position ) -> {
			Log.d( TAG, adapter.getItem( position ).toString() );
		} );
		recyclerView.setAdapter( adapter );
		_linearProviderService.setVisibility( View.VISIBLE );
	}
}
