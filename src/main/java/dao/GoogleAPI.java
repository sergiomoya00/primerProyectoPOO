/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.google.maps.DirectionsApi;
import com.google.maps.DistanceMatrixApi;
import com.google.maps.DistanceMatrixApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.PlacesApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.TravelMode;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;

/**
 *
 * @author jabre
 */
public class GoogleAPI {
    
    /**
     *
     * Constructor vacío de la clase
     */
    
    public GoogleAPI(){}
    
    /**
     * Método para obtener el id de un lugar
     * @param place Parametro utilizada como de entrada, para el nombre del lugar
     * @throws ApiException Excepción utilizada para capturar cualquier error referente a la conexión con el API
     * @throws InterruptedException Excepción utilizada para capturar errores cuando un proceso se está completando y se interrumpe
     * @throws IOException Excepción utilizada para capturar errores referentes a la entrada, salida de información
     * @return 
     * 
     */
    
    public static String PlaceID(String place) throws ApiException, InterruptedException, IOException {
        String PlaceID;
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey("AIzaSyDMRs2FQsJt2jmepKH9f2LSYA5rzGLuyP8")
                .build();
        GeocodingResult[] results = GeocodingApi.geocode(context,
                place).awaitIgnoreError();
        PlaceID = results[0].placeId;
        return PlaceID;
    }
    
    /**
     * Método para obtener el la dirección exacta del lugar
     * @param place Parametro utilizada como de entrada, para el nombre del lugar
     * @throws ApiException Excepción utilizada para capturar cualquier error referente a la conexión con el API
     * @throws InterruptedException Excepción utilizada para capturar errores cuando un proceso se está completando y se interrumpe
     * @throws IOException Excepción utilizada para capturar errores referentes a la entrada, salida de información
     * @return 
     * 
     */
    
    public static String ExactAddress(String place) throws ApiException, InterruptedException, IOException {
        String Address;
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey("AIzaSyDMRs2FQsJt2jmepKH9f2LSYA5rzGLuyP8")
                .build();
        GeocodingResult[] results = GeocodingApi.geocode(context,
                place).awaitIgnoreError();
        Address = results[0].formattedAddress;
        return Address;
    }
    
    /**
     * Método para obtener el tipo de lugar
     * @param place Parametro utilizada como de entrada, para el nombre del lugar
     * @throws ApiException Excepción utilizada para capturar cualquier error referente a la conexión con el API
     * @throws InterruptedException Excepción utilizada para capturar errores cuando un proceso se está completando y se interrumpe
     * @throws IOException Excepción utilizada para capturar errores referentes a la entrada, salida de información
     * @return 
     * 
     */
 

    public static String PlaceType(String place) throws ApiException, InterruptedException, IOException {
        String Type;
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey("AIzaSyDMRs2FQsJt2jmepKH9f2LSYA5rzGLuyP8")
                .build();
        GeocodingResult[] results = GeocodingApi.geocode(context,
                place).awaitIgnoreError();
        Type = Arrays.toString(results[0].types);
        return Type;
    }
    
    /**
     * Método para obtener la latitud de un lugar
     * @param place Parametro utilizada como de entrada, para el nombre del lugar
     * @throws ApiException Excepción utilizada para capturar cualquier error referente a la conexión con el API
     * @throws InterruptedException Excepción utilizada para capturar errores cuando un proceso se está completando y se interrumpe
     * @throws IOException Excepción utilizada para capturar errores referentes a la entrada, salida de información
     * @return 
     * 
     */

    public static double Latitude(String place) throws ApiException, InterruptedException, IOException {
        double Latitude;
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey("AIzaSyDMRs2FQsJt2jmepKH9f2LSYA5rzGLuyP8")
                .build();
        GeocodingResult[] results = GeocodingApi.geocode(context,
                place).awaitIgnoreError();
        Latitude = results[0].geometry.location.lat;
        return Latitude;
    }
    
    /**
     * Método para obtener la longitud de un lugar
     * @param place Parametro utilizada como de entrada, para el nombre del lugar
     * @throws ApiException Excepción utilizada para capturar cualquier error referente a la conexión con el API
     * @throws InterruptedException Excepción utilizada para capturar errores cuando un proceso se está completando y se interrumpe
     * @throws IOException Excepción utilizada para capturar errores referentes a la entrada, salida de información
     * @return 
     * 
     */

    public static double Longitude(String place) throws ApiException, InterruptedException, IOException {
        double Longitude;
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey("AIzaSyDMRs2FQsJt2jmepKH9f2LSYA5rzGLuyP8")
                .build();
        GeocodingResult[] results = GeocodingApi.geocode(context,
                place).awaitIgnoreError();
        Longitude = results[0].geometry.location.lng;
        return Longitude;
    }

    /**
     * Método para obtener la distancia entre un lugar y otro
     * @param AddressOne Parametro utilizada como de entrada, para el nombre del lugar de origen
     * @param AddressTwo Parametro utilizada como de entrada, para el nombre del lugar de destino
     * @throws ApiException Excepción utilizada para capturar cualquier error referente a la conexión con el API
     * @throws InterruptedException Excepción utilizada para capturar errores cuando un proceso se está completando y se interrumpe
     * @throws IOException Excepción utilizada para capturar errores referentes a la entrada, salida de información
     * @return 
     * 
     */

    public static long Distance(String AddressOne, String AddressTwo) throws ApiException, InterruptedException, IOException {
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey("AIzaSyDMRs2FQsJt2jmepKH9f2LSYA5rzGLuyP8")
                .build();
        DistanceMatrixApiRequest req = DistanceMatrixApi.newRequest(context);
        DistanceMatrix result = req.origins(AddressOne)
                .destinations(AddressTwo)
                .mode(TravelMode.DRIVING)
                .avoid(DirectionsApi.RouteRestriction.TOLLS)
                .language("es-ES")
                .await();
        long distApart = result.rows[0].elements[0].distance.inMeters;
        return distApart;
    }
    
    /**
     * Método para obtener el tiempo de duración entre un lugar y otro
     * @param AddressOne Parametro utilizada como de entrada, para el nombre del lugar de origen
     * @param AddressTwo Parametro utilizada como de entrada, para el nombre del lugar de destino
     * @throws ApiException Excepción utilizada para capturar cualquier error referente a la conexión con el API
     * @throws InterruptedException Excepción utilizada para capturar errores cuando un proceso se está completando y se interrumpe
     * @throws IOException Excepción utilizada para capturar errores referentes a la entrada, salida de información
     * @return 
     * 
     */

    public static long Time(String AddressOne, String AddressTwo) throws ApiException, InterruptedException, IOException {
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey("AIzaSyDMRs2FQsJt2jmepKH9f2LSYA5rzGLuyP8")
                .build();
        DistanceMatrixApiRequest req = DistanceMatrixApi.newRequest(context);
        DistanceMatrix result = req.origins(AddressOne)
                .destinations(AddressTwo)
                .mode(TravelMode.DRIVING)
                .avoid(DirectionsApi.RouteRestriction.TOLLS)
                .language("es-ES")
                .await();
        long distApart = result.rows[0].elements[0].duration.inSeconds;
        return distApart;
    }

}
