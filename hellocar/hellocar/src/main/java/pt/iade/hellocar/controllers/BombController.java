package pt.iade.hellocar.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@RestController
@RequestMapping(path = "/bomb")
public class BombController {
    private Logger logger = LoggerFactory.getLogger(BombController.class);

    @GetMapping(path = "/localizacao/{lat}/{loc}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String Localization(@PathVariable("lat") float latitude, @PathVariable("loc") float longitude) {
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

            JSONObject jsonObject = (JSONObject) JsonValue.parse(result);


            URL urls = new URL("https://precocombustiveis.pt/"+JsonDeserialize(result).address[0].city+"/");
            HttpURLConnection conns = (HttpURLConnection) urls.openConnection();
            conns.setRequestMethod("GET");
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(conns.getInputStream()))) {
                for (String line; (line = reader.readLine()) != null;) {
                    result.append(line);
                }
            }


            return result.toString();
        } catch (Exception e) {

        }

        return "Hello World";
    }
}
