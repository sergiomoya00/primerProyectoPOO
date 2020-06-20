/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.*;
import javax.swing.JOptionPane;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author jabre
 */
public class Conexion {

    private static Connection con=null;
    private static final String url = "jdbc:sqlserver://DESKTOP-PPIO21T:1433;databaseName=TareaProgramada1";

    public Connection getConnection() {
       try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(url,"sa","123");
            if (con != null) {
                System.out.print("Conexion establecida");
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.print("Conexion fail");
        }  
        return con;
    }

}
