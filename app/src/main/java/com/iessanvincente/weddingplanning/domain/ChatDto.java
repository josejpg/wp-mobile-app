package com.iessanvincente.weddingplanning.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * @author Jose J. Pardines
 */
public class ChatDto implements Serializable {

	@SerializedName( "event" )
	@Expose
	private Long id;

	@SerializedName( "event" )
	@Expose
	private EventDto event;

	@SerializedName( "provider" )
	@Expose
	private ProviderDto provider;

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

	@Override
	public String toString( ) {
		return "ChatDto{" +
				"id=" + id +
				", event=" + event +
				", provider=" + provider +
				'}';
	}
}
