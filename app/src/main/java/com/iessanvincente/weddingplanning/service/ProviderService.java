package com.iessanvincente.weddingplanning.service;


import android.util.Log;

import com.iessanvincente.weddingplanning.interfaces.ProviderAPI;
import com.iessanvincente.weddingplanning.utils.APIClient;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

/**
 * @author Jose J. Pardines
 */
public class ProviderService {
	private final String TAG = "ProviderService";

	/**
	 * Find provider by ID
	 *
	 * @param userToken  user token
	 * @param providerID provider to find
	 * @param callback   handled API response
	 */
	public void findById( String userToken, String providerID, Callback<ResponseBody> callback ) {
		Log.d( TAG, "findById" );
		Retrofit retrofit = APIClient.getRetrofitClient();
		ProviderAPI providerAPI = retrofit.create( ProviderAPI.class );
		Call<ResponseBody> call = providerAPI.findById( userToken, providerID );
		call.enqueue( callback );
	}

	/**
	 * Find provider by Service ID
	 *
	 * @param userToken user token
	 * @param serviceID service to find
	 * @param callback  handled API response
	 */
	public void findByServiceId( String userToken, String serviceID, Callback<ResponseBody> callback ) {
		Log.d( TAG, "findById" );
		Retrofit retrofit = APIClient.getRetrofitClient();
		ProviderAPI providerAPI = retrofit.create( ProviderAPI.class );
		Call<ResponseBody> call = providerAPI.findByServiceId( userToken, serviceID );
		call.enqueue( callback );
	}

}
