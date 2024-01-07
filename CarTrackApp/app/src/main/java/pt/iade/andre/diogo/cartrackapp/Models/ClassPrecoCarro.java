package pt.iade.andre.diogo.cartrackapp.Models;

import java.util.ArrayList;

public class ClassPrecoCarro {
    private final String Preco;
    private final String Marca;
    private int imageResource;

    public ClassPrecoCarro(String preco,String marca,int imageResource) {
        this.Preco = preco;
        this.imageResource = imageResource;
        this.Marca = marca;
    }

    public String getPreco() {
        return Preco;
    }
    public String getMarca(){return Marca;}

    public int getImageResource() {
        return imageResource;
    }
}

