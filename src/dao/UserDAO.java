/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import vista.ProviderRegister;

/**
 *
 * @author jabre
 */
public class UserDAO {

    ProviderRegister providerRegister;
    Conexion conexion = new Conexion();
    Connection cin = conexion.getConnection();
    PreparedStatement ps;

    public UserDAO() {

    }

    public void userRegister(String username, String email, String password,String role) {

        String insertar = "insert into usuarios (nombreUsuario,correoElectronico,contraseña,rol) values (?,?,?,?) ";

        try {
            ps = cin.prepareCall(insertar);
            ps.setString(1, username);
            ps.setString(2, email);
            ps.setString(3, password);
            ps.setString(4, role);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(providerRegister, "Registrado con exito");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(providerRegister, "No Registrado ");
        }
    }
}
