/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

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

    private ProviderLogIn userRegister;
    private UserDAO user = new UserDAO();
    private UserRegister userR = new UserRegister();
    private ChooseRole role = new ChooseRole();
    private ProviderRole p = new ProviderRole();

    public ProviderLogInControlator() {

    }

    public ProviderLogInControlator(ProviderLogIn user) {
        this.userRegister = user;
    }

    public void openUserRegister() {
        userRegister.setTitle("Ingreso Proveedor");
        userRegister.setLocationRelativeTo(null);
        userRegister.setVisible(true);

        this.userRegister.menuButton.setActionCommand("menuButton");
        this.userRegister.menuButton.addActionListener(this);
        this.userRegister.btnRegister.setActionCommand("btnRegister");
        this.userRegister.btnRegister.addActionListener(this);
        this.userRegister.buttonBack.setActionCommand("buttonBack");
        this.userRegister.buttonBack.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        switch (buttons.valueOf(evento.getActionCommand())) {
            case menuButton:
                if (user.logIn(userRegister.txt_email.getText(), userRegister.txtPassword.getText()) == true) {
                    if (user.getRole(userRegister.txt_email.getText()).equals("Proveedor")) {
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

    public enum buttons {
        menuButton,
        buttonBack,
        btnRegister
    }
}
