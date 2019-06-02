package com.iessanvincente.weddingplanning.interfaces;


import com.iessanvincente.weddingplanning.response.ResponseEvent;

public interface ResponseEventCallbackInterface {
	void onSuccess( ResponseEvent responseEvent );
	void onError( String message );
}
