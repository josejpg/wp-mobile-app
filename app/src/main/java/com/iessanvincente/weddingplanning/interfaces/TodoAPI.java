package com.iessanvincente.weddingplanning.interfaces;


import com.iessanvincente.weddingplanning.entity.TodoEntity;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface TodoAPI {

	/**
	 * Get todo list by event
	 *
	 * @param userToken auth token
	 * @param eventID Event ID
	 *
	 * @return ResponseEvent
	 */
	@GET( "todo/{event_id}" )
	@Headers( "Content-Type: application/json;charset=UTF-8" )
	Call<ResponseBody> getByEvent( @Header( "authorization" ) String userToken , @Path( "event_id" ) String eventID);

	/**
	 * Create new todo
	 *
	 * @param userToken auth token
	 * @param body todo data
	 *
	 * @return ResponseEvent
	 */
	@POST( "todo/" )
	@Headers( "Content-Type: application/json;charset=UTF-8" )
	Call<ResponseBody> createTodo( @Header( "authorization" ) String userToken, @Body TodoEntity body );

	/**
	 * Update data todo
	 *
	 * @param userToken auth token
	 * @param todoID Todo ID
	 * @param body event data
	 *
	 * @return ResponseEvent
	 */
	@PUT( "todo/{todo_id}" )
	@Headers( "Content-Type: application/json;charset=UTF-8" )
	Call<ResponseBody> updateTodo( @Header( "authorization" ) String userToken, @Path( "todo_id" ) String todoID, @Body TodoEntity body );

	/**
	 * Delete todo
	 *
	 * @param userToken auth token
	 * @param todoID Todo ID
	 *
	 * @return ResponseEvent
	 */
	@DELETE( "todo/{todo_id}" )
	@Headers( "Content-Type: application/json;charset=UTF-8" )
	Call<ResponseBody> deleteTodo( @Header( "authorization" ) String userToken, @Path( "todo_id" ) String todoID );


}
