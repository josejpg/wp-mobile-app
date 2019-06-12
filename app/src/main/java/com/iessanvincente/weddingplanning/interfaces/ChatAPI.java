package com.iessanvincente.weddingplanning.interfaces;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface ChatAPI {

	/**
	 * Get all chats for client
	 *
	 * @param userToken auth token
	 * @param clientID client ID
	 *
	 * @return ResponseBody
	 */
	@GET( "messages/chats/{client_id}" )
	@Headers( "Content-Type: application/json;charset=UTF-8" )
	Call<ResponseBody> getChats( @Header( "authorization" ) String userToken, @Path( "client_id" ) String clientID );

}
