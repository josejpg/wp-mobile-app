package com.iessanvincente.weddingplanning.interfaces;


import com.iessanvincente.weddingplanning.response.ResponseProvider;

public interface ResponseProviderCallbackInterface {
	void onSuccess( ResponseProvider responseProvider );
	void onError( String message );
}
