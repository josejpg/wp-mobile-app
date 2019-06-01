package com.iessanvincente.weddingplanning.interfaces;


import com.iessanvincente.weddingplanning.response.ResponseClient;

public interface ResponseClientCallbackInterface {
	void onSuccess( ResponseClient responseClient );
	void onError( String message );
}
