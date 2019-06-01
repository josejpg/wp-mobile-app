package com.iessanvincente.weddingplanning.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.iessanvincente.weddingplanning.domain.ClientDto;
import com.iessanvincente.weddingplanning.entity.EventosEntity;

import java.io.Serializable;
import java.util.Set;

public class ResponseEvent implements Serializable {
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

	@SerializedName( "events" )
	@Expose
	Set<EventosEntity> events;

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

	public Set<EventosEntity> getEvents( ) {
		return events;
	}

	public void setEvetns( Set<EventosEntity> events ) {
		this.events = events;
	}

	@Override
	public String toString( ) {
		return "ResponseEvent{" +
				"ok=" + ok +
				", ko=" + ko +
				", error='" + error + '\'' +
				", token='" + token + '\'' +
				", events=" + events +
				'}';
	}
}
