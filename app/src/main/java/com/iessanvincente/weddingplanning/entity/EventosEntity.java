package com.iessanvincente.weddingplanning.entity;

import java.util.List;

public class EventosEntity {
    private long id;
    private String nombre;
    private String descripcion;
    private Integer fecha;
    private Boolean activo;
    private List<ProveedoesrEntity> proveedores;
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
     * @param id event ID
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Get the event name
     *
     * @return String
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Set the event name
     *
     * @param nombre event name
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Get the event name
     *
     * @return String
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Set the event name
     *
     * @param descripcion event name
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Get the event date
     *
     * @return Integer
     */
    public Integer getFecha() {
        return fecha;
    }

    /**
     * Set the date
     *
     * @param fecha event date
     */
    public void setFecha(Integer fecha) {
        this.fecha = fecha;
    }

    /**
     * Get the active status
     *
     * @return Boolean
     */
    public Boolean getActivo() {
        return activo;
    }

    /**
     * Set the active status
     *
     * @param activo status
     */
    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    /**
     * Get the list of provider associates to the event
     *
     * @return List<ProveedoesrEntity>
     */
    public List<ProveedoesrEntity> getProveedores() {
        return proveedores;
    }

    /**
     * Set a list of providers associates to the event
     *
     * @param proveedores list
     */
    public void setProveedores(List<ProveedoesrEntity> proveedores) {
        this.proveedores = proveedores;
    }

    /**
     * Get the list of clients associates to the event
     *
     * @return List<ClientesEntity>
     */
    public List<ClientesEntity> getClientes() {
        return clientes;
    }

    /**
     * Set a list of clients associates to the event
     *
     * @param clientes list
     */
    public void setClientes(List<ClientesEntity> clientes) {
        this.clientes = clientes;
    }

    @Override
    public String toString() {
        return "EventosEntity{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", fecha=" + fecha +
                ", activo=" + activo +
                ", proveedores=" + proveedores +
                ", clientes=" + clientes +
                '}';
    }
}
