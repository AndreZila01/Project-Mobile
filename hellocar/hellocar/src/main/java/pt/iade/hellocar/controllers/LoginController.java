package pt.iade.hellocar.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONString;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mysql.cj.xdevapi.JsonArray;
import com.mysql.cj.xdevapi.JsonValue;

import pt.iade.hellocar.models.ClassBombas;
import pt.iade.hellocar.models.ClassLogin;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "/Account")
public class LoginController {
    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    @PostMapping(path = "/Login", produces = MediaType.APPLICATION_JSON_VALUE)
    public String Login(@RequestBody String body) {
        logger.info("Saying Hello to the world11");

        String url = "jdbc:mysql://localhost:3306/trackcar";
        String user = "root";
        String pass = "admin";
        // You don't need a username and password for integrated security

        ClassLogin as = new ClassLogin();

        try {

            JsonObject jsonObject = new Gson().fromJson(body, JsonObject.class);

            // Now you can access values from the JsonObject
            String password = jsonObject.get("password").getAsString();
            String username = jsonObject.get("username").getAsString();

            Connection connection = DriverManager.getConnection(url, user, pass);
            var st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT Username, Password FROM trackcar.tbllogin where Username='"
                    + username + "' and Password='" + password + "'");

            System.out.println("Database connected!");
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }

        return "Hello World";
    }

    @GetMapping(path = "/Create", produces = MediaType.APPLICATION_JSON_VALUE)
    public String RegistNewAccount() {
        logger.info("Saying Hello to the world");

        String url = "jdbc:mysql://localhost:3306/trackcar";
        String user = "root";
        String pass = "admin";
        // You don't need a username and password for integrated security

        try {
            Connection connection = DriverManager.getConnection(url, user, pass);
            var st = connection.createStatement();
            ResultSet rs = st.executeQuery("select * from tblcar");
            System.out.println("Database connected!");
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }

        return "Hello World";
    }
}