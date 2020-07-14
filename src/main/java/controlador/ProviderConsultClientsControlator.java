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
    
    /**
     *
     * Atributos necesarios para la implementación de los métodos de la clase
     */

    private ProviderConsultClients providerConsult;
    private ProviderRole p = new ProviderRole();
    private ClientDAO clients = new ClientDAO();
    private ProviderDAO provider = new ProviderDAO();
    private String id = "";
    
     /**
     *
     * Constructor vacío de la clase
     */

    public ProviderConsultClientsControlator() {
    }
    
    /**
     * Constructor de la clase
     *
     * @param user atributo que hace referencia a la ventana de tipo ProviderConsultClients
     */

    public ProviderConsultClientsControlator(ProviderConsultClients user) {
        this.providerConsult = user;
    }
    
     /**
     * Método que inicializa la ventana ProviderConsultClients
     */

    public void openUserRegister() {
        providerConsult.setTitle("Consultar clientes");
        providerConsult.setLocationRelativeTo(null);
        id = ProviderList.getInstance().getUserList().get(1).getId();
        
        Providers newProvider = new Providers();
        newProvider.setId(id);
        newProvider.setName("provider");
        
        clients.consultClient(providerConsult.tableClients, ProviderList.getInstance().searchUser(newProvider).get(0).getId());
        providerConsult.setVisible(true);

        this.providerConsult.buttonBack.setActionCommand("buttonBack");
        this.providerConsult.buttonBack.addActionListener(this);
        this.providerConsult.buttonList.setActionCommand("buttonList");
        this.providerConsult.buttonList.addActionListener(this);

    }
    
    /**
     * Método que ejecuta una determinada acción dependiendo del botón
     *
     * @param evento Atributo que hace referencia a la acción de un botón en
     * caso de ser pulsado
     */
    @Override
    public void actionPerformed(ActionEvent evento) {
        switch (ProviderConsultClientsControlator.buttons.valueOf(evento.getActionCommand())) {
            case buttonBack:
                Providers newProvider = new Providers();
                ProviderList.getInstance().searchUser(newProvider).clear();
                new ProviderRoleControlator(p).openUserRegister();
                providerConsult.setVisible(false);
                break;

        }
    }
    
    /**
     *
     * Método que etiqueta los botones para usarlos en la clase actionPerformed
     */

    public enum buttons {
        buttonBack, buttonList
    }
}
