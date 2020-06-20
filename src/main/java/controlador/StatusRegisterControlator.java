/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.OrdersDAO;
import dao.UserDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.AdminOrderStatus;
import vista.AdminRole;
import vista.ProviderRole;
import vista.StatusRegister;
import vista.UserRegister;

/**
 *
 * @author jabre
 */
public class StatusRegisterControlator implements ActionListener{
     private StatusRegister providerRole;
    private AdminOrderStatus role = new AdminOrderStatus();
    private ProviderRole p=new ProviderRole();
    private OrdersDAO order=new  OrdersDAO();
    private int selection;
    
    

    public StatusRegisterControlator() {
        
    }
    public StatusRegisterControlator(StatusRegister user) {
        this.providerRole=user;
    }
    
    
    public void openUserRegister(){
    providerRole.setTitle("Registo Usuario");
    providerRole.setLocationRelativeTo(null);
    providerRole.setVisible(true);
    order.getComboOrders(providerRole.comboOrder);
    
    this.providerRole.buttonInsert.setActionCommand("buttonInsert");
    this.providerRole.buttonInsert.addActionListener(this);
    this.providerRole.buttonBack.setActionCommand("buttonBack");
    this.providerRole.buttonBack.addActionListener(this);
    
    
    }
    
  
 
    

    @Override
    public void actionPerformed(ActionEvent evento) {
       switch(buttons.valueOf(evento.getActionCommand())){
           case buttonInsert:
               order.orderStatus(providerRole.comboOrder.getSelectedItem().toString(), providerRole.comboStatus.getSelectedItem().toString());
               providerRole.setVisible(false);
               break;
           case buttonBack:
               new AdminOrderStatusControlator(role).openUserRegister();
               providerRole.setVisible(false);
               break;
           
       
       }
    }
    
    
    
    public enum buttons{
    btnUpdate,buttonInsert,buttonBack,buttonDelete
    }   
}
