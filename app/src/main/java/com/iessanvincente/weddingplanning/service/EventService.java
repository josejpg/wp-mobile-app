package com.iessanvincente.weddingplanning.service;


import android.util.Log;

import com.iessanvincente.weddingplanning.interfaces.EventAPI;
import com.iessanvincente.weddingplanning.utils.APIClient;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class EventService {
	private final String TAG = "EventsService";

	/**
	 * Get events by client ID
	 *
	 * @param userToken auth token
	 * @param clientID client id
	 * @param callback manage API response
	 */
	public void getEventsByClient( String userToken, Long clientID, Callback<ResponseBody> callback ) {
		Log.d( TAG, "getEventsByClient" );
		Retrofit retrofit = APIClient.getRetrofitClient();
		EventAPI eventAPI = retrofit.create( EventAPI.class );
		Call<ResponseBody> call = eventAPI.getEventsByClient( userToken, Long.toString( clientID ) );
		call.enqueue( callback );
	}

	/**
	 * Get event by ID
	 *
	 * @param userToken auth token
	 * @param eventID event id
	 * @param callback manage API response
	 */
	public void getEventById( String userToken, Long eventID, Callback<ResponseBody> callback ) {
		Log.d( TAG, "getEventById" );
		Retrofit retrofit = APIClient.getRetrofitClient();
		EventAPI eventAPI = retrofit.create( EventAPI.class );
		Call<ResponseBody> call = eventAPI.getEventById( userToken, Long.toString( eventID ) );
		call.enqueue( callback );
	}

}