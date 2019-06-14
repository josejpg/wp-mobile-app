package com.iessanvincente.weddingplanning.service;


import android.util.Log;

import com.iessanvincente.weddingplanning.entity.MensajesEntity;
import com.iessanvincente.weddingplanning.interfaces.ChatAPI;
import com.iessanvincente.weddingplanning.interfaces.MessageAPI;
import com.iessanvincente.weddingplanning.utils.APIClient;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

/**
 * @author Jose J. Pardines
 */
public class MessageService {
	private final String TAG = "MessageService";

	/**
	 * Get messages by event ID
	 *
	 * @param userToken auth token
	 * @param eventID event ID
	 * @param callback  handled API response
	 */
	public void getMessages( String userToken, String eventID, Callback<ResponseBody> callback ) {
		Log.d( TAG, "getChats" );
		Retrofit retrofit = APIClient.getRetrofitClient();
		MessageAPI messageAPI = retrofit.create( MessageAPI.class );
		Call<ResponseBody> call = messageAPI.getMesssages( userToken, eventID );
		call.enqueue( callback );
	}

	/**
	 * Get messages by event ID
	 *
	 * @param userToken auth token
	 * @param mensajesEntity message data
	 * @param callback  handled API response
	 */
	public void sendMessage( String userToken, MensajesEntity mensajesEntity, Callback<ResponseBody> callback ) {
		Log.d( TAG, "setMessage" );
		Retrofit retrofit = APIClient.getRetrofitClient();
		MessageAPI messageAPI = retrofit.create( MessageAPI.class );
		Call<ResponseBody> call = messageAPI.setMesssages( userToken, mensajesEntity );
		call.enqueue( callback );
	}

}
