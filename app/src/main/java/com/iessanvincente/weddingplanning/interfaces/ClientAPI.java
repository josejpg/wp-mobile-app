package com.iessanvincente.weddingplanning.interfaces;

import com.iessanvincente.weddingplanning.entity.ClientesEntity;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ClientAPI {

	/**
	 * Login
	 *
	 * @param body ClientesEntity
	 * @return ResponseClient
	 */
	@POST( "clients/login" )
	@Headers({ "Content-Type: application/json;charset=UTF-8"})
	Call<ResponseBody> getLogin( @Body ClientesEntity body );

	/**
	 * Register a new client
	 *
	 * @param body ClientesEntity
	 * @return ResponseClient
	 */
	@POST( "clients/" )
	@Headers({ "Content-Type: application/json;charset=UTF-8"})
	Call<ResponseBody> registerClient( @Body ClientesEntity body );

}
