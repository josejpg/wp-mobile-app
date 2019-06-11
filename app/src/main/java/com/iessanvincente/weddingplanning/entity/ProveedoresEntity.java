package com.iessanvincente.weddingplanning.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Set;

/**
 * @author Jose J. Pardines
 */
public class ProveedoresEntity implements Serializable {

	@SerializedName( "id" )
	@Expose
	private Long id;

	@SerializedName( "nombre" )
	@Expose
	private String nombre;

	@SerializedName( "cif" )
	@Expose
	private String cif;

	@SerializedName( "direccion" )
	@Expose
	private String direccion;

	@SerializedName( "poblacion" )
	@Expose
	private String poblacion;

	@SerializedName( "provincia" )
	@Expose
	private String provincia;

	@SerializedName( "cp" )
	@Expose
	private String cp;

	@SerializedName( "email" )
	@Expose
	private String email;

	@SerializedName( "password" )
	@Expose
	private String password;

	@SerializedName( "telefono" )
	@Expose
	private String telefono;

	@SerializedName( "movil" )
	@Expose
	private String movil;

	@SerializedName( "servicios" )
	@Expose
	private Set<ServiciosEntity> servicios;

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
	 * Get the provider name
	 *
	 * @return String
	 */
	public String getNombre( ) {
		return nombre;
	}

	/**
	 * Set a new name to the provider
	 *
	 * @param nombre provider name
	 */
	public void setNombre( String nombre ) {
		this.nombre = nombre;
	}

	/**
	 * Get the CIF
	 *
	 * @return String
	 */
	public String getCif( ) {
		return cif;
	}

	/**
	 * Set a new CIF to the provider
	 *
	 * @param cif provider CIF
	 */
	public void setCif( String cif ) {
		this.cif = cif;
	}

	/**
	 * Get the provider address
	 *
	 * @return String
	 */
	public String getDireccion( ) {
		return direccion;
	}

	/**
	 * Set a new address to a provider
	 *
	 * @param direccion provider address
	 */
	public void setDireccion( String direccion ) {
		this.direccion = direccion;
	}

	/**
	 * Get the provider town
	 *
	 * @return String
	 */
	public String getPoblacion( ) {
		return poblacion;
	}

	/**
	 * Se a new town to the provider
	 *
	 * @param poblacion provider town
	 */
	public void setPoblacion( String poblacion ) {
		this.poblacion = poblacion;
	}

	/**
	 * Get the provider province
	 *
	 * @return String
	 */
	public String getProvincia( ) {
		return provincia;
	}

	/**
	 * Set a new province to the provider
	 *
	 * @param provincia province town
	 */
	public void setProvincia( String provincia ) {
		this.provincia = provincia;
	}

	/**
	 * Get the provider postal code
	 *
	 * @return String
	 */
	public String getCp( ) {
		return cp;
	}

	/**
	 * Set a new postal code
	 *
	 * @param cp provider postal code
	 */
	public void setCp( String cp ) {
		this.cp = cp;
	}

	/**
	 * Get the provider email
	 *
	 * @return String
	 */
	public String getEmail( ) {
		return email;
	}

	/**
	 * Set a new email to the provider
	 *
	 * @param email provider email
	 */
	public void setEmail( String email ) {
		this.email = email;
	}

	/**
	 * Get the provider password
	 *
	 * @return String
	 */
	public String getPassword( ) {
		return password;
	}

	/**
	 * Set a new password to the provider
	 *
	 * @param password provider password
	 */
	public void setPassword( String password ) {
		this.password = password;
	}

	/**
	 * Get the provider phone
	 *
	 * @return String
	 */
	public String getTelefono( ) {
		return telefono;
	}

	/**
	 * Set a new phone to the provider
	 *
	 * @param telefono phone number
	 */
	public void setTelefono( String telefono ) {
		this.telefono = telefono;
	}

	/**
	 * Get the provider mobile phone
	 *
	 * @return String
	 */
	public String getMovil( ) {
		return movil;
	}

	/**
	 * Set a new mobile to the provider
	 *
	 * @param movil mobile number
	 */
	public void setMovil( String movil ) {
		this.movil = movil;
	}

	/**
	 * Get the list of services associates to the provider
	 *
	 * @return List<ServiciosEntity>
	 */
	public Set<ServiciosEntity> getServicios( ) {
		return servicios;
	}

	/**
	 * Set a list of services associates to the provider
	 *
	 * @param servicios list
	 */
	public void setServicios( Set<ServiciosEntity> servicios ) {
		this.servicios = servicios;
	}

	@Override
	public String toString( ) {
		return "ProveedoresEntity{" +
				"id=" + id +
				", nombre='" + nombre + '\'' +
				", cif='" + cif + '\'' +
				", direccion='" + direccion + '\'' +
				", poblacion='" + poblacion + '\'' +
				", provincia='" + provincia + '\'' +
				", cp='" + cp + '\'' +
				", email='" + email + '\'' +
				", password='" + password + '\'' +
				", telefono='" + telefono + '\'' +
				", movil='" + movil + '\'' +
				", servicios=" + servicios +
				'}';
	}
}
