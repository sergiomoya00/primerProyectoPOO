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
import modelo.Providers;
import vista.ProviderClientList;
import vista.ProviderConsultClients;
import vista.ProviderRole;

/**
 *
 * @author samoy
 */
public class ProviderClientListControlator implements ActionListener {

    private ProviderClientList providerClient;
    private ProviderConsultClients providerConsult;
    private ProviderConsultClientsControlator show;
    private ProviderRole p = new ProviderRole();
    private ProviderConsultClients consut = new ProviderConsultClients();
    private ClientDAO clients = new ClientDAO();
    private ProviderDAO provider = new ProviderDAO();
    private String id = "";
    private String clientName = "";
    private int selection;

    public ProviderClientListControlator() {
    }

    public ProviderClientListControlator(ProviderClientList user) {
        this.providerClient = user;
    }

    public void openUserRegister() {
        providerClient.setTitle("Consultar cliente");
        providerClient.setLocationRelativeTo(null);

        providerClient.setVisible(true);
        
        this.providerClient.buttonBack.setActionCommand("buttonBack");
        this.providerClient.buttonBack.addActionListener(this);
        this.providerClient.buttonShow.setActionCommand("buttonShow");
        this.providerClient.buttonShow.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        switch (ProviderClientListControlator.buttons.valueOf(evento.getActionCommand())) {
            case buttonBack:
                //new ProviderConsultClientsControlator(consut).openUserRegister();
                providerClient.setVisible(false);
                break;
            case buttonShow:
                id = ProviderList.getInstance().getUserList().get(0).getId();
                Providers newProvider = new Providers();
                newProvider.setId(id);
                newProvider.setName("provider");

                //clientName = String.valueOf(providerConsult.tableClients.getValueAt(show.selection, 0));
                //clients.consultSpecificClient(providerClient.tableClients, ProviderList.getInstance().searchUser(newProvider).get(0).getId(), "Andres");
                clients.consultSpecificClient(providerClient.tableClients, "1", "Andres");

        }
    }

    public enum buttons {
        buttonBack, buttonShow
    }
}
