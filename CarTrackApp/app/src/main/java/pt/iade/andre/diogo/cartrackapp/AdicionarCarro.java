package pt.iade.andre.diogo.cartrackapp;

import java.util.ArrayList;

public class AdicionarCarro {
        private final String marca;
        private final String modelo;
        private final String matricula;
        private final String Cilindrada;
        private final String sistema_de_tracao;
        private final String potencia;
        private final String peso;
        private final String consumo;



        public AdicionarCarro(String marca, String modelo,String matricula,String Cilindrada,String sistema_de_tracao,String potencia,String peso,String consumo) {
            this.marca = marca;
            this.modelo = modelo;
            this.matricula = matricula;
            this.Cilindrada= Cilindrada;
            this.sistema_de_tracao= sistema_de_tracao;
            this.potencia= potencia;
            this.peso= peso;
            this.consumo= consumo;
        }
        public static ArrayList<AdicionarCarro> List() {
            ArrayList<AdicionarCarro> carros = new ArrayList<AdicionarCarro>();

            carros.add(new AdicionarCarro("Mercedes", "","AA-01-AA","1497cm^3","Frente","150CV","1443KG","6.7km/L"));
            carros.add(new AdicionarCarro("Ford", "Focus","AA-02-AA","999cm^3","Traz","125CV","1379KG","6.7km/L"));
            carros.add(new AdicionarCarro("Opel","Zafira","AA-03-AA","1200cm^3","Frente","140CV","1400KG","6.7km/L"));

            return carros;

        }
        public static AdicionarCarro GetByMarca(String Marca) {

            return new AdicionarCarro(Marca,"Focus","AA-02-AA","999cm^3","Traz","125CV","1379KG","6.7km/L");

        }



        public String getMarca() {
            return marca;
        }

        public String getModelo() {
            return modelo;
        }
        public String getMatricula() {
            return matricula;
        }
        public String getCilindrada() {
            return Cilindrada;
        }
        public String getsistema_de_tracao() {
            return sistema_de_tracao;
        }

        public String getPotencia() {
            return potencia;
        }

        public String getPeso() {
            return peso;
            }

        public String getConsumo() {
            return consumo;
        }
}
