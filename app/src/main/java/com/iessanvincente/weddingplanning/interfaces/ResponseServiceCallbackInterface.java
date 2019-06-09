package com.iessanvincente.weddingplanning.interfaces;


import com.iessanvincente.weddingplanning.response.ResponseService;

public interface ResponseServiceCallbackInterface {
	void onSuccess( ResponseService responseService );
	void onError( String message );
}
