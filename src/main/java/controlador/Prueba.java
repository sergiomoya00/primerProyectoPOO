/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.UserDAO;
import java.sql.SQLException;
import vista.ChooseRole;

/**
 *
 * @author jabre
 */
public class Prueba {
    public static void main(String[] args) throws SQLException {
        long time=System.currentTimeMillis();
        java.sql.Date d=new java.sql.Date(time);
        long now = System.currentTimeMillis();
        java.sql.Time a=new java.sql.Time(now);
        String fecha=d+" "+a;
        System.out.print(fecha);
    }
}