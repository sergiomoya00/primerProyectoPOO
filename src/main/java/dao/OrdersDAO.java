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

    Conexion conexion = new Conexion();
    Connection cin = conexion.getConnection();
    private ClientSearch client = new ClientSearch();
    PreparedStatement ps;
    StatusRegister statusR;

    public void insertOrder(String nombreUsuario, String idProveedor, String idProducto, int cantidad, String categoria) {
        String insertar = "insert into pedidos (nombreUsuario,idProveedor,idProducto,cantidad,estado,fecha_hora_Entrega,categoria) values (?,?,?,?,?,?,?) ";
        long time = System.currentTimeMillis();
        java.sql.Date d = new java.sql.Date(time);
        long now = System.currentTimeMillis();
        java.sql.Time a = new java.sql.Time(now);
        String fecha = d + " " + a;

        try {
            ps = cin.prepareCall(insertar);
            ps.setString(1, nombreUsuario);
            ps.setString(2, idProveedor);
            ps.setString(3, idProducto);
            ps.setInt(4, cantidad);
            ps.setString(5, "En Proceso");
            ps.setString(6, fecha);
            ps.setString(7, categoria);

            ps.executeUpdate();
            JOptionPane.showMessageDialog(client, "Registrado con exito");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(client, "No Registrado ");
        }
    }

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

    public void getAllClientOrders(JTable table, String client) {
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

    public void getAllClientOrdersByCategory(JTable table, String client, String category) {
        try {
            DefaultTableModel modelo = new DefaultTableModel();
            table.setModel(modelo);
            ResultSet rs = null;
            String selection = "SELECT idPedido, idProducto,idProveedor, nombreUsuario, cantidad, estado,fecha_hora_Entrega,categoria FROM pedidos WHERE nombreUsuario = '" + client + "' AND categoria='" + category + "'";
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

    public void getAllClientOrdersByProv(JTable table, String client, String provider) {
        try {
            DefaultTableModel modelo = new DefaultTableModel();
            table.setModel(modelo);
            ResultSet rs = null;
            String selection = "SELECT idPedido, idProducto,idProveedor, nombreUsuario, cantidad, estado,fecha_hora_Entrega,categoria FROM pedidos WHERE nombreUsuario = '" + client + "' AND idProveedor='" + provider + "'";
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

    public void getAllClientOrdersByDate(JTable table, String client, String date) {
        try {
            DefaultTableModel modelo = new DefaultTableModel();
            table.setModel(modelo);
            ResultSet rs = null;
            String selection = "SELECT idPedido, idProducto,idProveedor, nombreUsuario, cantidad, estado,fecha_hora_Entrega,categoria FROM pedidos WHERE nombreUsuario = '" + client + "' AND fecha_hora_Entrega='" + date + "'";
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

    public void getAllClientOrdersByStatus(JTable table, String client, String status) {
        try {
            DefaultTableModel modelo = new DefaultTableModel();
            table.setModel(modelo);
            ResultSet rs = null;
            String selection = "SELECT idPedido, idProducto,idProveedor, nombreUsuario, cantidad, estado,fecha_hora_Entrega,categoria FROM pedidos WHERE nombreUsuario = '" + client + "' AND estado='" + status + "'";
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

    public void getSpecificOrderStatus(JTable table, String status, String idProveedor) {
        try {
            DefaultTableModel modelo = new DefaultTableModel();
            table.setModel(modelo);
            ResultSet rs = null;
            String selection = "SELECT idPedido, idProducto,idProveedor, nombreUsuario, cantidad, estado,fecha_hora_Entrega FROM pedidos WHERE estado = '" + status + "' and idProveedor='" + idProveedor + "'";
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

    public void getComboOrders(JComboBox combo) {
        java.sql.Connection conectar = null;
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

    public void getComboOrdersByDate(JComboBox combo) {
        java.sql.Connection conectar = null;
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

    public void clearOrders(JTable table) {
        try {
            DefaultTableModel modelo = (DefaultTableModel) table.getModel();
            int filas = table.getRowCount();
            for (int i = 0; filas > i; i++) {
                modelo.removeRow(0);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al limpiar la tabla.");
        }
    }

}
