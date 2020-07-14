/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.awt.Color;
import static java.awt.Component.TOP_ALIGNMENT;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
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

    public void insertClient(String nombreUsuario, int cedula, String provincia, String canton, String distrito, String senas, int telefono, String correoElectronico, String ubicacion, String sitio, String horario, String perfil) {
        String insertar = "insert into informacionCliente (nombreUsuario,cedula,provincia,canton,distrito,señas,telefono,correoElectronico,ubicacion,sitio,horario,perfil) values (?,?,?,?,?,?,?,?,?,?,?,?) ";

        try {
            ps = cin.prepareCall(insertar);
            ps.setString(1, nombreUsuario);
            ps.setInt(2, cedula);
            ps.setString(3, provincia);
            ps.setString(4, canton);
            ps.setString(5, distrito);
            ps.setString(6, senas);
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

    public void consultClient(JTable table, String idProvider) {
        try {
            DefaultTableModel modelo = new DefaultTableModel();
            table.setModel(modelo);
            ResultSet rs = null;
            String login = "SELECT B.nombreUsuario, B.correoElectronico, B.telefono, C.idPedido FROM proveedores A, informacionCliente B, pedidos C WHERE A.idProveedor='" + idProvider + "' and A.idProveedor=C.idProveedor and B.nombreUsuario=C.nombreUsuario";
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

                for (int i = 1; i <= cantidadColumnas; i++) {

                    filas[i - 1] = rs.getObject(i);

                }

                modelo.addRow(filas);

            }

        } catch (SQLException ex) {

        }
    }

    public void consultSpecificClient(JTable table, String idProvider, String idClient) {
        try {
            DefaultTableModel modelo = new DefaultTableModel();
            table.setModel(modelo);
            ResultSet rs = null;
            String login = "SELECT * FROM pedidos WHERE nombreUsuario='" + idClient + "' and idProveedor='" + idProvider + "'";
            ps = cin.prepareStatement(login);
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
            modelo.addColumn("Categoria");

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
            String poi = "select top 5 (B.nombreUsuario), count(B.nombreUsuario) as cantidad from pedidos A inner join informacionCliente B on B.nombreUsuario=A.nombreUsuario group by B.nombreUsuario order by cantidad desc";
            ps = cin.prepareCall(poi);
            ResultSet result = ps.executeQuery();

            DefaultPieDataset dataset = new DefaultPieDataset();
            while (result.next()) {
                dataset.setValue(result.getString(1), result.getInt(2));
            }

            JFreeChart jchart = ChartFactory.createPieChart("Clientes", dataset, true, true, true);
            PiePlot plot = (PiePlot) jchart.getPlot();

            ChartPanel chartPanel = new ChartPanel(jchart);
            panel.removeAll();
            panel.add(chartPanel);
            panel.updateUI();

        } catch (SQLException ex) {

        }
    }

    public String getClientCordinates(String id) {
        String cordinates = "";
        String poi = "SELECT latitud,longitud FROM informacionCliente where nombreUsuario='" + id + "'";
        try {

            ps = cin.prepareCall(poi);
            ResultSet result = ps.executeQuery();

            while (result.next()) {
                cordinates = "lat:" + result.getString("latitud") + ", lng: " + result.getString("longitud");
            }

        } catch (Exception e) {

        }
        return cordinates;

    }
}
