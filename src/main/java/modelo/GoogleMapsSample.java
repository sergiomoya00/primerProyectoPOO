/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;



import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApiRequest;
import com.google.maps.errors.ApiException;
import com.google.maps.internal.ApiResponse;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;



/**
 * This sample demonstrates how to load a web page with Google Maps
 * and control it using JxBrowser API.
 */
public class GoogleMapsSample {
    public static void main(String[] args){}
       

/**
 * Geocoding is the process of converting addresses (like "1600 Amphitheatre Parkway, Mountain View,
 * CA") into geographic coordinates (like latitude 37.423021 and longitude -122.083739), which you
 * can use to place markers or position the map. Reverse geocoding is the process of converting
 * geographic coordinates into a human-readable address.
 *
 * @see <a href="https://developers.google.com/maps/documentation/geocoding/">Geocoding
 *     documentation</a>
 */

  

  /**
   * Creates a new Geocoding API request.
   *
   * @param context The {@link GeoApiContext} to make requests through.
   * @return Returns the request, ready to run.
   */
  public static GeocodingApiRequest newRequest(GeoApiContext context) {
    return new GeocodingApiRequest(context);
  }

  /**
   * Requests the latitude and longitude of an {@code address}.
   *
   * @param context The {@link GeoApiContext} to make requests through.
   * @param address The address to geocode.
   * @return Returns the request, ready to run.
   */
  public static GeocodingApiRequest geocode(GeoApiContext context, String address) {
    GeocodingApiRequest request = new GeocodingApiRequest(context);
    request.address(address);
    return request;
  }

  /**
   * Requests the street address of a {@code location}.
   *
   * @param context The {@link GeoApiContext} to make requests through.
   * @param location The location to reverse geocode.
   * @return Returns the request, ready to run.
   */
  public static GeocodingApiRequest reverseGeocode(GeoApiContext context, LatLng location) {
    GeocodingApiRequest request = new GeocodingApiRequest(context);
    request.latlng(location);
    return request;
  }

}


    

    
