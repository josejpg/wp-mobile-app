package com.iessanvincente.weddingplanning.service;


import android.util.Log;

import com.iessanvincente.weddingplanning.interfaces.ChatAPI;
import com.iessanvincente.weddingplanning.interfaces.ServiceAPI;
import com.iessanvincente.weddingplanning.utils.APIClient;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

/**
 * @author Jose J. Pardines
 */
public class ChatService {
	private final String TAG = "ChatService";

	/**
	 * Get chats by client ID
	 *
	 * @param userToken auth token
	 * @param callback  handled API response
	 */
	public void getChats( String userToken, String clientID, Callback<ResponseBody> callback ) {
		Log.d( TAG, "getChats" );
		Retrofit retrofit = APIClient.getRetrofitClient();
		ChatAPI chatAPI = retrofit.create( ChatAPI.class );
		Call<ResponseBody> call = chatAPI.getChats( userToken, clientID );
		call.enqueue( callback );
	}

}
