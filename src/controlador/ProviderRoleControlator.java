/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.UserDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.ChooseRole;
import vista.ProviderLogIn;
import vista.ProviderRole;

/**
 *
 * @author jabre
 */
public class ProviderRoleControlator implements ActionListener {
    
    private ProviderRole providerRole;
    private ChooseRole role = new ChooseRole();
    private ProviderRole p=new ProviderRole();

    public ProviderRoleControlator() {
        
    }
    public ProviderRoleControlator(ProviderRole user) {
        this.providerRole=user;
    }
    
    
    public void openUserRegister(){
    providerRole.setTitle("Registo Usuario");
    providerRole.setLocationRelativeTo(null);
    providerRole.setVisible(true);
    
    this.providerRole.buttonConsult.setActionCommand("buttonConsult");
    this.providerRole.buttonConsult.addActionListener(this);
    this.providerRole.buttonOrders.setActionCommand("buttonOrders");
    this.providerRole.buttonOrders.addActionListener(this);
    this.providerRole.buttonProducts.setActionCommand("buttonProducts");
    this.providerRole.buttonProducts.addActionListener(this);
    this.providerRole.buttonBack.setActionCommand("buttonBack");
    this.providerRole.buttonBack.addActionListener(this);
    
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
       switch(buttons.valueOf(evento.getActionCommand())){
           
       
       }
    }
    
    public enum buttons{
   
    }   
}
