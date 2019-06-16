package com.iessanvincente.weddingplanning.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.iessanvincente.weddingplanning.R;
import com.iessanvincente.weddingplanning.domain.ChatDto;
import com.iessanvincente.weddingplanning.domain.ClientDto;
import com.iessanvincente.weddingplanning.domain.EventDto;
import com.iessanvincente.weddingplanning.domain.MessageDto;
import com.iessanvincente.weddingplanning.domain.TodoDto;
import com.iessanvincente.weddingplanning.entity.TodoEntity;
import com.iessanvincente.weddingplanning.helper.MappingHelper;
import com.iessanvincente.weddingplanning.interfaces.ResponseTodoCallbackInterface;
import com.iessanvincente.weddingplanning.response.ResponseTodo;
import com.iessanvincente.weddingplanning.utils.APICalls;
import com.iessanvincente.weddingplanning.utils.TodoRecyclerView;

import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ToDoActivity extends AppCompatActivity {
	private static final String TAG = "ToDoActivity";
	@BindView( R.id.enter_todo )
	EditText _todo;
	@BindView( R.id.btn_send_todo )
	Button _btnSendTodo;
	@BindView( R.id.todoRecyclerView )
	RecyclerView _todoRecyclerView;
	@BindView( R.id.todoDoneRecyclerView )
	RecyclerView _todoDoneRecyclerView;
	private Intent actualIntent;
	private SharedPreferences settings;
	private APICalls apiCalls = new APICalls();
	private ClientDto clientDto = null;
	private EventDto eventDto = null;
	private Set<TodoDto> todoDtoSet = new HashSet<>();
	private Set<TodoDto> todoDtoSetDone = new HashSet<>();
	private String userToken;

	@Override
	protected void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_to_do );
		ButterKnife.bind( this );

		// Get SharedPreferences
		settings = getSharedPreferences( "PREF_CLI", Context.MODE_PRIVATE );
		userToken = settings.getString( "token", "" );

		// Set token for calls
		apiCalls.setUserToken( userToken );
		apiCalls.setContext( getApplicationContext() );

		// Save the intent intlo a private variable
		actualIntent = getIntent();

		_btnSendTodo.setOnClickListener( v -> sendTodo() );

		getClientInfo();
		getEventInfo();
		getTodoList();
	}
	/**
	 * Back to EventInfoActivity
	 *
	 * @return
	 */
	@Override
	public boolean onSupportNavigateUp( ) {
		Log.d( TAG, "Go to EventInfoActivity" );
		Intent intent = new Intent( getApplicationContext(), EventInfoActivity.class );
		intent.putExtra( "client", actualIntent.getSerializableExtra( "client" ) );
		intent.putExtra( "event", actualIntent.getSerializableExtra( "event" ) );
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
	 * Get event data and set into eventDto
	 */
	private void getEventInfo( ) {
		eventDto = (EventDto) actualIntent.getSerializableExtra( "event" );
	}

	/**
	 * Get event data and set into eventDto
	 */
	private void getTodoList( ) {
		todoDtoSetDone = new HashSet<>(  );
		todoDtoSet = new HashSet<>(  );
		apiCalls.getTodoListByEvent( eventDto, new ResponseTodoCallbackInterface() {
			@Override
			public void onSuccess( ResponseTodo responseTodo ) {
				Log.d( TAG, "onSuccess getTodoList" );
				TodoDto todoDto = null;
				for ( TodoEntity todoEntity : responseTodo.getTodoList() ) {
					todoDto = MappingHelper.getTodoDtoFromTodoEntity( todoEntity );
					if ( todoDto.getDone() ) {
						todoDtoSetDone.add( todoDto );
					} else {
						todoDtoSet.add( todoDto );
					}
				}

				SortedSet<TodoDto> todoDtoSortedSet = new TreeSet<>( todoDtoSet );
				todoDtoSortedSet.comparator();
				SortedSet<TodoDto> todoDtoSortedSetDone = new TreeSet<>( todoDtoSetDone );
				todoDtoSortedSetDone.comparator();
				setConfigRecyclerViewTodo( _todoRecyclerView, todoDtoSortedSet );
				setConfigRecyclerViewTodo( _todoDoneRecyclerView, todoDtoSortedSetDone );

			}

			@Override
			public void onError( String message ) {
				Log.d( TAG + " onError getTodoList", message );
			}
		} );
	}

	private void sendTodo(){
		TodoDto todoDto = new TodoDto();
		todoDto.setEvent( eventDto );
		todoDto.setTodo( _todo.getText().toString() );
		todoDto.setDone( false );
		apiCalls.createTodo( todoDto, new ResponseTodoCallbackInterface() {
			@Override
			public void onSuccess( ResponseTodo responseTodo ) {
				Log.d( TAG, "onSuccess sendTodo" );
				getTodoList();
			}

			@Override
			public void onError( String message ) {
				Log.d( TAG + " onError getTodoList", message );
			}
		} );
	}

	/**
	 * Set up the RecyclerView configuration
	 *
	 * @param recyclerView       to config
	 * @param todoDtoSetRecycler to push into recyclerView
	 */
	private void setConfigRecyclerViewTodo( RecyclerView recyclerView, Set<TodoDto> todoDtoSetRecycler ) {
		LinearLayoutManager layoutManager
				= new LinearLayoutManager( ToDoActivity.this, RecyclerView.VERTICAL, false );
		layoutManager.setOrientation( RecyclerView.VERTICAL );
		recyclerView.setLayoutManager( layoutManager );
		recyclerView.addItemDecoration( new DividerItemDecoration( getApplicationContext(), DividerItemDecoration.VERTICAL ) );
		TodoRecyclerView adapter = new TodoRecyclerView( getApplicationContext(), todoDtoSetRecycler );
		adapter.setClickListener( ( view, position ) -> {
			if ( view.getId() == R.id.btn_remove_todo ) {
				apiCalls.removeTodo( adapter.getItem( position ), new ResponseTodoCallbackInterface() {
					@Override
					public void onSuccess( ResponseTodo responseTodo ) {
						if ( todoDtoSet.contains( adapter.getItem( position ) ) ) {
							todoDtoSet.remove( adapter.getItem( position ) );
							setConfigRecyclerViewTodo( recyclerView, todoDtoSet );
						} else {
							todoDtoSetDone.remove( adapter.getItem( position ) );
							setConfigRecyclerViewTodo( recyclerView, todoDtoSetDone );
						}
					}

					@Override
					public void onError( String message ) {

					}
				} );
			} else if ( view.getId() == R.id.check_todo ) {
				TodoDto todoDto = adapter.getItem( position );
				todoDto.setEvent( eventDto );
				apiCalls.updateTodo( todoDto, new ResponseTodoCallbackInterface() {
					@Override
					public void onSuccess( ResponseTodo responseTodo ) {
						getTodoList();
					}

					@Override
					public void onError( String message ) {

					}
				} );
			}

		} );
		recyclerView.setAdapter( adapter );
	}
}
