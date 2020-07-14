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
public class Users {
    
     /**
     *
     * Atributos necesarios para la implementación de los métodos de la clase
     */

    private String email;
    private String name;
    private String password;
    private String role;
    
    /**
     *
     * Constructor de clase vacío
     */

    public Users() {
    }
    
     /**
     *
     * Constructor de clase
     * @param name Parametro que da como entrada el nombre
     * @param password Parametro que da como entrada la contraseña
     * @param role Parametro que da como entrada el rol
     */

    public Users(String name, String password, String role) {
        this.name = name;
        this.password = password;
        this.role = role;
    }
     /**
     *
     * Constructor de clase
     * @param email Parametro que da como entrada el email
     * @param name Parametro que da como entrada el nombre
     * @param password Parametro que da como entrada la contraseña
     * @param role Parametro que da como entrada el rol
     */

    public Users(String email, String name, String password, String role) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
