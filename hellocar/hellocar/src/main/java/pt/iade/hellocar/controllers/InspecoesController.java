package pt.iade.hellocar.controllers;

import java.io.StringReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import pt.iade.hellocar.models.ClassCar;
import pt.iade.hellocar.models.ClassInspecoes;
import pt.iade.hellocar.models.ClassLogin;
import pt.iade.hellocar.models.ClassNewInspection;

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

            while (rs.next()) {
                ClassInspecoes as = new ClassInspecoes();
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

    @PostMapping(path = "/nova", produces = MediaType.APPLICATION_JSON_VALUE)
    public String Login(@RequestBody String body) {
        logger.info("Saying Hello to the world11");

        String url = "jdbc:mysql://localhost:3306/trackcar";
        String user = "root";
        String pass = "admin";
        // You don't need a username and password for integrated security

        ClassLogin as = new ClassLogin();

        try {

            ClassNewInspection jsonObject = new Gson().fromJson(body, ClassNewInspection.class);

            String CentroDeInspecoes = jsonObject.getCentroDeInspecoes();
            String DataHoraCentro = jsonObject.getDataHoraCentro();
            String Carro = jsonObject.getCarro();

            String Modelo = Carro.split("(")[0];
            String MatriculaCarro = Carro.split("(")[1].replace(")", "");

            Connection connection = DriverManager.getConnection(url, user, pass);
            var st = connection.createStatement();

            st.executeUpdate("insert into tblinspecoes(DataHora, CentroDeInspecao, idEstado, Reprovado) values('"
                    + DataHoraCentro + "', (select idCentro From tblcentrodeinspecao where NomeCentro like '"
                    + CentroDeInspecoes + "'), 3, false)");

            st.executeUpdate(
                    "insert into tblinscar(idCar, idInspecao) values((select idCar From tblCar where MatriculaCarro like '"
                            + MatriculaCarro + "' and Modelo like '" + Modelo
                            + "'), (Select idInspecao From tblinspecoes where dataHora like '" + DataHoraCentro
                            + "' and CentroDeInspecao like '" + CentroDeInspecoes + "'))");

            System.out.println("Database connected!");

            connection.close();
            return "A inspecção foi adicionada com sucesso";
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return "Error, " + e.getMessage();
            // throw new IllegalStateException("Cannot connect the database!", e);
        }

    }

}
