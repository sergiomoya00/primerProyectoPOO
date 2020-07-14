/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.Categories;
import vista.AdminUser;
import vista.CategoryRegister;
import vista.ProviderRegister;

/**
 *
 * @author jabre
 */
public class CategoryDAO {
    
    /**
     *
     * Atributos necesarios para la implementación de los métodos de la clase
     */

    private CategoryRegister providerRegister;
    private AdminUser admin;
    private Conexion conexion = new Conexion();
    private Connection cin = conexion.getConnection();
    private PreparedStatement ps;
    private Categories categ;
    private List<Categories> category = new ArrayList<>();
    
    /**
     * Método para insertar una categoria en la base de datos
     * @param name Atributo que guarda el nombre a insertar en la base de datos
     * @param description Atributo que guarda la descripcion de la categoria en la base de datos
     * @param status Atributo que guarda el estado de la categoria en la base de datos
     */

    public void categoryRegister(String name, String description, String status) {
        String insertar = "insert into categorias (nombre,descripcion,estado) values (?,?,?) ";

        try {
            ps = cin.prepareCall(insertar);
            ps.setString(1, name);
            ps.setString(2, description);
            ps.setString(3, status);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(providerRegister, "Registrado con exito");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(providerRegister, "No Registrado ");
        }
    }
    
    /**
     * Método para actualizar una categoria en la base de datos
     * @param codigo Atributo que guarda el codigo a actualizar en la base de datos
     * @param name Atributo que guarda el nombre a actualizar en la base de datos
     * @param description Atributo que guarda la descripcion de la categoria en la base de datos
     * @param status Atributo que guarda el estado de la categoria en la base de datos
     */

    public void updateCategory(String codigo, String name, String description, String status) {
        String update = "UPDATE categorias SET  nombre='" + name + "', descripcion='" + description + "', estado='" + status + "' WHERE codigo=" + codigo;
        try {

            ps = cin.prepareStatement(update);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(admin, "Informacion editada con exito");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(admin, e.toString());
        }
    }
    
    /**
     * Método para eliminar una categoria en la base de datos
     * @param codigo Atributo que guarda el codigo de a categoría a eliminar en la base de datos
     */

    public void deleteCategory(String codigo) {
        String update = "Delete from categorias where codigo=" + codigo;
        try {

            ps = cin.prepareStatement(update);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(admin, "Informacion eliminada con exito");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(admin, e.toString());
        }
    }
    
    /**
     * Método insertar en una tabla todas las categorias de la base de datos
     * @param table Atributo que guarda la tabla en la cual se insertarán los datos de la base
     */
    
    public void getAllCategories(JTable table) {
        try {
            DefaultTableModel modelo = new DefaultTableModel();
            table.setModel(modelo);
            ResultSet rs = null;
            String login = "SELECT * FROM categorias ";
            ps = cin.prepareStatement(login);
            rs = ps.executeQuery();
            ResultSetMetaData rsMd = rs.getMetaData();
            int cantidadColumnas = rsMd.getColumnCount();
            modelo.addColumn("ID CATEGORIA");
            modelo.addColumn("NOMBRE");
            modelo.addColumn("DESCRIPCION");
            modelo.addColumn("ESTADO");

            while (rs.next()) {
                Object[] filas = new Object[cantidadColumnas];

                for (int i = 1; i <= cantidadColumnas; i++) {

                    filas[i - 1] = rs.getObject(i);

                }

                modelo.addRow(filas);

            }

        } catch (SQLException ex) {

        }
    }
    
    /**
     * Método para obtener las categorias en una lista agrupadas por nombre
       @return 
     */

    public List<Categories> getCategories() {
        String poi = "SELECT nombre FROM categorias GROUP BY nombre";
        try {

            ps = cin.prepareCall(poi);
            ResultSet result = ps.executeQuery();

            while (result.next()) {
                this.categ = new Categories(result.getString("nombre"));
                category.add(categ);
            }

        } catch (Exception e) {

        }
        return category;
    }
    
    /**
     * Método para obtener las categorias mediante una busqueda
     * @param newCategory Atributo de tipo Categories que sirve como referencia para realizar la busqueda
       @return 
     */

    public List<Categories> search(Categories newCategory) {
        
        List<Categories> results = new ArrayList<>();  
        boolean byName = newCategory.getName() != null && newCategory.getName().length() > 0;

        for (Categories cat : category) {
            boolean add = !(byName);
            if (!add && byName && cat.getName().contains(newCategory.getName())) {
                add = true;
            }
            if (add) {
                results.add(cat);
            }
        }
        return results;
    }
    
    /**
     * Método para obtener las categorias mediante una busqueda
     * @param combo Atributo de tipo JComboBox que da como entrada el combobox donde se cargarán las categorias 
     */

    public void getComboCategory(JComboBox combo) {
        String poi = "SELECT nombre FROM categorias";
        try {

            ps = cin.prepareCall(poi);
            ResultSet result = ps.executeQuery();

            while (result.next()) {
                combo.addItem(result.getString("nombre"));
            }

        } catch (Exception e) {

        }
    }
}
