/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.UserDAO;
import java.sql.SQLException;
import vista.ChooseRole;
import vista.UserRegister;

/**
 *
 * @author samoy
 */
public class PRIMERPROYECTOPROGRAMADO {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        UserRegisterControlador c=new UserRegisterControlador();
        
        ChooseRole principal = new ChooseRole();
        
        new ChooseRoleControlator(principal).openChooseRole();
    }

}
