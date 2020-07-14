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
    
     /**
     *
     * Atributos necesarios para la implementación de los métodos de la clase
     */

    private Conexion conexion = new Conexion();
    private Connection cin = conexion.getConnection();
    private ClientSearch client = new ClientSearch();
    private PreparedStatement ps;
    
        /**
     * Método para insertar una categoria en la base de datos
     * @param userName  Atributo que guarda el nombre de usuario del cliente a insertar en la base de datos
     * @param id Atributo que guarda la cédula del cliente en la base de datos
     * @param province Atributo que guarda la provincia del cliente en la base de datos
     * @param canton Atributo que guarda el cantón del cliente en la base de datos
     * @param distrito Atributo que guarda el distrito del cliente en la base de datos
     * @param senas Atributo que guarda la dirección en señas del cliente en la base de datos
     * @param phone Atributo que guarda la cédula del cliente en la base de datos
     * @param email Atributo que guarda el correo electrónico del cliente en la base de datos
     * @param ubication Atributo que guarda la cédula del cliente en la base de datos
     * @param website Atributo que guarda el sitio web del cliente en la base de datos
     * @param schedule Atributo que guarda el horario del cliente en la base de datos
     * @param profile Atributo que guarda el perfil del cliente en la base de datos
     */

    public void insertClient(String userName, int id, String province, String canton, String distrito, String senas, int phone, String email, String ubication, String website, String schedule, String profile) {
        String insertar = "insert into informacionCliente (nombreUsuario,cedula,provincia,canton,distrito,señas,telefono,correoElectronico,ubicacion,sitio,horario,perfil) values (?,?,?,?,?,?,?,?,?,?,?,?) ";

        try {
            ps = cin.prepareCall(insertar);
            ps.setString(1, userName);
            ps.setInt(2, id);
            ps.setString(3, province);
            ps.setString(4, canton);
            ps.setString(5, distrito);
            ps.setString(6, senas);
            ps.setInt(7, phone);
            ps.setString(8, email);
            ps.setString(9, ubication);
            ps.setString(10, website);
            ps.setString(11, schedule);
            ps.setString(12, profile);

            ps.executeUpdate();
            JOptionPane.showMessageDialog(client, "Registrado con exito");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(client, "No Registrado ");
        }
    }
    
        /**
     * Método para consultar un cliente en la base de datos
     * @param table  Atributo que guarda la tabla en la cual se insertarán los datos
     * @param idProvider Atributo que guarda el codigo del proveedor al cual se le consultarán los clientes en la base de datos
     */
    
    

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
    
      /**
     * Método para mostrar un gráfico en la ventana
     * @param panel  Atributo que guarda el panel en el cual se mostrará el gráfico
     
     */
    
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
            
            JFreeChart jchart = ChartFactory.createPieChart("Clientes", dataset, true, true, true);
            PiePlot plot = (PiePlot) jchart.getPlot();
            //plot.setForegroundAlpha(TOP_ALIGNMENT);
            
            ChartFrame chartFrm = new ChartFrame("Productos", jchart, true);
            chartFrm.setVisible(true);
            chartFrm.setSize(450,500);

            ChartPanel chartPanel = new ChartPanel(jchart);
            panel.removeAll();
            panel.add(chartPanel);
            panel.updateUI();
            
        } catch (SQLException ex) {

        }
    }
    
      /**
     * Método para insertar una categoria en la base de datos
     * @param id  Atributo que funciona como entrada para buscar la ubicación del cliente, en la base de datos
      @return
     */
    
       public String getClientCordinates(String id){
        String cordinates="";
        String poi = "SELECT latitud,longitud FROM informacionCliente where nombreUsuario='"+id+"'";
        try {

            ps = cin.prepareCall(poi);
            ResultSet result = ps.executeQuery();

            while (result.next()) {
                cordinates="lat:"+result.getString("latitud")+", lng: "+result.getString("longitud");
            }

        } catch (Exception e) {

        }
        return cordinates;
    
    }
}
