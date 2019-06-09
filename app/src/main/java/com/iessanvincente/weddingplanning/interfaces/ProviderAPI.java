package com.iessanvincente.weddingplanning.interfaces;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface ProviderAPI {

	/**
	 * Get provider by ID
	 *
	 * @param userToken token
	 * @param providerID id to search
	 * @return ResponseClient
	 */
	@GET( "providers/{provider_id}" )
	@Headers( "Content-Type: application/json;charset=UTF-8" )
	Call<ResponseBody> findById( @Header( "authorization" ) String userToken, @Path( "provider_id" ) String providerID );

	/**
	 * Get user by ID
	 *
	 * @param userToken token
	 * @param serviceID id to search
	 * @return ResponseClient
	 */
	@GET( "providers/service/{service_id}" )
	@Headers( "Content-Type: application/json;charset=UTF-8" )
	Call<ResponseBody> findByServiceId( @Header( "authorization" ) String userToken, @Path( "service_id" ) String serviceID );

}
