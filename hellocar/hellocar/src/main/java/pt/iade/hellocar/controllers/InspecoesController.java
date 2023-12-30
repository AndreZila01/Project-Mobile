package pt.iade.hellocar.controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import pt.iade.hellocar.models.ClassInspecoes;
import pt.iade.hellocar.models.ClassLogin;

@RestController
@RequestMapping(path = "/inspecoes")
public class InspecoesController {

    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    @GetMapping(path = "/centros", produces = MediaType.APPLICATION_JSON_VALUE)
    public String RetornarCentros() {
        logger.info("Saying Hello to the world");

        String url = "jdbc:mysql://localhost:3306/trackcar";
        String user = "root";
        String pass = "admin";
        // You don't need a username and password for integrated security

        try {

            Connection connection = DriverManager.getConnection(url, user, pass);
            var st = connection.createStatement();
            ResultSet rs = st.executeQuery("select * from tblcentrodeinspecao order by idCentro");

            System.out.println("Database connected!");
            ArrayList<ClassInspecoes> s = new ArrayList<ClassInspecoes>();
            ClassInspecoes as = new ClassInspecoes();

            while (rs.next()) {
                as.setNomeCentro(rs.getString("NomeCentro"));
                // as.setChefeDoCentro(rs.getString("ChefeDoCentro"));
                as.setIdCentro(Integer.parseInt(rs.getString("idCentro")));
                // as.setTelefone(rs.getString("Telefone"));
                // as.setMorada(rs.getString("Morada"));

                s.add(as);
            }
            if (s.size() > 0)
                return new Gson().toJson(s);
            else
                return "vazio!!";

        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }

    }

}
