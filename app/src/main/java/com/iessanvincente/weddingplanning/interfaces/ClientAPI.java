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

public interface ClientAPI {

	/**
	 * Login
	 *
	 * @param body ClientesEntity
	 * @return ResponseClient
	 */
	@POST( "clients/login" )
	@Headers( "Content-Type: application/json;charset=UTF-8" )
	Call<ResponseBody> getLogin( @Body ClientesEntity body );

	/**
	 * Login by Token
	 *
	 * @return ResponseClient
	 */
	@POST( "clients/login/token" )
	@Headers( "Content-Type: application/json;charset=UTF-8" )
	Call<ResponseBody> getLoginByToken( @Header( "authorization" ) String userToken );

	/**
	 * Register a new client
	 *
	 * @param body ClientesEntity
	 * @return ResponseClient
	 */
	@POST( "clients/" )
	@Headers( "Content-Type: application/json;charset=UTF-8" )
	Call<ResponseBody> registerClient( @Body ClientesEntity body );

	/**
	 * Update data client
	 *
	 * @param body ClientesEntity
	 * @return ResponseClient
	 */
	@PUT( "clients/{client_id}" )
	@Headers( "Content-Type: application/json;charset=UTF-8" )
	Call<ResponseBody> updateClient(  @Header( "authorization" ) String userToken, @Path( "client_id" ) String clientID, @Body ClientesEntity body );

}
