package com.iessanvincente.weddingplanning.entity;

import java.util.List;

public class MensajesEntity {
    private long id;
    private String mensaje;
    private Integer fecha;
    private Boolean propietario;
    private EventosEntity evento;
    private List<ProveedoresEntity> proveedores;
    private List<ClientesEntity> clientes;

    /**
     * Get the ID
     *
     * @return Long
     */
    public long getId() {
        return id;
    }

    /**
     * Set the ID
     *
     * @param id provider ID
     */
    public void setId(long id) {
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
    public List<ProveedoresEntity> getProveedores() {
        return proveedores;
    }

    /**
     * Set the providers list
     *
     * @param proveedores
     */
    public void setProveedores(List<ProveedoresEntity> proveedores) {
        this.proveedores = proveedores;
    }

    /**
     * Get the list clients
     *
     * @return List<ClientesEntity>
     */
    public List<ClientesEntity> getClientes() {
        return clientes;
    }

    /**
     * Set the clients list
     *
     * @param clientes
     */
    public void setClientes(List<ClientesEntity> clientes) {
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
