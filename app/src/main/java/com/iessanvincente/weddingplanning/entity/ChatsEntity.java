package com.iessanvincente.weddingplanning.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Set;

/**
 * @author Jose J. Pardines
 */
public class ChatsEntity implements Serializable {

	@SerializedName( "id" )
	@Expose
	private Long id;

	@SerializedName( "evento" )
	@Expose
	private EventosEntity evento;

	@SerializedName( "proveedor" )
	@Expose
	private ProveedoresEntity proveedor;

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
	 * @param id provider ID
	 */
	public void setId( Long id ) {
		this.id = id;
	}

	/**
	 * Get the data event
	 *
	 * @return EventosEntity
	 */
	public EventosEntity getEvento( ) {
		return evento;
	}

	/**
	 * Set the event
	 *
	 * @param evento from message
	 */
	public void setEvento( EventosEntity evento ) {
		this.evento = evento;
	}

	/**
	 * Get the providers
	 *
	 * @return List<ProveedoresEntity>
	 */
	public ProveedoresEntity getProveedor( ) {
		return proveedor;
	}

	/**
	 * Set the provider
	 *
	 * @param proveedor provider
	 */
	public void setProveedor( ProveedoresEntity proveedor ) {
		this.proveedor = proveedor;
	}

	@Override
	public String toString( ) {
		return "ChatsEntity{" +
				"id=" + id +
				", evento=" + evento +
				", proveedor=" + proveedor +
				'}';
	}
}
