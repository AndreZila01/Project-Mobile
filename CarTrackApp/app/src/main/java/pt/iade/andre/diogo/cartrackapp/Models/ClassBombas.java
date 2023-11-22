package pt.iade.andre.diogo.cartrackapp.Models;

import java.util.ArrayList;
import java.util.Calendar;

public class ClassBombas {
    private int id;
    private String Lojas;
    private String JsonTipoPreco;

    public int getId() {
        return id;
    }
    public String getLojas() {
        return Lojas;
    }

    public String getJsonTipoPreco() {
        return JsonTipoPreco;
    }

    public void setLojas(String Lojas) {
        this.Lojas = Lojas;
    }

    public void setJsonTipoPreco(String JsonTipoPreco) {
        this.JsonTipoPreco = JsonTipoPreco;
    }

}

