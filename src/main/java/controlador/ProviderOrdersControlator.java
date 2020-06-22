/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.ProviderOrders;
import vista.ProviderRole;

/**
 *
 * @author samoy
 */
public class ProviderOrdersControlator implements ActionListener {

    private ProviderOrders providerOrders;
    private ProviderRole p = new ProviderRole();

    public ProviderOrdersControlator() {
    }

    public ProviderOrdersControlator(ProviderOrders user) {
        this.providerOrders = user;
    }

    public void openUserRegister() {
        providerOrders.setTitle("Registo Usuario");
        providerOrders.setLocationRelativeTo(null);
        providerOrders.setVisible(true);

        this.providerOrders.buttonSearch.setActionCommand("buttonSearch");
        this.providerOrders.buttonSearch.addActionListener(this);
        this.providerOrders.buttonEntregado.setActionCommand("buttonEntregado");
        this.providerOrders.buttonEntregado.addActionListener(this);
        this.providerOrders.buttonProcesado.setActionCommand("buttonProcesado");
        this.providerOrders.buttonProcesado.addActionListener(this);
        this.providerOrders.buttonBack.setActionCommand("buttonBack");
        this.providerOrders.buttonBack.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        switch (buttons.valueOf(evento.getActionCommand())) {
            case buttonSearch:
                break;
            case buttonEntregado:
                break;
            case buttonProcesado:
                break;
            case buttonBack:
                new ProviderRoleControlator(p).openUserRegister();
                providerOrders.setVisible(false);
                break;

        }
    }

    public enum buttons {
        buttonSearch, buttonEntregado, buttonProcesado, buttonBack
    }

}
