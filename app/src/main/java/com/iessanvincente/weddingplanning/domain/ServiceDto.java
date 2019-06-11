package com.iessanvincente.weddingplanning.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * @author Jose J. Pardines
 */
public class ServiceDto implements Serializable {

	@SerializedName( "id" )
	@Expose
	private Long id;

	@SerializedName( "service" )
	@Expose
	private String service;

	/**
	 * Get the ID
	 *
	 * @return Long
	 */
	public Long getId( ) {
		return id;
	}

	/**
	 * Set the ID
	 *
	 * @param id event ID
	 */
	public void setId( Long id ) {
		this.id = id;
	}

	/**
	 * Get the service name
	 *
	 * @return String
	 */
	public String getService( ) {
		return service;
	}

	/**
	 * Set the service name
	 *
	 * @param service service name
	 */
	public void setService( String service ) {
		this.service = service;
	}

	@Override
	public String toString( ) {
		return service;
	}
}
