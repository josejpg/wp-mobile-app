package com.iessanvincente.weddingplanning.service;


import android.util.Log;

import com.iessanvincente.weddingplanning.entity.ClientesEntity;
import com.iessanvincente.weddingplanning.interfaces.ClientAPI;
import com.iessanvincente.weddingplanning.response.ResponseClient;
import com.iessanvincente.weddingplanning.utils.APIClient;
import com.iessanvincente.weddingplanning.utils.Encryptation;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class ClientService {
	private final String TAG = "ClientService";

	/**
	 * Login client with email and password
	 *
	 * @param email
	 * @param password
	 * @param callback
	 */
	public void login( String email, String password, Callback<ResponseBody> callback ) {
		Log.d( TAG, "Login" );
		ClientesEntity clientesEntity = new ClientesEntity();
		clientesEntity.setEmail( email );
		clientesEntity.setPassword( Encryptation.encryptMD5( password ) );
		Retrofit retrofit = APIClient.getRetrofitClient();
		ClientAPI clientsAPI = retrofit.create( ClientAPI.class );
		Call<ResponseBody> call = clientsAPI.getLogin( clientesEntity );
		call.enqueue( callback );
	}
	/**
	 * Login client with token
	 *
	 * @param userToken
	 * @param callback
	 */
	public void loginByToken( String userToken, Callback<ResponseBody> callback ) {
		Log.d( TAG, "Login" );
		Retrofit retrofit = APIClient.getRetrofitClient();
		ClientAPI clientsAPI = retrofit.create( ClientAPI.class );
		Call<ResponseBody> call = clientsAPI.getLoginByToken( userToken );
		call.enqueue( callback );
	}

	/**
	 * Register a new client
	 * @param clientData
	 * @param callback
	 */
	public void registerClient( ClientesEntity clientData, Callback<ResponseBody> callback ) {
		Log.d( TAG, "Register client" );
		clientData.setPassword( Encryptation.encryptMD5( clientData.getPassword() ) );
		Retrofit retrofit = APIClient.getRetrofitClient();
		ClientAPI clientsAPI = retrofit.create( ClientAPI.class );
		Call<ResponseBody> call = clientsAPI.registerClient( clientData );
		call.enqueue( callback );
	}
}
