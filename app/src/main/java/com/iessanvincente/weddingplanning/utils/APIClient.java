package com.iessanvincente.weddingplanning.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
	private static final String BASE_URL = "https://tfg-weddingplanning-node.herokuapp.com/api/v1/";
	private static Retrofit retrofit;
	private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
	private static HttpLoggingInterceptor logging = new HttpLoggingInterceptor();

	/**
	 * This public static method will return Retrofit client
	 * anywhere in the appplication
	 *
	 * @return Retrofit
	 */
	public static Retrofit getRetrofitClient() {
		// Set log interceptor
		logging.setLevel( HttpLoggingInterceptor.Level.BODY );
		httpClient.addInterceptor( logging );

		if ( retrofit == null ) {
			Gson gson = new GsonBuilder()
					.setLenient()
					.create();

			retrofit = new Retrofit.Builder()
					.baseUrl( BASE_URL )
					.client( httpClient.build() )
					.addConverterFactory( GsonConverterFactory.create( gson ) )
					.build();
		}
		return retrofit;
	}
}
