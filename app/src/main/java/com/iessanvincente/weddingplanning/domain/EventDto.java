package com.iessanvincente.weddingplanning.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Set;

public class EventDto implements Serializable {

	@SerializedName( "id" )
	@Expose
	private Long id;

	@SerializedName( "event" )
	@Expose
	private String event;

	@SerializedName( "description" )
	@Expose
	private String description;

	@SerializedName( "date" )
	@Expose
	private Long date;

	@SerializedName( "active" )
	@Expose
	private Boolean active;

	@SerializedName( "providers" )
	@Expose
	private Set<ProviderDto> providers;

	@SerializedName( "clients" )
	@Expose
	private Set<ClientDto> clients;

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
	public String getEvent( ) {
		return event;
	}

	/**
	 * Set the event name
	 *
	 * @param event event name
	 */
	public void setEvent( String event ) {
		this.event = event;
	}

	/**
	 * Get the event description
	 *
	 * @return String
	 */
	public String getDescription( ) {
		return description;
	}

	/**
	 * Set the event description
	 *
	 * @param description event description
	 */
	public void setDescription( String description ) {
		this.description = description;
	}

	/**
	 * Get the event date
	 *
	 * @return Long
	 */
	public Long getDate( ) {
		return date;
	}

	/**
	 * Set the date
	 *
	 * @param date event date
	 */
	public void setDate( Long date ) {
		this.date = date;
	}

	/**
	 * Get the active status
	 *
	 * @return Boolean
	 */
	public Boolean getActive( ) {
		return active;
	}

	/**
	 * Set the active status
	 *
	 * @param active status
	 */
	public void setActive( Boolean active ) {
		this.active = active;
	}

	/**
	 * Get the list of provider associates to the event
	 *
	 * @return List<ProviderDto>
	 */
	public Set<ProviderDto> getProviders( ) {
		return providers;
	}

	/**
	 * Set a list of providers associates to the event
	 *
	 * @param providers list
	 */
	public void setProviders( Set<ProviderDto> providers ) {
		this.providers = providers;
	}

	/**
	 * Get the list of clients associates to the event
	 *
	 * @return Set<ClientDto>
	 */
	public Set<ClientDto> getClients( ) {
		return clients;
	}

	/**
	 * Set a list of clients associates to the event
	 *
	 * @param clients list
	 */
	public void setClients( Set<ClientDto> clients ) {
		this.clients = clients;
	}

	@Override
	public String toString( ) {
		return "EventDto{" +
				"id=" + id +
				", event='" + event + '\'' +
				", date=" + date +
				", active=" + active +
				", providers=" + providers +
				", clients=" + clients +
				'}';
	}
}
