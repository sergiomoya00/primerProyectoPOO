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
public class Orders {
    
     /**
     *
     * Atributos necesarios para la implementación de los métodos de la clase
     */

    private String product;
    private int quantity;
    private String condition;
    private String username;
    private String provider;
    private String fecha;
    
    /**
     *
     * Constructor de clase vacío
     */

    public Orders() {
    }
    
    /**
     *
     * Constructor de clase
     * @param product Parametro que da como entrada el codigo del producto
     * @param username Parametro que da como entrada el nombre del usuario
     * @param provider Parametro que da como entrada el codigo del proveedor
     * @param quantity Parametro que da como entrada la cantidad
     * @param condition Parametro que da como entrada la condicion
     */

    public Orders(String product, int quantity, String condition, String username, String provider) {
        this.product = product;
        this.quantity = quantity;
        this.condition = condition;
        this.username = username;
        this.provider = provider;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

}
