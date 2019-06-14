package com.iessanvincente.weddingplanning.interfaces;


import com.iessanvincente.weddingplanning.entity.MensajesEntity;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface MessageAPI {

	/**
	 * Get all chats for client
	 *
	 * @param userToken auth token
	 * @param eventID event ID
	 *
	 * @return ResponseBody
	 */
	@GET( "messages/{event_id}" )
	@Headers( "Content-Type: application/json;charset=UTF-8" )
	Call<ResponseBody> getMesssages( @Header( "authorization" ) String userToken, @Path( "event_id" ) String eventID );

	/**
	 * Set a new message
	 *
	 * @param userToken auth token
	 * @param body message data
	 *
	 * @return ResponseBody
	 */
	@POST( "messages/" )
	@Headers( "Content-Type: application/json;charset=UTF-8" )
	Call<ResponseBody> setMesssages( @Header( "authorization" ) String userToken, @Body MensajesEntity body );

}
