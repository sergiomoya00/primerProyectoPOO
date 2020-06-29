/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.ProviderDAO;
import dao.ProviderList;
import dao.UserDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.ChooseRole;
import vista.ProviderConsultClients;
import vista.ProviderLogIn;
import vista.ProviderOrders;
import vista.ProviderProducts;
import vista.ProviderRole;

/**
 *
 * @author jabre
 */
public class ProviderRoleControlator implements ActionListener {

    private ProviderRole providerRole;
    private ChooseRole role = new ChooseRole();
    private ProviderRole p = new ProviderRole();
    private ProviderProducts product = new ProviderProducts();
    private ProviderConsultClients consult = new ProviderConsultClients();
    private ProviderOrders order = new ProviderOrders();

    public ProviderRoleControlator() {
    }

    public ProviderRoleControlator(ProviderRole user) {
        this.providerRole = user;
    }

    public void openUserRegister() {
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
        switch (buttons.valueOf(evento.getActionCommand())) {
            case buttonProducts:
                ProviderList.getInstance().getUserList().get(1).getName();
                new ProviderProductsControlator(product).openUserRegister();
                providerRole.setVisible(false);
                break;
            case buttonOrders:
                new ProviderOrdersControlator(order).openUserRegister();
                providerRole.setVisible(false);
                break;
            case buttonConsult:
                ProviderList.getInstance().getUserList().get(1).getName();
                new ProviderConsultClientsControlator(consult).openUserRegister();
                providerRole.setVisible(false);
                break;
            case buttonBack:
                new ChooseRoleControlator(role).openChooseRole();
                providerRole.setVisible(false);
                break;

        }
    }

    public enum buttons {
        buttonProducts, buttonOrders, buttonConsult, buttonBack
    }
}
