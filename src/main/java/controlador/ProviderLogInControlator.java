/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.ProviderDAO;
import dao.ProviderList;
import dao.UserDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import vista.ChooseRole;
import vista.ClientRegister;
import vista.ProviderLogIn;
import vista.ProviderRegister;
import vista.ProviderRole;
import vista.UserRegister;

/**
 *
 * @author jabre
 */
public class ProviderLogInControlator implements ActionListener {
    
    /**
     *
     * Atributos necesarios para la implementación de los métodos de la clase
     */

    private ProviderLogIn userRegister;
    private UserDAO user = new UserDAO();
    private ProviderDAO providerUser = new ProviderDAO();
    private UserRegister userR = new UserRegister();
    private ChooseRole role = new ChooseRole();
    private ProviderRole p = new ProviderRole();
    
    /**
     *
     * Constructor vacío de la clase
     */

    public ProviderLogInControlator() {

    }
    
    /**
     * Constructor de la clase
     *
     * @param user atributo que hace referencia a la ventana de tipo ProviderLogIn
     */

    public ProviderLogInControlator(ProviderLogIn user) {
        this.userRegister = user;
    }
    
    /**
     * Método que inicializa la ventana ProviderLogIn
     */

    public void openUserRegister() {
        userRegister.setTitle("Ingreso Proveedor");
        userRegister.setLocationRelativeTo(null);
        ProviderList.getInstance().getUserList().clear();
        userRegister.setVisible(true);

        this.userRegister.menuButton.setActionCommand("menuButton");
        this.userRegister.menuButton.addActionListener(this);
        this.userRegister.btnRegister.setActionCommand("btnRegister");
        this.userRegister.btnRegister.addActionListener(this);
        this.userRegister.buttonBack.setActionCommand("buttonBack");
        this.userRegister.buttonBack.addActionListener(this);

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
            case menuButton:
                if (user.logIn(userRegister.txt_email.getText(), userRegister.txtPassword.getText()) == true) {
                    if (user.getRole(userRegister.txt_email.getText()).equals("Proveedor")) {
                        ProviderList.getInstance().getProvidersUser(userRegister.txt_email.getText());
                        new ProviderRoleControlator(p).openUserRegister();
                        userRegister.setVisible(false);
                    } else {
                        JOptionPane.showMessageDialog(userRegister, "Solo los usuarios proveedores pueden ingresar");
                    }
                }

                break;
            case buttonBack:
                new ChooseRoleControlator(role).openChooseRole();
                userRegister.setVisible(false);
                break;
            case btnRegister:
                new UserRegisterControlador(userR).openUserRegister("provider");
                userRegister.setVisible(false);
                break;

        }
    }
    
    /**
     *
     * Método que etiqueta los botones para usarlos en la clase actionPerformed
     */

    public enum buttons {
        menuButton,
        buttonBack,
        btnRegister
    }
}
