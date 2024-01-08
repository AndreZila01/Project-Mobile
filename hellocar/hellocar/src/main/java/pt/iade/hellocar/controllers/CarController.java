package pt.iade.hellocar.controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import pt.iade.hellocar.models.ClassCar;
import pt.iade.hellocar.models.ClassInspecoes;
import pt.iade.hellocar.models.ClassLogin;

@RestController
@RequestMapping(path = "/car")
public class CarController {
    private Logger logger = LoggerFactory.getLogger(CarController.class);

    @GetMapping(path = "/{idUser}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String CarIdUser(@PathVariable("idUser") int idUser) {
        logger.info("Saying Hello to the world");

        String url = "jdbc:mysql://localhost:3306/trackcar";
        String user = "root";
        String pass = "admin";
        // You don't need a username and password for integrated security

        try {

            Connection connection = DriverManager.getConnection(url, user, pass);
            var st = connection.createStatement();
            ResultSet rs = st.executeQuery(
                    "select tbldetalhescarro.idCar, tblcar.MatriculaCarro, tblcar.Modelo from tblcar inner join tbldetalhescarro  on tbldetalhescarro.idCar = tblcar.idCar where idClient like "
                            + idUser);

            System.out.println("Database connected!");
            ArrayList<ClassCar> s = new ArrayList<ClassCar>();
            ClassCar as = new ClassCar();

            while (rs.next()) {
                as.setId(Integer.parseInt(rs.getString("idCar")));
                as.setMatriculaCarro(rs.getString("MatriculaCarro"));
                as.setModelo(rs.getString("Modelo"));
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

    @GetMapping(path = "/{idUser}/details", produces = MediaType.APPLICATION_JSON_VALUE)
    public String CarIdUserDetails(@PathVariable("idUser") int idUser) {
        logger.info("Saying Hello to the world");

        String url = "jdbc:mysql://localhost:3306/trackcar";
        String user = "root";
        String pass = "admin";
        // You don't need a username and password for integrated security

        try {
            
            Connection connection = DriverManager.getConnection(url, user, pass);
            var st = connection.createStatement();
            ResultSet rs = st.executeQuery(
                "select tbldetalhescarro.idCar, tblcar.MatriculaCarro, tblcar.Modelo, tblcar.Modelo, tblcar.MesAno, tblcar.CV, tblconsumo.Consumo, tblcar.KmFeitos, tblcar.LastLocalization from tblcar inner join tbldetalhescarro  on tbldetalhescarro.idCar = tblcar.idCar inner join tblconsumo on tblconsumo.idConsumo = tblcar.Consumo where idClient like "
                + idUser);
                
            System.out.println("Database connected!");
            ArrayList<ClassCar> s = new ArrayList<ClassCar>();
            
            while (rs.next()) {
                ClassCar as = new ClassCar();
                as.setId(Integer.parseInt(rs.getString("idCar")));
                as.setMatriculaCarro(rs.getString("MatriculaCarro"));
                as.setModelo(rs.getString("Modelo"));
                as.setMesAno(rs.getString("MesAno"));
                as.setCV(Integer.parseInt(rs.getString("CV")));
                as.setConsumo(rs.getString("Consumo"));
                as.setKmFeitos(Float.parseFloat(rs.getString("KmFeitos")));
                as.setLastLoc(rs.getString("LastLocalization"));
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
        
        @PostMapping(path = "/{idUser}/newCar", produces = MediaType.APPLICATION_JSON_VALUE)
        public String NewCarIdUser(@RequestBody String body, @PathVariable("idUser") int idUser) {
            logger.info("Saying Hello to the world");
            
            String url = "jdbc:mysql://localhost:3306/trackcar";
            String user = "root";
            String pass = "admin";
            // You don't need a username and password for integrated security
            
            try {
                
                Connection connection = DriverManager.getConnection(url, user, pass);
                // JsonObject jsonObject = new Gson().fromJson(body, JsonObject.class);
                ClassCar jsonObject = new Gson().fromJson(body, ClassCar.class);
                
                // Now you can access values from the JsonObject
                
                
                String MatriculaCarro = jsonObject.getMatriculaCarro();
                String Modelo = jsonObject.getModelo();
            String MesAno = jsonObject.getMesAno();
            int CV = jsonObject.getCV();
            String Consumo = jsonObject.getConsumo();
            Float KmFeitos = jsonObject.getKmFeitos();

            var st = connection.createStatement();
            ResultSet rs = st.executeQuery(
                    "SELECT idCar FROM trackcar.tblcar where MatriculaCarro like '" + MatriculaCarro + "'");

            
            if(!rs.next())//if (rs.getString("idCar") != null) 
            {
                
                st.executeUpdate("Insert into tblcar(MatriculaCarro, Modelo, MesAno, CV, Consumo, KmFeitos, LastLocalization) values('"+MatriculaCarro+"', '"+Modelo+"', '"+MesAno+"', '"+CV+"', (SELECT idConsumo FROM trackcar.tblconsumo where Consumo like '"+Consumo+"'), "+KmFeitos+", 'A')");
                st.executeUpdate("insert into tbldetalhescarro(idCar, IdClient) values ((select idCar From tblCar where MatriculaCarro like '"+MatriculaCarro+"' and Modelo like '"+Modelo+"'), "+idUser+")");

                connection.close();
                return "O carro foi adicionado com sucesso ao user!!";
            } else
                return "Carro já registado!!";
        }catch(

    SQLException e)
    {

        System.out.println(e.getMessage());
        return "Error, " + e.getMessage();
        // throw new IllegalStateException("Cannot connect the database!", e);
    }

}

}
