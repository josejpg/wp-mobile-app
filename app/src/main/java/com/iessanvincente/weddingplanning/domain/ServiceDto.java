package com.iessanvincente.weddingplanning.domain;

public class ServiceDto {

    private long id;
    private String service;

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
    public String getService() {
        return service;
    }

    /**
     * Set the service name
     *
     * @param service service name
     */
    public void setService( String service ) {
        this.service = service;
    }

    @Override
    public String toString() {
        return "ServiceDto{" +
                "id=" + id +
                ", service='" + service + '\'' +
                '}';
    }
}
