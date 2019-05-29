package com.iessanvincente.weddingplanning.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.iessanvincente.weddingplanning.domain.ClientDto;

import java.io.Serializable;

public class ResponseClient implements Serializable {
	@SerializedName( "ok" )
	@Expose
	Boolean ok;

	@SerializedName( "ko" )
	@Expose
	Boolean ko;

	@SerializedName( "error" )
	@Expose
	String error;

	@SerializedName( "token" )
	@Expose
	String token;

	@SerializedName( "cliente" )
	@Expose
	ClientDto client;

	public Boolean getOk( ) {
		return ok;
	}

	public void setOk( Boolean ok ) {
		this.ok = ok;
	}

	public Boolean getKo( ) {
		return ko;
	}

	public void setKo( Boolean ko ) {
		this.ko = ko;
	}

	public String getError( ) {
		return error;
	}

	public void setError( String error ) {
		this.error = error;
	}

	public String getToken( ) {
		return token;
	}

	public void setToken( String token ) {
		this.token = token;
	}

	public ClientDto getClient( ) {
		return client;
	}

	public void setClient( ClientDto client ) {
		this.client = client;
	}

	@Override
	public String toString( ) {
		return "ResponseClient{" +
				"ok=" + ok +
				", ko=" + ko +
				", token='" + token + '\'' +
				", client=" + client +
				'}';
	}
}
