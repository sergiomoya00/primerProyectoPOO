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

    Providers provi;
    Conexion conexion = new Conexion();
    Connection cin = conexion.getConnection();
    PreparedStatement ps;
    ArrayList<Providers> arrayUser = new ArrayList<>();

    private static final ProviderList instance;

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

    public List<Providers> getUserList() {
        return arrayUser;
    }
    
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
