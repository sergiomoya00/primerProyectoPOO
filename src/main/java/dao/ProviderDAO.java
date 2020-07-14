/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.google.maps.errors.ApiException;
import static dao.GoogleAPI.Latitude;
import static dao.GoogleAPI.Longitude;
import java.awt.Color;
import java.io.IOException;
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
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.Categories;
import modelo.Providers;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import vista.AdminProvider;
import vista.ClientRegister;
import vista.ProviderRegister;

/**
 *
 * @author jabre
 */
public class ProviderDAO {
    
    /**
     *
     * Atributos necesarios para la implementación de los métodos de la clase
     */

    private Providers provi;
    private ProviderRegister providerRegister;
    private AdminProvider adminProvider;
    private ClientRegister clientRegister;
    private Conexion conexion = new Conexion();
    private Connection cin = conexion.getConnection();
    private PreparedStatement ps;
    private ArrayList<Providers> array = new ArrayList<>();
    private ArrayList<Providers> arrayUser = new ArrayList<>();
    
    /**
     *
     * Constructor vacío de la clase
     */

    public ProviderDAO() {
    }
    
      /**
     * Método para insertar un proveedor en la base de datos
     * @param user  Atributo que guarda el nombre de usuario del cliente a insertar en la base de datos
     * @param id Atributo que guarda el identificador del proveedor en la base de datos
     * @param company Atributo que guarda la empresa del en la base de datos
     */

    public void providerRegister(String id, String user, String company) {

        String insertar = "insert into proveedores (idProveedor,nombreUsuario,empresa,calificacion,estado) values (?,?,?,?,?) ";

        try {
            ps = cin.prepareCall(insertar);
            ps.setString(1, id);
            ps.setString(2, user);
            ps.setString(3, company);
            ps.setFloat(4, 0.0F);
            ps.setString(5, "Activo");
            ps.executeUpdate();
            JOptionPane.showMessageDialog(providerRegister, "Registrado con exito");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(providerRegister, "No Registrado ");
        }
    }
    
      /**
     * Método para insertar la información del proveedor en la base de datos
     * @param userName  Atributo que guarda el nombre de usuario del proveedor a insertar en la base de datos
     * @param province Atributo que guarda la provincia del proveedor en la base de datos
     * @param canton Atributo que guarda el cantón del proveedor en la base de datos
     * @param distrito Atributo que guarda el distrito del proveedor en la base de datos
     * @param senas Atributo que guarda la dirección en señas del proveedor en la base de datos
     * @param phone Atributo que guarda la cédula del proveedor en la base de datos
     * @param email Atributo que guarda el correo electrónico del proveedor en la base de datos
     * @param website Atributo que guarda el sitio web del proveedor en la base de datos
     * @param schedule Atributo que guarda el horario del proveedor en la base de datos
     * @param profile Atributo que guarda el perfil del proveedor en la base de datos
     * @throws ApiException Excepción utilizada para capturar cualquier error referente a la conexión con el API
     * @throws InterruptedException Excepción utilizada para capturar errores cuando un proceso se está completando y se interrumpe
     * @throws IOException Excepción utilizada para capturar errores referentes a la entrada, salida de información
     */

    public void providerInformationRegister(String userName, String province, String canton, String distrito, String senas, int phone, String email, String website, String schedule, String profile) throws ApiException, InterruptedException, IOException {

        String insertar = "insert into informacionProveedor(nombreUsuario,cedula,provincia,canton,distrito,señas,telefono,correoElectronico,ubicacion,latitud,longitud,sitio,horario,perfil) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
        String ubication = distrito + "," + canton + "," + province + "," + "Costa Rica";
        String lat = String.valueOf(Latitude(ubication));
        String lon = String.valueOf(Longitude(ubication));
        try {
            ps = cin.prepareCall(insertar);
            ps.setString(1, userName);
            ps.setString(2, null);
            ps.setString(3, province);
            ps.setString(4, canton);
            ps.setString(5, distrito);
            ps.setString(6, senas);
            ps.setInt(7, phone);
            ps.setString(8, email);
            ps.setString(9, ubication);
            ps.setString(10, lat);
            ps.setString(11, lon);
            ps.setString(12, website);
            ps.setString(13, schedule);
            ps.setString(14, profile);

            ps.executeUpdate();
            JOptionPane.showMessageDialog(providerRegister, "Registrado con exito");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(providerRegister, e.toString());
        }
    }
    
      /**
     * Método para insertar la información del proveedor en la base de datos
     * @param userName  Atributo que guarda el nombre de usuario del cliente a insertar en la base de datos
     * @param id Atributo que guarda la cédula del cliente en la base de datos
     * @param province Atributo que guarda la provincia del cliente en la base de datos
     * @param canton Atributo que guarda el cantón del cliente en la base de datos
     * @param distrito Atributo que guarda el distrito del cliente en la base de datos
     * @param senas Atributo que guarda la dirección en señas del cliente en la base de datos
     * @param phone Atributo que guarda la cédula del cliente en la base de datos
     * @param email Atributo que guarda el correo electrónico del cliente en la base de datos
     * @throws ApiException Excepción utilizada para capturar cualquier error referente a la conexión con el API
     * @throws InterruptedException Excepción utilizada para capturar errores cuando un proceso se está completando y se interrumpe
     * @throws IOException Excepción utilizada para capturar errores referentes a la entrada, salida de información
     */

    public void clientInformationRegister(String userName, int id, String province, String canton, String distrito, String senas, int phone, String email) throws ApiException, InterruptedException, IOException {

        String insertar = "insert into informacionCliente (nombreUsuario,cedula,provincia,canton,distrito,señas,telefono,correoElectronico,ubicacion,latitud,longitud,sitio,horario,perfil) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
        String ubication = distrito + "," + canton + "," + province + "," + "Costa Rica";
        String lat = String.valueOf(Latitude(ubication));
        String lon = String.valueOf(Longitude(ubication));
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
            ps.setString(10, lat);
            ps.setString(11, lon);
            ps.setString(12, null);
            ps.setString(13, null);
            ps.setString(14, null);

            ps.executeUpdate();
            JOptionPane.showMessageDialog(clientRegister, "Registrado con exito");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(clientRegister, "No Registrado ");
        }
    }
    
    /**
     * Método para obtener los proveedores en la base de datos
     * @param table  Atributo que guarda la tabla donde se mostrará todos los proveedores de la base de datos
     */

    public void getAllProviders(JTable table) {

        try {
            DefaultTableModel modelo = new DefaultTableModel();
            table.setModel(modelo);
            ResultSet rs = null;
            String login = "SELECT A.idProveedor,A.nombreUsuario,A.empresa,A.calificacion,A.estado,B.provincia,B.canton,B.distrito,B.señas,B.telefono,B.correoElectronico,B.ubicacion,B.sitio,B.horario,B.perfil FROM proveedores A,informacionProveedor B where A.idProveedor=B.nombreUsuario";
            ps = cin.prepareStatement(login);
            rs = ps.executeQuery();
            ResultSetMetaData rsMd = rs.getMetaData();
            int cantidadColumnas = rsMd.getColumnCount();
            modelo.addColumn("idProveedor");
            modelo.addColumn("Nombre");
            modelo.addColumn("Empresa");
            modelo.addColumn("Calificacion");
            modelo.addColumn("Estado");
            modelo.addColumn("Provincia");
            modelo.addColumn("Canton");
            modelo.addColumn("Distrito");
            modelo.addColumn("Señas");
            modelo.addColumn("Telefono");
            modelo.addColumn("Correo");
            modelo.addColumn("Ubicacion");
            modelo.addColumn("Sito Web");
            modelo.addColumn("Horario");
            modelo.addColumn("Perfil");

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
     * Método para eliminar los proveedores en la base de datos
     * @param id  Atributo que guarda e codigo del proveedor a eliminar de la base de datos
     */

    public void deleteProvider(String id) {
        String delete = "delete  from proveedores where idProveedor='" + id + "'";

        try {
            ps = cin.prepareCall(delete);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(adminProvider, "Proveedor eliminado con exito");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(adminProvider, e.toString());
        }
    }
    
    /**
     * Método para eliminar la información de los proveedores en la base de datos
     * @param id  Atributo que guarda e codigo del proveedor a eliminar de la base de datos
     */

    public void deleteProviderInformation(String id) {
        String delete = "delete from informacionProveedor where nombreUsuario='" + id + "'";

        try {
            ps = cin.prepareCall(delete);
            ps.executeQuery();

        } catch (Exception e) {

        }
    }
    
      /**
     * Método para insertar la información del proveedor en la base de datos
     * @param userName  Atributo que guarda el nombre de usuario del proveedor a insertar en la base de datos
     * @param province Atributo que guarda la provincia del proveedor en la base de datos
     * @param canton Atributo que guarda el cantón del proveedor en la base de datos
     * @param distrito Atributo que guarda el distrito del proveedor en la base de datos
     * @param señas Atributo que guarda la dirección en señas del proveedor en la base de datos
     * @param phone Atributo que guarda la cédula del proveedor en la base de datos
     * @param email Atributo que guarda el correo electrónico del proveedor en la base de datos
     * @param website Atributo que guarda el sitio web del proveedor en la base de datos
     * @param schedule Atributo que guarda el horario del proveedor en la base de datos
     * @param profile Atributo que guarda el perfil del proveedor en la base de datos
     * @param ubication Atributo que guarda la ubicacion del proveedor en la base de datos
     */

    public void updateProviderInformation(String userName, String province, String canton, String distrito, String señas, int phone, String email, String ubication, String website, String schedule, String profile) {

        String update = "UPDATE informacionProveedor SET  provincia='" + province + "',canton='" + canton + "',distrito='" + distrito + "',señas='" + señas + "',telefono='" + phone + "',correoElectronico='" + email + "',ubicacion='" + ubication + "',sitio='" + website + "',horario='" + schedule + "',perfil='" + profile + "' WHERE nombreUsuario='" + userName + "'";
        try {

            ps = cin.prepareStatement(update);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(adminProvider, "Informacion editada con exito");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(adminProvider, e.toString());
        }
    }
    
    /**
     * Método para actualizar los proveedores en la base de datos
     * @param id  Atributo que guarda e codigo del proveedor a actualizar de la base de datos
     */

    public void updateProviderStatus(String id) {
        String update = "UPDATE proveedores SET  estado='Inactivo' WHERE idProveeedor='"+id+"'";
        try {

            ps = cin.prepareStatement(update);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(adminProvider, "Informacion editada con exito");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(adminProvider, e.toString());
        }
    }
    
    /**
     * Método para eliminar los proveedores en la base de datos
     * @param combo  Atributo que guarda el combobox donde se mostrará los proveedores de la base de datos
     */

    public void getComboProviders(JComboBox combo) {
        String poi = "SELECT idProveedor FROM proveedores";
        try {

            ps = cin.prepareCall(poi);
            ResultSet result = ps.executeQuery();

            while (result.next()) {
                combo.addItem(result.getString("idProveedor"));
            }

        } catch (Exception e) {

        }
    }
    
    /**
     * Método para eliminar los proveedores en la base de datos
     * @param combo  Atributo que guarda el combobox donde se mostrará los proveedores por ubicacion de la base de datos
     */

    public void getComboProvidersUbication(JComboBox combo) {
        String poi = "SELECT ubicacion FROM informacionProveedor GROUP BY ubicacion";
        try {

            ps = cin.prepareCall(poi);
            ResultSet result = ps.executeQuery();

            while (result.next()) {
                combo.addItem(result.getString("ubicacion"));
            }

        } catch (Exception e) {

        }
    }
    
    /**
     * Método para eliminar los proveedores en la base de datos
     * @return
     */

    public Collection<Providers> getProviders() {
        String poi = "SELECT ubicacion FROM informacionProveedor GROUP BY ubicacion";
        try {

            ps = cin.prepareCall(poi);
            ResultSet result = ps.executeQuery();

            while (result.next()) {
                this.provi = new Providers(result.getString("ubicacion"));
                array.add(provi);
            }

        } catch (Exception e) {

        }
        return array;
    }
    
    /**
     * Método para eliminar los proveedores en la base de datos
     * @param userName  Atributo que guarda el nombre de usuario del proveedor para obtener la informacion de la base de datos
     * @return
     */

    public List<Providers> getProvidersUser(String userName) {
        String poi = "SELECT idProveedor FROM proveedores WHERE nombreUsuario = '" + userName + "'";
        try {

            ps = cin.prepareCall(poi);
            ResultSet result = ps.executeQuery();

            while (result.next()) {
                this.provi = new Providers(result.getString("idProveedor"), "lol");
                arrayUser.add(provi);
            }

        } catch (Exception e) {

        }
        return arrayUser;
    }
    
    /**
     * Método para obetener la lista de usuarios
     * @return
     */

    public List<Providers> getUserList() {
        return arrayUser;
    }
    
    /**
     * Método para obetener la ubicacion de los proveedores
     * @return
     */

    public List<Providers> getUbication() {
        return array;
    }
    
    /**
     * Método para eliminar los proveedores en la base de datos
     * @param newProvider  Atributo que guarda el el proveedor para obtener la informacion de la lista
     * @return
     */
    
    public List<Providers> searchUser(Providers newProvider) {

        List<Providers> results = new ArrayList<>();
        boolean byID = newProvider.getId() != null && newProvider.getId().length() > 0;

        for (Providers pro : arrayUser) {
            boolean add = !(byID);
            if (!add && byID && pro.getId().contains(newProvider.getId())) {
                add = true;
            }
            if (add) {
                results.add(pro);
            }
        }
        return results;
    }
    
    /**
     * Método para eliminar los proveedores en la base de datos
     * @param newProvider  Atributo que guarda el proveedor para obtener la informacion de la lista
     * @return
     */

    public List<Providers> search(Providers newProvider) {

        List<Providers> results = new ArrayList<>();
        boolean byUbication = newProvider.getUbication() != null && newProvider.getUbication().length() > 0;

        for (Providers pro : array) {
            boolean add = !(byUbication);
            if (!add && byUbication && pro.getUbication().contains(newProvider.getUbication())) {
                add = true;
            }
            if (add) {
                results.add(pro);
            }
        }
        return results;
    }
    
    /**
     * Método para mostrar gráfico de proveedores
     * @param panel  Atributo que guarda el panel donde se mostrará el gráfico de los proveedores
     */

    public void showGraph(JPanel panel) {
        try {
            ResultSet rs = null;
            String poi = "select top 5 (B.nombreUsuario), avg(B.calificacion) as cantidad from pedidos A inner join proveedores B on B.idProveedor=A.idProveedor group by B.nombreUsuario order by cantidad desc";
            ps = cin.prepareCall(poi);
            ResultSet result = ps.executeQuery();

            DefaultCategoryDataset dod = new DefaultCategoryDataset();
            while (result.next()) {
                dod.addValue(result.getInt(2), "Productos", result.getString(1));
            }

            JFreeChart jchart = ChartFactory.createBarChart("Proveedores", "Calificacion", "Producto", dod, PlotOrientation.VERTICAL, true, true, false);
            CategoryPlot plot = jchart.getCategoryPlot();
            plot.setRangeGridlinePaint(Color.black);

            ChartPanel chartPanel = new ChartPanel(jchart);
            panel.removeAll();
            panel.add(chartPanel);
            panel.updateUI();

        } catch (SQLException ex) {

        }
    }
    
    /**
     * Método para eliminar los proveedores en la base de datos
     * @param id Atributo que guarda el proveedor para obtener la informacion de la lista
     * @return
     */

    public String getProviderCordinates(String id) {
        String cordinates = "";
        String poi = "SELECT latitud,longitud FROM informacionProveedor where nombreUsuario='" + id + "'";
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
