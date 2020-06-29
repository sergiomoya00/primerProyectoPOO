/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.ClientDAO;
import dao.ProviderDAO;
import dao.ProviderList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import modelo.Providers;
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
    private ProviderDAO provider = new ProviderDAO();
    private String id = "";

    public ProviderConsultClientsControlator() {
    }

    public ProviderConsultClientsControlator(ProviderConsultClients user) {
        this.providerConsult = user;
    }

    public void openUserRegister() {
        providerConsult.setTitle("Consultar clientes");
        providerConsult.setLocationRelativeTo(null);
        id = ProviderList.getInstance().getUserList().get(1).getId();
        
        Providers newProvider = new Providers();
        newProvider.setId(id);
        newProvider.setName("provider");
        
        clients.consultClient(providerConsult.tableClients, ProviderList.getInstance().searchUser(newProvider).get(1).getId());
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
                //Providers newProvider = new Providers();
                //ProviderList.getInstance().searchUser(newProvider).clear();
                providerConsult.setVisible(false);
                break;

        }
    }

    public enum buttons {
        buttonBack, buttonList
    }
}
