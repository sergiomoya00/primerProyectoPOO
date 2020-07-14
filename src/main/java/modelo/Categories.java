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
public class Categories {
    
     /**
     *
     * Atributos necesarios para la implementación de los métodos de la clase
     */

    private String name;
    private String description;
    private String condition;
    
    /**
     *
     * Constructor de clase vacío
     */

    public Categories() {
    }
    
    /**
     *
     * Constructor de clase
     * @param name Parametro que da como entrada el nombre de la categoria
     */
    
    public Categories(String name) {
        this.name = name;
    }
    
    /**
     *
     * Constructor de clase
     * @param name Parametro que da como entrada el nombre de la categoria
     * @param description Parametro que da como entrada la descripcion de la categoria
     * @param condition Parametro que da como entrada la condicion
     */

    public Categories(String name, String description, String condition) {
        this.name = name;
        this.description = description;
        this.condition = condition;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

}
