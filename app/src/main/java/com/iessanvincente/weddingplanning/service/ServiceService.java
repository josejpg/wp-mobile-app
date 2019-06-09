package com.iessanvincente.weddingplanning.service;


import android.util.Log;

import com.iessanvincente.weddingplanning.entity.EventosEntity;
import com.iessanvincente.weddingplanning.interfaces.EventAPI;
import com.iessanvincente.weddingplanning.interfaces.ServiceAPI;
import com.iessanvincente.weddingplanning.utils.APIClient;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class ServiceService {
	private final String TAG = "ServicesService";

	/**
	 * Get events by client ID
	 *
	 * @param userToken auth token
	 * @param callback manage API response
	 */
	public void getServices( String userToken, Callback<ResponseBody> callback ) {
		Log.d( TAG, "getEventsByClient" );
		Retrofit retrofit = APIClient.getRetrofitClient();
		ServiceAPI serviceAPI = retrofit.create( ServiceAPI.class );
		Call<ResponseBody> call = serviceAPI.getAll( userToken );
		call.enqueue( callback );
	}

}
