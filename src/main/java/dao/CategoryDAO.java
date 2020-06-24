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

    CategoryRegister providerRegister;
    AdminUser admin;
    Conexion conexion = new Conexion();
    Connection cin = conexion.getConnection();
    PreparedStatement ps;
    Categories categ;
    List<Categories> category = new ArrayList<>();

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

    public Collection<Categories> getCategories() {
        String poi = "SELECT nombre FROM categorias";
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

    public Categories search(Categories newCategory) {
        
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
        return results.get(0);
    }

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
