package com.iessanvincente.weddingplanning.interfaces;

import com.iessanvincente.weddingplanning.entity.ClientesEntity;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ProviderAPI {

	/**
	 * Get user by ID
	 *
	 * @param userToken token
	 * @param providerID id to search
	 * @return ResponseClient
	 */
	@GET( "providers/{provider_id}" )
	@Headers( "Content-Type: application/json;charset=UTF-8" )
	Call<ResponseBody> findById( @Header( "authorization" ) String userToken, @Path( "provider_id" ) String providerID );

}
