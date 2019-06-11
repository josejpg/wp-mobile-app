package com.iessanvincente.weddingplanning.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * @author Jose J. Pardines
 */
public class ServiciosEntity implements Serializable {

	@SerializedName( "id" )
	@Expose
	private Long id;

	@SerializedName( "nombre" )
	@Expose
	private String nombre;

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
	public String getNombre( ) {
		return nombre;
	}

	/**
	 * Set the service name
	 *
	 * @param nombre service name
	 */
	public void setNombre( String nombre ) {
		this.nombre = nombre;
	}

	@Override
	public String toString( ) {
		return "ServiciosEntity{" +
				"id=" + id +
				", nombre='" + nombre + '\'' +
				'}';
	}
}
