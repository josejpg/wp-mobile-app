package com.iessanvincente.weddingplanning.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ClientesEntity implements Serializable {

    @SerializedName( "id" )
    @Expose
    private Long id;

    @SerializedName( "dni" )
    @Expose
    private String dni;

    @SerializedName( "email" )
    @Expose
    private String email;

    @SerializedName( "password" )
    @Expose
    private String password;

    @SerializedName( "nombre" )
    @Expose
    private String nombre;

    @SerializedName( "apellidos" )
    @Expose
    private String apellidos;

    @SerializedName( "fnac" )
    @Expose
    private Long fnac;

    @SerializedName( "telefono" )
    @Expose
    private String telefono;

    @SerializedName( "movil" )
    @Expose
    private String movil;

    @SerializedName( "direccion" )
    @Expose
    private String direccion;

    @SerializedName( "poblacion" )
    @Expose
    private String poblacion;

    @SerializedName( "provincia" )
    @Expose
    private String provincia;

    @SerializedName( "cp" )
    @Expose
    private String cp;

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
     * @param id client ID
     */
    public void setId( Long id ) {
        this.id = id;
    }

    /**
     * Get the client dni
     *
     * @return String
     */
    public String getDni() {
        return dni;
    }

    /**
     * Set a new dni to the client
     *
     * @param dni client dni
     */
    public void setDni( String dni ) {
        this.dni = dni;
    }

    /**
     * Get the client name
     *
     * @return String
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Set a new name to the client
     *
     * @param nombre client name
     */
    public void setNombre( String nombre ) {
        this.nombre = nombre;
    }

    /**
     * Get the client last name
     *
     * @return String
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * Set a new last name to the client
     *
     * @param apellidos client last name
     */
    public void setApellidos( String apellidos ) {
        this.apellidos = apellidos;
    }

    /**
     * Get the client address
     *
     * @return String
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Set a new address to the client
     *
     * @param direccion client address
     */
    public void setDireccion( String direccion ) {
        this.direccion = direccion;
    }

    /**
     * Get the client town
     *
     * @return String
     */
    public String getPoblacion() {
        return poblacion;
    }

    /**
     * Set a new town to the client
     *
     * @param poblacion client Town
     */
    public void setPoblacion( String poblacion ) {
        this.poblacion = poblacion;
    }

    /**
     * Get the client province
     *
     * @return String
     */
    public String getProvincia() {
        return provincia;
    }

    /**
     * Set a new province to the client
     *
     * @param provincia client province
     */
    public void setProvincia( String provincia ) {
        this.provincia = provincia;
    }

    /**
     * Get a client postal code
     *
     * @return String
     */
    public String getCp() {
        return cp;
    }

    /**
     * Set a new postal code to the client
     *
     * @param cp client postal code
     */
    public void setCp( String cp ) {
        this.cp = cp;
    }

    /**
     * Get the client email
     *
     * @return String
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set a new email to the client
     *
     * @param email client email
     */
    public void setEmail( String email ) {
        this.email = email;
    }

    /**
     * Get the client password
     *
     * @return String
     */
    public String getPassword() { return password; }

    /**
     * Set a new password to the client
     *
     * @param password client password
     */
    public void setPassword( String password ) { this.password = password; }

    /**
     * Get the client birth date in time
     *
     * @return Long
     */
    public Long getFnac() {
        return fnac;
    }

    /**
     * Set a new birth date to the client
     *
     * @param fnac client birthday date
     */
    public void setFnac( Long fnac ) {
        this.fnac = fnac;
    }

    /**
     * Get the client phone number
     *
     * @return String
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Set a new phone number to a client
     *
     * @param telefono client phone number
     */
    public void setTelefono( String telefono ) {
        this.telefono = telefono;
    }

    /**
     * Get the client mobile phone number
     *
     * @return String
     */
    public String getMovil() {
        return movil;
    }

    /**
     * Set a new mobile number to the client
     *
     * @param movil client mobile phone number
     */
    public void setMovil( String movil ) {
        this.movil = movil;
    }

    @Override
    public String toString() {
        return "ClientesEntity{" +
                "id=" + id +
                ", dni='" + dni + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", fnac=" + fnac +
                ", telefono='" + telefono + '\'' +
                ", movil='" + movil + '\'' +
                ", direccion='" + direccion + '\'' +
                ", poblacion='" + poblacion + '\'' +
                ", provincia='" + provincia + '\'' +
                ", cp='" + cp + '\'' +
                '}';
    }
}
