/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.ClientDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import vista.ProviderConsultClients;
import vista.ProviderRegister;
import vista.ProviderRole;

/**
 *
 * @author samoy
 */
public class ProviderConsultClientsControlator implements ActionListener {

    private ProviderConsultClients providerConsult;
    private ProviderRole p = new ProviderRole();
    private ClientDAO clients = new ClientDAO();

    public ProviderConsultClientsControlator() {
    }

    public ProviderConsultClientsControlator(ProviderConsultClients user) {
        this.providerConsult = user;
    }

    public void openUserRegister() {
        providerConsult.setTitle("Consultar clientes");
        providerConsult.setLocationRelativeTo(null);
        clients.consultClient(providerConsult.tableClients);
        providerConsult.setVisible(true);

        this.providerConsult.buttonBack.setActionCommand("buttonBack");
        this.providerConsult.buttonBack.addActionListener(this);
        this.providerConsult.buttonList.setActionCommand("buttonList");
        this.providerConsult.buttonList.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        switch (ProviderConsultClientsControlator.buttons.valueOf(evento.getActionCommand())) {
            case buttonBack:
                new ProviderRoleControlator(p).openUserRegister();
                providerConsult.setVisible(false);
                break;

        }
    }

    public enum buttons {
        buttonBack, buttonList
    }
}
