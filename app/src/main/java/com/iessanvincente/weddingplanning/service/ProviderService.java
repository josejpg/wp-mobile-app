package com.iessanvincente.weddingplanning.service;


import android.util.Log;

import com.iessanvincente.weddingplanning.entity.ClientesEntity;
import com.iessanvincente.weddingplanning.interfaces.ClientAPI;
import com.iessanvincente.weddingplanning.interfaces.ProviderAPI;
import com.iessanvincente.weddingplanning.utils.APIClient;
import com.iessanvincente.weddingplanning.utils.Encryptation;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class ProviderService {
	private final String TAG = "ProviderService";

	/**
	 * Find provider by ID
	 *
	 * @param userToken
	 * @param providerID
	 * @param callback
	 */
	public void findById( String userToken, String providerID, Callback<ResponseBody> callback ) {
		Log.d( TAG, "findById" );
		Retrofit retrofit = APIClient.getRetrofitClient();
		ProviderAPI providerAPI = retrofit.create( ProviderAPI.class );
		Call<ResponseBody> call = providerAPI.findById( userToken, providerID );
		call.enqueue( callback );
	}

}
