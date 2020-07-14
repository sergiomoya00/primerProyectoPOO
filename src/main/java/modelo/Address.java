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
public class Address {
    
     /**
     *
     * Atributos necesarios para la implementación de los métodos de la clase
     */

    private String province;
    private String canton;
    private String district;
    private String details;
    
    /**
     *
     * Constructor de clase
     * @param province Parametro que da como entrada a provincia
     * @param canton Parametro que da como entrada el canton
     * @param district Parametro que da como entrada el distrito
     * @param details Parametro que da como entrada los detalles
     */

    public Address(String province, String canton, String district, String details) {
        this.province = province;
        this.canton = canton;
        this.district = district;
        this.details = details;
    }
    
    /**
     *
     * Constructor de clase vacío
     */

    public Address() {
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCanton() {
        return canton;
    }

    public void setCanton(String canton) {
        this.canton = canton;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

}
