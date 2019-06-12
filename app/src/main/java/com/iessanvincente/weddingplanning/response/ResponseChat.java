package com.iessanvincente.weddingplanning.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.iessanvincente.weddingplanning.entity.ChatsEntity;
import com.iessanvincente.weddingplanning.entity.EventosEntity;

import java.io.Serializable;
import java.util.Set;

/**
 * @author Jose J. Pardines
 */
public class ResponseChat implements Serializable {
	@SerializedName( "ok" )
	@Expose
	Boolean ok;

	@SerializedName( "ko" )
	@Expose
	Boolean ko;

	@SerializedName( "error" )
	@Expose
	String error;

	@SerializedName( "chats" )
	@Expose
	Set<ChatsEntity> chats;

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

	public Set<ChatsEntity> getChats( ) {
		return chats;
	}

	public void setChats( Set<ChatsEntity> chats ) {
		this.chats = chats;
	}


	@Override
	public String toString( ) {
		return "ResponseChat{" +
				"ok=" + ok +
				", ko=" + ko +
				", error='" + error + '\'' +
				", chats=" + chats +
				'}';
	}
}
