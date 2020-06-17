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

/**
 *
 * @author jabre
 */
public class ChooseRoleControlator implements ActionListener {

    private ChooseRole chooseRole;
    private AdminCategory adminCategory;
    private AdminOrderStatus adminOrderStatus;
    private AdminProvider adminOrderProvider;
    private AdminRole adminRole;
    private AdminUser adminUser;
    private CategoryRegister categoryRegister;
    private ClientLogIn clientLogin;
    private ClientRegister clientRegister;
    private ClientSearch clientSearch;
    private ProductRegister productRegister;
    private ProviderConsultClients providerConsultClients;
    private ProviderLogIn providerLogIn;
    private ProviderProducts providerProducts;
    private ProviderRegister providerRegister;
    private ProviderRole providerRole;
    private ProviderUser providerUser;
    private StatusRegister statusRegister;

    public ChooseRoleControlator() {

    }
    
    public ChooseRoleControlator(ChooseRole choose) {
    this.chooseRole=choose;
    }
    

    public void openChooseRole() {
        chooseRole.setTitle("Registo Usuario");
        chooseRole.setLocationRelativeTo(null);
        chooseRole.setVisible(true);

        this.chooseRole.adminButton.setActionCommand("btnRegister");
        this.chooseRole.adminButton.addActionListener(this);
        this.chooseRole.providerButton.setActionCommand("btnLogIn");
        this.chooseRole.providerButton.addActionListener(this);
        this.chooseRole.clientButton.setActionCommand("btnLogIn");
        this.chooseRole.clientButton.addActionListener(this);
        this.chooseRole.btnBack.setActionCommand("btnLogIn");
        this.chooseRole.btnBack.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        switch (buttons.valueOf(evento.getActionCommand())) {
            case adminButton:
                break;
            case providerButton:
                break;
            case clientButton:
                break;
            case btnBack:
                break;
        }
    }

    public enum buttons {
        adminButton, providerButton, clientButton, btnBack
    }

}
