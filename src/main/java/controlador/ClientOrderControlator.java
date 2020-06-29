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
import vista.ClientOrder;
import vista.ClientRole;

import vista.ClientSearch;

/**
 *
 * @author jabre
 */
public class ClientOrderControlator implements ActionListener {

    private ClientOrder providerRole;
    private ClientRole roleP = new ClientRole();
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
    private String comboSelectionP;
    private String comboSelectionC;
    private String comboSelectionE;
    private String comboSelectionD;
    private String comboSelectionY;
    private String comboSelectionM;
    private int min;
    private int max;

    public ClientOrderControlator() {

    }

    public ClientOrderControlator(ClientOrder user) {
        this.providerRole = user;
    }

    public void openUserRegister(String nombre) {
        providerRole.setTitle("Registo Usuario");
        providerRole.setLocationRelativeTo(null);
        providerRole.setVisible(true);
        order.getAllClientOrders(providerRole.tableClient, nombre);
        category.getComboCategory(providerRole.comboCat);
        provid.getComboProviders(providerRole.comboProv);
        order.getComboOrdersByDate(providerRole.comboDate);

        this.nombre = nombre;
        this.providerRole.buttonBack.setActionCommand("buttonBack");
        this.providerRole.buttonBack.addActionListener(this);
        this.providerRole.buttonOrder.setActionCommand("buttonOrder");
        this.providerRole.buttonOrder.addActionListener(this);
        this.providerRole.buttonSearch.setActionCommand("buttonSearch");
        this.providerRole.buttonSearch.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        switch (buttons.valueOf(evento.getActionCommand())) {
            case buttonOrder:
                selection = providerRole.tableClient.getSelectedRow();
                if(String.valueOf(providerRole.tableClient.getValueAt(selection, 5)).equals("En Proceso")||String.valueOf(providerRole.tableClient.getValueAt(selection, 5)).equals("Procesado")){
                  p.productMin(String.valueOf(providerRole.tableClient.getValueAt(selection, 1)), Integer.parseInt(String.valueOf(providerRole.tableClient.getValueAt(selection, 4))));
                  order.updateStatusCanceled(String.valueOf(providerRole.tableClient.getValueAt(selection, 0)));
                    
                };

                break;

            case buttonSearch:
                
                comboSelectionP = (String) this.providerRole.comboProv.getSelectedItem().toString();
                comboSelectionC = (String) this.providerRole.comboCat.getSelectedItem().toString();
                comboSelectionE = (String) this.providerRole.comboCondi.getSelectedItem().toString();
                comboSelectionD = (String) this.providerRole.comboDate.getSelectedItem().toString();
                
                order.clearOrders(providerRole.tableClient);
                System.out.print("prueba");
                if (comboSelectionP.equals("Proveedor")) {
                    System.out.print("pan");
                    if (comboSelectionC.equals("Categoria")) {
                        if (comboSelectionC.equals("Estado")) {
                            if (comboSelectionD.equals("fecha")) {
                                System.out.print("a");
                             break;
                            } else {
                                order.getAllClientOrdersByDate(providerRole.tableClient, nombre, comboSelectionD);
                            }

                        } else {
                            order.getAllClientOrdersByStatus(providerRole.tableClient, nombre, comboSelectionE);
                            if (comboSelectionD.equals("fecha")) {
                                break;
                            } else {
                                order.getAllClientOrdersByDate(providerRole.tableClient, nombre, comboSelectionD);
                            }
                        }
                    } else {
                        order.getAllClientOrdersByCategory(providerRole.tableClient, nombre, comboSelectionC);
                        if (comboSelectionC.equals("Estado")) {
                            if (comboSelectionD.equals("fecha")) {
                                break;
                            } else {
                                order.getAllClientOrdersByDate(providerRole.tableClient, nombre, comboSelectionD);
                            }
                        } else {
                            order.getAllClientOrdersByStatus(providerRole.tableClient, nombre, comboSelectionE);
                            if (comboSelectionD.equals("fecha")) {
                            } else {
                                order.getAllClientOrdersByDate(providerRole.tableClient, nombre, comboSelectionD);
                            }
                        }
                    }

                } else {
                    
                    order.getAllClientOrdersByProv(providerRole.tableClient, nombre, comboSelectionP);
                    if (comboSelectionC.equals("Categoria")) {
                        if (comboSelectionC.equals("Estado")) {
                            if (comboSelectionD.equals("fecha")) {
                                break;
                            } else {
                                order.getAllClientOrdersByDate(providerRole.tableClient, nombre, comboSelectionD);
                            }
                        } else {
                            order.getAllClientOrdersByStatus(providerRole.tableClient, nombre, comboSelectionE);
                            if (comboSelectionD.equals("fecha")) {
                            } else {
                                order.getAllClientOrdersByDate(providerRole.tableClient, nombre, comboSelectionD);
                            }
                        }
                    } else {
                        order.getAllClientOrdersByCategory(providerRole.tableClient, nombre, comboSelectionC);
                        if (comboSelectionC.equals("Estado")) {
                            if (comboSelectionD.equals("fecha")) {
                             break;
                            } else {
                                order.getAllClientOrdersByDate(providerRole.tableClient, nombre, comboSelectionD);
                            }

                        } else {
                            order.getAllClientOrdersByStatus(providerRole.tableClient, nombre, comboSelectionE);
                            if (comboSelectionD.equals("fecha")) {
                            } else {
                                order.getAllClientOrdersByDate(providerRole.tableClient, nombre, comboSelectionD);
                            }
                        }
                    }
                }

                break;
            case buttonBack:
                new ClientRoleControlator(roleP).openUserRegister(nombre);
                providerRole.setVisible(false);
                break;

        }
    }

    public enum buttons {

        buttonOrder, buttonBack, buttonSearch

    }
}
