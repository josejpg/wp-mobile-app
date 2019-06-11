package com.iessanvincente.weddingplanning.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Set;

/**
 * @author Jose J. Pardines
 */
public class EventosEntity implements Serializable {

	@SerializedName( "id" )
	@Expose
	private Long id;

	@SerializedName( "nombre" )
	@Expose
	private String nombre;

	@SerializedName( "descripcion" )
	@Expose
	private String descripcion;

	@SerializedName( "fecha" )
	@Expose
	private Long fecha;

	@SerializedName( "activo" )
	@Expose
	private Integer activo;

	@SerializedName( "proveedores" )
	@Expose
	private Set<ProveedoresEntity> proveedores;

	@SerializedName( "clientes" )
	@Expose
	private Set<ClientesEntity> clientes;

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
	 * Get the event name
	 *
	 * @return String
	 */
	public String getNombre( ) {
		return nombre;
	}

	/**
	 * Set the event name
	 *
	 * @param nombre event name
	 */
	public void setNombre( String nombre ) {
		this.nombre = nombre;
	}

	/**
	 * Get the event name
	 *
	 * @return String
	 */
	public String getDescripcion( ) {
		return descripcion;
	}

	/**
	 * Set the event name
	 *
	 * @param descripcion event name
	 */
	public void setDescripcion( String descripcion ) {
		this.descripcion = descripcion;
	}

	/**
	 * Get the event date
	 *
	 * @return Integer
	 */
	public Long getFecha( ) {
		return fecha;
	}

	/**
	 * Set the date
	 *
	 * @param fecha event date
	 */
	public void setFecha( Long fecha ) {
		this.fecha = fecha;
	}

	/**
	 * Get the active status
	 *
	 * @return Boolean
	 */
	public Integer getActivo( ) {
		return activo;
	}

	/**
	 * Set the active status
	 *
	 * @param activo status
	 */
	public void setActivo( Integer activo ) {
		this.activo = activo;
	}

	/**
	 * Get the list of provider associates to the event
	 *
	 * @return Set<ProveedoresEntity>
	 */
	public Set<ProveedoresEntity> getProveedores( ) {
		return proveedores;
	}

	/**
	 * Set a list of providers associates to the event
	 *
	 * @param proveedores list
	 */
	public void setProveedores( Set<ProveedoresEntity> proveedores ) {
		this.proveedores = proveedores;
	}

	/**
	 * Get the list of clients associates to the event
	 *
	 * @return Set<ClientesEntity>
	 */
	public Set<ClientesEntity> getClientes( ) {
		return clientes;
	}

	/**
	 * Set a list of clients associates to the event
	 *
	 * @param clientes list
	 */
	public void setClientes( Set<ClientesEntity> clientes ) {
		this.clientes = clientes;
	}


	@Override
	public String toString( ) {
		return "EventosEntity{" +
				"id=" + id +
				", nombre='" + nombre + '\'' +
				", descripcion='" + descripcion + '\'' +
				", fecha=" + fecha +
				", activo=" + activo +
				", proveedores=" + proveedores +
				", clientes=" + clientes +
				'}';
	}

}
