package pt.iade.hellocar.models;

import java.io.Serializable;

public class ClassCar implements Serializable {

    private int IdCar;
    private String MatriculaCarro;
    private String Modelo;
    private String MesAno;
    private int CV;
    private String Consumo;
    private Float KmFeitos;
    private String LastLocalization;

    public ClassCar() {
        this(0, "", "", "", 0, "", 0.00f, "");
    }

    public ClassCar(int idcar, String matriculacarro, String modelo, String mesano, int cv, String consumo, Float kmfeitos, String lastloc) {
        this.IdCar = idcar;
        this.MatriculaCarro = matriculacarro;
        this.Modelo = modelo;
        this.MesAno = mesano;
        this.CV = cv;
        this.Consumo = consumo;
        this.KmFeitos = kmfeitos;
        this.LastLocalization = lastloc;
    }

    public void setId(int idcar) {
        this.IdCar = idcar;
    }

    public void setMatriculaCarro(String matriculacarro) {
        this.MatriculaCarro = matriculacarro;
    }

    public void setModelo(String modelo) {
        this.Modelo = modelo;
    }

    public void setMesAno(String mesano) {
        this.MesAno = mesano;
    }

    public void setCV(int cv) {
        this.CV = cv;
    }

    public void setConsumo(String consumo) {
        this.Consumo = consumo;
    }

    public void setKmFeitos(Float kmfeitos) {
        this.KmFeitos = kmfeitos;
    }

    public void setLastLoc(String lastloc) {
        this.LastLocalization = lastloc;
    }

    public int getId() {
        return this.IdCar;
    }

    public String getMatriculaCarro() {
        return this.MatriculaCarro;
    }

    public String getModelo() {
        return this.Modelo;
    }

    public String getMesAno() {
        return this.MesAno;
    }

    public int getCV() {
        return this.CV;
    }

    public String getConsumo() {
        return this.Consumo;
    }

    public Float getKmFeitos() {
        return this.KmFeitos;
    }

    public String getLastLoc() {
        return this.LastLocalization;
    }
}
