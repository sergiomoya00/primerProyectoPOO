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
    
    /**
     *
     * Atributos necesarios para la implementación de los métodos de la clase
     */
    
    private ClientRole providerRole;
    private ChooseRole role = new ChooseRole();
    private ClientSearch order = new ClientSearch();
    private ClientOrder category = new ClientOrder();
    private String nombre;
    
    /**
     *
     * Constructor vacío de la clase
     */

    public ClientRoleControlator() {

    }
    
    /**
     * Constructor de la clase
     *
     * @param user atributo que hace referencia a la ventana de tipo ClientRole
     */

    public ClientRoleControlator(ClientRole user) {
        this.providerRole = user;
    }
    
    /**
     * Método que inicializa la ventana ClientRole
     * @param nombre Atributo que guarda el nombre de usuario utilizado en una ventana anterior
     */

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
    
    /**
     * Método que ejecuta una determinada acción dependiendo del botón
     *
     * @param evento Atributo que hace referencia a la acción de un botón en
     * caso de ser pulsado
     */

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
        buttonGetOrder, buttonBack, buttonOrder
    }
}
