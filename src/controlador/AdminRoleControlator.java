/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.AdminCategory;
import vista.AdminOrderStatus;
import vista.AdminProvider;
import vista.AdminRole;
import vista.AdminUser;
import vista.ChooseRole;
import vista.ProviderRole;
import vista.UserRegister;

/**
 *
 * @author jabre
 */
public class AdminRoleControlator implements ActionListener {
    private AdminRole providerRole;
    private ChooseRole role = new ChooseRole();
    private ProviderRole p=new ProviderRole();
    private AdminProvider pa=new AdminProvider();
    private AdminUser user=new AdminUser();
    private AdminOrderStatus order=new AdminOrderStatus();
    private AdminCategory category=new AdminCategory();
    private UserRegister a=new UserRegister();

    public AdminRoleControlator() {
        
    }
    public AdminRoleControlator(AdminRole user) {
        this.providerRole=user;
    }
    
    
    public void openUserRegister(){
    providerRole.setTitle("Registo Usuario");
    providerRole.setLocationRelativeTo(null);
    providerRole.setVisible(true);
    
    this.providerRole.buttonCate.setActionCommand("buttonCate");
    this.providerRole.buttonCate.addActionListener(this);
    this.providerRole.buttonCondi.setActionCommand("buttonCondi");
    this.providerRole.buttonCondi.addActionListener(this);
    this.providerRole.buttonProvi.setActionCommand("buttonProvi");
    this.providerRole.buttonProvi.addActionListener(this);
    this.providerRole.buttonUser.setActionCommand("buttonUser");
    this.providerRole.buttonUser.addActionListener(this);
    this.providerRole.buttonBack.setActionCommand("buttonBack");
    this.providerRole.buttonBack.addActionListener(this);
    this.providerRole.buttonInsert.setActionCommand("buttonInsert");
    this.providerRole.buttonInsert.addActionListener(this);
    
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
       switch(buttons.valueOf(evento.getActionCommand())){
           case buttonCate:
               new AdminCategoryControlator(category).openUserRegister();
               break;
           case buttonCondi:
               new AdminOrderStatusControlator(order).openUserRegister();
               break;
           case buttonProvi:
               new AdminProviderControlator(pa).openUserRegister();
               break;
           case buttonUser:
               new AdminUserControlator(user).openUserRegister();
               break;
           case buttonBack:
               new ChooseRoleControlator(role).openChooseRole();
               break;
           case buttonInsert:
               new UserRegisterControlador(a).openUserRegister("administracion");
               break;
           
       
       }
    }
    
    public enum buttons{
    buttonCate,buttonCondi,buttonProvi,buttonUser,buttonBack,buttonInsert
    }   
}
