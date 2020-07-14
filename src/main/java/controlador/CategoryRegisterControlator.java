/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.CategoryDAO;
import dao.UserDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.AdminCategory;
import vista.AdminUser;
import vista.AdministratorLogIn;
import vista.CategoryRegister;
import vista.ClientLogIn;
import vista.ClientRegister;
import vista.ProviderLogIn;
import vista.UserRegister;

/**
 *
 * @author jabre
 */
public class CategoryRegisterControlator implements ActionListener {
    
    /**
     *
     * Atributos necesarios para la implementación de los métodos de la clase
     */
    
    private CategoryRegister userRegister;
    private CategoryDAO category = new CategoryDAO();
    private AdminCategory role = new AdminCategory();
    
    /**
     *
     * Constructor vacío de la clase
     */

    public CategoryRegisterControlator() {

    }
    
    /**
     * Constructor de la clase
     *
     * @param user atributo que hace referencia a la ventana de tipo CategoryRegister
     */

    public CategoryRegisterControlator(CategoryRegister user) {
        this.userRegister = user;
    }
    
    /**
     *
     * Método que inicializa la ventana CategoryRegister
     */

    public void openUserRegister() {
        userRegister.setTitle("Registo Categoria");
        userRegister.setLocationRelativeTo(null);
        userRegister.setVisible(true);

        this.userRegister.buttonInsert.setActionCommand("buttonInsert");
        this.userRegister.buttonInsert.addActionListener(this);
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
            case buttonInsert:

                category.categoryRegister(userRegister.txtName.getText(), userRegister.txtDescription.getText(), userRegister.comboActive.getSelectedItem().toString());
                break;
            case buttonBack:
                new AdminCategoryControlator(role).openUserRegister();
                userRegister.setVisible(false);
                break;

        }
    }
    
    /**
     *
     * Método que etiqueta los botones para usarlos en la clase actionPerformed
     */

    public enum buttons {
        buttonInsert,
        buttonBack
    }
}
