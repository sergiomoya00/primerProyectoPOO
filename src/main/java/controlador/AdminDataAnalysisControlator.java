/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.ClientDAO;
import dao.ProductsDAO;
import dao.ProviderDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import vista.AdminDataAnalysis;
import vista.AdminProvider;
import vista.AdminRole;
import vista.ProviderRegister;
import vista.ProviderRole;

/**
 *
 * @author samoy
 */
public class AdminDataAnalysisControlator implements ActionListener {

    private AdminDataAnalysis dataAnalysis;
    private AdminRole role = new AdminRole();
    private ProviderRole p = new ProviderRole();
    private ProviderRegister a = new ProviderRegister();
    private ProviderDAO provider = new ProviderDAO();
    private ProductsDAO product = new ProductsDAO();
    private ClientDAO client = new ClientDAO();
    private int selection;
    private MouseEvent mouse;

    public AdminDataAnalysisControlator() {
    }

    public AdminDataAnalysisControlator(AdminDataAnalysis user) {
        this.dataAnalysis = user;
    }

    public void openUserRegister() {
        dataAnalysis.setTitle("Registo Usuario");
        dataAnalysis.setLocationRelativeTo(null);
        dataAnalysis.setVisible(true);

        this.dataAnalysis.buttonBack.setActionCommand("buttonBack");
        this.dataAnalysis.buttonBack.addActionListener(this);
        this.dataAnalysis.buttonProduct.setActionCommand("buttonProduct");
        this.dataAnalysis.buttonProduct.addActionListener(this);
        this.dataAnalysis.buttonProvider.setActionCommand("buttonProvider");
        this.dataAnalysis.buttonProvider.addActionListener(this);
        this.dataAnalysis.buttonClient.setActionCommand("buttonClient");
        this.dataAnalysis.buttonClient.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        switch (buttons.valueOf(evento.getActionCommand())) {
            case buttonProduct:
                
                break;
            case buttonProvider:
                
                break;
            case buttonClient:
                
                break;
            case buttonBack:
                new AdminRoleControlator(role).openUserRegister();
                dataAnalysis.setVisible(false);
                break;
        }
    }

    public enum buttons {
        buttonBack, buttonProduct, buttonProvider, buttonClient
    }
}
