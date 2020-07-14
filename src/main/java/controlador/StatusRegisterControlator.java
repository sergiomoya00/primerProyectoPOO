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
import vista.AdminOrderStatus;
import vista.AdminRole;
import vista.ProviderRole;
import vista.StatusRegister;
import vista.UserRegister;

/**
 *
 * @author jabre
 */
public class StatusRegisterControlator implements ActionListener{
    
    /**
     *
     * Atributos necesarios para la implementación de los métodos de la clase
     */
    
    private StatusRegister providerRole;
    private AdminOrderStatus role = new AdminOrderStatus();
    private ProviderRole p=new ProviderRole();
    private OrdersDAO order=new  OrdersDAO();
    
    /**
     *
     * Constructor vacío de la clase
     */

    public StatusRegisterControlator() {
        
    }
    
    /**
     * Constructor de la clase
     *
     * @param user atributo que hace referencia a la ventana de tipo StatusRegister
     */
    
    public StatusRegisterControlator(StatusRegister user) {
        this.providerRole=user;
    }
    
    /**
     * Método que inicializa la ventana StatusRegister
     */
    
    public void openUserRegister(){
    providerRole.setTitle("Registo Usuario");
    providerRole.setLocationRelativeTo(null);
    providerRole.setVisible(true);
    order.getComboOrders(providerRole.comboOrder);
    
    this.providerRole.buttonInsert.setActionCommand("buttonInsert");
    this.providerRole.buttonInsert.addActionListener(this);
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
       switch(buttons.valueOf(evento.getActionCommand())){
           case buttonInsert:
               order.orderStatus(providerRole.comboOrder.getSelectedItem().toString(), providerRole.comboStatus.getSelectedItem().toString());
               break;
           case buttonBack:
               new AdminOrderStatusControlator(role).openUserRegister();
               providerRole.setVisible(false);
               break;
           
       
       }
    }
    
    /**
     *
     * Método que etiqueta los botones para usarlos en la clase actionPerformed
     */
    
    public enum buttons{
    btnUpdate,buttonInsert,buttonBack,buttonDelete
    }   
}
