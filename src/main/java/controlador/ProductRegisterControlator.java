/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.CategoryDAO;
import dao.ProductsDAO;
import dao.ProviderDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import vista.AdminCategory;
import vista.CategoryRegister;
import vista.ProductRegister;
import vista.ProviderProducts;

/**
 *
 * @author jabre
 */
public class ProductRegisterControlator implements ActionListener {
    
    /**
     *
     * Atributos necesarios para la implementación de los métodos de la clase
     */

    private ProductRegister userRegister;
    private byte[] foto;
    private ProductsDAO product = new ProductsDAO();
    private ProviderDAO provider = new ProviderDAO();
    private CategoryDAO category = new CategoryDAO();
    private ProviderProducts role = new ProviderProducts();
    
    /**
     *
     * Constructor vacío de la clase
     */
    
    public ProductRegisterControlator() {

    }
    
    /**
     * Constructor de la clase
     *
     * @param user atributo que hace referencia a la ventana de tipo ProductRegister
     */

    public ProductRegisterControlator(ProductRegister user) {
        this.userRegister = user;
    }
    
    /**
     * Método que inicializa la ventana ProductRegister
     */

    public void openUserRegister() {
        userRegister.setTitle("Registo Categoria");
        userRegister.setLocationRelativeTo(null);
        userRegister.setVisible(true);
        provider.getComboProviders(userRegister.comboProveedor);
        category.getComboCategory(userRegister.comboCategory);

        this.userRegister.buttonInsert.setActionCommand("buttonInsert");
        this.userRegister.buttonInsert.addActionListener(this);
        this.userRegister.buttonPhoto.setActionCommand("buttonPhoto");
        this.userRegister.buttonPhoto.addActionListener(this);
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
                File rut = new File(userRegister.txtRoot.getText());
                try {
                    byte[] icono = new byte[(int) rut.length()];
                    InputStream input = new FileInputStream(rut);
                    input.read(icono);
                    foto = icono;
                } catch (Exception ex) {
                    foto = null;
                }
                product.insertProduct(userRegister.txtId.getText(), userRegister.comboProveedor.getSelectedItem().toString(), userRegister.txtName.getText(), userRegister.txtDescription.getText(), userRegister.comboType.getSelectedItem().toString(), userRegister.comboCategory.getSelectedItem().toString(), Integer.parseInt(userRegister.txtQuantity.getText()), Integer.parseInt(userRegister.txtUnitary.getText()), Integer.parseInt(userRegister.txtDeliver.getText()),foto, userRegister.comboStatus.getSelectedItem().toString());
                break;
            case buttonPhoto:
                JFileChooser j = new JFileChooser();
                FileNameExtensionFilter fil = new FileNameExtensionFilter("JPG, PNG & GIF", "jpg", "png", "gif");
                j.setFileFilter(fil);

                int s = j.showOpenDialog(userRegister);
                if (s == JFileChooser.APPROVE_OPTION) {
                    String ruta = j.getSelectedFile().getAbsolutePath();
                    userRegister.txtRoot.setText(ruta);
                }
                break;
            case buttonBack:
                new ProviderProductsControlator(role).openUserRegister();
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
        buttonBack,
        buttonPhoto
    }
}
