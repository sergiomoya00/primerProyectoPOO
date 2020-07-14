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

    /**
     *
     * Atributos necesarios para la implementación de los métodos de la clase
     */
    private AdminDataAnalysis dataAnalysis;
    private AdminRole role = new AdminRole();
    private ProviderRole p = new ProviderRole();
    private ProviderRegister a = new ProviderRegister();
    private ProviderDAO provider = new ProviderDAO();
    private ProductsDAO product = new ProductsDAO();
    private ClientDAO client = new ClientDAO();

    /**
     *
     * Constructor vacío de la clase
     */
    public AdminDataAnalysisControlator() {
    }

    /**
     * Constructor de la clase
     * @param user atributo que hace referencia a la ventana de tipo AdminDataAnalysis
     */

    public AdminDataAnalysisControlator(AdminDataAnalysis user) {
        this.dataAnalysis = user;
    }
    
    /**
     *
     * Método que inicializa la ventana AdminDataAnalysis
     */

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
    
    /**
     * Método que ejecuta una determinada acción dependiendo del botón
     * @param evento Atributo que hace referencia a la acción de un botón en caso de ser pulsado
     */

    @Override
    public void actionPerformed(ActionEvent evento) {
        switch (buttons.valueOf(evento.getActionCommand())) {
            case buttonProduct:
                product.showGraph(dataAnalysis.panelGraph);
                break;
            case buttonProvider:
                provider.showGraph(dataAnalysis.panelGraph);
                break;
            case buttonClient:
                client.showGraph(dataAnalysis.panelGraph);
                break;
            case buttonBack:
                new AdminRoleControlator(role).openUserRegister();
                dataAnalysis.setVisible(false);
                break;
        }
    }
    
    /**
     *
     * Método que etiqueta los botones para usarlos en la clase actionPerformed
     */

    public enum buttons {
        buttonBack, buttonProduct, buttonProvider, buttonClient
    }
}
