package com.iessanvincente.weddingplanning.service;


import android.util.Log;

import com.iessanvincente.weddingplanning.entity.ClientesEntity;
import com.iessanvincente.weddingplanning.interfaces.ClientAPI;
import com.iessanvincente.weddingplanning.interfaces.EventAPI;
import com.iessanvincente.weddingplanning.utils.APIClient;
import com.iessanvincente.weddingplanning.utils.Encryptation;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class EventService {
	private final String TAG = "EventsService";

	/**
	 * Login client with email and password
	 *
	 * @param userToken
	 * @param clientID
	 * @param callback
	 */
	public void getEventsByClient( String userToken, Long clientID, Callback<ResponseBody> callback ) {
		Log.d( TAG, "Login" );
		ClientesEntity clientesEntity = new ClientesEntity();
		Retrofit retrofit = APIClient.getRetrofitClient();
		EventAPI eventAPI = retrofit.create( EventAPI.class );
		Call<ResponseBody> call = eventAPI.getEventsByClient( userToken, Long.toString( clientID ) );
		call.enqueue( callback );
	}

}
