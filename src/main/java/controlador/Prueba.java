/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

/*
import dao.UserDAO;
import java.sql.SQLException;
import vista.ChooseRole;*/
//import com.google.maps.errors.ApiException;
import com.google.maps.errors.ApiException;
import static dao.GoogleAPI.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.Random;

/**
 *
 * @author jabre
 */
public class Prueba {

 
    public static void main(String[] args) throws InterruptedException, IOException, ApiException {

        double pan;
        pan = Latitude("San Rafael,Cartago,Costa Rica");
        System.out.println("--------------------------------");
        System.out.println(pan);
        System.out.println(Longitude("San Rafael,Cartago,Costa Rica"));

        pan = Latitude("Pavas,San Jose,Costa Rica");
        System.out.println("--------------------------------");
        System.out.println(pan);
        System.out.println(Longitude("Pavas,San Jose,Costa Rica"));

        File fNuevo = new File("C:\\HTMLGMaps\\simple_map.html");
        //Archivos arch = new Archivos();
        //arch.modificar(fNuevo,"origen","lat: 9.9280694, lng: -84.0907246");

    }
}
    