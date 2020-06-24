/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.Categories;
import modelo.Products;
import modelo.Render;
import vista.ProductRegister;
import vista.ProviderProducts;

/**
 *
 * @author jabre
 */
public class ProductsDAO {

    ProductRegister providerRegister;
    Conexion conexion = new Conexion();
    Connection cin = conexion.getConnection();
    PreparedStatement ps;
    ProviderProducts admin = new ProviderProducts();

    public void insertProduct(String idProducto, String idProveedor, String nombreProducto, String descripcion, String tipo, String categoria, int cantidadDisponible, int precioUnitario, int precioEntrega, byte[] foto, String estado) {
        String insertar = "insert into productos (idProducto,idProveedor,nombreProducto,descripcion,tipo,categoria,cantidadDisponible,precioUnitario,precioEntrega,foto,estado) values (?,?,?,?,?,?,?,?,?,?,?)";

        try {
            ps = cin.prepareCall(insertar);
            ps.setString(1, idProducto);
            ps.setString(2, idProveedor);
            ps.setString(3, nombreProducto);
            ps.setString(4, descripcion);
            ps.setString(5, tipo);
            ps.setString(6, categoria);
            ps.setInt(7, cantidadDisponible);
            ps.setInt(8, precioUnitario);
            ps.setInt(9, precioEntrega);
            ps.setBytes(10, foto);
            ps.setString(11, estado);

            ps.executeUpdate();
            JOptionPane.showMessageDialog(providerRegister, "Registrado con exito");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(providerRegister, "No Registrado ");
        }
    }

    public void getAllProducts(JTable table) {

        table.setDefaultRenderer(Object.class, new Render());
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        try {
            ResultSet rs = null;
            String login = "SELECT * FROM productos ";
            ps = cin.prepareStatement(login);
            rs = ps.executeQuery();
            ResultSetMetaData rsMd = rs.getMetaData();
            int cantidadColumnas = rsMd.getColumnCount();
            modelo.addColumn("idProducto");
            modelo.addColumn("idProveedor");
            modelo.addColumn("nombre");
            modelo.addColumn("descripcion");
            modelo.addColumn("tipo");
            modelo.addColumn("categoria");
            modelo.addColumn("cantidad dispinoble");
            modelo.addColumn("precio Unitario");
            modelo.addColumn("precio Entrega");
            modelo.addColumn("foto");
            modelo.addColumn("estado");

            while (rs.next()) {
                Object[] filas = new Object[cantidadColumnas];

                for (int i = 1; i <= cantidadColumnas; i++) {

                    filas[i - 1] = rs.getObject(i);
                    try {
                        byte[] bi = rs.getBytes(10);
                        BufferedImage image = null;
                        InputStream in = new ByteArrayInputStream(bi);
                        image = ImageIO.read(in);
                        ImageIcon imgi = new ImageIcon(image.getScaledInstance(60, 60, 0));
                        filas[9] = new JLabel(imgi);

                    } catch (Exception ex) {
                        filas[9] = new JLabel("No imagen");
                    }

                }

                modelo.addRow(filas);

            }
            table.setModel(modelo);
            table.setRowHeight(60);

        } catch (SQLException ex) {

        }

    }

    public void getProductByType(JTable table, String type) {
        try {
            DefaultTableModel modelo = new DefaultTableModel();
            table.setModel(modelo);
            ResultSet rs = null;
            String login = "SELECT * FROM productos WHERE type='" + type + "'";
            ps = cin.prepareStatement(login);
            rs = ps.executeQuery();
            ResultSetMetaData rsMd = rs.getMetaData();
            int cantidadColumnas = rsMd.getColumnCount();
            modelo.addColumn("idProducto");
            modelo.addColumn("idProveedor");
            modelo.addColumn("nombre");
            modelo.addColumn("descripcion");
            modelo.addColumn("tipo");
            modelo.addColumn("categoria");
            modelo.addColumn("cantidad dispinoble");
            modelo.addColumn("precio Unitario");
            modelo.addColumn("precio Entrega");
            modelo.addColumn("foto");
            modelo.addColumn("estado");

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

    public void getProductByCategory(JTable table, String category) {
        try {
            DefaultTableModel modelo = new DefaultTableModel();
            table.setModel(modelo);
            ResultSet rs = null;
            String login = "SELECT * FROM productos WHERE categoria='" + category + "'";
            ps = cin.prepareStatement(login);
            rs = ps.executeQuery();
            ResultSetMetaData rsMd = rs.getMetaData();
            int cantidadColumnas = rsMd.getColumnCount();
            modelo.addColumn("idProducto");
            modelo.addColumn("idProveedor");
            modelo.addColumn("nombre");
            modelo.addColumn("descripcion");
            modelo.addColumn("tipo");
            modelo.addColumn("categoria");
            modelo.addColumn("cantidad dispinoble");
            modelo.addColumn("precio Unitario");
            modelo.addColumn("precio Entrega");
            modelo.addColumn("foto");
            modelo.addColumn("estado");

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

    public void getProductByCondition(JTable table, String condition) {
        try {
            DefaultTableModel modelo = new DefaultTableModel();
            table.setModel(modelo);
            ResultSet rs = null;
            String login = "SELECT * FROM productos WHERE estado='" + condition + "'";
            ps = cin.prepareStatement(login);
            rs = ps.executeQuery();
            ResultSetMetaData rsMd = rs.getMetaData();
            int cantidadColumnas = rsMd.getColumnCount();
            modelo.addColumn("idProducto");
            modelo.addColumn("idProveedor");
            modelo.addColumn("nombre");
            modelo.addColumn("descripcion");
            modelo.addColumn("tipo");
            modelo.addColumn("categoria");
            modelo.addColumn("cantidad dispinoble");
            modelo.addColumn("precio Unitario");
            modelo.addColumn("precio Entrega");
            modelo.addColumn("foto");
            modelo.addColumn("estado");

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

    public void getProductByPlace(JTable table, String place) {
        try {
            DefaultTableModel modelo = new DefaultTableModel();
            table.setModel(modelo);
            ResultSet rs = null;
            String login = "SELECT B.idProducto, B.idProveedor, B.nombre, B.descripcion, B.tipo, B.categoria, B.cantidadDisponible, B.precioUnitario, B.precioEntrega, B.foto, B.estado FROM proveedores A inner join informacionProveedor B on B.nombreUsuario=A.idProveedor inner join productos C on A.idProveedor=C.idProveedor WHERE B.ubicacion='" + place + "'";
            ps = cin.prepareStatement(login);
            rs = ps.executeQuery();
            ResultSetMetaData rsMd = rs.getMetaData();
            int cantidadColumnas = rsMd.getColumnCount();
            modelo.addColumn("idProducto");
            modelo.addColumn("idProveedor");
            modelo.addColumn("nombre");
            modelo.addColumn("descripcion");
            modelo.addColumn("tipo");
            modelo.addColumn("categoria");
            modelo.addColumn("cantidad dispinoble");
            modelo.addColumn("precio Unitario");
            modelo.addColumn("precio Entrega");
            modelo.addColumn("foto");
            modelo.addColumn("estado");

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

    public void getProductByPrice(JTable table, int min, int max) {
        try {
            DefaultTableModel modelo = new DefaultTableModel();
            table.setModel(modelo);
            ResultSet rs = null;
            String login = "SELECT * FROM productos WHERE precioUnitario>" + min + " and precioUnitario<=" + max;
            ps = cin.prepareStatement(login);
            rs = ps.executeQuery();
            ResultSetMetaData rsMd = rs.getMetaData();
            int cantidadColumnas = rsMd.getColumnCount();
            modelo.addColumn("idProducto");
            modelo.addColumn("idProveedor");
            modelo.addColumn("nombre");
            modelo.addColumn("descripcion");
            modelo.addColumn("tipo");
            modelo.addColumn("categoria");
            modelo.addColumn("cantidad dispinoble");
            modelo.addColumn("precio Unitario");
            modelo.addColumn("precio Entrega");
            modelo.addColumn("foto");
            modelo.addColumn("estado");

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

    public void searchProduct(JTable table, String type, String category, int min, int max, String condition, String place) {
        try {
            DefaultTableModel modelo = new DefaultTableModel();
            table.setModel(modelo);
            ResultSet rs = null;
            String login = "SELECT * FROM productos WHERE precioUnitario>" + min + " and precioUnitario<=" + max + " and lugar='" + place + "'" + " and estado='" + condition + "' and categoria='" + category + "' and type='" + type + "'";
            ps = cin.prepareStatement(login);
            rs = ps.executeQuery();
            ResultSetMetaData rsMd = rs.getMetaData();
            int cantidadColumnas = rsMd.getColumnCount();
            modelo.addColumn("idProducto");
            modelo.addColumn("idProveedor");
            modelo.addColumn("nombre");
            modelo.addColumn("descripcion");
            modelo.addColumn("tipo");
            modelo.addColumn("categoria");
            modelo.addColumn("cantidad dispinoble");
            modelo.addColumn("precio Unitario");
            modelo.addColumn("precio Entrega");
            modelo.addColumn("foto");
            modelo.addColumn("estado");

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

    public int getProductQuatity(String codigo) {
        int result = 0;
        try {
            ResultSet rs = null;
            String login = "SELECT cantidadDisponible FROM productos where idProducto='" + codigo + "'";
            ps = cin.prepareStatement(login);
            rs = ps.executeQuery();

            while (rs.next()) {
                result = Integer.parseInt(rs.getString("cantidadDisponible"));

            }

        } catch (SQLException ex) {

        }
        return result;
    }

    public void updateProduct(String idProducto, String idProveedor, String nombreProducto, String descripcion, String tipo, String categoria, int cantidadDisponible, int precioUnitario, int precioEntrega, String estado) {
        String update = "UPDATE productos SET  idProveedor='" + idProveedor + "', nombreProducto='" + nombreProducto + "', descripcion='" + descripcion + "',tipo= '" + tipo + "',categoria='" + categoria + "',cantidadDisponible=" + cantidadDisponible + ",precioUnitario=" + precioUnitario + ",precioEntrega=" + precioEntrega + ",estado='" + estado + "' WHERE idProducto='" + idProducto + "'";
        try {

            ps = cin.prepareStatement(update);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(admin, "Informacion editada con exito");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(admin, e.toString());
        }
    }

    public void updateProductQuantity(String idProducto, int cantidadDisponible) {
        String update = "UPDATE productos SET cantidadDisponible=" + cantidadDisponible + "WHERE idProducto='" + idProducto + "'";
        try {

            ps = cin.prepareStatement(update);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(admin, "Informacion actualizada");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(admin, e.toString());
        }
    }

    public void deleteProduct(String codigo) {
        String update = "Delete from productos where idProducto='" + codigo + "'";
        try {

            ps = cin.prepareStatement(update);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(admin, "Informacion eliminada con exito");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(admin, e.toString());
        }
    }

    public void productMin(String codigo, int cantidad) {
        int result = getProductQuatity(codigo) - cantidad;
        String update = "UPDATE productos SET cantidadDisponible=" + result + " WHERE idProducto='" + codigo + "'";
        try {

            ps = cin.prepareStatement(update);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(admin, "Informacion editada con exito");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(admin, e.toString());
        }
    }

    public void getComboProductPlace(JComboBox combo) {
        String poi = "SELECT estado FROM productos";
        try {

            ps = cin.prepareCall(poi);
            ResultSet result = ps.executeQuery();

            while (result.next()) {
                combo.addItem(result.getString("estado"));
            }

        } catch (Exception e) {

        }
    }

}
