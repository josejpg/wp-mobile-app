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
	 * @param email user email
	 * @param password user password
	 * @param callback manage API response
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
	 * @param userToken auth token
	 * @param callback manage API response
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
	 * @param clientesEntity client data
	 * @param callback manage API response
	 */
	public void registerClient( ClientesEntity clientesEntity, Callback<ResponseBody> callback ) {
		Log.d( TAG, "Register client" );
		clientesEntity.setPassword( Encryptation.encryptMD5( clientesEntity.getPassword() ) );
		Retrofit retrofit = APIClient.getRetrofitClient();
		ClientAPI clientsAPI = retrofit.create( ClientAPI.class );
		Call<ResponseBody> call = clientsAPI.registerClient( clientesEntity );
		call.enqueue( callback );
	}

	/**
	 * Register a new client
	 * @param userToken auth token
	 * @param clientesEntity client data
	 * @param callback manage API response
	 */
	public void updateClient( String userToken, ClientesEntity clientesEntity, Callback<ResponseBody> callback ) {
		Log.d( TAG, "Update client" );
		if( clientesEntity.getPassword() != null ) {
			clientesEntity.setPassword( Encryptation.encryptMD5( clientesEntity.getPassword() ) );
		}
		Retrofit retrofit = APIClient.getRetrofitClient();
		ClientAPI clientsAPI = retrofit.create( ClientAPI.class );
		Call<ResponseBody> call = clientsAPI.updateClient( userToken, Long.toString( clientesEntity.getId() ), clientesEntity );
		call.enqueue( callback );
	}
}
