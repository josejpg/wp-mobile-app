package com.iessanvincente.weddingplanning.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.iessanvincente.weddingplanning.R;
import com.iessanvincente.weddingplanning.domain.ChatDto;
import com.iessanvincente.weddingplanning.domain.ClientDto;
import com.iessanvincente.weddingplanning.entity.ChatsEntity;
import com.iessanvincente.weddingplanning.helper.MappingHelper;
import com.iessanvincente.weddingplanning.interfaces.ResponseChatCallbackInterface;
import com.iessanvincente.weddingplanning.response.ResponseChat;
import com.iessanvincente.weddingplanning.utils.APICalls;
import com.iessanvincente.weddingplanning.utils.ChatRecyclerView;

import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChatsActivity extends AppCompatActivity {
	private static final String TAG = "ChatsActivity";
	@BindView( R.id.chatsRecyclerView )
	RecyclerView recyclerViewChats;
	private ProgressDialog progressDialog;
	private Intent actualIntent;
	private SharedPreferences settings;
	private APICalls apiCalls = new APICalls();
	private ClientDto clientDto = null;
	private Set<ChatDto> chatDtoSet = new HashSet<>(  );
	private String userToken;

	@Override
	protected void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_chats );
		ButterKnife.bind( this );

		// Get SharedPreferences
		settings = getSharedPreferences( "PREF_CLI", Context.MODE_PRIVATE );
		userToken = settings.getString( "token", "" );

		// Set token for calls
		apiCalls.setUserToken( userToken );
		apiCalls.setContext( getApplicationContext() );

		// Save the intent intlo a private variable
		actualIntent = getIntent();

		// Init progressDialog
		progressDialog = new ProgressDialog( ChatsActivity.this, R.style.AppTheme_Light_Dialog );

		getClientInfo();
		getChats();
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
	 * Get client data and set into clientDto
	 */
	private void getClientInfo( ) {
		clientDto = (ClientDto) actualIntent.getSerializableExtra( "client" );
	}


	/**
	 * Get chats
	 */
	private void getChats( ) {
		progressDialog.setIndeterminate( true );
		progressDialog.setMessage( getResources().getString( R.string.progressDialog_loading_chats ) );
		progressDialog.show();
		apiCalls.getChats( clientDto, new ResponseChatCallbackInterface() {
			@Override
			public void onSuccess( ResponseChat responseChat ) {
				Log.d( TAG, "onSuccess getChats" );

				for ( ChatsEntity chatsEntity : responseChat.getChats() ) {
					chatDtoSet.add( MappingHelper.getChatDtoFromChatsEntity( chatsEntity ) );
				}
				SortedSet<ChatDto> chatDtoSortedSet = new TreeSet<>();
				chatDtoSortedSet.addAll( chatDtoSet );
				chatDtoSortedSet.comparator();
				setConfigRecyclerViewEvents( recyclerViewChats, chatDtoSortedSet );
				progressDialog.dismiss();
			}

			@Override
			public void onError( String message ) {
				Log.d( TAG + " onError getChats", message );
				progressDialog.dismiss();
			}
		} );
	}

	/**
	 * Set up the RecyclerView configuration
	 *
	 * @param recyclerView to config
	 * @param chatDtoSet   to push into recyclerView
	 */
	private void setConfigRecyclerViewEvents( RecyclerView recyclerView, Set<ChatDto> chatDtoSet ) {
		LinearLayoutManager layoutManager
				= new LinearLayoutManager( ChatsActivity.this, RecyclerView.VERTICAL, false );
		layoutManager.setOrientation( RecyclerView.VERTICAL );
		recyclerView.setLayoutManager( layoutManager );
		recyclerView.addItemDecoration( new DividerItemDecoration( getApplicationContext(), DividerItemDecoration.VERTICAL ) );
		ChatRecyclerView adapter = new ChatRecyclerView( getApplicationContext(), chatDtoSet );
		adapter.setClickListener( ( view, position ) -> {
			Log.d( TAG, "Go to MessageActivity" );
			Intent intent = new Intent( getApplicationContext(), MessageActivity.class );
			intent.putExtra( "client", clientDto );
			intent.putExtra( "chat", adapter.getItem( position ) );
			startActivity( intent );
			overridePendingTransition( R.anim.push_left_in, R.anim.push_left_out );
			finish();
		} );
		recyclerView.setAdapter( adapter );
	}
}
