package com.iessanvincente.weddingplanning.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Set;

/**
 * @author Jose J. Pardines
 */
public class ClientDto implements Serializable {

	@SerializedName( "id" )
	@Expose
	private Long id;

	@SerializedName( "dni" )
	@Expose
	private String dni;

	@SerializedName( "email" )
	@Expose
	private String email;

	@SerializedName( "name" )
	@Expose
	private String name;

	@SerializedName( "lastName" )
	@Expose
	private String lastName;

	@SerializedName( "birthDate" )
	@Expose
	private Long birthDate;

	@SerializedName( "phone" )
	@Expose
	private String phone;

	@SerializedName( "mobile" )
	@Expose
	private String mobile;

	@SerializedName( "address" )
	@Expose
	private String address;

	@SerializedName( "town" )
	@Expose
	private String town;

	@SerializedName( "province" )
	@Expose
	private String province;

	@SerializedName( "postalCode" )
	@Expose
	private String postalCode;

	@SerializedName( "events" )
	@Expose
	private Set<EventDto> events;

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
	 * @param id client ID
	 */
	public void setId( Long id ) {
		this.id = id;
	}

	/**
	 * Get the client dni
	 *
	 * @return String
	 */
	public String getDni( ) {
		return dni;
	}

	/**
	 * Set a new dni to the client
	 *
	 * @param dni client name
	 */
	public void setDni( String dni ) {
		this.dni = dni;
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
	 * Get the client events
	 *
	 * @return EventDto
	 */
	public Set<EventDto> getEvents( ) {
		return events;
	}

	/**
	 * Set events to client
	 *
	 * @param events client
	 */
	public void setEvents( Set<EventDto> events ) {
		this.events = events;
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
				", dni='" + dni + '\'' +
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
				", events='" + events + '\'' +
				'}';
	}
}
