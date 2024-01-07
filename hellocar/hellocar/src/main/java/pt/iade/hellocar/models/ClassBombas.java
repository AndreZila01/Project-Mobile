package pt.iade.hellocar.models;

import java.io.Serializable;

public class ClassBombas implements Serializable {

    private int Id;
    private String Bomba;
    private String Url;
    private String LonxLac;
        private float GasoleoS;
        private float GasoleoE;
        private float GasolinaS95;
        private float GasolinaE95;
        private float Gasolina98;
        private float GasolinaE98;
        private float GPLAuto;

    public ClassBombas() {
        this(0, "", "", "", 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
    }

    public ClassBombas(int id, String bombas, String url, String lonxlac, Float gasoleos, Float gasoleoe,
            Float gasolinas95, Float gasolinae95, Float gasolina98, Float gasolinaE98, Float gplauto) {
        this.Bomba = bombas;
        this.Id = id;
        this.Url = url;
        this.LonxLac = lonxlac;
        this.GasoleoS = gasoleos;
        this.GasoleoE = gasoleoe;
        this.GasolinaS95 = gasolinas95;
        this.GasolinaE95 = gasolinae95;
        this.Gasolina98 = gasolina98;
        this.GasolinaE98 = gasolinaE98;
        this.GPLAuto = gplauto;
    }

    public void setId(int id) {
        this.Id = id;
    }

    public void setBomba(String bomba) {
        this.Bomba = bomba;
    }

    public void setUrl(String url) {
        this.Url = url;
    }

    public void setLonc(String lonxlac) {
        this.LonxLac = lonxlac;
    }

    public void seteoS(Float gasoleos) {
        this.GasoleoS = gasoleos;
    }

    public void seteoE(Float gasoleoe) {
        this.GasoleoE = gasoleoe;
    }

    public void setiNAs95(Float gasolinas95) {
        this.GasolinaS95 = gasolinas95;
    }

    public void setiNAe95(Float gasolinae95) {
        this.GasolinaE95 = gasolinae95;
    }

    public void setiNA98(Float gasolina98) {
        this.Gasolina98 = gasolina98;
    }

    public void setinaE98(Float gasolinaE98) {
        this.GasolinaE98 = gasolinaE98;
    }

    public void setGPL(Float gplauto) {
        this.GPLAuto = gplauto;
    }

    public int getId() {
        return this.Id;
    }

    public String getBomba() {
        return this.Bomba;
    }

    public String getUrl() {
        return this.Url;
    }

    public String getLonc() {
        return this.LonxLac;
    }

    public float geteoS() {
        return this.GasoleoS;
    }

    public float geteoE() {
        return this.GasoleoE;
    }

    public float getiNAs95() {
        return this.GasolinaS95;
    }

    public float getiNAe95() {
        return this.GasolinaE95;
    }

    public float getiNA98() {
        return this.Gasolina98;
    }

    public float getinaE98() {
        return this.GasolinaE98;
    }

    public float getGPL() {
        return this.GPLAuto;
    }
}
