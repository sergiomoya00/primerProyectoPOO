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
import vista.AdministratorLogIn;
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
    private AdministratorLogIn role = new AdministratorLogIn();
    private ClientLogIn ro = new ClientLogIn();
    private ProviderLogIn rol = new ProviderLogIn();

    public ChooseRoleControlator() {

    }

    public ChooseRoleControlator(ChooseRole choose) {
        this.chooseRole = choose;
    }

    public void openChooseRole() {
        chooseRole.setTitle("Registo Usuario");
        chooseRole.setLocationRelativeTo(null);
        chooseRole.setVisible(true);

        this.chooseRole.adminButton.setActionCommand("adminButton");
        this.chooseRole.adminButton.addActionListener(this);
        this.chooseRole.providerButton.setActionCommand("providerButton");
        this.chooseRole.providerButton.addActionListener(this);
        this.chooseRole.clientButton.setActionCommand("clientButton");
        this.chooseRole.clientButton.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        switch (buttons.valueOf(evento.getActionCommand())) {
            case adminButton:
                new AdministratorLogInControlator(role).openAdministratorLogIn("a");
                chooseRole.setVisible(false);
                break;
            case providerButton:
                new ProviderLogInControlator(rol).openUserRegister();
                chooseRole.setVisible(false);
                break;
            case clientButton:
                new ClientLogInControlator(ro).openUserRegister();
                chooseRole.setVisible(false);
                break;

        }
    }

    public enum buttons {
        adminButton, providerButton, clientButton
    }

}
