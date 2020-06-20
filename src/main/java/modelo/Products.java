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

    public Products() {
    }

    public Products(String productId, String productName, String description, String type, String category, int quantity, int unitaryPrice, int deliverPrice, String condition) {
        this.productId = productId;
        this.productName = productName;
        this.description = description;
        this.type = type;
        this.category = category;
        this.quantity = quantity;
        this.unitaryPrice = unitaryPrice;
        this.deliverPrice = deliverPrice;
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
