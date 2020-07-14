/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.OrdersDAO;
import dao.UserDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import vista.AdminOrderStatus;
import vista.AdminRole;
import vista.AdminUser;
import vista.ProviderRole;
import vista.StatusRegister;
import vista.UserRegister;

/**
 *
 * @author jabre
 */
public class AdminOrderStatusControlator implements ActionListener {

    /**
     *
     * Atributos necesarios para la implementación de los métodos de la clase
     */
    private AdminOrderStatus providerRole;
    private AdminRole role = new AdminRole();
    private ProviderRole p = new ProviderRole();
    private StatusRegister a = new StatusRegister();
    private OrdersDAO user = new OrdersDAO();
    private int selection;

    /**
     *
     * Constructor vacío de la clase
     */
    public AdminOrderStatusControlator() {

    }

    /**
     * Constructor de la clase
     *
     * @param user atributo que hace referencia a la ventana de tipo
     * AdminDataAnalysis
     */
    public AdminOrderStatusControlator(AdminOrderStatus user) {
        this.providerRole = user;
    }

    /**
     *
     * Método que inicializa la ventana AdminOrderStatus
     */
    public void openUserRegister() {
        providerRole.setTitle("Registo Usuario");
        providerRole.setLocationRelativeTo(null);
        user.getAllOrderStatus(providerRole.tableOrder);
        providerRole.setVisible(true);

        this.providerRole.buttonUpdate.setActionCommand("btnUpdate");
        this.providerRole.buttonUpdate.addActionListener(this);
        this.providerRole.buttonDelete.setActionCommand("buttonDelete");
        this.providerRole.buttonDelete.addActionListener(this);
        this.providerRole.buttonInsert.setActionCommand("buttonInsert");
        this.providerRole.buttonInsert.addActionListener(this);
        this.providerRole.buttonBack.setActionCommand("buttonBack");
        this.providerRole.buttonBack.addActionListener(this);

    }
    
    /**
     * Método que ejecuta una determinada acción dependiendo del botón
     * @param evento Atributo que hace referencia a la acción de un botón en caso de ser pulsado
     */

    @Override
    public void actionPerformed(ActionEvent evento) {
        switch (buttons.valueOf(evento.getActionCommand())) {
            case buttonInsert:
                new StatusRegisterControlator(a).openUserRegister();
                providerRole.setVisible(false);
                break;
            case btnUpdate:
                selection = providerRole.tableOrder.getSelectedRow();
                user.orderStatus(String.valueOf(providerRole.tableOrder.getValueAt(selection, 0)), String.valueOf(providerRole.tableOrder.getValueAt(selection, 1)));
                ;
                break;
            case buttonDelete:
                selection = providerRole.tableOrder.getSelectedRow();
                user.deleteOrderStatus(String.valueOf(providerRole.tableOrder.getValueAt(selection, 0)));
                break;
            case buttonBack:
                new AdminRoleControlator(role).openUserRegister();
                providerRole.setVisible(false);
                break;

        }
    }
    
    /**
     *
     * Método que etiqueta los botones para usarlos en la clase actionPerformed
     */
    public enum buttons {
        btnUpdate, buttonInsert, buttonBack, buttonDelete
    }
}
