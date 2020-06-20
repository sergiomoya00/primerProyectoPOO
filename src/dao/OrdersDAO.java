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
import vista.StatusRegister;

/**
 *
 * @author jabre
 */
public class OrdersDAO {

    Conexion conexion = new Conexion();
    Connection cin = conexion.getConnection();
    PreparedStatement ps;
    StatusRegister statusR;

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
    
    public void getComboOrders(JComboBox combo){
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
}
