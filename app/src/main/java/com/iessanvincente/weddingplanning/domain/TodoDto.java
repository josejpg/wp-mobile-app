package com.iessanvincente.weddingplanning.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * @author Jose J. Pardines
 */
public class TodoDto implements Serializable, Comparable<TodoDto> {

	@SerializedName( "id" )
	@Expose
	private Long id;

	@SerializedName( "todo" )
	@Expose
	private String todo;

	@SerializedName( "date" )
	@Expose
	private Long date;

	@SerializedName( "done" )
	@Expose
	private Boolean done;

	@SerializedName( "event" )
	@Expose
	private EventDto event;

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
	 * Get the todo name
	 *
	 * @return String
	 */
	public String getTodo( ) {
		return todo;
	}

	/**
	 * Set the todo name
	 *
	 * @param todo todo name
	 */
	public void setTodo( String todo ) {
		this.todo = todo;
	}

	/**
	 * Get the todo date
	 *
	 * @return Long
	 */
	public Long getDate( ) {
		return date;
	}

	/**
	 * Set the todo date
	 *
	 * @param date todo date
	 */
	public void setDate( Long date ) {
		this.date = date;
	}

	/**
	 * Get the todo done
	 *
	 * @return Integer
	 */
	public Boolean getDone( ) {
		return done;
	}

	/**
	 * Set the todo done
	 *
	 * @param done todo done
	 */
	public void setDone( Boolean done ) {
		this.done = done;
	}
	/**
	 * Get the todo event
	 *
	 * @return EventDto
	 */
	public EventDto getEvent( ) {
		return event;
	}

	/**
	 * Set the todo event
	 *
	 * @param event todo event
	 */
	public void setEvent( EventDto event ) {
		this.event = event;
	}

	@Override
	public String toString( ) {
		return "TodoDto{" +
				"id=" + id +
				", todo='" + todo + '\'' +
				", date=" + date +
				", done=" + done +
				", event=" + event +
				'}';
	}

	@Override
	public int compareTo( TodoDto o ) {
		return (int) ( this.date - o.getDate() );
	}
}
