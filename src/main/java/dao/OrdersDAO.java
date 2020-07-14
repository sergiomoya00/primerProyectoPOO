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
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import vista.ClientSearch;
import vista.StatusRegister;

/**
 *
 * @author jabre
 */
public class OrdersDAO {
    
    /**
     *
     * Atributos necesarios para la implementación de los métodos de la clase
     */

    private Conexion conexion = new Conexion();
    private Connection cin = conexion.getConnection();
    private ClientSearch client = new ClientSearch();
    private PreparedStatement ps;
    private StatusRegister statusR;
    
       /**
     * Método para insertar un pedido en la base de datos
     * @param userName  Atributo que guarda el nombre de usuario del cliente a insertar en la base de datos
     * @param idProvider Atributo que guarda la cédula del cliente en la base de datos
     * @param idProduct Atributo que guarda la provincia del cliente en la base de datos
     * @param quantity Atributo que guarda el cantón del cliente en la base de datos
     * @param category Atributo que guarda el distrito del cliente en la base de datos
     */

    public void insertOrder(String userName, String idProvider, String idProduct, int quantity,String category) {
        String insertar = "insert into pedidos (nombreUsuario,idProveedor,idProducto,cantidad,estado,fecha_hora_Entrega,categoria) values (?,?,?,?,?,?,?) ";
        long time = System.currentTimeMillis();
        java.sql.Date d = new java.sql.Date(time);
        long now = System.currentTimeMillis();
        java.sql.Time a = new java.sql.Time(now);
        String fecha = d + " " + a;

        try {
            ps = cin.prepareCall(insertar);
            ps.setString(1, userName);
            ps.setString(2, idProvider);
            ps.setString(3, idProduct);
            ps.setInt(4, quantity);
            ps.setString(5, "En Proceso");
            ps.setString(6, fecha);
            ps.setString(7, category);

            ps.executeUpdate();
            JOptionPane.showMessageDialog(client, "Registrado con exito");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(client, "No Registrado ");
        }
    }
    
       /**
     * Método para actualizar una categoria en la base de datos
     * @param idOrder  Atributo que guarda el codigo del pedido del cliente a actualizar en la base de datos
     * @param status Atributo que guarda el estado del pedido del cliente a actualizar en la base de datos
     */

    public void orderStatus(String idOrder, String status) {
        String update = "UPDATE pedidos SET  estado='" + status + "' WHERE idPedido='" + idOrder + "'";
        try {

            ps = cin.prepareStatement(update);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(statusR, "Pedido actualizado con exito");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(statusR, e.toString());
        }
    }
    
    /**
     * Método para actualizar la fecha de entrega en la base de datos
     * @param idOrder  Atributo que guarda el codigo del pedido del cliente a actualizar en la base de datos
     * @param date Atributo que guarda la fecha de entrega del pedido del cliente a actualizar en la base de datos
     */
    
    public void orderDeliveryDate(String idOrder, String date) {
        String update = "UPDATE pedidos SET  fecha_hora_Entrega='" + date + "' WHERE idPedido='" + idOrder + "'";
        try {

            ps = cin.prepareStatement(update);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(statusR, "Pedido actualizado con exito");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(statusR, e.toString());
        }
    }
    
    /**
     * Método para actualizar el estado de un pedido en la base de datos
     * @param idOrder  Atributo que guarda el codigo del pedido del cliente a actualizar en la base de datos
     */
    
    public void updateStatusCanceled(String idOrder) {
        String update = "UPDATE pedidos SET  estado='Cancelado' WHERE idPedido='" + idOrder + "'";
        try {

            ps = cin.prepareStatement(update);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(statusR, "Pedido actualizado con exito");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(statusR, e.toString());
        }
    }
    
    /**
     * Método para obtener la cantidad de productos de un pedido en la base de datos
     * @param idProduct  Atributo que guarda el codigo del producto del cuál se obtendrá la cantidad de la base de datos
     * @return
     */

    public int getOrderQuatity(String idProduct) {
        int result = 0;
        try {
            ResultSet rs = null;
            String login = "SELECT cantidad FROM pedidos where idPedido='" + idProduct + "'";
            ps = cin.prepareStatement(login);
            rs = ps.executeQuery();

            while (rs.next()) {
                result = Integer.parseInt(rs.getString("cantidad"));

            }

        } catch (SQLException ex) {

        }
        return result;
    }
    
    /**
     * Método para eliminar un pedido en la base de datos
     * @param id  Atributo que guarda el codigo del producto a eliminar de la base de datos
     */

    public void deleteOrderStatus(String id) {
        String update = "Delete from pedidos Where idPedido='" + id + "'";
        try {

            ps = cin.prepareStatement(update);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(statusR, "Pedido eliminado con exito");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(statusR, e.toString());
        }
    }
    
    /**
     * Método para obtener el estado de todos los pedidos en la base de datos
     * @param table  Atributo que guarda la tabla donde se mostrará  todos los pedidos de la base de datos
     */

    public void getAllOrderStatus(JTable table) {
        try {
            DefaultTableModel modelo = new DefaultTableModel();
            table.setModel(modelo);
            ResultSet rs = null;
            String login = "SELECT idPedido,estado FROM pedidos ";
            ps = cin.prepareStatement(login);
            rs = ps.executeQuery();
            ResultSetMetaData rsMd = rs.getMetaData();
            int cantidadColumnas = rsMd.getColumnCount();
            modelo.addColumn("ID DEL PEDIDO");
            modelo.addColumn("ESTADO ACTUAL");

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
     * Método para obtener todos los pedidoss de un cliente en la base de datos
     * @param table  Atributo que guarda la tabla donde se mostrará  todos los pedidos de la base de datos
     * @param client Atributo que guarda el nombre del cliente del cual se obtendrá los pedidos
     */
    
    public void getAllClientOrders(JTable table,String client){
        try {
            DefaultTableModel modelo = new DefaultTableModel();
            table.setModel(modelo);
            ResultSet rs = null;
            String selection = "SELECT idPedido, idProducto,idProveedor, nombreUsuario, cantidad, estado,fecha_hora_Entrega,categoria FROM pedidos WHERE nombreUsuario = '" + client + "'";
            ps = cin.prepareStatement(selection);
            rs = ps.executeQuery();
            ResultSetMetaData rsMd = rs.getMetaData();
            int cantidadColumnas = rsMd.getColumnCount();
            modelo.addColumn("ID del pedido");
            modelo.addColumn("ID del producto");
            modelo.addColumn("ID del proveedor");
            modelo.addColumn("Cliente");
            modelo.addColumn("Cantidad");
            modelo.addColumn("Estado");
            modelo.addColumn("Fecha");
            modelo.addColumn("categoria");

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
     * Método para obtener todos los pedidos por categoria en la base de datos
     * @param table  Atributo que guarda la tabla donde se mostrará  todos los pedidos de la base de datos
     * @param client Atributo que guarda el nombre del cliente del cual se obtendrá los pedidos
     * @param category Atributo que guarda la categoría del pedido a obtener
     */
    
    public void getAllClientOrdersByCategory(JTable table,String client,String category){
        try {
            DefaultTableModel modelo = new DefaultTableModel();
            table.setModel(modelo);
            ResultSet rs = null;
            String selection = "SELECT idPedido, idProducto,idProveedor, nombreUsuario, cantidad, estado,fecha_hora_Entrega,categoria FROM pedidos WHERE nombreUsuario = '" + client + "' AND categoria='"+category+"'";
            ps = cin.prepareStatement(selection);
            rs = ps.executeQuery();
            ResultSetMetaData rsMd = rs.getMetaData();
            int cantidadColumnas = rsMd.getColumnCount();
            modelo.addColumn("ID del pedido");
            modelo.addColumn("ID del producto");
            modelo.addColumn("ID del proveedor");
            modelo.addColumn("Cliente");
            modelo.addColumn("Cantidad");
            modelo.addColumn("Estado");
            modelo.addColumn("Fecha");
            modelo.addColumn("categoria");

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
     * Método para obtener todos los pedidos por proveedor de la  base de datos
     * @param table  Atributo que guarda la tabla donde se mostrará  todos los pedidos de la base de datos
     * @param client Atributo que guarda el nombre del cliente del cual se obtendrá los pedidos
     * @param provider Atributo que guarda el proveedor del producto del pedido a obtener
     */
    
    
     public void getAllClientOrdersByProv(JTable table,String client,String provider){
        try {
            DefaultTableModel modelo = new DefaultTableModel();
            table.setModel(modelo);
            ResultSet rs = null;
            String selection = "SELECT idPedido, idProducto,idProveedor, nombreUsuario, cantidad, estado,fecha_hora_Entrega,categoria FROM pedidos WHERE nombreUsuario = '" + client + "' AND idProveedor='"+provider+"'";
            ps = cin.prepareStatement(selection);
            rs = ps.executeQuery();
            ResultSetMetaData rsMd = rs.getMetaData();
            int cantidadColumnas = rsMd.getColumnCount();
            modelo.addColumn("ID del pedido");
            modelo.addColumn("ID del producto");
            modelo.addColumn("ID del proveedor");
            modelo.addColumn("Cliente");
            modelo.addColumn("Cantidad");
            modelo.addColumn("Estado");
            modelo.addColumn("Fecha");
            modelo.addColumn("categoria");

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
     * Método para obtener todos los pedidos por fecha en la base de datos
     * @param table  Atributo que guarda la tabla donde se mostrará  todos los pedidos de la base de datos
     * @param client Atributo que guarda el nombre del cliente del cual se obtendrá los pedidos
     * @param date Atributo que guarda la fecha del pedido a obtener
     */
     
        public void getAllClientOrdersByDate(JTable table,String client,String date){
        try {
            DefaultTableModel modelo = new DefaultTableModel();
            table.setModel(modelo);
            ResultSet rs = null;
            String selection = "SELECT idPedido, idProducto,idProveedor, nombreUsuario, cantidad, estado,fecha_hora_Entrega,categoria FROM pedidos WHERE nombreUsuario = '" + client + "' AND fecha_hora_Entrega='"+date+"'";
            ps = cin.prepareStatement(selection);
            rs = ps.executeQuery();
            ResultSetMetaData rsMd = rs.getMetaData();
            int cantidadColumnas = rsMd.getColumnCount();
            modelo.addColumn("ID del pedido");
            modelo.addColumn("ID del producto");
            modelo.addColumn("ID del proveedor");
            modelo.addColumn("Cliente");
            modelo.addColumn("Cantidad");
            modelo.addColumn("Estado");
            modelo.addColumn("Fecha");
            modelo.addColumn("categoria");

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
     * Método para obtener todos los pedidos por estado en la base de datos
     * @param table  Atributo que guarda la tabla donde se mostrará  todos los pedidos de la base de datos
     * @param client Atributo que guarda el nombre del cliente del cual se obtendrá los pedidos
     * @param status Atributo que guarda el estado del pedido a obtener
     */
     
     public void getAllClientOrdersByStatus(JTable table,String client,String status){
        try {
            DefaultTableModel modelo = new DefaultTableModel();
            table.setModel(modelo);
            ResultSet rs = null;
            String selection = "SELECT idPedido, idProducto,idProveedor, nombreUsuario, cantidad, estado,fecha_hora_Entrega,categoria FROM pedidos WHERE nombreUsuario = '" + client + "' AND estado='"+status+"'";
            ps = cin.prepareStatement(selection);
            rs = ps.executeQuery();
            ResultSetMetaData rsMd = rs.getMetaData();
            int cantidadColumnas = rsMd.getColumnCount();
            modelo.addColumn("ID del pedido");
            modelo.addColumn("ID del producto");
            modelo.addColumn("ID del proveedor");
            modelo.addColumn("Cliente");
            modelo.addColumn("Cantidad");
            modelo.addColumn("Estado");
            modelo.addColumn("Fecha");
            modelo.addColumn("categoria");

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
     * Método para obtener el estado de los pedidos en la base de datos
     * @param table  Atributo que guarda la tabla donde se mostrará  todos los pedidos de la base de datos
     * @param status Atributo que guarda el estado del pedido a obtener
     */

    public void getSpecificOrderStatus(JTable table, String status) {
        try {
            DefaultTableModel modelo = new DefaultTableModel();
            table.setModel(modelo);
            ResultSet rs = null;
            String selection = "SELECT idPedido, idProducto,idProveedor, nombreUsuario, cantidad, estado,fecha_hora_Entrega,categoria  FROM pedidos WHERE estado = '" + status + "'";
            ps = cin.prepareStatement(selection);
            rs = ps.executeQuery();
            ResultSetMetaData rsMd = rs.getMetaData();
            int cantidadColumnas = rsMd.getColumnCount();
            modelo.addColumn("ID del pedido");
            modelo.addColumn("ID del producto");
            modelo.addColumn("ID del proveedor");
            modelo.addColumn("Cliente");
            modelo.addColumn("Cantidad");
            modelo.addColumn("Estado");
             modelo.addColumn("Fecha");
            modelo.addColumn("categoria");

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
     * Método para obtener todos los pedidos de la base de datos
     * @param combo  Atributo que guarda el combobox en el cual se mostrará todos los pedidos
     */

    public void getComboOrders(JComboBox combo) {
        String poi = "SELECT idPedido FROM pedidos";
        try {

            ps = cin.prepareCall(poi);
            ResultSet result = ps.executeQuery();

            while (result.next()) {
                combo.addItem(result.getString("idPedido"));
            }

        } catch (Exception e) {

        }
    }
    
    /**
     * Método para obtener los pedidos por fecha de la base de datos
     * @param combo  Atributo que guarda el combobox en el cual se mostrará todos los pedidos por fecha
     */
    
      public void getComboOrdersByDate(JComboBox combo) {
        String poi = "SELECT fecha_hora_Entrega FROM pedidos";
        try {

            ps = cin.prepareCall(poi);
            ResultSet result = ps.executeQuery();

            while (result.next()) {
                combo.addItem(result.getString("fecha_hora_Entrega"));
            }

        } catch (Exception e) {

        }
    }
      
    /**
     * Método para limpiar la tabla en la cual se muestra los datos de la base de datos
     * @param table  Atributo que guarda el combobox en el cual se mostrará todos los pedidos
     */
      
    public void clearOrders(JTable table){
    try {
            DefaultTableModel modelo=(DefaultTableModel) table.getModel();
            int filas=table.getRowCount();
            for (int i = 0;filas>i; i++) {
                modelo.removeRow(0);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al limpiar la tabla.");
        }
    }
       
    
    
}
