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
public class Proveedor {

    private String identificador;
    private String nombre;
    private String empresa;
    private String canton;
    private String señas;
    private int telefono;
    private String correoElectronico;
    private String horarioAtencion;
    private int ubicación;
    private String perfilRedes;
    private int calificacion;
    
    public Proveedor(){
    
    }

    public Proveedor(String identificador, String nombre, String empresa, String canton, String señas, int telefono, String correoElectronico, String horarioAtencion, int ubicación, String perfilRedes) {
        this.identificador = identificador;
        this.nombre = nombre;
        this.empresa = empresa;
        this.canton = canton;
        this.señas = señas;
        this.telefono = telefono;
        this.correoElectronico = correoElectronico;
        this.horarioAtencion = horarioAtencion;
        this.ubicación = ubicación;
        this.perfilRedes = perfilRedes;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
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

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getHorarioAtencion() {
        return horarioAtencion;
    }

    public void setHorarioAtencion(String horarioAtencion) {
        this.horarioAtencion = horarioAtencion;
    }

    public int getUbicación() {
        return ubicación;
    }

    public void setUbicación(int ubicación) {
        this.ubicación = ubicación;
    }

    public String getPerfilRedes() {
        return perfilRedes;
    }

    public void setPerfilRedes(String perfilRedes) {
        this.perfilRedes = perfilRedes;
    }
}
