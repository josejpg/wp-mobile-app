package com.iessanvincente.weddingplanning.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ClientDto implements Serializable {
	@SerializedName( "id" )
	@Expose
	private Long id;

	@SerializedName( "email" )
	@Expose
	private String email;

	@SerializedName( "nombre" )
	@Expose
	private String name;

	@SerializedName( "apellidos" )
	@Expose
	private String lastName;

	@SerializedName( "fnac" )
	@Expose
	private Long birthDate;

	@SerializedName( "telefono" )
	@Expose
	private String phone;

	@SerializedName( "movil" )
	@Expose
	private String mobile;

	@SerializedName( "direccion" )
	@Expose
	private String address;

	@SerializedName( "poblacion" )
	@Expose
	private String town;

	@SerializedName( "provincia" )
	@Expose
	private String province;

	@SerializedName( "cp" )
	@Expose
	private String postalCode;

	/**
	 * Get the ID
	 *
	 * @return Long
	 */
	public long getId( ) {
		return id;
	}

	/**
	 * Set the ID
	 *
	 * @param id client ID
	 */
	public void setId( long id ) {
		this.id = id;
	}

	/**
	 * Get the client name
	 *
	 * @return String
	 */
	public String getName( ) {
		return name;
	}

	/**
	 * Set a new name to the client
	 *
	 * @param name client name
	 */
	public void setName( String name ) {
		this.name = name;
	}

	/**
	 * Get the client last name
	 *
	 * @return String
	 */
	public String getLastName( ) {
		return lastName;
	}

	/**
	 * Set a new last name to the client
	 *
	 * @param lastName client last name
	 */
	public void setLastName( String lastName ) {
		this.lastName = lastName;
	}

	/**
	 * Get the client address
	 *
	 * @return String
	 */
	public String getAddress( ) {
		return address;
	}

	/**
	 * Set a new address to the client
	 *
	 * @param address client address
	 */
	public void setAddress( String address ) {
		this.address = address;
	}

	/**
	 * Get the client town
	 *
	 * @return String
	 */
	public String getTown( ) {
		return town;
	}

	/**
	 * Set a new town to the client
	 *
	 * @param town client Town
	 */
	public void setTown( String town ) {
		this.town = town;
	}

	/**
	 * Get the client province
	 *
	 * @return String
	 */
	public String getProvince( ) {
		return province;
	}

	/**
	 * Set a new province to the client
	 *
	 * @param province client province
	 */
	public void setProvince( String province ) {
		this.province = province;
	}

	/**
	 * Get a client postal code
	 *
	 * @return String
	 */
	public String getPostalCode( ) {
		return postalCode;
	}

	/**
	 * Set a new postal code to the client
	 *
	 * @param postalCode client postal code
	 */
	public void setPostalCode( String postalCode ) {
		this.postalCode = postalCode;
	}

	/**
	 * Get the client email
	 *
	 * @return String
	 */
	public String getEmail( ) {
		return email;
	}

	/**
	 * Set a new email to the client
	 *
	 * @param email client email
	 */
	public void setEmail( String email ) {
		this.email = email;
	}

	/**
	 * Get the client birth date in time
	 *
	 * @return Long
	 */
	public Long getBirthDate( ) {
		return birthDate;
	}

	/**
	 * Set a new birth date to the client
	 *
	 * @param birthDate client birthday date
	 */
	public void setBirthDate( Long birthDate ) {
		this.birthDate = birthDate;
	}

	/**
	 * Get the client phone number
	 *
	 * @return String
	 */
	public String getPhone( ) {
		return phone;
	}

	/**
	 * Set a new phone number to a client
	 *
	 * @param phone client phone number
	 */
	public void setPhone( String phone ) {
		this.phone = phone;
	}

	/**
	 * Get the client mobile phone number
	 *
	 * @return String
	 */
	public String getMobile( ) {
		return mobile;
	}

	/**
	 * Set a new mobile number to the client
	 *
	 * @param mobile client mobile phone number
	 */
	public void setMobile( String mobile ) {
		this.mobile = mobile;
	}

	/**
	 * Get a display name for a client
	 *
	 * @return String
	 */
	public String displayName( ) {
		StringBuilder displayName = new StringBuilder();
		displayName.append( name );
		displayName.append( " " );
		displayName.append( lastName );
		return displayName.toString().trim();
	}

	@Override
	public String toString( ) {
		return "ClientDto{" +
				"id=" + id +
				", email='" + email + '\'' +
				", name='" + name + '\'' +
				", lastName='" + lastName + '\'' +
				", birthDate=" + birthDate +
				", phone='" + phone + '\'' +
				", mobile='" + mobile + '\'' +
				", address='" + address + '\'' +
				", town='" + town + '\'' +
				", province='" + province + '\'' +
				", postalCode='" + postalCode + '\'' +
				'}';
	}
}
