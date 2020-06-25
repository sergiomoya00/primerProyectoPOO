/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import com.itextpdf.text.DocumentException;
import dao.EmailNotification;

import dao.CategoryDAO;

import dao.OrdersDAO;
import dao.ProductsDAO;

import dao.UserDAO;

import dao.ProviderDAO;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.PDF;
import modelo.Categories;
import modelo.Providers;
import vista.ChooseRole;
import vista.ClientSearch;
import vista.ProviderRole;

import vista.ClientSearch;

import vista.ClientSearch;

import java.awt.event.ActionListener;

import vista.ProviderRole;

import vista.ChooseRole;

import vista.ClientSearch;

/**
 *
 * @author jabre
 */
public class ClientSearchControlator implements ActionListener {

    private ClientSearch providerRole;
    private ProviderRole roleP = new ProviderRole();
    private ChooseRole role = new ChooseRole();
    private ClientSearch client = new ClientSearch();
    private ProductsDAO p = new ProductsDAO();

    private OrdersDAO order = new OrdersDAO();
    private UserDAO user = new UserDAO();
    private PDF P = new PDF();

    private ProviderDAO provid = new ProviderDAO();
    private CategoryDAO category = new CategoryDAO();

    private String nombre;

    private EmailNotification email = new EmailNotification();

    private int selection;
    private String comboSelectionT;
    private String comboSelectionC;
    private String comboSelectionE;
    private String comboSelectionL;
    private int min;
    private int max;

    public ClientSearchControlator() {

    }

    public ClientSearchControlator(ClientSearch user) {
        this.providerRole = user;
    }

    public void openUserRegister(String nombre) {
        providerRole.setTitle("Registo Usuario");
        providerRole.setLocationRelativeTo(null);
        providerRole.setVisible(true);

        p.getAllProducts(providerRole.tableClient);

        this.nombre = nombre;
        this.providerRole.buttonOrder.setActionCommand("buttonOrder");
        this.providerRole.buttonOrder.addActionListener(this);
        this.providerRole.buttonBack.setActionCommand("buttonBack");
        this.providerRole.buttonBack.addActionListener(this);

        //p.getAllProducts(providerRole.tableClient);
        provid.getComboProvidersUbication(providerRole.comboPlace);
        p.getComboProductPlace(providerRole.comboPlace);
        category.getComboCategory(providerRole.comboCat);
        category.getCategories();
        provid.getProviders();

        this.nombre = nombre;
        this.providerRole.buttonOrder.setActionCommand("buttonOrder");
        this.providerRole.buttonOrder.addActionListener(this);
        this.providerRole.buttonBack.setActionCommand("buttonBack");
        this.providerRole.buttonBack.addActionListener(this);
        this.providerRole.buttonSearch.setActionCommand("buttonSearch");
        this.providerRole.buttonSearch.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        switch (buttons.valueOf(evento.getActionCommand())) {
            case buttonOrder:
                selection = providerRole.tableClient.getSelectedRow();
                if (p.getProductQuatity(String.valueOf(providerRole.tableClient.getValueAt(selection, 0))) >= Integer.parseInt(providerRole.txtQuantity.getText())) {
                    order.insertOrder(nombre, String.valueOf(providerRole.tableClient.getValueAt(selection, 1)), String.valueOf(providerRole.tableClient.getValueAt(selection, 0)), Integer.parseInt(providerRole.txtQuantity.getText()));
                    p.productMin(String.valueOf(providerRole.tableClient.getValueAt(selection, 0)), Integer.parseInt(providerRole.txtQuantity.getText()));

                    try {
                        //P.crearPDF(nombre, String.valueOf(providerRole.tableClient.getValueAt(selection, 2)), Integer.parseInt(providerRole.txtQuantity.getText()), p.calcTotalPrice(String.valueOf(providerRole.tableClient.getValueAt(selection, 0)), Integer.parseInt(providerRole.txtQuantity.getText())));
                        email.sendEmail(user.getEmail(nombre), nombre);
                        String url = "C:\\Users\\jabre\\OneDrive\\Documentos\\NetBeansProjects\\PRIMERPROYECTOPOO\\HTMLGMaps\\simple_map.html";
                        ProcessBuilder p = new ProcessBuilder();
                        p.command("cmd.exe", "/c", url);
                        p.start();
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(ClientSearchControlator.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(ClientSearchControlator.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } else {
                    JOptionPane.showMessageDialog(client, "La cantidad solicitada no puede ser completada");
                }

                break;

            case buttonSearch:
                selection = providerRole.tableClient.getSelectedRow();
                comboSelectionT = (String) providerRole.comboType.getSelectedItem();
                comboSelectionC = (String) providerRole.comboCat.getSelectedItem();
                comboSelectionE = (String) providerRole.comboCondi.getSelectedItem();
                comboSelectionL = (String) providerRole.comboPlace.getSelectedItem();
                min = Integer.parseInt(providerRole.txtmin.getText());
                max = Integer.parseInt(providerRole.txtmax.getText());

                Categories newCategory = new Categories();
                newCategory.setName(comboSelectionC);

                Providers newProvider = new Providers();
                newProvider.setUbication(comboSelectionL);

                if (comboSelectionT.equals("Producto")) {
                    p.getProductByType(providerRole.tableClient, comboSelectionT);
                }
                if (comboSelectionT.equals("Servicio")) {
                    p.getProductByType(providerRole.tableClient, comboSelectionT);
                }
                if (comboSelectionC.equals(category.search(newCategory).get(0).getName())) {
                    p.getProductByCategory(providerRole.tableClient, comboSelectionC);
                    category.search(newCategory).clear();
                }
                if (comboSelectionE.equals("Nuevo")) {
                    p.getProductByCondition(providerRole.tableClient, comboSelectionE);
                }
                if (comboSelectionE.equals("Usado")) {
                    p.getProductByCondition(providerRole.tableClient, comboSelectionE);
                }
                if (comboSelectionE.equals("Reparado")) {
                    p.getProductByCondition(providerRole.tableClient, comboSelectionE);
                }
                if (comboSelectionL.equals(provid.search(newProvider).getUbication())) {
                    p.getProductByPlace(providerRole.tableClient, comboSelectionL);
                }
                if (providerRole.txtmin.getText().isEmpty() == false & providerRole.txtmax.getText().isEmpty() == false) {
                    p.getProductByPrice(providerRole.tableClient, min, max);
                } else {
                    JOptionPane.showMessageDialog(client, "Seleccione correctamente");
                }
                break;
            case buttonBack:
                new ProviderRoleControlator(roleP).openUserRegister();
                break;

        }}
    

    

    public enum buttons {

        buttonOrder, buttonBack, buttonSearch

    }
}
