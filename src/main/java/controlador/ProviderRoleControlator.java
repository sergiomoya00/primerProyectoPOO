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
import modelo.Providers;
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

    /**
     *
     * Atributos necesarios para la implementación de los métodos de la clase
     */
    private ProviderRole providerRole;
    private ChooseRole role = new ChooseRole();
    private ProviderRole p = new ProviderRole();
    private ProviderProducts product = new ProviderProducts();
    private ProviderConsultClients consult = new ProviderConsultClients();
    private ProviderOrders order = new ProviderOrders();

    /**
     *
     * Constructor vacío de la clase
     */
    public ProviderRoleControlator() {
    }

    /**
     * Constructor de la clase
     *
     * @param user atributo que hace referencia a la ventana de tipo
     * ProviderRole
     */
    public ProviderRoleControlator(ProviderRole user) {
        this.providerRole = user;
    }

    /**
     * Método que inicializa la ventana ProviderRole
     */
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

    /**
     * Método que ejecuta una determinada acción dependiendo del botón
     *
     * @param evento Atributo que hace referencia a la acción de un botón en
     * caso de ser pulsado
     */
    @Override
    public void actionPerformed(ActionEvent evento) {
        switch (buttons.valueOf(evento.getActionCommand())) {
            case buttonProducts:
                ProviderList.getInstance().getUserList().get(0).getName();
                new ProviderProductsControlator(product).openUserRegister();
                providerRole.setVisible(false);
                break;
            case buttonOrders:
                ProviderList.getInstance().getUserList().get(0).getName();
                new ProviderOrdersControlator(order).openUserRegister();
                providerRole.setVisible(false);
                break;
            case buttonConsult:
                ProviderList.getInstance().getUserList().get(0).getName();
                new ProviderConsultClientsControlator(consult).openUserRegister();
                providerRole.setVisible(false);
                break;
            case buttonBack:
                Providers newProvider = new Providers();
                ProviderList.getInstance().searchUser(newProvider).clear();
                ProviderList.getInstance().getUserList().clear();
                new ChooseRoleControlator(role).openChooseRole();
                providerRole.setVisible(false);
                break;

        }
    }

    /**
     *
     * Método que etiqueta los botones para usarlos en la clase actionPerformed
     */
    public enum buttons {
        buttonProducts, buttonOrders, buttonConsult, buttonBack
    }
}
