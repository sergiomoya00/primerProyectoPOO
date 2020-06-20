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
import vista.AdminRole;
import vista.AdministratorLogIn;
import vista.ChooseRole;
import vista.ClientLogIn;

/**
 *
 * @author jabre
 */
public class AdministratorLogInControlator implements ActionListener {

    private AdministratorLogIn userRegister;
    private String opcion;
    private UserDAO user = new UserDAO();
    private ChooseRole role = new ChooseRole();
    private AdminRole p = new AdminRole();

    public AdministratorLogInControlator() {

    }

    public AdministratorLogInControlator(AdministratorLogIn user) {
        this.userRegister = user;
    }

    public void openAdministratorLogIn(String opcion) {
        userRegister.setTitle("Ingreso Administrador");
        userRegister.setLocationRelativeTo(null);
        userRegister.setVisible(true);

        this.opcion = opcion;
        this.userRegister.menuButton.setActionCommand("menuButton");
        this.userRegister.menuButton.addActionListener(this);
        this.userRegister.buttonBack.setActionCommand("buttonBack");
        this.userRegister.buttonBack.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        switch (buttons.valueOf(evento.getActionCommand())) {
            case menuButton:

                if (user.logIn(userRegister.txt_email.getText(), userRegister.txtPassword.getText()) == true) {
                    if (user.getRole(userRegister.txt_email.getText()).equals("Administracion")) {
                        if (opcion.equals("a")) {
                            new AdminRoleControlator(p).openUserRegister();
                            userRegister.setVisible(false);
                        }
                        if (opcion.equals("b")) {
                            new ChooseRoleControlator(role).openChooseRole();
                            userRegister.setVisible(false);
                        }
                    } else {
                        JOptionPane.showMessageDialog(userRegister, "Solo los usuarios administradores pueden ingresar");
                    }

                }

                break;
            case buttonBack:
                new ChooseRoleControlator(role).openChooseRole();
                userRegister.setVisible(false);
                break;

        }
    }

    public enum buttons {
        menuButton, buttonBack

    }
}
