package com.iessanvincente.weddingplanning.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.iessanvincente.weddingplanning.entity.ServiciosEntity;

import java.io.Serializable;
import java.util.Set;

/**
 * @author Jose J. Pardines
 */
public class ResponseService implements Serializable {
	@SerializedName( "ok" )
	@Expose
	Boolean ok;

	@SerializedName( "ko" )
	@Expose
	Boolean ko;

	@SerializedName( "error" )
	@Expose
	String error;

	@SerializedName( "services" )
	@Expose
	Set<ServiciosEntity> services;

	@SerializedName( "service" )
	@Expose
	ServiciosEntity service;

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

	public Set<ServiciosEntity> getServices( ) {
		return services;
	}

	public void setServices( Set<ServiciosEntity> services ) {
		this.services = services;
	}

	public ServiciosEntity getService( ) {
		return service;
	}

	public void setService( ServiciosEntity service ) {
		this.service = service;
	}


	@Override
	public String toString( ) {
		return "ResponseService{" +
				"ok=" + ok +
				", ko=" + ko +
				", error='" + error + '\'' +
				", services=" + services +
				", service=" + service +
				'}';
	}
}
