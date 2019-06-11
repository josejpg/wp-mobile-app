package com.iessanvincente.weddingplanning.service;


import android.util.Log;

import com.iessanvincente.weddingplanning.entity.EventosEntity;
import com.iessanvincente.weddingplanning.interfaces.EventAPI;
import com.iessanvincente.weddingplanning.utils.APIClient;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

/**
 * @author Jose J. Pardines
 */
public class EventService {
	private final String TAG = "EventsService";

	/**
	 * Get events by client ID
	 *
	 * @param userToken auth token
	 * @param clientID  client id
	 * @param callback  handled API response
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
	 * @param eventID   event id
	 * @param callback  handled API response
	 */
	public void getEventById( String userToken, Long eventID, Callback<ResponseBody> callback ) {
		Log.d( TAG, "getEventById" );
		Retrofit retrofit = APIClient.getRetrofitClient();
		EventAPI eventAPI = retrofit.create( EventAPI.class );
		Call<ResponseBody> call = eventAPI.getEventById( userToken, Long.toString( eventID ) );
		call.enqueue( callback );
	}

	/**
	 * Create event
	 *
	 * @param userToken     auth token
	 * @param eventosEntity event data
	 * @param callback      handled API response
	 */
	public void createEvent( String userToken, EventosEntity eventosEntity, Callback<ResponseBody> callback ) {
		Log.d( TAG, "createEvent" );
		Retrofit retrofit = APIClient.getRetrofitClient();
		EventAPI eventAPI = retrofit.create( EventAPI.class );
		Call<ResponseBody> call = eventAPI.createEvent( userToken, eventosEntity );
		call.enqueue( callback );
	}

	/**
	 * Update data event
	 *
	 * @param userToken     auth token
	 * @param eventosEntity event data
	 * @param callback      handled API response
	 */
	public void updateEvent( String userToken, EventosEntity eventosEntity, Callback<ResponseBody> callback ) {
		Log.d( TAG, "updateEvent" );
		Retrofit retrofit = APIClient.getRetrofitClient();
		EventAPI eventAPI = retrofit.create( EventAPI.class );
		Call<ResponseBody> call = eventAPI.updateEvent( userToken, Long.toString( eventosEntity.getId() ), eventosEntity );
		call.enqueue( callback );
	}

}
