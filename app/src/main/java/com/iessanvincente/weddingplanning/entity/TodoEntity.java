package com.iessanvincente.weddingplanning.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * @author Jose J. Pardines
 */
public class TodoEntity implements Serializable {

	@SerializedName( "id" )
	@Expose
	private Long id;

	@SerializedName( "nombre" )
	@Expose
	private String nombre;

	@SerializedName( "fecha" )
	@Expose
	private Long fecha;

	@SerializedName( "realizada" )
	@Expose
	private Integer realizada;

	@SerializedName( "evento" )
	@Expose
	private EventosEntity evento;

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

	/**
	 * Get the todo date
	 *
	 * @return Long
	 */
	public Long getFecha( ) {
		return fecha;
	}

	/**
	 * Set the todo date
	 *
	 * @param fecha todo date
	 */
	public void setFecha( Long fecha ) {
		this.fecha = fecha;
	}

	/**
	 * Get the todo done
	 *
	 * @return Integer
	 */
	public Integer getRealizada( ) {
		return realizada;
	}

	/**
	 * Set the todo done
	 *
	 * @param realizada todo done
	 */
	public void setRealizada( Integer realizada ) {
		this.realizada = realizada;
	}

	/**
	 * Get the todo event
	 *
	 * @return EventosEntity
	 */
	public EventosEntity getEvento( ) {
		return evento;
	}

	/**
	 * Set the todo event
	 *
	 * @param evento todo event
	 */
	public void setEvento( EventosEntity evento ) {
		this.evento = evento;
	}

	@Override
	public String toString( ) {
		return "TodoEntity{" +
				"id=" + id +
				", nombre='" + nombre + '\'' +
				", fecha=" + fecha +
				", realizada=" + realizada +
				", evento=" + evento +
				'}';
	}
}
