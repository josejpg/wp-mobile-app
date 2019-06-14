package com.iessanvincente.weddingplanning.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * @author Jose J. Pardines
 */
public class MessageDto implements Serializable, Comparable<MessageDto> {

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

	@SerializedName( "provider" )
	@Expose
	private ProviderDto provider;

	@SerializedName( "client" )
	@Expose
	private ClientDto client;

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
	 * Get the provider
	 *
	 * @return ProviderDto
	 */
	public ProviderDto getProvider( ) {
		return provider;
	}

	/**
	 * Set the providers
	 *
	 * @param provider
	 */
	public void setProvider( ProviderDto provider ) {
		this.provider = provider;
	}

	/**
	 * Get the clients
	 *
	 * @return ClientDto
	 */
	public ClientDto getClient( ) {
		return client;
	}

	/**
	 * Set the clients
	 *
	 * @param client
	 */
	public void setClient( ClientDto client ) {
		this.client = client;
	}

	@Override
	public String toString( ) {
		return "MessageDto{" +
				"id=" + id +
				", message='" + message + '\'' +
				", date=" + date +
				", owner=" + owner +
				", event=" + event +
				", provider=" + provider +
				", client=" + client +
				'}';
	}

	@Override
	public int compareTo( MessageDto o ) {
		return (int) ( this.date - o.getDate() );
	}
}
