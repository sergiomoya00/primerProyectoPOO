/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.ProviderDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.AdminProvider;
import vista.ProviderRegister;
import vista.UserRegister;

/**
 *
 * @author jabre
 */
public class ProviderRegisterControlator implements ActionListener {
    private ProviderRegister providerRegister;
    private AdminProvider p=new AdminProvider();
    ProviderDAO provider=new ProviderDAO();
    private String nombre;
    
    public ProviderRegisterControlator() {
        
    }
    public ProviderRegisterControlator(ProviderRegister user) {
        this.providerRegister=user;
    }
    
    public void openProviderRegister(String nombre){
    providerRegister.setTitle("Registo Proveedor");
    providerRegister.setLocationRelativeTo(null);
    providerRegister.setVisible(true);
    
    this.nombre=nombre;
    this.providerRegister.nextButton.setActionCommand("nextButton");
    this.providerRegister.nextButton.addActionListener(this);
    this.providerRegister.buttonBack.setActionCommand("buttonBack");
    this.providerRegister.buttonBack.addActionListener(this);
    
    }
    
    public enum buttons{
    nextButton,
    buttonBack
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        switch(buttons.valueOf(evento.getActionCommand())){
            case nextButton:
                provider.providerRegister(providerRegister.txtId.getText(), providerRegister.txtNombre.getText(), providerRegister.txtCompany.getText());
                provider.providerInformationRegister(providerRegister.txtId.getText(), providerRegister.txtProvince.getText(), providerRegister.txtcanton.getText(), providerRegister.txtDistrict.getText(), providerRegister.txtInfo.getText(), Integer.parseInt(providerRegister.txtphone.getText()), providerRegister.txtemail.getText(), providerRegister.txtUbication.getText(), providerRegister.txtWeb.getText(), providerRegister.txtHora.getText(), providerRegister.txtSocialM.getText());
                break;
            
            case buttonBack:
                new AdminProviderControlator(p).openUserRegister();
                providerRegister.setVisible(false);
                break;
            
        }
    }
}
