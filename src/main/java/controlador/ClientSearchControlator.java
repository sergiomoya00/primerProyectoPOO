/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import com.itextpdf.text.DocumentException;
import dao.EmailNotification;
import dao.OrdersDAO;
import dao.ProductsDAO;
import dao.UserDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.PDF;
import vista.ChooseRole;
import vista.ClientSearch;
import vista.ProviderRole;

/**
 *
 * @author jabre
 */
public class ClientSearchControlator implements ActionListener {

    private ClientSearch providerRole;
    private ChooseRole role = new ChooseRole();
    private ClientSearch client = new ClientSearch();
    private ProductsDAO p = new ProductsDAO();
    private OrdersDAO order = new OrdersDAO();
    private UserDAO user = new UserDAO();
    private PDF P = new PDF();
    private String nombre;
    private EmailNotification email = new EmailNotification();

    private int selection;

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
                        P.crearPDF(nombre, String.valueOf(providerRole.tableClient.getValueAt(selection, 2)), Integer.parseInt(providerRole.txtQuantity.getText()), p.calcTotalPrice(String.valueOf(providerRole.tableClient.getValueAt(selection, 0)), Integer.parseInt(providerRole.txtQuantity.getText())));
                        email.sendEmail(user.getEmail(nombre), nombre);
                        String url = "C:\\Users\\jabre\\OneDrive\\Documentos\\NetBeansProjects\\PRIMERPROYECTOPOO\\HTMLGMaps\\simple_map.html";
                        ProcessBuilder p = new ProcessBuilder();
                        p.command("cmd.exe", "/c", url);
                        p.start();
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(ClientSearchControlator.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (DocumentException ex) {
                        Logger.getLogger(ClientSearchControlator.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                Logger.getLogger(ClientSearchControlator.class.getName()).log(Level.SEVERE, null, ex);
            }

                } else {
                    JOptionPane.showMessageDialog(client, "La cantidad solicitada no puede ser completada");
                }

                break;

        }
    }

    public enum buttons {
        buttonOrder, buttonBack
    }
}
