package pt.iade.hellocar.controllers;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.mysql.cj.xdevapi.JsonArray;

import pt.iade.hellocar.models.ClassBombas;

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
                connection.setRequestProperty("content-type", "application/json;  charset=utf-8");

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

                ArrayList<ClassBombas> lstbomb = new ArrayList<ClassBombas>();
                for (String string : bombas) {
                    ClassBombas bb = new ClassBombas();
                    values++;
                    json += "{\"id\":" + values;
                    urls = new URL(string.split("href=\"")[1].split("\"")[0].toString());

                    json += ",\"NomeBomba\":\""
                            + string.split("<div class=\"simg ")[1].split("></div>")[1].split("</div>")[0] + "\"";
                    if (string.contains("simg-f5\"></div><span>€ "))
                        json += ",\"GasoleoS\":"
                                + Float.parseFloat(string.split("simg-f5\"></div><span>€ ")[1].split("</span>")[0]);

                    if (string.contains("simg-f4\"></div><span>€ "))
                        json += ",\"GasoleoE\":"
                                + Float.parseFloat(string.split("simg-f4\"></div><span>€ ")[1].split("</span>")[0]);

                    if (string.contains("simg-f10\"></div><span>€ "))
                        json += ",\"GasolinaS95\":" + Float
                                .parseFloat(string.split("simg-f10\"></div><span>€ ")[1].split("</span>")[0]);

                    if (string.contains("simg-f8\"></div><span>€ "))
                        json += ",\"GasolinaE95\":" + Float
                                .parseFloat(string.split("simg-f8\"></div><span>€ ")[1].split("</span>")[0]);

                    if (string.contains("simg-f6\"></div><span>€ "))
                        json += ",\"Gasolina98\":" + Float
                                .parseFloat(string.split("simg-f6\"></div><span>€ ")[1].split("</span>")[0]);

                    if (string.contains("simg-f9\"></div><span>€ "))
                        json += ",\"GasolinaE98\":" + Float
                                .parseFloat(string.split("simg-f9\"></div><span>€ ")[1].split("</span>")[0]);

                    if (string.contains("simg-f14\"></div><span>€ "))
                        json += ",\"GPLAuto\":"
                                + Float.parseFloat(string.split("simg-f14\"></div><span>€ ")[1].split("</span>")[0]);

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

    @GetMapping(path = "/localizacao/{lat}/{loc}/bombas/avg", produces = MediaType.APPLICATION_JSON_VALUE)
    public String AVG(@PathVariable("lat") float latitude, @PathVariable("loc") float longitude) {

        String s = BombasClose(latitude, longitude);
        try {
            ClassBombas[] json = new Gson().fromJson(s, ClassBombas[].class);
            int len = json.length;

            float GasoleoS = 0.0f;
            float GasoleoE = 0.0f;
            float GasolinaS95 = 0.0f;
            float GasolinaE95 = 0.0f;
            float Gasolina98 = 0.0f;
            float GasolinaE98 = 0.0f;
            float GPLAuto = 0.0f;

            // String aaa = (String) json.get(1).toString();

            // GasoleoS += (((JsonValue) json.get(a)).get("GasoleoS").getAsFloat());
            for (int a = 0; a < len; a++) {
                GasoleoS += json[a].geteoS();
                GasoleoE += json[a].geteoE();
                GasolinaS95 += json[a].getiNAs95();
                GasolinaE95 += json[a].getiNAe95();
                Gasolina98 += json[a].getiNA98();
                GasolinaE98 += json[a].getinaE98();
                GPLAuto += json[a].getGPL();
            }

            GasoleoS = (GasoleoS / len);
            GasoleoE = (GasoleoE / len);
            GasolinaS95 = (GasolinaS95 / len);
            GasolinaE95 = (GasolinaE95 / len);
            Gasolina98 = (Gasolina98 / len);
            GasolinaE98 = (GasolinaE98 / len);
            GPLAuto = (GPLAuto / len);
            return "[{\"GasoleoS\":" + GasoleoE + ",\"GasoleoE\":" + GasoleoE + ",\"GasolinaS95\":" + GasolinaS95
                    + ",\"GasolinaE95\":" + GasolinaE95 + ",\"Gasolina98\":" + Gasolina98 + ",\"GasolinaE98\":"
                    + GasolinaE98 + ",\"GPLAuto\":" + GPLAuto + "}]";
        } catch (Exception ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
        return "";
    }

    @GetMapping(path = "/localizacao/{lat}/{loc}/bombas/max", produces = MediaType.APPLICATION_JSON_VALUE)
    public String Max(@PathVariable("lat") float latitude, @PathVariable("loc") float longitude) {

        String s = BombasClose(latitude, longitude);
        try {
            ClassBombas[] json = new Gson().fromJson(s, ClassBombas[].class);
            int len = json.length;

            float GasoleoS = 0.0f;
            float GasoleoE = 0.0f;
            float GasolinaS95 = 0.0f;
            float GasolinaE95 = 0.0f;
            float Gasolina98 = 0.0f;
            float GasolinaE98 = 0.0f;
            float GPLAuto = 0.0f;

            // String aaa = (String) json.get(1).toString();

            // GasoleoS += (((JsonValue) json.get(a)).get("GasoleoS").getAsFloat());
            for (int a = 0; a < len; a++) {
                if (GasoleoS < json[a].geteoS() || GasoleoS != 0.0f)
                    GasoleoS = json[a].geteoS();

                if (GasoleoE < json[a].geteoE() || GasoleoE != 0.0f)
                    GasoleoE = json[a].geteoE();

                if (GasolinaS95 < json[a].getiNAs95() || GasolinaS95 != 0.0f)
                    GasolinaS95 = json[a].getiNAs95();

                if (GasolinaE95 < json[a].getiNAe95() || GasolinaE95 != 0.0f)
                    GasolinaE95 = json[a].getiNAe95();

                if (Gasolina98 < json[a].getiNA98() || Gasolina98 != 0.0f)
                    Gasolina98 = json[a].getiNA98();

                if (GasolinaE98 < json[a].getinaE98() || GasolinaE98 != 0.0f)
                    GasolinaE98 = json[a].getinaE98();

                if (GPLAuto < json[a].getGPL() || GPLAuto != 0.0f)
                    GPLAuto = json[a].getGPL();

            }

            return "[{\"GasoleoS\":" + GasoleoE + ",\"GasoleoE\":" + GasoleoE + ",\"GasolinaS95\":" + GasolinaS95
                    + ",\"GasolinaE95\":" + GasolinaE95 + ",\"Gasolina98\":" + Gasolina98 + ",\"GasolinaE98\":"
                    + GasolinaE98 + ",\"GPLAuto\":" + GPLAuto + "}]";
        } catch (Exception ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
        return "";
    }

    @GetMapping(path = "/localizacao/{lat}/{loc}/bombas/min", produces = MediaType.APPLICATION_JSON_VALUE)
    public String Min(@PathVariable("lat") float latitude, @PathVariable("loc") float longitude) {

        String s = BombasClose(latitude, longitude);
        try {
            ClassBombas[] json = new Gson().fromJson(s, ClassBombas[].class);
            int len = json.length;

            float GasoleoS = 0.0f;
            float GasoleoE = 0.0f;
            float GasolinaS95 = 0.0f;
            float GasolinaE95 = 0.0f;
            float Gasolina98 = 0.0f;
            float GasolinaE98 = 0.0f;
            float GPLAuto = 0.0f;

            // String aaa = (String) json.get(1).toString();

            // GasoleoS += (((JsonValue) json.get(a)).get("GasoleoS").getAsFloat());
            for (int a = 1; a < len; a++) {
                if (GasoleoS > json[a].geteoS() || GasoleoS == 0.0f)
                    GasoleoS = json[a].geteoS();

                if (GasoleoE > json[a].geteoE() || GasoleoE == 0.0f)
                    GasoleoE = json[a].geteoE();

                if (GasolinaS95 > json[a].getiNAs95() || GasolinaS95 == 0.0f)
                    GasolinaS95 = json[a].getiNAs95();

                if (GasolinaE95 > json[a].getiNAe95() || GasolinaE95 == 0.0f)
                    GasolinaE95 = json[a].getiNAe95();

                if (Gasolina98 > json[a].getiNA98() || Gasolina98 == 0.0f)
                    Gasolina98 = json[a].getiNA98();

                if (GasolinaE98 > json[a].getinaE98() || GasolinaE98 == 0.0f)
                    GasolinaE98 = json[a].getinaE98();

                if (GPLAuto > json[a].getGPL() || GPLAuto == 0.0f)
                    GPLAuto = json[a].getGPL();

            }

            return "[{\"GasoleoS\":" + GasoleoE + ",\"GasoleoE\":" + GasoleoE + ",\"GasolinaS95\":" + GasolinaS95
                    + ",\"GasolinaE95\":" + GasolinaE95 + ",\"Gasolina98\":" + Gasolina98 + ",\"GasolinaE98\":"
                    + GasolinaE98 + ",\"GPLAuto\":" + GPLAuto + "}]";
        } catch (Exception ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
        return "";
    }
}
