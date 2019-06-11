package com.iessanvincente.weddingplanning.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * @author Jose J. Pardines
 */
public class MessageDto implements Serializable {

	@SerializedName( "event" )
	@Expose
	private Long id;

	@SerializedName( "message" )
	@Expose
	private String message;

	@SerializedName( "date" )
	@Expose
	private Long date;

	@SerializedName( "owner" )
	@Expose
	private Boolean owner;

	@SerializedName( "event" )
	@Expose
	private EventDto event;

	@SerializedName( "providers" )
	@Expose
	private List<ProviderDto> providers;

	@SerializedName( "clients" )
	@Expose
	private List<ClientDto> clients;

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
	public String getMessage( ) {
		return message;
	}

	/**
	 * Set the message
	 *
	 * @param message text
	 */
	public void setMessage( String message ) {
		this.message = message;
	}

	/**
	 * Get the date
	 *
	 * @return Date
	 */
	public Long getDate( ) {
		return date;
	}

	/**
	 * Set the date
	 *
	 * @param date from message
	 */
	public void setDate( Long date ) {
		this.date = date;
	}

	/**
	 * Get the owner boolean
	 *
	 * @return Boolean
	 */
	public Boolean getOwner( ) {
		return owner;
	}

	/**
	 * Set the owner
	 *
	 * @param owner from message
	 */
	public void setOwner( Boolean owner ) {
		this.owner = owner;
	}

	/**
	 * Get the data event
	 *
	 * @return EventDto
	 */
	public EventDto getEvent( ) {
		return event;
	}

	/**
	 * Set the event
	 *
	 * @param event from message
	 */
	public void setEvent( EventDto event ) {
		this.event = event;
	}

	/**
	 * Get the list providers
	 *
	 * @return List<ProviderDto>
	 */
	public List<ProviderDto> getProviders( ) {
		return providers;
	}

	/**
	 * Set the providers list
	 *
	 * @param providers
	 */
	public void setProviders( List<ProviderDto> providers ) {
		this.providers = providers;
	}

	/**
	 * Get the list clients
	 *
	 * @return List<ClientDto>
	 */
	public List<ClientDto> getClients( ) {
		return clients;
	}

	/**
	 * Set the clients list
	 *
	 * @param clients
	 */
	public void setClients( List<ClientDto> clients ) {
		this.clients = clients;
	}

	@Override
	public String toString( ) {
		return "MessageDto{" +
				"id=" + id +
				", message='" + message + '\'' +
				", date=" + date +
				", owner=" + owner +
				", event=" + event +
				", providers=" + providers +
				", clients=" + clients +
				'}';
	}
}
