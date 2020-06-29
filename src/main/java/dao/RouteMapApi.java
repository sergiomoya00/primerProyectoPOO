package dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RouteMapApi {

    public RouteMapApi() {
    }

    public void createMap(String nombre, String origin, String destination) throws FileNotFoundException {

        String a = "<!DOCTYPE html>\n"
                + "<html>\n"
                + "  <head>\n"
                + "    <meta name=\"viewport\" content=\"initial-scale=1.0, user-scalable=no\">\n"
                + "    <meta charset=\"utf-8\">\n"
                + "    <title>Travel Modes in Directions</title>\n"
                + "    <style>\n"
                + "      /* Always set the map height explicitly to define the size of the div\n"
                + "       * element that contains the map. */\n"
                + "      #map {\n"
                + "        height: 100%;\n"
                + "      }\n"
                + "      /* Optional: Makes the sample page fill the window. */\n"
                + "      html, body {\n"
                + "        height: 100%;\n"
                + "        margin: 0;\n"
                + "        padding: 0;\n"
                + "      }\n"
                + "      #floating-panel {\n"
                + "        position: absolute;\n"
                + "        top: 10px;\n"
                + "        left: 25%;\n"
                + "        z-index: 5;\n"
                + "        background-color: #fff;\n"
                + "        padding: 5px;\n"
                + "        border: 1px solid #999;\n"
                + "        text-align: center;\n"
                + "        font-family: 'Roboto','sans-serif';\n"
                + "        line-height: 30px;\n"
                + "        padding-left: 10px;\n"
                + "      }\n"
                + "    </style>\n"
                + "  </head>\n"
                + "  <body>\n"
                + "    <div id=\"floating-panel\">\n"
                + "    <b>Mode of Travel: </b>\n"
                + "    <select id=\"mode\">\n"
                + "      <option value=\"DRIVING\">Driving</option>\n"
                + "      <option value=\"WALKING\">Walking</option>\n"
                + "      <option value=\"BICYCLING\">Bicycling</option>\n"
                + "      <option value=\"TRANSIT\">Transit</option>\n"
                + "    </select>\n"
                + "    </div>\n"
                + "    <div id=\"map\"></div>\n"
                + "    <script>\n"
                + "      function initMap() {\n"
                + "        var directionsRenderer = new google.maps.DirectionsRenderer;\n"
                + "        var directionsService = new google.maps.DirectionsService;\n"
                + "        var map = new google.maps.Map(document.getElementById('map'), {\n"
                + "          zoom: 14,\n"
                + "          center: {lat: 37.77, lng: -122.447}\n"
                + "        });\n"
                + "        directionsRenderer.setMap(map);\n"
                + "\n"
                + "        calculateAndDisplayRoute(directionsService, directionsRenderer);\n"
                + "        document.getElementById('mode').addEventListener('change', function() {\n"
                + "          calculateAndDisplayRoute(directionsService, directionsRenderer);\n"
                + "        });\n"
                + "      }\n"
                + "\n"
                + "      function calculateAndDisplayRoute(directionsService, directionsRenderer) {\n"
                + "        var selectedMode = document.getElementById('mode').value;\n"
                + "		\n"
                + "        directionsService.route({\n"
                + "          origin: {" + origin + "},  // Haight.\n"
                + "          destination: {" + destination + "},  // Ocean Beach.\n"
                + "          // Note that Javascript allows us to access the constant\n"
                + "          // using square brackets and a string value as its\n"
                + "          // \"property.\"\n"
                + "          travelMode: google.maps.TravelMode[selectedMode]\n"
                + "        }, function(response, status) {\n"
                + "          if (status == 'OK') {\n"
                + "            directionsRenderer.setDirections(response);\n"
                + "          } else {\n"
                + "            window.alert('Directions request failed due to ' + status);\n"
                + "          }\n"
                + "        });\n"
                + "      }\n"
                + "    </script>\n"
                + "    <script async defer\n"
                + "    src=\"https://maps.googleapis.com/maps/api/js?key=AIzaSyDMRs2FQsJt2jmepKH9f2LSYA5rzGLuyP8&callback=initMap\">\n"
                + "    </script>\n"
                + "  </body>\n"
                + "</html>\n"
                + "    ";

        PrintWriter pw = new PrintWriter(nombre + ".html");
        pw.println(a);
        pw.close();

    }

    public void openMap(String nombre) throws URISyntaxException, IOException {
        String url = "C:\\Users\\jabre\\OneDrive\\Documentos\\NetBeansProjects\\PRIMERPROYECTOPOO\\"+nombre+".html";
        ProcessBuilder p = new ProcessBuilder();
        p.command("cmd.exe", "/c", url);
        p.start();
    }

}
