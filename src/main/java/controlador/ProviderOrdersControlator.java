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
import vista.ProviderOrders;
import vista.ProviderRole;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

/**
 *
 * @author samoy
 */
public class ProviderOrdersControlator implements ActionListener {

    private ProviderOrders providerOrders;
    private ProviderRole p = new ProviderRole();
    private OrdersDAO orders = new OrdersDAO();
    private ProductsDAO products = new ProductsDAO();
    private int selection;

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
                if (providerOrders.comboOrder.getSelectedItem().toString().equals("En proceso")) {
                    orders.getSpecificOrderStatus(providerOrders.tableOrder, "En proceso");
                }
                if (providerOrders.comboOrder.getSelectedItem().toString().equals("Procesado")) {
                    orders.getSpecificOrderStatus(providerOrders.tableOrder, "Procesado");
                }
                if (providerOrders.comboOrder.getSelectedItem().toString().equals("Entregado")) {
                    orders.getSpecificOrderStatus(providerOrders.tableOrder, "Entregado");
                }
                if (providerOrders.comboOrder.getSelectedItem().toString().equals("Cancelado")) {
                    orders.getSpecificOrderStatus(providerOrders.tableOrder, "Cancelado");
                }
                break;
            case buttonEntregado:
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();
                String date =  dtf.format(now);
                selection = providerOrders.tableOrder.getSelectedRow();
                orders.orderStatus(String.valueOf(providerOrders.tableOrder.getValueAt(selection, 0)), "Entregado");
                orders.orderDeliveryDate(String.valueOf(providerOrders.tableOrder.getValueAt(selection, 0)), date);
                break;
            case buttonProcesado:
                int quantity = products.getProductQuatity(String.valueOf(providerOrders.tableOrder.getValueAt(selection, 1)));
                int dif = orders.getOrderQuatity((String.valueOf(providerOrders.tableOrder.getValueAt(selection, 1))));
                int total = quantity - dif;
                selection = providerOrders.tableOrder.getSelectedRow();
                orders.orderStatus(String.valueOf(providerOrders.tableOrder.getValueAt(selection, 0)), "Procesado");
                products.updateProductQuantity(String.valueOf(providerOrders.tableOrder.getValueAt(selection, 3)), total);
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
