package com.iessanvincente.weddingplanning.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.iessanvincente.weddingplanning.R;
import com.iessanvincente.weddingplanning.domain.ClientDto;
import com.iessanvincente.weddingplanning.domain.EventDto;
import com.iessanvincente.weddingplanning.domain.ProviderDto;
import com.iessanvincente.weddingplanning.domain.ServiceDto;
import com.iessanvincente.weddingplanning.entity.EventosEntity;
import com.iessanvincente.weddingplanning.helper.MappingHelper;
import com.iessanvincente.weddingplanning.interfaces.ResponseEventCallbackInterface;
import com.iessanvincente.weddingplanning.response.ResponseEvent;
import com.iessanvincente.weddingplanning.utils.APICalls;

import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Jose J. Pardines
 */
public class ProviderInfoActivity extends AppCompatActivity {
	private static final String TAG = "ProviderInfoActivity";
	@BindView( R.id.input_cif )
	EditText _cifText;
	@BindView( R.id.input_name )
	EditText _nameText;
	@BindView( R.id.input_email )
	EditText _emailText;
	@BindView( R.id.input_phone )
	EditText _phoneText;
	@BindView( R.id.input_mobile )
	EditText _mobileText;
	@BindView( R.id.input_address )
	EditText _addressText;
	@BindView( R.id.input_postalCode )
	EditText _postalCodeText;
	@BindView( R.id.input_town )
	EditText _townText;
	@BindView( R.id.input_province )
	EditText _provinceText;
	@BindView( R.id.btn_link_event )
	Button _link2EventButton;
	private ProgressDialog progressDialog;
	private Intent actualIntent;
	private SharedPreferences settings;
	private APICalls apiCalls = new APICalls();
	private ClientDto clientDto = null;
	private ProviderDto providerDto = null;
	private ServiceDto serviceDto = null;
	private String userToken;

	@Override
	protected void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_provider_info );

		ButterKnife.bind( this );
		progressDialog = new ProgressDialog( ProviderInfoActivity.this, R.style.AppTheme_Light_Dialog );
		// Get SharedPreferences
		settings = getSharedPreferences( "PREF_CLI", Context.MODE_PRIVATE );
		userToken = settings.getString( "token", "" );

		// Set token for calls
		apiCalls.setUserToken( userToken );

		// Save the intent intlo a private variable
		actualIntent = getIntent();

		_link2EventButton.setOnClickListener( v -> showDialogChooseEvent() );

		getClientInfo();
		getProviderInfo();
		getServiceSelected();
		setProviderInfoIntoForm();

		getSupportActionBar().setTitle( providerDto.displayName() );
	}

	/**
	 * Back to ServicesActivity
	 *
	 * @return
	 */
	@Override
	public boolean onSupportNavigateUp( ) {
		Intent intent;
		switch ( actualIntent.getStringExtra( "back" ) ){
			case "services":
				Log.d( TAG, "Go to ServicesActivity" );
				intent = new Intent( getApplicationContext(), ServicesActivity.class );
				intent.putExtra( "client", actualIntent.getSerializableExtra( "client" ) );
				intent.putExtra( "serviceSelected", actualIntent.getSerializableExtra( "serviceSelected" ) );
				startActivity( intent );
				overridePendingTransition( R.anim.push_left_in, R.anim.push_left_out );
				finish();
				break;
			case "event":
				Log.d( TAG, "Go to EventInfoActivity" );
				intent = new Intent( getApplicationContext(), EventInfoActivity.class );
				intent.putExtra( "client", actualIntent.getSerializableExtra( "client" ) );
				intent.putExtra( "event", actualIntent.getSerializableExtra( "event" ) );
				startActivity( intent );
				overridePendingTransition( R.anim.push_left_in, R.anim.push_left_out );
				finish();
				break;
		}
		return true;
	}

	/**
	 * Get client data and set into clientDto
	 */
	private void getClientInfo( ) {
		clientDto = (ClientDto) actualIntent.getSerializableExtra( "client" );
	}

	/**
	 * Get provider data and set into providerDto
	 */
	private void getProviderInfo( ) {
		providerDto = (ProviderDto) actualIntent.getSerializableExtra( "provider" );
	}

	/**
	 * Get provider data and set into providerDto
	 */
	private void getServiceSelected( ) {
		serviceDto = (ServiceDto) actualIntent.getSerializableExtra( "serviceSelected" );
	}

	/**
	 * Set provider info into form
	 */
	private void setProviderInfoIntoForm( ) {
		_cifText.setText( providerDto.getCif() );
		_nameText.setText( providerDto.getName() );
		_emailText.setText( providerDto.getEmail() );
		_phoneText.setText( providerDto.getPhone() );
		_mobileText.setText( providerDto.getMobile() );
		_addressText.setText( providerDto.getAddress() );
		_postalCodeText.setText( providerDto.getPostalCode() );
		_townText.setText( providerDto.getTown() );
		_provinceText.setText( providerDto.getProvince() );
	}

	/**
	 * Show a dialog for select a event
	 */
	private void showDialogChooseEvent( ) {
		_link2EventButton.setEnabled( false );
		AlertDialog.Builder builder = new AlertDialog.Builder( this );
		builder.setTitle( getResources().getString( R.string.dialog_choose_event ) );

		CharSequence[] eventsCharSequences = clientDto.getEvents().stream().filter( e -> !e.isPastEvent() ).map( e -> (CharSequence) e.getEvent() ).toArray( CharSequence[]::new );

		builder.setSingleChoiceItems( eventsCharSequences, -1, ( dialog, item ) -> {
			showConfirmDialog( (EventDto) clientDto.getEvents().toArray()[ item ], dialog );
		} );

		builder.setOnDismissListener( dialog -> {
			_link2EventButton.setEnabled( true );
			dialog.dismiss();
		} );
		AlertDialog alert = builder.create();
		alert.show();
	}

	/**
	 * Show confirm dialog
	 */
	private void showConfirmDialog( EventDto eventDto, DialogInterface alertDialog ) {
		AlertDialog.Builder builder = new AlertDialog.Builder( this );
		builder.setMessage( getResources().getString( R.string.dialog_confirm_choose_event ) );
		builder.setNegativeButton( R.string.dialog_confirm_no, ( dialog, which ) -> {
			dialog.dismiss();
		} );
		builder.setPositiveButton( R.string.dialog_confirm_yes, ( dialog, which ) -> {
			System.out.println( eventDto );
			Set<ProviderDto> providerDtoSet = eventDto.getProviders();
			providerDtoSet.add( providerDto );
			eventDto.setProviders( providerDtoSet );
			System.out.println( eventDto );
			EventosEntity eventosEntity = MappingHelper.getEventosEntityFromEventDto( eventDto );
			apiCalls.setUpdateEvent( eventosEntity, new ResponseEventCallbackInterface() {
				@Override
				public void onSuccess( ResponseEvent responseEvent ) {
					onSuccessUpdate();
					alertDialog.dismiss();
					dialog.dismiss();
				}

				@Override
				public void onError( String message ) {
					onFailedUpdate( message );
				}
			} );
		} );
		AlertDialog alert = builder.create();
		alert.show();
	}

	/**
	 * Handled a success update
	 */
	private void onSuccessUpdate( ) {
		_link2EventButton.setEnabled( true );
		Toast.makeText( getBaseContext(), getResources().getString( R.string.toast_event_update_successful ), Toast.LENGTH_LONG ).show();
		progressDialog.dismiss();
	}

	/**
	 * Handled a failed update
	 *
	 * @param message with error
	 */
	private void onFailedUpdate( String message ) {
		_link2EventButton.setEnabled( true );
		Toast.makeText( getBaseContext(), message, Toast.LENGTH_LONG ).show();
		progressDialog.dismiss();
	}
}
