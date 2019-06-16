package com.iessanvincente.weddingplanning.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Set;

/**
 * @author Jose J. Pardines
 */
public class ProviderDto implements Serializable {

	@SerializedName( "id" )
	@Expose
	private Long id;

	@SerializedName( "email" )
	@Expose
	private String email;

	@SerializedName( "name" )
	@Expose
	private String name;

	@SerializedName( "cif" )
	@Expose
	private String cif;

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

	@SerializedName( "services" )
	@Expose
	private Set<ServiceDto> services;

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
	public String getName( ) {
		return name;
	}

	/**
	 * Set a new name to the provider
	 *
	 * @param name provider name
	 */
	public void setName( String name ) {
		this.name = name;
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
	public String getAddress( ) {
		return address;
	}

	/**
	 * Set a new address to a provider
	 *
	 * @param address provider address
	 */
	public void setAddress( String address ) {
		this.address = address;
	}

	/**
	 * Get the provider town
	 *
	 * @return String
	 */
	public String getTown( ) {
		return town;
	}

	/**
	 * Se a new town to the provider
	 *
	 * @param town provider town
	 */
	public void setTown( String town ) {
		this.town = town;
	}

	/**
	 * Get the provider province
	 *
	 * @return String
	 */
	public String getProvince( ) {
		return province;
	}

	/**
	 * Set a new province to the provider
	 *
	 * @param province province town
	 */
	public void setProvince( String province ) {
		this.province = province;
	}

	/**
	 * Get the provider postal code
	 *
	 * @return String
	 */
	public String getPostalCode( ) {
		return postalCode;
	}

	/**
	 * Set a new postal code
	 *
	 * @param postalCode provider postal code
	 */
	public void setPostalCode( String postalCode ) {
		this.postalCode = postalCode;
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
	 * Get the provider phone
	 *
	 * @return String
	 */
	public String getPhone( ) {
		return phone;
	}

	/**
	 * Set a new phone to the provider
	 *
	 * @param phone phone number
	 */
	public void setPhone( String phone ) {
		this.phone = phone;
	}

	/**
	 * Get the provider mobile phone
	 *
	 * @return String
	 */
	public String getMobile( ) {
		return mobile;
	}

	/**
	 * Set a new mobile to the provider
	 *
	 * @param mobile mobile number
	 */
	public void setMobile( String mobile ) {
		this.mobile = mobile;
	}

	/**
	 * Get the list of services associates to the provider
	 *
	 * @return List<ServiceDto>
	 */
	public Set<ServiceDto> getServices( ) {
		return services;
	}

	/**
	 * Set a list of services associates to the provider
	 *
	 * @param services list
	 */
	public void setServices( Set<ServiceDto> services ) {
		this.services = services;
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
	 * Get name to display
	 *
	 * @return string with name
	 */
	public String displayName( ) {
		StringBuilder displayName = new StringBuilder();
		if ( cif != null &&
				!cif.isEmpty() &&
				( name == null ||
						name.isEmpty() )
		) {
			displayName.append( cif );
		}
		if ( name != null &&
				!name.isEmpty() ) {
			displayName.append( name );
		}
		return displayName.toString().trim();
	}

	/**
	 * Get phones to display
	 *
	 * @return string with phones
	 */
	public String displayPhone( ) {
		StringBuilder displayPhone = new StringBuilder();
		if ( phone != null &&
				!phone.isEmpty() ) {
			displayPhone.append( "Teléfono: " );
			displayPhone.append( phone );
		}
		if ( phone != null &&
				mobile != null &&
				!phone.isEmpty() &&
				!mobile.isEmpty() ) {
			displayPhone.append( " / " );
		}
		if ( mobile != null &&
				!mobile.isEmpty() ) {
			displayPhone.append( "Móvil: " );
			displayPhone.append( mobile );
		}
		return displayPhone.toString().trim();
	}

	@Override
	public String toString( ) {
		return "ProviderDto{" +
				"id=" + id +
				", name='" + name + '\'' +
				", cif='" + cif + '\'' +
				", address='" + address + '\'' +
				", town='" + town + '\'' +
				", province='" + province + '\'' +
				", postalCode='" + postalCode + '\'' +
				", email='" + email + '\'' +
				", phone='" + phone + '\'' +
				", mobile='" + mobile + '\'' +
				", services=" + services +
				", events=" + events +
				'}';
	}
}
