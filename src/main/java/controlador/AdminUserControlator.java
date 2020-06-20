/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.ProviderDAO;
import dao.UserDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import vista.AdminProvider;
import vista.AdminRole;
import vista.AdminUser;
import vista.ProviderRegister;
import vista.ProviderRole;
import vista.UserRegister;

/**
 *
 * @author jabre
 */
public class AdminUserControlator implements ActionListener {
    private AdminUser providerRole;
    private AdminRole role = new AdminRole();
    private ProviderRole p=new ProviderRole();
    private UserRegister a=new UserRegister();
    private UserDAO user=new UserDAO();
    private int selection;
    private MouseEvent mouse;
    

    public AdminUserControlator() {
        
    }
    public AdminUserControlator(AdminUser user) {
        this.providerRole=user;
    }
    
    
    public void openUserRegister(){
    providerRole.setTitle("Registo Usuario");
    providerRole.setLocationRelativeTo(null);
    user.getAllUsers(providerRole.tableUser);
    providerRole.setVisible(true);
    
    this.providerRole.buttonUpdate.setActionCommand("btnUpdate");
    this.providerRole.buttonUpdate.addActionListener(this);
    this.providerRole.buttonDelete.setActionCommand("buttonDelete");
    this.providerRole.buttonDelete.addActionListener(this);
    this.providerRole.buttonInsert.setActionCommand("buttonInsert");
    this.providerRole.buttonInsert.addActionListener(this);
    this.providerRole.buttonBack.setActionCommand("buttonBack");
    this.providerRole.buttonBack.addActionListener(this);
    
    
    }
    
  
 
    

    @Override
    public void actionPerformed(ActionEvent evento) {
       switch(buttons.valueOf(evento.getActionCommand())){
           case buttonInsert:
               new UserRegisterControlador(a).openUserRegister("b");
               providerRole.setVisible(false);
               break;
           case btnUpdate:
                selection=providerRole.tableUser.getSelectedRow();
                user.updateUser(String.valueOf(providerRole.tableUser.getValueAt(selection, 0)), String.valueOf(providerRole.tableUser.getValueAt(selection, 1)), String.valueOf(providerRole.tableUser.getValueAt(selection, 2)), String.valueOf(providerRole.tableUser.getValueAt(selection, 3)));
               break;
           case buttonDelete:
               selection=providerRole.tableUser.getSelectedRow();
               user.deleteUser(String.valueOf(providerRole.tableUser.getValueAt(selection, 0)));
               break;
           case buttonBack:
               new AdminRoleControlator(role).openUserRegister();
               providerRole.setVisible(false);
               break;
           
       
       }
    }
    
    
    
    public enum buttons{
    btnUpdate,buttonInsert,buttonBack,buttonDelete
    }   
}
