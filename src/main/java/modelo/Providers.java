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

    public Providers() {
    }

    public Providers(String ubication) {
        this.ubication = ubication;
    }

    public Providers(String id, String name) {
        this.id = id;
        this.name = name;
    }

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
