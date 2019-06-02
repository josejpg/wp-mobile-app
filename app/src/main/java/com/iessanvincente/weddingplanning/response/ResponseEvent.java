package com.iessanvincente.weddingplanning.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.iessanvincente.weddingplanning.entity.EventosEntity;
import com.iessanvincente.weddingplanning.entity.ProveedoresEntity;

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

	@SerializedName( "events" )
	@Expose
	Set<EventosEntity> events;

	@SerializedName( "event" )
	@Expose
	EventosEntity event;

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

	public void setEvents( Set<EventosEntity> events ) {
		this.events = events;
	}

	public EventosEntity getEvent( ) {
		return event;
	}

	public void setEvent( EventosEntity event ) {
		this.event = event;
	}


	@Override
	public String toString( ) {
		return "ResponseEvent{" +
				"ok=" + ok +
				", ko=" + ko +
				", error='" + error + '\'' +
				", events=" + events +
				", event=" + event +
				'}';
	}
}
