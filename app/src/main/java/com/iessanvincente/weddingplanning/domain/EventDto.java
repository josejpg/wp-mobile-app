package com.iessanvincente.weddingplanning.domain;

import java.util.List;

public class EventDto {
    private long id;
    private String event;
    private Integer date;
    private Boolean active;
    private List<ProviderDto> providers;
    private List<ClientDto> clients;

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
    public String getEvent() {
        return event;
    }

    /**
     * Set the event name
     *
     * @param event event name
     */
    public void setEvent(String event) {
        this.event = event;
    }

    /**
     * Get the event date
     *
     * @return Integer
     */
    public Integer getDate() {
        return date;
    }

    /**
     * Set the date
     *
     * @param date event date
     */
    public void setDate(Integer date) {
        this.date = date;
    }

    /**
     * Get the active status
     *
     * @return Boolean
     */
    public Boolean getActive() {
        return active;
    }

    /**
     * Set the active status
     *
     * @param active status
     */
    public void setActive(Boolean active) {
        this.active = active;
    }

    /**
     * Get the list of provider associates to the event
     *
     * @return List<ProviderDto>
     */
    public List<ProviderDto> getProviders() {
        return providers;
    }

    /**
     * Set a list of providers associates to the event
     *
     * @param providers list
     */
    public void setProviders(List<ProviderDto> providers) {
        this.providers = providers;
    }

    /**
     * Get the list of clients associates to the event
     *
     * @return List<ClientDto>
     */
    public List<ClientDto> getClients() {
        return clients;
    }

    /**
     * Set a list of clients associates to the event
     *
     * @param clients list
     */
    public void setClients(List<ClientDto> clients) {
        this.clients = clients;
    }

    @Override
    public String toString() {
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
