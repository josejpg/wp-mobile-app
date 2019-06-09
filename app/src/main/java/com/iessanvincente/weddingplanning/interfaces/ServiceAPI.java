package com.iessanvincente.weddingplanning.interfaces;


import com.iessanvincente.weddingplanning.entity.EventosEntity;
import com.iessanvincente.weddingplanning.entity.ServiciosEntity;

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

public interface ServiceAPI {

	/**
	 * Get all services
	 *
	 * @param userToken auth token
	 *
	 * @return ResponseEvent
	 */
	@GET( "services" )
	@Headers( "Content-Type: application/json;charset=UTF-8" )
	Call<ResponseBody> getAll( @Header( "authorization" ) String userToken );

	/**
	 * Create new service
	 *
	 * @param userToken auth token
	 * @param body service data
	 *
	 * @return ResponseEvent
	 */
	@POST( "services/" )
	@Headers( "Content-Type: application/json;charset=UTF-8" )
	Call<ResponseBody> createService( @Header( "authorization" ) String userToken, @Body ServiciosEntity body );

	/**
	 * Update data service
	 *
	 * @param userToken auth token
	 * @param serviceID Service ID
	 * @param body event data
	 *
	 * @return ResponseEvent
	 */
	@PUT( "services/{service_id}" )
	@Headers( "Content-Type: application/json;charset=UTF-8" )
	Call<ResponseBody> updateService( @Header( "authorization" ) String userToken, @Path( "service_id" ) String serviceID, @Body ServiciosEntity body );

	/**
	 * Delete service
	 *
	 * @param userToken auth token
	 * @param serviceID Service ID
	 *
	 * @return ResponseEvent
	 */
	@DELETE( "services/{service_id}" )
	@Headers( "Content-Type: application/json;charset=UTF-8" )
	Call<ResponseBody> deleteService( @Header( "authorization" ) String userToken, @Path( "service_id" ) String serviceID );


}
