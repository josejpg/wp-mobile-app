package com.iessanvincente.weddingplanning.interfaces;


import com.iessanvincente.weddingplanning.domain.ClientDto;

public interface ClientsDtoCallbackInterface {
	void onSuccess( ClientDto clientDto );
	void onError( String message );
}
