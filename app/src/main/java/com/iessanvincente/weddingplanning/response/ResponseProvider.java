package com.iessanvincente.weddingplanning.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.iessanvincente.weddingplanning.entity.ProveedoresEntity;

import java.io.Serializable;
import java.util.Set;

/**
 * @author Jose J. Pardines
 */
public class ResponseProvider implements Serializable {
	@SerializedName( "ok" )
	@Expose
	Boolean ok;

	@SerializedName( "ko" )
	@Expose
	Boolean ko;

	@SerializedName( "error" )
	@Expose
	String error;

	@SerializedName( "token" )
	@Expose
	String token;

	@SerializedName( "providers" )
	@Expose
	Set<ProveedoresEntity> providers;

	@SerializedName( "provider" )
	@Expose
	ProveedoresEntity provider;

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

	public Set<ProveedoresEntity> getProviders( ) {
		return providers;
	}

	public void setProviders( Set<ProveedoresEntity> providers ) {
		this.providers = providers;
	}

	public ProveedoresEntity getProvider( ) {
		return provider;
	}

	public void setProvider( ProveedoresEntity provider ) {
		this.provider = provider;
	}

	@Override
	public String toString( ) {
		return "ResponseEvent{" +
				"ok=" + ok +
				", ko=" + ko +
				", error='" + error + '\'' +
				", token='" + token + '\'' +
				", provider=" + provider +
				", providers=" + providers +
				'}';
	}
}
