package pt.iade.hellocar.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pt.iade.hellocar.models.ClassBombas.Bombas;

@RestController
@RequestMapping(path = "/bomb")
public class BombController {
    private Logger logger = LoggerFactory.getLogger(BombController.class);

    
    @GetMapping(path = "/localizacao/{lat}/{loc}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getGreeting(@PathVariable("lat") float latitude, @PathVariable("loc") float longitude) {
        logger.info("Saying Hello to the world");

        StringBuilder result = new StringBuilder();

        try {
            URL url = new URL("https://nominatim.openstreetmap.org/reverse?lat=" + latitude + "&lon=" + longitude
                    + "&format=json&extratags=1");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()))) {
                for (String line; (line = reader.readLine()) != null;) {
                    result.append(line);
                }
            }
            return result.toString();
        } catch (Exception e) {

        }

        return "Hello World";
    }

    @GetMapping(path = "/localizacao/{lat}/{loc}/bombas", produces = MediaType.APPLICATION_JSON_VALUE)
    public String BombasClose(@PathVariable("lat") float latitude, @PathVariable("loc") float longitude) {
        logger.info("Saying Hello to the world");
        StringBuilder result = new StringBuilder();
        String json = "[";
        String value = new String();
        try {
            URL url = new URL("https://nominatim.openstreetmap.org/reverse?lat=" + latitude + "&lon=" + longitude
                    + "&format=json&extratags=1");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                for (String line; (line = reader.readLine()) != null;) {
                    result.append(line);
                    value = line;
                }
            }

            JSONObject jsonObject = new JSONObject(value);
            JSONObject addressObject = jsonObject.getJSONObject("address");

            String cityName = addressObject.getString("city");

            URL urls = new URL("https://precocombustiveis.pt/" + cityName + "/");
            HttpURLConnection connection = null;

            try {
                // Create connection
                // URL ursl = new URL(urls);
                connection = (HttpURLConnection) urls.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Content-Type",
                        "application/x-www-form-urlencoded");

                connection.setRequestProperty("Content-Language", "en-US");

                connection.setUseCaches(false);
                connection.setDoOutput(true);

                // Get Response
                InputStream is = connection.getInputStream();
                BufferedReader rd = new BufferedReader(new InputStreamReader(is));
                StringBuilder response = new StringBuilder(); // or StringBuffer if Java version 5+
                String line;
                while ((line = rd.readLine()) != null) {
                    response.append(line);
                    response.append('\r');
                }
                rd.close();

                String[] bombas;

                bombas = response.toString().split("<main>")[1].split("</div></div></div> </a> <a ");
                int values = 0;

                ArrayList<Bombas> lstbomb = new ArrayList<Bombas>();
                for (String string : bombas) {
                    Bombas bb = new Bombas();
                    values++;
                    json += "{\"id\":" + values;
                    urls = new URL(string.split("href=\"")[1].split("\"")[0].toString());

                    json += ",\"NomeBomba\":\""
                            + string.split("<div class=\"simg ")[1].split("></div>")[1].split("</div>")[0]+"\"";
                    if (string.contains("simg-f5\"></div><span>€ "))
                        json += ",\"GasoleoS\":"
                                + Float.parseFloat(string.split("simg-f5\"></div><span>€ ")[1].split("</span>")[0]);
                    else
                        bb.GasoleoS = 0.0f;

                    if (string.contains("simg-f4\"></div><span>€ "))
                        json += ",\"GasoleoE\":"
                                + Float.parseFloat(string.split("simg-f4\"></div><span>€ ")[1].split("</span>")[0]);
                    else
                        bb.GasoleoE = 0.0f;

                    if (string.contains("simg-f10\"></div><span>€ "))
                        json += ",\"GasolinaS95\":" + Float
                                .parseFloat(string.split("simg-f10\"></div><span>€ ")[1].split("</span>")[0]);
                    else
                        bb.GasolinaS95 = 0.0f;

                    if (string.contains("simg-f8\"></div><span>€ "))
                        json += ",\"GasolinaE95\":" + Float
                                .parseFloat(string.split("simg-f8\"></div><span>€ ")[1].split("</span>")[0]);
                    else
                        bb.GasolinaE95 = 0.0f;

                    if (string.contains("simg-f6\"></div><span>€ "))
                        json += ",\"Gasolina98\":" + Float
                                .parseFloat(string.split("simg-f6\"></div><span>€ ")[1].split("</span>")[0]);
                    else
                        bb.Gasolina98 = 0.0f;

                    if (string.contains("simg-f9\"></div><span>€ "))
                        json += ",\"GasolinaE98\":" + Float
                                .parseFloat(string.split("simg-f9\"></div><span>€ ")[1].split("</span>")[0]);
                    else
                        bb.GasolinaE98 = 0.0f;

                    if (string.contains("simg-f14\"></div><span>€ "))
                        json += ",\"GPLAuto\":"
                                + Float.parseFloat(string.split("simg-f14\"></div><span>€ ")[1].split("</span>")[0]);
                    else
                        bb.GPLAuto = 0.0f;

                    try {
                        HttpURLConnection connections = null;

                        connections = (HttpURLConnection) urls.openConnection();
                        connections.setRequestMethod("GET");
                        connections.setRequestProperty("Content-Type",
                                "application/x-www-form-urlencoded");

                        connections.setRequestProperty("Content-Language", "en-US");

                        connections.setUseCaches(false);
                        connections.setDoOutput(true);

                        // Get Response
                        InputStream iss = connections.getInputStream();
                        BufferedReader rds = new BufferedReader(new InputStreamReader(iss));
                        StringBuilder responses = new StringBuilder(); // or StringBuffer if Java version 5+
                        String lines;
                        while ((lines = rds.readLine()) != null) {
                            responses.append(lines);
                            responses.append('\r');
                        }
                        rds.close();
                        float loc = 0.0f;
                        float lat = 0.0f;

                        loc = Float.parseFloat(responses.toString().split("longitude\":\"")[1].split("\"")[0]);
                        lat = Float.parseFloat(responses.toString().split("latitude\":\"")[1].split("\"")[0]);

                        json += ", \"LonxLac\": \"" + ("" + loc + "x" + lat) + "\"},";

                    } catch (Exception ex) {

                    }
                }
            } catch (Exception ex) {

            }

        } catch (Exception e) {
        }
        return json.substring(0, (json.length() - 1)) + "]";
        // return "Hello World";

    }
}
