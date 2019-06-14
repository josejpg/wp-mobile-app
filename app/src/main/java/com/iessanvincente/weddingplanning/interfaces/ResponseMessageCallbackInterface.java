package com.iessanvincente.weddingplanning.interfaces;


import com.iessanvincente.weddingplanning.response.ResponseMessage;

public interface ResponseMessageCallbackInterface {
	void onSuccess( ResponseMessage responseMessage );
	void onError( String message );
}
