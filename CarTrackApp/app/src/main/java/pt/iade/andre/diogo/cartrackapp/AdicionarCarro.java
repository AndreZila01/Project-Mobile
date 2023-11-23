package pt.iade.andre.diogo.cartrackapp;

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
