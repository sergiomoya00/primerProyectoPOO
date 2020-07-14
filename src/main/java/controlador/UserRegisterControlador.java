/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.UserDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.text.View;
import vista.AdminCategory;
import vista.AdminOrderStatus;
import vista.AdminProvider;
import vista.AdminRole;
import vista.AdminUser;
import vista.AdministratorLogIn;
import vista.CategoryRegister;
import vista.ChooseRole;
import vista.ClientLogIn;
import vista.ClientRegister;
import vista.ClientSearch;
import vista.ProductRegister;
import vista.ProviderConsultClients;
import vista.ProviderLogIn;
import vista.ProviderProducts;
import vista.ProviderRegister;
import vista.ProviderRole;
import vista.ProviderUser;
import vista.StatusRegister;
import vista.UserRegister;

/**
 *
 * @author jabre
 */
public class UserRegisterControlador implements ActionListener {
    
    /**
     *
     * Atributos necesarios para la implementación de los métodos de la clase
     */

    private UserRegister userRegister;
    private UserDAO user = new UserDAO();
    private ProviderLogIn role = new ProviderLogIn();
    private ClientLogIn role2 = new ClientLogIn();
    private ProviderLogIn provider = new ProviderLogIn();
    private ClientRegister client = new ClientRegister();
    private AdminRole rol = new AdminRole();
    private AdminUser adminuser = new AdminUser();
    private String opcion;
    
    /**
     *
     * Constructor vacío de la clase
     */

    public UserRegisterControlador() {

    }
    
    /**
     * Constructor de la clase
     *
     * @param user atributo que hace referencia a la ventana de tipo UserRegister
     */

    public UserRegisterControlador(UserRegister user) {
        this.userRegister = user;
    }
    
    /**
     * Método que inicializa la ventana UserRegister
     */

    public void openUserRegister(String opcion) {
        userRegister.setTitle("Registo Usuario");
        userRegister.setLocationRelativeTo(null);
        userRegister.setVisible(true);

        this.opcion = opcion;
        this.userRegister.btnRegister.setActionCommand("btnRegister");
        this.userRegister.btnRegister.addActionListener(this);
        this.userRegister.btnLogIn.setActionCommand("btnLogIn");
        this.userRegister.btnLogIn.addActionListener(this);

    }
    
    /**
     * Método que ejecuta una determinada acción dependiendo del botón
     *
     * @param evento Atributo que hace referencia a la acción de un botón en
     * caso de ser pulsado
     */
    
    @Override
    public void actionPerformed(ActionEvent evento) {
        switch (buttons.valueOf(evento.getActionCommand())) {
            case btnRegister:
                user.userRegister(userRegister.txtName.getText(), userRegister.txtEmail.getText(), userRegister.txtPassword.getText(), userRegister.comboActive.getSelectedItem().toString());
                if (userRegister.comboActive.getSelectedItem().toString().equals("Proveedor")) {
                    if (opcion.equals("provider")) {
                        new ProviderLogInControlator(provider).openUserRegister();
                    }
                    if (opcion.equals("b")) {
                        new AdminUserControlator(adminuser).openUserRegister();
                        userRegister.setVisible(false);
                    }
                }
                if (userRegister.comboActive.getSelectedItem().toString().equals("Cliente")) {
                    if (opcion.equals("b")) {
                        new AdminUserControlator(adminuser).openUserRegister();
                        userRegister.setVisible(false);
                    }
                    if (opcion.equals("client")) {
                        new ClientRegisterControlator(client).openClientRegister(userRegister.txtName.getText(), "a");
                    }
                }
                if (userRegister.comboActive.getSelectedItem().toString().equals("Administracion")) {
                    if (opcion.equals("b")) {
                        new AdminUserControlator(adminuser).openUserRegister();
                        userRegister.setVisible(false);
                    }
                    if (opcion.equals("administracion")) {
                        new AdminRoleControlator(rol).openUserRegister();
                    }
                }
                break;
            case btnLogIn:
                if (opcion.equals("provider")) {
                    new ProviderLogInControlator(role).openUserRegister();
                    userRegister.setVisible(false);
                }
                if (opcion.equals("client")) {
                    new ClientLogInControlator(role2).openUserRegister();
                    userRegister.setVisible(false);
                }
                if (opcion.equals("b")) {
                    new AdminUserControlator(adminuser).openUserRegister();
                    userRegister.setVisible(false);
                }
                if (opcion.equals("administracion")) {
                    new AdminRoleControlator(rol).openUserRegister();
                    userRegister.setVisible(false);
                }
                break;

        }
    }
    
    /**
     *
     * Método que etiqueta los botones para usarlos en la clase actionPerformed
     */

    public enum buttons {
        btnRegister,
        btnLogIn
    }

}
