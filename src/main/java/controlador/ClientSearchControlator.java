/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.ChooseRole;
import vista.ClientSearch;
import vista.ProviderRole;

/**
 *
 * @author jabre
 */
public class ClientSearchControlator implements ActionListener{
   private ClientSearch providerRole;
    private ChooseRole role = new ChooseRole();
    private ProviderRole p=new ProviderRole();

    public ClientSearchControlator() {
        
    }
    public ClientSearchControlator(ClientSearch user) {
        this.providerRole=user;
    }
    
    
    public void openUserRegister(){
    providerRole.setTitle("Registo Usuario");
    providerRole.setLocationRelativeTo(null);
    providerRole.setVisible(true);
    
    
    
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
       switch(buttons.valueOf(evento.getActionCommand())){
           
       
       }
    }
    
    public enum buttons{
   
    }    
}
