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
public class Products {
    
     /**
     *
     * Atributos necesarios para la implementación de los métodos de la clase
     */

    private String productId;
    private String productName;
    private String description;
    private String type;
    private String category;
    private int quantity;
    private int unitaryPrice;
    private int deliverPrice;
    private String foto;
    private String condition;
    
    /**
     *
     * Constructor de clase vacío
     */

    public Products() {
    }
    
    /**
     *
     * Constructor de clase
     * @param productId Parametro que da como entrada el codigo del producto
     * @param productName Parametro que da como entrada el nombre del producto
     * @param description Parametro que da como entrada la descripcion
     * @param type Parametro que da como entrada el tipo
     * @param category Parametro que da como entrada la categoria
     * @param quantity Parametro que da como entrada la cantidad
     * @param unitaryPrice Parametro que da como entrada el precio Unitario
     * @param deliveryPrice Parametro que da como entrada el precio de entrega
     * @param condition Parametro que da como entrada la condicion
     */

    public Products(String productId, String productName, String description, String type, String category, int quantity, int unitaryPrice, int deliveryPrice, String condition) {
        this.productId = productId;
        this.productName = productName;
        this.description = description;
        this.type = type;
        this.category = category;
        this.quantity = quantity;
        this.unitaryPrice = unitaryPrice;
        this.deliverPrice = deliveryPrice;
        this.condition = condition;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getUnitaryPrice() {
        return unitaryPrice;
    }

    public void setUnitaryPrice(int unitaryPrice) {
        this.unitaryPrice = unitaryPrice;
    }

    public int getDeliverPrice() {
        return deliverPrice;
    }

    public void setDeliverPrice(int deliverPrice) {
        this.deliverPrice = deliverPrice;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

}
