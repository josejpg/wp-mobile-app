package com.iessanvincente.weddingplanning.domain;

import java.util.List;

public class ProviderDto {
    private long id;
    private String name;
    private String cif;
    private String address;
    private String town;
    private String province;
    private String postalCode;
    private String email;
    private String password;
    private String phone;
    private String mobile;
    private List<ServiceDto> services;

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
    public void setId( long id ) {
        this.id = id;
    }

    /**
     * Get the provider name
     *
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * Set a new name to the provider
     *
     * @param name provider name
     */
    public void setName( String name ) {
        this.name = name;
    }

    /**
     * Get the CIF
     *
     * @return String
     */
    public String getCif() {
        return cif;
    }

    /**
     * Set a new CIF to the provider
     *
     * @param cif provider CIF
     */
    public void setCif( String cif ) {
        this.cif = cif;
    }

    /**
     * Get the provider address
     *
     * @return String
     */
    public String getAddress() {
        return address;
    }

    /**
     * Set a new address to a provider
     *
     * @param address provider address
     */
    public void setAddress( String address ) {
        this.address = address;
    }

    /**
     * Get the provider town
     *
     * @return String
     */
    public String getTown() {
        return town;
    }

    /**
     * Se a new town to the provider
     *
     * @param town provider town
     */
    public void setTown( String town ) {
        this.town = town;
    }

    /**
     * Get the provider province
     *
     * @return String
     */
    public String getProvince() {
        return province;
    }

    /**
     * Set a new province to the provider
     *
     * @param province province town
     */
    public void setProvince( String province ) {
        this.province = province;
    }

    /**
     * Get the provider postal code
     *
     * @return String
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * Set a new postal code
     *
     * @param postalCode provider postal code
     */
    public void setPostalCode( String postalCode ) {
        this.postalCode = postalCode;
    }

    /**
     * Get the provider email
     *
     * @return String
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set a new email to the provider
     *
     * @param email provider email
     */
    public void setEmail( String email ) {
        this.email = email;
    }

    /**
     * Get the provider password
     *
     * @return String
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set a new password to the provider
     *
     * @param password provider password
     */
    public void setPassword( String password ) {
        this.password = password;
    }

    /**
     * Get the provider phone
     *
     * @return String
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Set a new phone to the provider
     *
     * @param phone phone number
     */
    public void setPhone( String phone ) {
        this.phone = phone;
    }

    /**
     * Get the provider mobile phone
     *
     * @return String
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * Set a new mobile to the provider
     *
     * @param mobile mobile number
     */
    public void setMobile( String mobile ) {
        this.mobile = mobile;
    }

    /**
     * Get the list of services associates to the provider
     *
     * @return List<ServiceDto>
     */
    public List<ServiceDto> getServices() {
        return services;
    }

    /**
     * Set a list of services associates to the provider
     *
     * @param services list
     */
    public void setServices(List<ServiceDto> services) {
        this.services = services;
    }

    @Override
    public String toString() {
        return "ProviderDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cif='" + cif + '\'' +
                ", address='" + address + '\'' +
                ", town='" + town + '\'' +
                ", province='" + province + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", mobile='" + mobile + '\'' +
                ", services=" + services +
                '}';
    }
}
