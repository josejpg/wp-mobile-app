package com.iessanvincente.weddingplanning.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.iessanvincente.weddingplanning.R;
import com.iessanvincente.weddingplanning.domain.ChatDto;
import com.iessanvincente.weddingplanning.domain.ClientDto;
import com.iessanvincente.weddingplanning.domain.MessageDto;
import com.iessanvincente.weddingplanning.helper.MappingHelper;
import com.iessanvincente.weddingplanning.interfaces.ResponseMessageCallbackInterface;
import com.iessanvincente.weddingplanning.response.ResponseMessage;
import com.iessanvincente.weddingplanning.utils.APICalls;
import com.iessanvincente.weddingplanning.utils.MessageRecyclerView;

import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MessageActivity extends AppCompatActivity {
	static final int INTERVAL = 1000; // milliseconds
	private static final String TAG = "MessagesActivity";
	@BindView( R.id.messageRecyclerView )
	RecyclerView recyclerVieMessages;
	@BindView( R.id.enter_message )
	EditText _message;
	@BindView( R.id.btn_send_message )
	Button _btnSendMessage;

	private Intent actualIntent;
	private SharedPreferences settings;
	private APICalls apiCalls = new APICalls();
	private ClientDto clientDto = null;
	private ChatDto chatDto = null;
	private Set<MessageDto> messageDtoSet = new HashSet<>();
	private String userToken;
	private Handler handler = new Handler();

	Runnable runnableRefreshMessage = new Runnable() {
		@Override
		public void run( ) {
			getMessages();
			handler.postDelayed( this, INTERVAL );
		}
	};

	@Override
	protected void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_messages );
		ButterKnife.bind( this );

		// Get SharedPreferences
		settings = getSharedPreferences( "PREF_CLI", Context.MODE_PRIVATE );
		userToken = settings.getString( "token", "" );

		// Set token for calls
		apiCalls.setUserToken( userToken );
		apiCalls.setContext( getApplicationContext() );

		// Save the intent intlo a private variable
		actualIntent = getIntent();

		_btnSendMessage.setOnClickListener( v -> sendMessage() );

		getClientInfo();
		getChatInfo();
		getMessages();

		getSupportActionBar().setTitle( ( chatDto != null && chatDto.getEvent() != null ) ? chatDto.getEvent().getEvent() : getResources().getString( R.string.activity_event_info_new ) );

		handler.postDelayed( runnableRefreshMessage, INTERVAL );

	}

	/**
	 * Back to ChatsActivity
	 *
	 * @return
	 */
	@Override
	public boolean onSupportNavigateUp( ) {
		Log.d( TAG, "Go to ChatsActivity" );
		handler.removeCallbacks( runnableRefreshMessage );
		Intent intent = new Intent( getApplicationContext(), ChatsActivity.class );
		intent.putExtra( "client", actualIntent.getSerializableExtra( "client" ) );
		startActivity( intent );
		overridePendingTransition( R.anim.push_left_in, R.anim.push_left_out );
		finish();
		return true;
	}

	/**
	 * Get client data and set into clientDto
	 */
	private void getClientInfo( ) {
		clientDto = (ClientDto) actualIntent.getSerializableExtra( "client" );
	}

	/**
	 * Get chat data and set into chatDto
	 */
	private void getChatInfo( ) {
		chatDto = (ChatDto) actualIntent.getSerializableExtra( "chat" );
	}

	private void getMessages( ) {
		apiCalls.getMessages( chatDto, new ResponseMessageCallbackInterface() {
			@Override
			public void onSuccess( ResponseMessage responseMessage ) {
				Log.d( TAG, "onSuccess getChats" );
				Set<MessageDto> messageDtoSetMapped = MappingHelper.getSetMessageDtoFromSetMensajesEntity( responseMessage.getMessages() );
				boolean newMessage = false;
				for ( MessageDto messageDto : messageDtoSetMapped ) {
					if ( !isMessageSet( messageDtoSet, messageDto ) ) {
						messageDtoSet.add( messageDto );
						newMessage = true;
					}
				}
				if ( newMessage ) {
					SortedSet<MessageDto> messageDtoSortedSet = new TreeSet<>( messageDtoSet );
					messageDtoSortedSet.comparator();
					setConfigRecyclerViewEvents( recyclerVieMessages, messageDtoSortedSet );
				}
			}

			@Override
			public void onError( String message ) {
				Log.d( TAG + " onError getChats", message );
			}
		} );
	}

	public void sendMessage( ) {
		MessageDto messageDto = new MessageDto();
		messageDto.setEvent( chatDto.getEvent() );
		messageDto.setClient( clientDto );
		messageDto.setMessage( _message.getText().toString() );
		apiCalls.sendMessage( MappingHelper.getMensajesEntityFromMessageDto( messageDto ), new ResponseMessageCallbackInterface() {
			@Override
			public void onSuccess( ResponseMessage responseMessage ) {
				Log.d( TAG, "onSuccess sendMessage" );
				_message.setText( "" );
				getMessages();
			}

			@Override
			public void onError( String message ) {
				Log.d( TAG + " onError sendMessage", message );
			}
		} );
	}

	/**
	 * Set up the RecyclerView configuration
	 *
	 * @param recyclerView  to config
	 * @param messageDtoSet to push into recyclerView
	 */
	private void setConfigRecyclerViewEvents( RecyclerView recyclerView, Set<MessageDto> messageDtoSet ) {
		LinearLayoutManager layoutManager
				= new LinearLayoutManager( MessageActivity.this, RecyclerView.VERTICAL, false );
		layoutManager.setOrientation( RecyclerView.VERTICAL );
		layoutManager.setReverseLayout( false );
		layoutManager.setStackFromEnd( true );
		recyclerView.setLayoutManager( layoutManager );
		MessageRecyclerView adapter = new MessageRecyclerView( getApplicationContext(), messageDtoSet );
		recyclerView.setAdapter( adapter );
		recyclerVieMessages.smoothScrollToPosition( adapter.getItemCount() - 1 );
	}

	private boolean isMessageSet( Set<MessageDto> messageDtoSet, MessageDto messageDto ) {
		for ( MessageDto messageDtoInSet : messageDtoSet ) {
			if ( messageDtoInSet.getId().equals( messageDto.getId() ) ) {
				return true;
			}
		}
		return false;
	}
}
