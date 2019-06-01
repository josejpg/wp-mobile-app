package com.iessanvincente.weddingplanning.interfaces;

import com.iessanvincente.weddingplanning.entity.ClientesEntity;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface EventAPI {

	/**
	 * Get events by client ID
	 *
	 * @param clientID Client ID
	 * @return ResponseClient
	 */
	@POST( "events/client/{client_id}" )
	@Headers( "Content-Type: application/json;charset=UTF-8" )
	Call<ResponseBody> getEventsByClient(  @Header( "authorization" ) String userToken, @Path( "client_id" ) String clientID );


}
