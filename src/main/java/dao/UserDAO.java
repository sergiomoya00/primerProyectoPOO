/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import static jdk.nashorn.internal.objects.NativeFunction.call;

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
    CallableStatement cs;
    

    public UserDAO() {

    }

    public void userRegister(String username, String email, String password,String role) {

        String insertar = "insert into usuarios values('"+username+"','"+email+"',dbo.ENCRIPTA_PASS('"+password+"'),'"+role+"')";
        String contra=getPassword(password);
        System.out.print(contra);
        try {

            ps = cin.prepareCall(insertar);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(providerRegister, "Registrado con exito");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(providerRegister, "No Registrado ");
        }
    }
    public boolean logIn(String email,String password) {
            try {
            ResultSet rs = null;
            String login = "select nombreUsuario,dbo.desencriptar_pass(contraseña) from usuarios where nombreUsuario='"+email+"'";
            ps = cin.prepareStatement(login);
            rs = ps.executeQuery();

            while (rs.next()) {
                
                if(password.equals(rs.getObject(2).toString())){
                 JOptionPane.showMessageDialog(providerRegister, "Bienvenido"+rs.getString("nombreUsuario"));
                 return true;
                }

            }
            
        } catch (SQLException ex) {
         JOptionPane.showMessageDialog(providerRegister, "Usuario o contraseña incorrectos");
        }
        return false;
    }
    
    public String getEmail(String email){
    String user="";
        try {
            ResultSet rs = null;
            String login = "select correoElectronico from usuarios where nombreUsuario='" +email+ "'";
            ps = cin.prepareStatement(login);
            rs = ps.executeQuery();

            while (rs.next()) {
                
                user=rs.getString("correoElectronico");
                return user;
            }
            
        } catch (SQLException ex) {

        }
        return user;
    }
    
    
    public String getPassword(String pass){
         String result="";
         try {
            cs=cin.prepareCall("{?=call ENCRIPTA_PASS(?)}");
            cs.registerOutParameter(1, Types.VARBINARY);
            cs.setString(2, pass);
            cs.execute();
            result=cs.getString(1);
            
        } catch (SQLException ex) {

        }
        
        return result;
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
     String update = "UPDATE usuarios SET  nombreUsuario='"+username+"', correoElectronico='"+email+"', contraseña=dbo.ENCRIPTA_PASS('"+password+"'), rol='"+role+"'  WHERE nombreUsuario='" + username + "'";
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
