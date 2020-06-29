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

    Providers provi;
    ProviderRegister providerRegister;
    AdminProvider adminProvider;
    ClientRegister clientRegister;
    Conexion conexion = new Conexion();
    Connection cin = conexion.getConnection();
    PreparedStatement ps;
    ArrayList<Providers> array = new ArrayList<>();
    ArrayList<Providers> arrayUser = new ArrayList<>();

    public ProviderDAO() {
    }

    public void providerRegister(String id, String user, String name, String company) {

        String insertar = "insert into proveedores (idProveedor,nombreUsuario,nombre,empresa,calificacion,estado) values (?,?,?,?,?,?) ";

        try {
            ps = cin.prepareCall(insertar);
            ps.setString(1, id);
            ps.setString(2, user);
            ps.setString(3, name);
            ps.setString(4, company);
            ps.setFloat(5, 0.0F);
            ps.setString(6, "Activo");
            ps.executeUpdate();
            JOptionPane.showMessageDialog(providerRegister, "Registrado con exito");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(providerRegister, "No Registrado ");
        }
    }

    public void providerInformationRegister(String username, String provincia, String canton, String distrito, String señas, int phone, String email, String website, String schedule, String profile) throws ApiException, InterruptedException, IOException {

        String insertar = "insert into informacionProveedor(nombreUsuario,cedula,provincia,canton,distrito,señas,telefono,correoElectronico,ubicacion,latitud,longitud,sitio,horario,perfil) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
        String ubication = distrito + "," + canton + "," + provincia + "," + "Costa Rica";
        String lat = String.valueOf(Latitude(ubication));
        String lon = String.valueOf(Longitude(ubication));
        try {
            ps = cin.prepareCall(insertar);
            ps.setString(1, username);
            ps.setString(2, null);
            ps.setString(3, provincia);
            ps.setString(4, canton);
            ps.setString(5, distrito);
            ps.setString(6, señas);
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

    public void clientInformationRegister(String username, int cedula, String provincia, String canton, String distrito, String señas, int phone, String email) throws ApiException, InterruptedException, IOException {

        String insertar = "insert into informacion(nombreUsuario,cedula,provincia,canton,distrito,señas,telefono,correoElectronico,ubicacion,latitud,longitud,sitio,horario,perfil) values (?,?,?,?,?,?,?,?,?,?,?,?) ";
        String ubication = distrito + "," + canton + "," + provincia + "," + "Costa Rica";
        String lat = String.valueOf(Latitude(ubication));
        String lon = String.valueOf(Longitude(ubication));
        try {
            ps = cin.prepareCall(insertar);
            ps.setString(1, username);
            ps.setInt(2, cedula);
            ps.setString(3, provincia);
            ps.setString(4, canton);
            ps.setString(5, distrito);
            ps.setString(6, señas);
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

    public void getAllProviders(JTable table) {

        try {
            DefaultTableModel modelo = new DefaultTableModel();
            table.setModel(modelo);
            ResultSet rs = null;
            String login = "SELECT * FROM proveedores A,informacionProveedor B";
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

    public void deleteProviderInformation(String id) {
        String delete = "delete from informacionProveedor where nombreUsuario='" + id + "'";

        try {
            ps = cin.prepareCall(delete);
            ps.executeQuery();

        } catch (Exception e) {

        }
    }

    public void updateProviderInformation(String username, String provincia, String canton, String distrito, String señas, int phone, String email, String ubication, String website, String schedule, String profile) {

        String update = "UPDATE informacionProveedor SET  provincia='" + provincia + "',canton='" + canton + "',distrito='" + distrito + "',señas='" + señas + "',telefono='" + phone + "',correoElectronico='" + email + "',ubicacion='" + ubication + "',sitio='" + website + "',horario='" + schedule + "',perfil='" + profile + "' WHERE nombreUsuario='" + username + "'";
        try {

            ps = cin.prepareStatement(update);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(adminProvider, "Informacion editada con exito");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(adminProvider, e.toString());
        }
    }

    public void updateProviderStatus() {
        String update = "UPDATE proveedores SET  estado='Inactivo'";
        try {

            ps = cin.prepareStatement(update);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(adminProvider, "Informacion editada con exito");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(adminProvider, e.toString());
        }
    }

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

    public List<Providers> getUserList() {
        return arrayUser;
    }

    public List<Providers> getUbication() {
        return array;
    }

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

    public void showGraph(JPanel panel) {
        try {
            ResultSet rs = null;
            String poi = "select top 5 (B.nombre), avg(B.calificacion) as cantidad from pedidos A inner join proveedores B on B.idProveedor=A.idProveedor group by B.nombre order by cantidad desc";
            ps = cin.prepareCall(poi);
            ResultSet result = ps.executeQuery();

            DefaultCategoryDataset dod = new DefaultCategoryDataset();
            while (result.next()) {
                dod.addValue(result.getInt(2), "Productos", result.getString(1));
            }

            JFreeChart jchart = ChartFactory.createBarChart("Proveedores", "CalificaciÃ³n", "Producto", dod, PlotOrientation.VERTICAL, true, true, false);
            CategoryPlot plot = jchart.getCategoryPlot();
            plot.setRangeGridlinePaint(Color.black);

            ChartFrame chartFrm = new ChartFrame("Productos", jchart, true);
            chartFrm.setVisible(true);
            chartFrm.setSize(500, 400);

            ChartPanel chartPanel = new ChartPanel(jchart);
            panel.removeAll();
            panel.add(chartPanel);
            panel.updateUI();

        } catch (SQLException ex) {

        }
    }

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
