package com.iessanvincente.weddingplanning.service;


import android.util.Log;

import com.iessanvincente.weddingplanning.domain.TodoDto;
import com.iessanvincente.weddingplanning.entity.TodoEntity;
import com.iessanvincente.weddingplanning.interfaces.TodoAPI;
import com.iessanvincente.weddingplanning.utils.APIClient;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

/**
 * @author Jose J. Pardines
 */
public class TodoService {
	private final String TAG = "TodoService";

	/**
	 * Get events by client ID
	 *
	 * @param userToken auth token
	 * @param eventID event ID
	 * @param callback  handled API response
	 */
	public void getTodoListByEvent( String userToken, String eventID, Callback<ResponseBody> callback ) {
		Log.d( TAG, "getServices" );
		Retrofit retrofit = APIClient.getRetrofitClient();
		TodoAPI todoAPI = retrofit.create( TodoAPI.class );
		Call<ResponseBody> call = todoAPI.getByEvent( userToken, eventID );
		call.enqueue( callback );
	}

	/**
	 * New todo
	 *
	 * @param userToken auth token
	 * @param todoEntity todo data
	 * @param callback  handled API response
	 */
	public void createTodo( String userToken, TodoEntity todoEntity, Callback<ResponseBody> callback ) {
		Log.d( TAG, "createTodo" );
		Retrofit retrofit = APIClient.getRetrofitClient();
		TodoAPI todoAPI = retrofit.create( TodoAPI.class );
		Call<ResponseBody> call = todoAPI.createTodo( userToken, todoEntity );
		call.enqueue( callback );
	}

	/**
	 * Remove todo
	 *
	 * @param userToken auth token
	 * @param todoID todo ID
	 * @param callback  handled API response
	 */
	public void deleteTodo( String userToken, String todoID, Callback<ResponseBody> callback ) {
		Log.d( TAG, "deleteTodo" );
		Retrofit retrofit = APIClient.getRetrofitClient();
		TodoAPI todoAPI = retrofit.create( TodoAPI.class );
		Call<ResponseBody> call = todoAPI.deleteTodo( userToken, todoID );
		call.enqueue( callback );
	}

	/**
	 * Update todo
	 *
	 * @param userToken auth token
	 * @param todoEntity todo data
	 * @param callback  handled API response
	 */
	public void updateTodo( String userToken, TodoEntity todoEntity, Callback<ResponseBody> callback ) {
		Log.d( TAG, "updateTodo" );
		Retrofit retrofit = APIClient.getRetrofitClient();
		TodoAPI todoAPI = retrofit.create( TodoAPI.class );
		Call<ResponseBody> call = todoAPI.updateTodo( userToken, todoEntity.getId().toString(), todoEntity );
		call.enqueue( callback );
	}

}
