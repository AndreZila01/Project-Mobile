package pt.iade.hellocar.models;

public class ClassNewInspection {
    private String CentroDeInspecoes;
    private String DataHoraCentro;
    private String Carro;

    
    public ClassNewInspection() {
    }

    
    public ClassNewInspection(String centroDeInspecoes, String dataHoraCentro, String carro) {
        this.CentroDeInspecoes = centroDeInspecoes;
        this.DataHoraCentro = dataHoraCentro;
        this.Carro = carro;
    }

    
    public String getCentroDeInspecoes() {
        return this.CentroDeInspecoes;
    }

    public void setCentroDeInspecoes(String centroDeInspecoes) {
        this.CentroDeInspecoes = centroDeInspecoes;
    }

    
    public String getDataHoraCentro() {
        return this.DataHoraCentro;
    }

    public void setDataHoraCentro(String dataHoraCentro) {
        this.DataHoraCentro = dataHoraCentro;
    }

    
    public String getCarro() {
        return this.Carro;
    }

    public void setCarro(String carro) {
        this.Carro = carro;
    }
}
