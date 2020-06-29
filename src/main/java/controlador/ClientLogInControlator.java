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
import vista.ClientLogIn;
import vista.ClientRole;
import vista.ClientSearch;
import vista.ProviderLogIn;
import vista.UserRegister;

/**
 *
 * @author jabre
 */
public class ClientLogInControlator implements ActionListener {

    private ClientLogIn userRegister;
    private UserDAO user = new UserDAO();
    private ChooseRole role = new ChooseRole();
    private UserRegister userR = new UserRegister();
    private ClientRole p = new ClientRole();

    public ClientLogInControlator() {

    }

    public ClientLogInControlator(ClientLogIn user) {
        this.userRegister = user;
    }

    public void openUserRegister() {
        userRegister.setTitle("Ingreso Cliente");
        userRegister.setLocationRelativeTo(null);
        userRegister.setVisible(true);

        this.userRegister.menuButton.setActionCommand("menuButton");
        this.userRegister.menuButton.addActionListener(this);
        this.userRegister.registerButton.setActionCommand("registerButton");
        this.userRegister.registerButton.addActionListener(this);
        this.userRegister.backButton.setActionCommand("backButton");
        this.userRegister.backButton.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        switch (buttons.valueOf(evento.getActionCommand())) {
            case menuButton:
                if (user.logIn(userRegister.txt_email.getText(), userRegister.txtPassword.getText()) == true) {
                    if (user.getRole(userRegister.txt_email.getText()).equals("Cliente")) {
                        new ClientRoleControlator(p).openUserRegister(userRegister.txt_email.getText());
                        userRegister.setVisible(false);
                    } else {
                        JOptionPane.showMessageDialog(userRegister, "Solo los usuarios clientes pueden ingresar");
                    }
                }

                break;
            case registerButton:
                new UserRegisterControlador(userR).openUserRegister("client");
                userRegister.setVisible(false);
                break;
            case backButton:
                new ChooseRoleControlator(role).openChooseRole();
                userRegister.setVisible(false);
                break;

        }
    }

    public enum buttons {
        menuButton,
        registerButton,
        backButton
    }
}
