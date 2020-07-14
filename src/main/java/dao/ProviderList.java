/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import modelo.Providers;

/**
 *
 * @author samoy
 */
public class ProviderList {
    
    /**
     *
     * Atributos necesarios para la implementación de los métodos de la clase
     */

    private Providers provi;
    private Conexion conexion = new Conexion();
    private Connection cin = conexion.getConnection();
    private PreparedStatement ps;
    private ArrayList<Providers> arrayUser = new ArrayList<>();
    private static final ProviderList instance;
    
    /**
     *
     * Método estatico para llamar a los metodos de la clase desde otras clases
     * @return 
     */

    public static ProviderList getInstance() {
        return instance;
    }

    static {
        instance = new ProviderList();
        instance.arrayUser.add(new Providers(){
            {
                setId("15");
                setName("lolo");
            }
        });
    }
    
    /**
     *
     * Método para obtener la lista de usuarios de la base de datos
     * @param userName Parametro que sirve como entrada para buscar a un proveedor en especifíco por nombre de usuario
     * @return 
     */

    public List<Providers> getProvidersUser(String userName) {
        String poi = "SELECT idProveedor FROM proveedores WHERE nombreUsuario = '" + userName + "'";
        try {

            ps = cin.prepareCall(poi);
            ResultSet result = ps.executeQuery();

            while (result.next()) {
                this.provi = new Providers(result.getString("idProveedor"), "lol");
                arrayUser.add(provi);
            }

        } catch (Exception e) {

        }
        return arrayUser;
    }
    
     /**
     *
     * Método para obtener la lista de usuarios
     * @return 
     */

    public List<Providers> getUserList() {
        return arrayUser;
    }
    
    /**
     *
     * Método para buscar usuarios en la lista de usuarios
     * @param newProvider Parametro que sirve como entrada para buscar a un determinado usuario en la lista de usuarios
     * @return 
     */
    
    public List<Providers> searchUser(Providers newProvider) {

        List<Providers> results = new ArrayList<>();
        boolean byID = newProvider.getId() != null && newProvider.getId().length() > 0;

        for (Providers pro : arrayUser) {
            boolean add = !(byID);
            if (!add && byID && pro.getId().contains(newProvider.getId())) {
                add = true;
            }
            if (add) {
                results.add(pro);
            }
        }
        return results;
    }

}
