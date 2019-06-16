package com.iessanvincente.weddingplanning.interfaces;


import com.iessanvincente.weddingplanning.response.ResponseTodo;

public interface ResponseTodoCallbackInterface {
	void onSuccess( ResponseTodo responseTodo );
	void onError( String message );
}
