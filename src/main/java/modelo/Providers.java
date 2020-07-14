/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author jabre
 */
public class Providers {
    
     /**
     *
     * Atributos necesarios para la implementación de los métodos de la clase
     */

    private String id;
    private char user;
    private String name;
    private String company;
    private Address address;
    private int phoneNumber;
    private String email;
    private String schedule;
    private String ubication;
    private String profile;
    private int qualification;
    
    /**
     *
     * Constructor de clase vacío
     */

    public Providers() {
    }
    
     /**
     *
     * Constructor de clase
     * @param ubication Parametro que da como entrada la ubicación del proveedor
     */

    public Providers(String ubication) {
        this.ubication = ubication;
    }
    
     /**
     *
     * Constructor de clase
     * @param id Parametro que da como entrada el codigo del proveedor
     * @param name Parametro que da como entrada el nombre del proveedor
     */

    public Providers(String id, String name) {
        this.id = id;
        this.name = name;
    }
    
     /**
     *
     * Constructor de clase
     * @param id Parametro que da como entrada a provincia
     * @param user Parametro que da como entrada el canton
     * @param name Parametro que da como entrada el distrito
     * @param company Parametro que da como entrada los detalles
     * @param address Parametro que da como entrada la dirección
     * @param phoneNumber Parametro que da como entrada el numero telefonico
     * @param email Parametro que da como entrada el correo electronico
     * @param schedule Parametro que da como entrada el horario
     * @param ubication Parametro que da como entrada la ubicacion
     * @param profile Parametro que da como entrada el perfil
     * @param qualification parametro que da como entrada la calificacion
     */

    public Providers(String id, char user, String name, String company, Address address, int phoneNumber, String email, String schedule, String ubication, String profile, int qualification) {
        this.id = id;
        this.user = user;
        this.name = name;
        this.company = company;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.schedule = schedule;
        this.ubication = ubication;
        this.profile = profile;
        this.qualification = qualification;
    }
    
    /**
     *
     * Constructor de clase
     * @param user Parametro que da como entrada el canton
     */

    public Providers(char user) {
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getUbication() {
        return ubication;
    }

    public void setUbication(String ubication) {
        this.ubication = ubication;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public int getQualification() {
        return qualification;
    }

    public void setQualification(int qualification) {
        this.qualification = qualification;
    }

    public char getUser() {
        return user;
    }

    public void setUser(char user) {
        this.user = user;
    }

}
