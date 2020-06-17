/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import vista.ProviderRegister;

/**
 *
 * @author jabre
 */
public class ProviderDAO {
    ProviderRegister providerRegister;
    Conexion conexion = new Conexion();
    Connection cin = conexion.getConnection();
    PreparedStatement ps;

    public ProviderDAO() {
    }

    public void providerRegister(String id, String name, String company) {

        String insertar = "insert into proveedores (idProveedor,nombre,empresa,calificacion) values (?,?,?,?) ";

        try {
            ps = cin.prepareCall(insertar);
            ps.setString(1, id);
            ps.setString(2, name);
            ps.setString(3, company);
            ps.setFloat (4, 0.0F);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(providerRegister, "Registrado con exito");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(providerRegister, "No Registrado ");
        }
    }
    public void providerInformationRegister(String username, String provincia, String canton, String distrito, String señas,int phone,String email,String ubication, String website,String schedule,String profile) {

        String insertar = "insert into informacion(nombreUsuario,cedula,provincia,canton,distrito,señas,telefono,correoElectronico,ubicacion,sitio,horario,perfil) values (?,?,?,?,?,?,?,?,?,?,?,?) ";

        try {
            ps = cin.prepareCall(insertar);
            ps.setString(1, username);
            ps.setString(2, null);
            ps.setString(3, provincia);
            ps.setString(4, canton);
            ps.setString(5, distrito);
            ps.setString(6, señas);
            ps.setInt(7, phone);
            ps.setString(8, email);
            ps.setString(9, ubication);
            ps.setString(10, website);
            ps.setString(11, schedule);
            ps.setString(12, profile);

            ps.executeUpdate();
            JOptionPane.showMessageDialog(providerRegister, "Registrado con exito");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(providerRegister, "No Registrado ");
        }
    }
        
}
