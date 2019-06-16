package com.iessanvincente.weddingplanning.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.iessanvincente.weddingplanning.entity.TodoEntity;

import java.io.Serializable;
import java.util.Set;

/**
 * @author Jose J. Pardines
 */
public class ResponseTodo implements Serializable {
	@SerializedName( "ok" )
	@Expose
	Boolean ok;

	@SerializedName( "ko" )
	@Expose
	Boolean ko;

	@SerializedName( "error" )
	@Expose
	String error;

	@SerializedName( "todoList" )
	@Expose
	Set<TodoEntity> todoList;

	public Boolean getOk( ) {
		return ok;
	}

	public void setOk( Boolean ok ) {
		this.ok = ok;
	}

	public Boolean getKo( ) {
		return ko;
	}

	public void setKo( Boolean ko ) {
		this.ko = ko;
	}

	public String getError( ) {
		return error;
	}

	public void setError( String error ) {
		this.error = error;
	}

	public Set<TodoEntity> getTodoList( ) {
		return todoList;
	}

	public void setTodoList( Set<TodoEntity> todoList ) {
		this.todoList = todoList;
	}


	@Override
	public String toString( ) {
		return "ResponseChat{" +
				"ok=" + ok +
				", ko=" + ko +
				", error='" + error + '\'' +
				", todoList=" + todoList +
				'}';
	}
}
