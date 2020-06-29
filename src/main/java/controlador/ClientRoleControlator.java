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
import vista.ClientOrder;
import vista.ClientRole;
import vista.ClientSearch;
import vista.ProviderRole;
import vista.UserRegister;

/**
 *
 * @author jabre
 */
public class ClientRoleControlator implements ActionListener {
    
    private ClientRole providerRole;
    private ClientRole role = new ClientRole();
    private ClientSearch order = new ClientSearch();
    private ClientOrder category = new ClientOrder();
    private String nombre;

    public ClientRoleControlator() {

    }

    public ClientRoleControlator(ClientRole user) {
        this.providerRole = user;
    }

    public void openUserRegister(String nombre) {
        providerRole.setTitle("Registo Usuario");
        providerRole.setLocationRelativeTo(null);
        providerRole.setVisible(true);

        this.nombre = nombre;
        this.providerRole.buttonGetOrder.setActionCommand("buttonGetOrder");
        this.providerRole.buttonGetOrder.addActionListener(this);
        this.providerRole.buttonOrder.setActionCommand("buttonOrder");
        this.providerRole.buttonOrder.addActionListener(this);
        this.providerRole.buttonBack.setActionCommand("buttonBack");
        this.providerRole.buttonBack.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        switch (buttons.valueOf(evento.getActionCommand())) {
            case buttonGetOrder:
                new ClientOrderControlator(category).openUserRegister(nombre);
                providerRole.setVisible(false);
                break;
            case buttonOrder:
                new ClientSearchControlator(order).openUserRegister(nombre);
                providerRole.setVisible(false);
                break;
            case buttonBack:
                new ClientRoleControlator(role).openUserRegister(nombre);
                providerRole.setVisible(false);
                break;

        }
    }

    public enum buttons {
        buttonGetOrder, buttonBack, buttonOrder
    }
}
