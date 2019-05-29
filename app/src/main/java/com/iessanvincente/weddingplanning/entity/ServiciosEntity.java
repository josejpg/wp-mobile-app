package com.iessanvincente.weddingplanning.entity;

public class ServiciosEntity {

    private long id;
    private String nombre;

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
    public void setId( long id ) {
        this.id = id;
    }

    /**
     * Get the service name
     *
     * @return String
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Set the service name
     *
     * @param nombre service name
     */
    public void setNombre( String nombre ) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "ServiciosEntity{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
