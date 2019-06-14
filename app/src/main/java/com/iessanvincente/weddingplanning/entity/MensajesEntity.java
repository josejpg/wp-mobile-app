package com.iessanvincente.weddingplanning.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Set;

/**
 * @author Jose J. Pardines
 */
public class MensajesEntity implements Serializable {

	@SerializedName( "id" )
	@Expose
	private Long id;

	@SerializedName( "mensaje" )
	@Expose
	private String mensaje;

	@SerializedName( "fecha" )
	@Expose
	private Long fecha;

	@SerializedName( "propietario" )
	@Expose
	private Boolean propietario;

	@SerializedName( "evento" )
	@Expose
	private EventosEntity evento;

	@SerializedName( "proveedor" )
	@Expose
	private ProveedoresEntity proveedor;

	@SerializedName( "cliente" )
	@Expose
	private ClientesEntity cliente;

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
	 * Get the message
	 *
	 * @return String
	 */
	public String getMensaje( ) {
		return mensaje;
	}

	/**
	 * Set the message
	 *
	 * @param mensaje text
	 */
	public void setMensaje( String mensaje ) {
		this.mensaje = mensaje;
	}

	/**
	 * Get the date
	 *
	 * @return Date
	 */
	public Long getFecha( ) {
		return fecha;
	}

	/**
	 * Set the date
	 *
	 * @param fecha from message
	 */
	public void setFecha( Long fecha ) {
		this.fecha = fecha;
	}

	/**
	 * Get the owner boolean
	 *
	 * @return Boolean
	 */
	public Boolean getPropietario( ) {
		return propietario;
	}

	/**
	 * Set the owner
	 *
	 * @param propietario from message
	 */
	public void setPropietario( Boolean propietario ) {
		this.propietario = propietario;
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
	 * Get the provider
	 *
	 * @return ProveedoresEntity
	 *
	 */
	public ProveedoresEntity getProveedor( ) {
		return proveedor;
	}

	/**
	 * Set the providers
	 *
	 * @param proveedor provider
	 */
	public void setProveedor( ProveedoresEntity proveedor ) {
		this.proveedor = proveedor;
	}

	/**
	 * Get the client
	 *
	 * @return ClientesEntity
	 */
	public ClientesEntity getCliente( ) {
		return cliente;
	}

	/**
	 * Set the client
	 *
	 * @param cliente clients list
	 */
	public void setCliente( ClientesEntity cliente ) {
		this.cliente = cliente;
	}

	@Override
	public String toString( ) {
		return "MensajesEntity{" +
				"id=" + id +
				", mensaje='" + mensaje + '\'' +
				", fecha=" + fecha +
				", propietario=" + propietario +
				", evento=" + evento +
				", proveedores=" + proveedor +
				", clientes=" + cliente +
				'}';
	}
}
