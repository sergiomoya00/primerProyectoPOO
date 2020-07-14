/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author samoy
 */
public class Client {
    
     /**
     *
     * Atributos necesarios para la implementación de los métodos de la clase
     */

    private String name;
    private String password;
    private int identification;
    private String email;
    private int telephone;
    private Address address;
    private String location;
    
    /**
     *
     * Constructor de clase
     * @param name Parametro que da como entrada el nombre del proveedor
     * @param password Parametro que da como entrada la contraseña
     * @param identification Parametro que da como entrada el identificador
     * @param email Parametro que da como entrada el correo
     * @param telephone Parametro que da como entrada el numero de telefono
     * @param address Parametro que da como entrada la direccion
     * @param location Parametro que da como entrada la ubicacion
     */

    public Client(String name, String password, int identification, String email, int telephone, Address address, String location) {
        this.name = name;
        this.password = password;
        this.identification = identification;
        this.email = email;
        this.telephone = telephone;
        this.address = address;
        this.location = location;
    }
    
    /**
     *
     * Constructor de clase vacío
     */

    public Client() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIdentification() {
        return identification;
    }

    public void setIdentification(int identification) {
        this.identification = identification;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    
    

}
