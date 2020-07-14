/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import com.google.maps.errors.ApiException;
import dao.ProviderDAO;
import dao.UserDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import vista.AdminProvider;
import vista.ProviderRegister;
import vista.UserRegister;

/**
 *
 * @author jabre
 */
public class ProviderRegisterControlator implements ActionListener {
    
    /**
     *
     * Atributos necesarios para la implementación de los métodos de la clase
     */

    private ProviderRegister providerRegister;
    private AdminProvider p = new AdminProvider();
    private ProviderDAO provider = new ProviderDAO();
    private UserDAO user = new UserDAO();
    private String nombre;
    
    /**
     *
     * Constructor vacío de la clase
     */

    public ProviderRegisterControlator() {

    }
    
    /**
     * Constructor de la clase
     *
     * @param user atributo que hace referencia a la ventana de tipo ProviderRegister
     */

    public ProviderRegisterControlator(ProviderRegister user) {
        this.providerRegister = user;
    }
    
    /**
     * Método que inicializa la ventana ProviderRegister
     */

    public void openProviderRegister(String nombre) {
        providerRegister.setTitle("Registo Proveedor");
        providerRegister.setLocationRelativeTo(null);
        providerRegister.setVisible(true);
        user.getComboUserProviders(providerRegister.comboUser);

        this.nombre = nombre;
        this.providerRegister.nextButton.setActionCommand("nextButton");
        this.providerRegister.nextButton.addActionListener(this);
        this.providerRegister.buttonBack.setActionCommand("buttonBack");
        this.providerRegister.buttonBack.addActionListener(this);

    }
    
    /**
     *
     * Método que etiqueta los botones para usarlos en la clase actionPerformed
     */

    public enum buttons {
        nextButton,
        buttonBack
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
            case nextButton:
                provider.providerRegister(providerRegister.txtId.getText(), providerRegister.comboUser.getSelectedItem().toString(),providerRegister.txtCompany.getText());
                 {
                    try {
                        provider.providerInformationRegister(providerRegister.txtId.getText(), providerRegister.txtProvince.getText(), providerRegister.txtcanton.getText(), providerRegister.txtDistrict.getText(), providerRegister.txtInfo.getText(), Integer.parseInt(providerRegister.txtphone.getText()), providerRegister.txtemail.getText(), providerRegister.txtWeb.getText(), providerRegister.txtHora.getText(), providerRegister.txtSocialM.getText());
                    } catch (ApiException ex) {
                        Logger.getLogger(ProviderRegisterControlator.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(ProviderRegisterControlator.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(ProviderRegisterControlator.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                break;

            case buttonBack:
                new AdminProviderControlator(p).openUserRegister();
                providerRegister.setVisible(false);
                break;

        }
    }
}
