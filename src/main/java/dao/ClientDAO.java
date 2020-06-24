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
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import vista.ClientSearch;
import vista.ProviderConsultClients;
import vista.StatusRegister;

/**
 *
 * @author samoy
 */
public class ClientDAO {

    Conexion conexion = new Conexion();
    Connection cin = conexion.getConnection();
    private ClientSearch client = new ClientSearch();
    PreparedStatement ps;
    ProviderConsultClients consult;
    
    public void insertClient(String nombreUsuario,int cedula,String provincia, String canton, String distrito, String señas, int telefono, String correoElectronico, String ubicacion, String sitio, String horario, String perfil) {
        String insertar = "insert into informacionCliente (nombreUsuario,cedula,provincia,canton,distrito,señas,telefono,correoElectronico,ubicacion,sitio,horario,perfil) values (?,?,?,?,?,?,?,?,?,?,?,?) ";
        
        try {
            ps = cin.prepareCall(insertar);
            ps.setString(1, nombreUsuario);
            ps.setInt(2, cedula);
            ps.setString(3, provincia);
            ps.setString(4, canton);
            ps.setString(5, distrito);
            ps.setString(6, señas);
            ps.setInt(7, telefono);
            ps.setString(8, correoElectronico);
            ps.setString(9, ubicacion);
            ps.setString(10, sitio);
            ps.setString(11, horario);
            ps.setString(12, perfil);
            
            ps.executeUpdate();
            JOptionPane.showMessageDialog(client, "Registrado con exito");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(client, "No Registrado ");
        }
    }
    
    public void consultClient(JTable table){
         try {
            DefaultTableModel modelo = new DefaultTableModel();
            table.setModel(modelo);
            ResultSet rs = null;
            String login = "SELECT B.nombreUsuario as Cliente, B.correoElectronico, B.telefono, C.idPedido FROM proveedores A, informacionCliente B, pedidos C WHERE A.idProveedor=C.idProveedor and B.nombreUsuario=C.nombreUsuario";
            ps = cin.prepareStatement(login);
            rs = ps.executeQuery();
            ResultSetMetaData rsMd = rs.getMetaData();
            int cantidadColumnas = rsMd.getColumnCount();
            modelo.addColumn("Cliente");
            modelo.addColumn("Correo Electronico");
            modelo.addColumn("Telefono");
            modelo.addColumn("ID del pedido");
            

            while (rs.next()) {
                Object[] filas = new Object[cantidadColumnas];

                for (int i = 1; i <= cantidadColumnas + 2; i++) {
                    if (i < 8) {
                        if (i == 6 || i == 7) {

                        } else {
                            filas[i - 1] = rs.getObject(i);
                        }
                    } else {
                        if (i > cantidadColumnas) {
                        } else {
                            filas[i - 3] = rs.getObject(i);
                        }
                    }
                }

                modelo.addRow(filas);

            }

        } catch (SQLException ex) {

        }
    }
}
