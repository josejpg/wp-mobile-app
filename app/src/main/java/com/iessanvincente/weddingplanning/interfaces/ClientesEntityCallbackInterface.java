package com.iessanvincente.weddingplanning.interfaces;


import com.iessanvincente.weddingplanning.entity.ClientesEntity;

public interface ClientesEntityCallbackInterface {
	void onSuccess( ClientesEntity clientesEntity );
	void onError( String message );
}
