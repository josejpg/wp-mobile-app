package com.iessanvincente.weddingplanning.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Set;

public class MensajesEntity implements Serializable {

    @SerializedName( "id" )
    @Expose
    private Long id;

    @SerializedName( "mensaje" )
    @Expose
    private String mensaje;

    @SerializedName( "fecha" )
    @Expose
    private Integer fecha;

    @SerializedName( "propietario" )
    @Expose
    private Boolean propietario;

    @SerializedName( "evento" )
    @Expose
    private EventosEntity evento;

    @SerializedName( "proveedores" )
    @Expose
    private Set<ProveedoresEntity> proveedores;

    @SerializedName( "clientes" )
    @Expose
    private Set<ClientesEntity> clientes;

    /**
     * Get the ID
     *
     * @return Long
     */
    public Long getId() {
        return id;
    }

    /**
     * Set the ID
     *
     * @param id provider ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get the message
     *
     * @return String
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * Set the message
     *
     * @param mensaje text
     */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    /**
     * Get the date
     *
     * @return Date
     */
    public Integer getFecha() {
        return fecha;
    }

    /**
     * Set the date
     *
     * @param fecha from message
     */
    public void setFecha(Integer fecha) {
        this.fecha = fecha;
    }

    /**
     * Get the owner boolean
     *
     * @return Boolean
     */
    public Boolean getPropietario() {
        return propietario;
    }

    /**
     * Set the owner
     *
     * @param propietario from message
     */
    public void setPropietario(Boolean propietario) {
        this.propietario = propietario;
    }

    /**
     * Get the data event
     *
     * @return EventosEntity
     */
    public EventosEntity getEvento() {
        return evento;
    }

    /**
     * Set the event
     *
     * @param evento from message
     */
    public void setEvento(EventosEntity evento) {
        this.evento = evento;
    }

    /**
     * Get the list providers
     *
     * @return List<ProveedoresEntity>
     */
    public Set<ProveedoresEntity> getProveedores() {
        return proveedores;
    }

    /**
     * Set the providers list
     *
     * @param proveedores providers list
     */
    public void setProveedores(Set<ProveedoresEntity> proveedores) {
        this.proveedores = proveedores;
    }

    /**
     * Get the list clients
     *
     * @return List<ClientesEntity>
     */
    public Set<ClientesEntity> getClientes() {
        return clientes;
    }

    /**
     * Set the clients list
     *
     * @param clientes clients list
     */
    public void setClientes(Set<ClientesEntity> clientes) {
        this.clientes = clientes;
    }

    @Override
    public String toString() {
        return "MensajesEntity{" +
                "id=" + id +
                ", mensaje='" + mensaje + '\'' +
                ", fecha=" + fecha +
                ", propietario=" + propietario +
                ", evento=" + evento +
                ", proveedores=" + proveedores +
                ", clientes=" + clientes +
                '}';
    }
}
