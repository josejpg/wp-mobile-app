package com.iessanvincente.weddingplanning.interfaces;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface EventAPI {

	/**
	 * Get event by ID
	 *
	 * @param eventID ClieEventnt ID
	 * @return ResponseEvent
	 */
	@GET( "events/{event_id}" )
	@Headers( "Content-Type: application/json;charset=UTF-8" )
	Call<ResponseBody> getEventById(  @Header( "authorization" ) String userToken, @Path( "event_id" ) String eventID );

	/**
	 * Get events by client ID
	 *
	 * @param clientID Client ID
	 * @return ResponseEvent
	 */
	@GET( "events/client/{client_id}" )
	@Headers( "Content-Type: application/json;charset=UTF-8" )
	Call<ResponseBody> getEventsByClient(  @Header( "authorization" ) String userToken, @Path( "client_id" ) String clientID );


}
