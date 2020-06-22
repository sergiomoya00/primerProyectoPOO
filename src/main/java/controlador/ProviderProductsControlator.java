/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.CategoryDAO;
import dao.ProductsDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import vista.AdminCategory;
import vista.AdminRole;
import vista.CategoryRegister;
import vista.ProductRegister;
import vista.ProviderProducts;
import vista.ProviderRole;

/**
 *
 * @author jabre
 */
public class ProviderProductsControlator implements ActionListener {

    private ProviderProducts providerRole;
    private AdminRole role = new AdminRole();
    private ProviderRole p = new ProviderRole();
    private ProductRegister a = new ProductRegister();
    private ProductsDAO user = new ProductsDAO();
    private int selection;
    private MouseEvent mouse;

    public ProviderProductsControlator() {

    }

    public ProviderProductsControlator(ProviderProducts user) {
        this.providerRole = user;
    }

    public void openUserRegister() {
        providerRole.setTitle("Registo Usuario");
        providerRole.setLocationRelativeTo(null);
        user.getAllProducts(providerRole.tableProduct);
        providerRole.setVisible(true);

        this.providerRole.buttonUpdate.setActionCommand("buttonUpdate");
        this.providerRole.buttonUpdate.addActionListener(this);
        this.providerRole.buttonDelete.setActionCommand("buttonDelete");
        this.providerRole.buttonDelete.addActionListener(this);
        this.providerRole.buttonInsert.setActionCommand("buttonInsert");
        this.providerRole.buttonInsert.addActionListener(this);
        this.providerRole.buttonBack.setActionCommand("buttonBack");
        this.providerRole.buttonBack.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        switch (buttons.valueOf(evento.getActionCommand())) {
            case buttonInsert:
                new ProductRegisterControlator(a).openUserRegister();
                providerRole.setVisible(false);
                break;
            case buttonUpdate:
                selection = providerRole.tableProduct.getSelectedRow();
                user.updateProduct(String.valueOf(providerRole.tableProduct.getValueAt(selection, 0)), String.valueOf(providerRole.tableProduct.getValueAt(selection, 1)),String.valueOf( providerRole.tableProduct.getValueAt(selection, 2)), String.valueOf(providerRole.tableProduct.getValueAt(selection, 3)),String.valueOf(providerRole.tableProduct.getValueAt(selection, 4)), String.valueOf(providerRole.tableProduct.getValueAt(selection, 5)), Integer.parseInt(String.valueOf(providerRole.tableProduct.getValueAt(selection, 6))), Integer.parseInt(String.valueOf(providerRole.tableProduct.getValueAt(selection, 7))), Integer.parseInt(String.valueOf(providerRole.tableProduct.getValueAt(selection, 8))), String.valueOf(providerRole.tableProduct.getValueAt(selection, 9)));
                break;
            case buttonDelete:
                selection = providerRole.tableProduct.getSelectedRow();
                user.deleteProduct(String.valueOf(providerRole.tableProduct.getValueAt(selection, 0)));

                break;
            case buttonBack:
                new ProviderRoleControlator(p).openUserRegister();
                providerRole.setVisible(false);
                break;

        }
    }

    public enum buttons {
        buttonUpdate, buttonInsert, buttonBack, buttonDelete
    }
}
