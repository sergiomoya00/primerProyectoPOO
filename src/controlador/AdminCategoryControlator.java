/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.CategoryDAO;
import dao.UserDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import vista.AdminCategory;
import vista.AdminRole;
import vista.AdminUser;
import vista.CategoryRegister;
import vista.ProviderRole;
import vista.UserRegister;

/**
 *
 * @author jabre
 */
public class AdminCategoryControlator implements ActionListener {
  private AdminCategory providerRole;
    private AdminRole role = new AdminRole();
    private ProviderRole p=new ProviderRole();
    private CategoryRegister a=new CategoryRegister();
    private CategoryDAO user=new CategoryDAO();
    private int selection;
    private MouseEvent mouse;
    

    public AdminCategoryControlator() {
        
    }
    public AdminCategoryControlator(AdminCategory user) {
        this.providerRole=user;
    }
    
    
    public void openUserRegister(){
    providerRole.setTitle("Registo Usuario");
    providerRole.setLocationRelativeTo(null);
    user.getAllCategories(providerRole.tableCategory);
    providerRole.setVisible(true);
    
    this.providerRole.buttonUpdate.setActionCommand("buttonUpdate");
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
               new CategoryRegisterControlator(a).openUserRegister();
               providerRole.setVisible(false);
               break;
           case buttonUpdate:
                selection=providerRole.tableCategory.getSelectedRow();
                user.updateCategory(String.valueOf(providerRole.tableCategory.getValueAt(selection, 0)), String.valueOf(providerRole.tableCategory.getValueAt(selection, 1)), String.valueOf(providerRole.tableCategory.getValueAt(selection, 2)), String.valueOf(providerRole.tableCategory.getValueAt(selection, 3)));
               break;
           case buttonDelete:
               selection=providerRole.tableCategory.getSelectedRow();
               user.deleteCategory(String.valueOf(providerRole.tableCategory.getValueAt(selection, 0)));
               break;
           case buttonBack:
               new AdminRoleControlator(role).openUserRegister();
               providerRole.setVisible(false);
               break;
           
       
       }
    }
    
    
    
    public enum buttons{
    buttonUpdate,buttonInsert,buttonBack,buttonDelete
    }     
}
