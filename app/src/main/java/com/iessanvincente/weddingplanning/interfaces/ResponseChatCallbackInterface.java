package com.iessanvincente.weddingplanning.interfaces;


import com.iessanvincente.weddingplanning.response.ResponseChat;

public interface ResponseChatCallbackInterface {
	void onSuccess( ResponseChat responseChat );
	void onError( String message );
}
