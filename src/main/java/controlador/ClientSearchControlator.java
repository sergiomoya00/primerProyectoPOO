/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.OrdersDAO;
import dao.ProductsDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.ChooseRole;
import vista.ClientSearch;
import vista.ProviderRole;

/**
 *
 * @author jabre
 */
public class ClientSearchControlator implements ActionListener{
    private ClientSearch providerRole;
    private ChooseRole role = new ChooseRole();
    private ProductsDAO p=new ProductsDAO();
    private OrdersDAO order=new OrdersDAO();
    private String nombre;
    
    private int selection;

    public ClientSearchControlator() {
        
    }
    public ClientSearchControlator(ClientSearch user) {
        this.providerRole=user;
    }
    
    
    public void openUserRegister(String nombre){
    providerRole.setTitle("Registo Usuario");
    providerRole.setLocationRelativeTo(null);
    providerRole.setVisible(true);
    p.getAllProducts(providerRole.tableClient);
    
    this.nombre=nombre;
    this.providerRole.buttonOrder.setActionCommand("buttonOrder");
    this.providerRole.buttonOrder.addActionListener(this);
    this.providerRole.buttonBack.setActionCommand("buttonBack");
    this.providerRole.buttonBack.addActionListener(this);
    
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
       switch(buttons.valueOf(evento.getActionCommand())){
           case buttonOrder:
               selection=providerRole.tableClient.getSelectedRow();
               order.insertOrder(nombre, String.valueOf(providerRole.tableClient.getValueAt(selection, 1)), String.valueOf(providerRole.tableClient.getValueAt(selection, 0)), Integer.parseInt(providerRole.txtQuantity.getText()));
               break;
       
       }
    }
    
    public enum buttons{
    buttonOrder,buttonBack
    }    
}
