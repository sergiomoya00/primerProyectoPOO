/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import vista.AdminUser;
import vista.ProviderRegister;

/**
 *
 * @author jabre
 */
public class UserDAO {

    ProviderRegister providerRegister;
    AdminUser admin;
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
    public boolean logIn(String email,String password) {
        try {
            ResultSet rs = null;
            String login = "select * from usuarios where nombreUsuario='" +email + "' AND contraseña='" + password + "'";
            ps = cin.prepareStatement(login);
            rs = ps.executeQuery();

            while (rs.next()) {
                JOptionPane.showMessageDialog(providerRegister, "Bienvenido"+rs.getString("nombreUsuario"));
                return true;
            }
            JOptionPane.showMessageDialog(providerRegister, "Usuario o contraseña incorrectos ");
        } catch (SQLException ex) {

        }
        return false;

    }
    
    public String getRole(String username) {
        String user="";
        try {
            ResultSet rs = null;
            String login = "select rol from usuarios where nombreUsuario='" +username + "'";
            ps = cin.prepareStatement(login);
            rs = ps.executeQuery();

            while (rs.next()) {
                
                user=rs.getString("rol");
                return user;
            }
            
        } catch (SQLException ex) {

        }
        return user;

    }
    
    public void deleteUser(String username){
    String delete = "delete  from usuarios where nombreUsuario='" + username + "'";

        try {
            ps = cin.prepareCall(delete);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(admin, "Usuario eliminado con exito");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(admin, e.toString());
        }
    }
    
    public void updateUser(String username,String email,String password,String role){
     String update = "UPDATE usuarios SET  nombreUsuario='"+username+"', correoElectronico='"+email+"', contraseña='"+password+"', rol='"+role+"'  WHERE nombreUsuario='" + username + "'";
        try {

            ps = cin.prepareStatement(update);
            ps.executeUpdate();
             JOptionPane.showMessageDialog(admin, "Informacion editada con exito");

        } catch (Exception e) {
       JOptionPane.showMessageDialog(admin, e.toString());
        }
    }
    public void getAllUsers(JTable table){
      try {
            DefaultTableModel modelo = new DefaultTableModel();
            table.setModel(modelo);
            ResultSet rs = null;
            String login = "SELECT * FROM usuarios ";
            ps = cin.prepareStatement(login);
            rs = ps.executeQuery();
            ResultSetMetaData rsMd = rs.getMetaData();
            int cantidadColumnas = rsMd.getColumnCount();
            modelo.addColumn("Username");
            modelo.addColumn("Correo Electronico");
            modelo.addColumn("Contraseña");
            modelo.addColumn("Rol");
           

            while (rs.next()) {
                Object[] filas = new Object[cantidadColumnas];

                for (int i = 1; i <= cantidadColumnas; i++) {
                    
                    filas[i - 1] = rs.getObject(i);
                        
                    
                }

                modelo.addRow(filas);

            }

        } catch (SQLException ex) {

        }

    }
}
