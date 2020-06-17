/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.UserDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.text.View;
import vista.AdminCategory;
import vista.AdminOrderStatus;
import vista.AdminProvider;
import vista.AdminRole;
import vista.AdminUser;
import vista.CategoryRegister;
import vista.ChooseRole;
import vista.ClientLogIn;
import vista.ClientRegister;
import vista.ClientSearch;
import vista.ProductRegister;
import vista.ProviderConsultClients;
import vista.ProviderLogIn;
import vista.ProviderProducts;
import vista.ProviderRegister;
import vista.ProviderRole;
import vista.ProviderUser;
import vista.StatusRegister;
import vista.UserRegister;

/**
 *
 * @author jabre
 */
public class UserRegisterControlador implements ActionListener {

    private UserRegister userRegister;
    private UserDAO user =new UserDAO();
    private ChooseRole role=new ChooseRole();

    public UserRegisterControlador() {
        
    }
    public UserRegisterControlador(UserRegister user) {
        this.userRegister=user;
    }
    
    
    public void openUserRegister(){
    userRegister.setTitle("Registo Usuario");
    userRegister.setLocationRelativeTo(null);
    userRegister.setVisible(true);
    
    this.userRegister.btnRegister.setActionCommand("btnRegister");
    this.userRegister.btnRegister.addActionListener(this);
    this.userRegister.btnLogIn.setActionCommand("btnLogIn");
    this.userRegister.btnLogIn.addActionListener(this);
    
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
       switch(buttons.valueOf(evento.getActionCommand())){
           case btnRegister:
               user.userRegister(userRegister.txtName.getText(), userRegister.txtEmail.getText(), userRegister.txtPassword.getText(), userRegister.comboActive.getSelectedItem().toString());
               new ChooseRoleControlator(role).openChooseRole();
               break;
           case btnLogIn:
               new ChooseRoleControlator(role).openChooseRole();
               break;
       
       }
    }
    
    public enum buttons{
    btnRegister,
    btnLogIn
    }
    
    
    
    
    
    
    
    

}
