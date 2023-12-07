package pt.iade.hellocar.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/car/s")
public class LoginController {
    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getGreeting() {
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
