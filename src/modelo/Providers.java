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

    private String id;
    private String name;
    private String company;
    private String canton;
    private String provincia;
    private String distrito;
    private String señas;
    private int phoneNumber;
    private String email;
    private String schedule;
    private int ubication;
    private String profile;
    private int qualification;

    public Providers() {

    }

    public Providers(String id, String name, String company, String canton, String provincia, String distrito, String señas, int phoneNumber, String email, String schedule, int ubication, String profile, int qualification) {
        this.id = id;
        this.name = name;
        this.company = company;
        this.canton = canton;
        this.provincia = provincia;
        this.distrito = distrito;
        this.señas = señas;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.schedule = schedule;
        this.ubication = ubication;
        this.profile = profile;
        this.qualification = qualification;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
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

    public String getCanton() {
        return canton;
    }

    public void setCanton(String canton) {
        this.canton = canton;
    }

    public String getSeñas() {
        return señas;
    }

    public void setSeñas(String señas) {
        this.señas = señas;
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

    public int getUbication() {
        return ubication;
    }

    public void setUbication(int ubication) {
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

}
