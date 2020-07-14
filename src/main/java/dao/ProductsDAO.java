/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.awt.Color;
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
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.Categories;
import modelo.Products;
import modelo.Render;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import vista.ProductRegister;
import vista.ProviderProducts;

/**
 *
 * @author jabre
 */
public class ProductsDAO {
    
     /**
     *
     * Atributos necesarios para la implementación de los métodos de la clase
     */

    private ProductRegister providerRegister;
    private Conexion conexion = new Conexion();
    private Connection cin = conexion.getConnection();
    private PreparedStatement ps;
    private ProviderProducts admin = new ProviderProducts();
    
      /**
     * Método para insertar un producto en la base de datos
     * @param idProduct  Atributo que guarda el codigo del producto a insertar en la base de datos
     * @param idProvider Atributo que guarda el codigo del proveedor del producto en la base de datos
     * @param productName Atributo que guarda el nombre del producto en la base de datos
     * @param description Atributo que guarda la descripcion del producto en la base de datos
     * @param type Atributo que guarda el tipo de producto en la base de datos
     * @param category Atributo que guarda la categoria del producto en la base de datos
     * @param availableQuantity Atributo que guarda la cantidad disponible de un producto en la base de datos
     * @param unitaryPrice Atributo que guarda el precio unitario de un producto en la base de datos
     * @param deliveryPrice Atributo que guarda el precio de entrega de un producto en la base de datos
     * @param pic Atributo que guarda la foto de un producto en la base de datos
     * @param status Atributo que guarda el estado de un producto en la base de datos
     */

    public void insertProduct(String idProduct, String idProvider, String productName, String description, String type, String category, int availableQuantity, int unitaryPrice, int deliveryPrice, byte[] pic, String status) {
        String insertar = "insert into productos (idProducto,idProveedor,nombreProducto,descripcion,tipo,categoria,cantidadDisponible,precioUnitario,precioEntrega,foto,estado) values (?,?,?,?,?,?,?,?,?,?,?)";

        try {
            ps = cin.prepareCall(insertar);
            ps.setString(1, idProduct);
            ps.setString(2, idProvider);
            ps.setString(3, productName);
            ps.setString(4, description);
            ps.setString(5, type);
            ps.setString(6, category);
            ps.setInt(7, availableQuantity);
            ps.setInt(8, unitaryPrice);
            ps.setInt(9, deliveryPrice);
            ps.setBytes(10, pic);
            ps.setString(11, status);

            ps.executeUpdate();
            JOptionPane.showMessageDialog(providerRegister, "Registrado con exito");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(providerRegister, "No Registrado ");
        }
    }
    
      /**
     * Método para obtener todos los productos en la base de datos
     * @param table  Atributo que guarda la tabla en la cual se mostrará los productos obtenidos de la base de datos
     */

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
    
     /**
     * Método para obtener todos los productos por tipo en la base de datos
     * @param table  Atributo que guarda la tabla en la cual se mostrará los productos obtenidos de la base de datos
     * @param type  Atributo que guarda el tipo de productos que se obtendrá de la base de datos
     */

    public void getProductByType(JTable table, String type) {
        try {
            DefaultTableModel modelo = new DefaultTableModel();
            table.setModel(modelo);
            ResultSet rs = null;
            String login = "SELECT * FROM productos WHERE tipo='" + type + "'";
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
    
    /**
     * Método para obtener todos los productos por categoria en la base de datos
     * @param table  Atributo que guarda la tabla en la cual se mostrará los productos obtenidos de la base de datos
     * @param category  Atributo que guarda la categoria de los productos que se obtendrá de la base de datos
     */

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
    
    /**
     * Método para obtener todos los productos por estado en la base de datos
     * @param table  Atributo que guarda la tabla en la cual se mostrará los productos obtenidos de la base de datos
     * @param status Atributo que guarda el estado de los productos que se obtendrá de la base de datos
     */

    public void getProductByCondition(JTable table, String status) {
        try {
            DefaultTableModel modelo = new DefaultTableModel();
            table.setModel(modelo);
            ResultSet rs = null;
            String login = "SELECT * FROM productos WHERE estado='" + status + "'";
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
    
    /**
     * Método para obtener todos los productos por ubicacion en la base de datos
     * @param table  Atributo que guarda la tabla en la cual se mostrará los productos obtenidos de la base de datos
     * @param place  Atributo que guarda el lugar donde se encuentran los productos que se obtendrá de la base de datos
     */

    public void getProductByPlace(JTable table, String place) {
        try {
            DefaultTableModel modelo = new DefaultTableModel();
            table.setModel(modelo);
            ResultSet rs = null;
            String login = "SELECT B.idProducto, B.idProveedor, B.nombreProducto, B.descripcion, B.tipo, B.categoria, B.cantidadDisponible, B.precioUnitario, B.precioEntrega, B.foto, B.estado FROM proveedores A inner join informacionProveedor C on C.nombreUsuario=A.idProveedor inner join productos B on A.idProveedor=B.idProveedor WHERE C.ubicacion='" + place + "'";
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
    
    /**
     * Método para obtener todos los productos por precio en la base de datos
     * @param table  Atributo que guarda la tabla en la cual se mostrará los productos obtenidos de la base de datos
     * @param min  Atributo que guarda el precio minimo de los productos que se obtendrá de la base de datos
     * @param max  Atributo que guarda el precio maximo de los productos que se obtendrá de la base de datos
     */

    public void getProductByPrice(JTable table, int min, int max) {
        try {
            DefaultTableModel modelo = new DefaultTableModel();
            table.setModel(modelo);
            ResultSet rs = null;
            String login = "SELECT * FROM productos WHERE precioUnitario>=" + min + " and precioUnitario<=" + max;
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
    
    /**
     * Método para obtener todos los productos por busqueda en la base de datos
     * @param table  Atributo que guarda la tabla en la cual se mostrará los productos obtenidos de la base de datos
     * @param min  Atributo que guarda el precio minimo de los productos que se obtendrá de la base de datos
     * @param max  Atributo que guarda el precio maximo de los productos que se obtendrá de la base de datos
     * @param type  Atributo que guarda el tipo de productos que se obtendrá de la base de datos
     * @param category  Atributo que guarda la categoria de productos que se obtendrá de la base de datos
     * @param condition  Atributo que guarda el estado de los productos que se obtendrá de la base de datos
     * @param place  Atributo que guarda la ubicacion de los productos que se obtendrá de la base de datos
     */

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
    
    /**
     * Método para obtener la cantidad de productos en la base de datos
     * @param codigo  Atributo que guarda el codigo del producto a obtener de la base de datos
     */

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
    
     /**
     * Método para actualizar un producto en la base de datos
     * @param idProduct  Atributo que guarda el codigo del producto a insertar en la base de datos
     * @param idProvider Atributo que guarda el codigo del proveedor del producto en la base de datos
     * @param productName Atributo que guarda el nombre del producto en la base de datos
     * @param description Atributo que guarda la descripcion del producto en la base de datos
     * @param type Atributo que guarda el tipo de producto en la base de datos
     * @param category Atributo que guarda la categoria del producto en la base de datos
     * @param availableQuantity Atributo que guarda la cantidad disponible de un producto en la base de datos
     * @param unitaryPrice Atributo que guarda el precio unitario de un producto en la base de datos
     * @param deliveryPrice Atributo que guarda el precio de entrega de un producto en la base de datos
     * @param status Atributo que guarda el estado de un producto en la base de datos
     */

    public void updateProduct(String idProduct, String idProvider, String productName, String description, String type, String category, int availableQuantity, int unitaryPrice, int deliveryPrice, String status) {
        String update = "UPDATE productos SET  idProveedor='" + idProvider + "', nombreProducto='" + productName + "', descripcion='" + description + "',tipo= '" + type + "',categoria='" + category + "',cantidadDisponible=" + availableQuantity + ",precioUnitario=" + unitaryPrice + ",precioEntrega=" + deliveryPrice + ",estado='" + status + "' WHERE idProducto='" + idProduct + "'";
        try {

            ps = cin.prepareStatement(update);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(admin, "Informacion editada con exito");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(admin, e.toString());
        }
    }
    
     /**
     * Método para actualizar la cantidad de un producto en la base de datos
     * @param idProduct  Atributo que guarda el codigo del producto a actualizar en la base de datos
     * @param availableQuantity Atributo que guarda la cantidad disponible nueva del producto en la base de datos
     */

    public void updateProductQuantity(String idProduct, int availableQuantity) {
        String update = "UPDATE productos SET cantidadDisponible=" + availableQuantity + "WHERE idProducto='" + idProduct + "'";
        try {

            ps = cin.prepareStatement(update);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(admin, "Informacion actualizada");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(admin, e.toString());
        }
    }
    
    /**
     * Método para eliminar un producto en la base de datos
     * @param idProduct  Atributo que guarda el codigo del producto a eliminar en la base de datos
     */

    public void deleteProduct(String idProduct) {
        String update = "Delete from productos where idProducto='" + idProduct + "'";
        try {

            ps = cin.prepareStatement(update);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(admin, "Informacion eliminada con exito");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(admin, e.toString());
        }
    }
    
    /**
     * Método para calcular el precio total de un producto en la base de datos
     * @param idProduct  Atributo que guarda el codigo del producto a eliminar en la base de datos
     * @param quantity  Atributo que guarda el codigo del producto a eliminar en la base de datos
     * @return 
     */
    public int calcTotalPrice(String idProduct,int quantity){
    int precio=0;
    String poi = "SELECT precioUnitario FROM productos where idProducto='"+idProduct+"'";
        try {

            ps = cin.prepareCall(poi);
            ResultSet result = ps.executeQuery();

            while (result.next()) {
               precio=Integer.parseInt(result.getString("precioUnitario"))*quantity;
            }

        } catch (Exception e) {

        }
        return precio;
    }
    
    /**
     * Método para calcular el precio total de un producto en la base de datos
     * @param idProduct  Atributo que guarda el codigo del producto a eliminar en la base de datos
     * @param quantity  Atributo que guarda el codigo del producto a eliminar en la base de datos
     */

    public void productMin(String idProduct, int quantity) {
        int result = getProductQuatity(idProduct) - quantity;
        String update = "UPDATE productos SET cantidadDisponible=" + result + " WHERE idProducto='" + idProduct + "'";
        try {

            ps = cin.prepareStatement(update);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(admin, "Informacion editada con exito");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(admin, e.toString());
        }
    }
    
    /**
     * Método para obtener productos por lugar en la base de datos
     * @param combo  Atributo que guarda el combobox donde se mostrará los productos obtenidos de la base de datos
     */

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
    
    /**
     * Método para obtener productos por lugar en la base de datos
     * @param panel  Atributo que guarda el panel donde se mostrará el grafico de los productos obtenidos de la base de datos
     */

    public void getProductsByID(JTable table, String idProvider) {
        try {
            DefaultTableModel modelo = new DefaultTableModel();
            table.setModel(modelo);
            ResultSet rs = null;
            String login = "SELECT B.idProducto, B.idProveedor, B.nombreProducto, B.descripcion, B.tipo, B.categoria, B.cantidadDisponible, B.precioUnitario, B.precioEntrega, B.foto, B.estado FROM productos B WHERE B.idProveedor='" + idProvider + "'";
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

    public void showGraph(JPanel panel) {
        try {
            ResultSet rs = null;
            String poi = "select top 5 (B.nombreProducto), count(A.idProducto) as cantidad from pedidos A inner join productos B on B.idProducto=A.idProducto group by B.nombreProducto order by cantidad desc";
            ps = cin.prepareCall(poi);
            ResultSet result = ps.executeQuery();

            DefaultPieDataset dataset = new DefaultPieDataset();
            while (result.next()) {
                dataset.setValue(result.getString(1), result.getInt(2));
            }

            JFreeChart jchart = ChartFactory.createPieChart("Productos", dataset, true, true, true);
            PiePlot plot = (PiePlot) jchart.getPlot();

            ChartPanel chartPanel = new ChartPanel(jchart);
            panel.removeAll();
            panel.add(chartPanel);
            panel.updateUI();

        } catch (SQLException ex) {

        }
    }

}
