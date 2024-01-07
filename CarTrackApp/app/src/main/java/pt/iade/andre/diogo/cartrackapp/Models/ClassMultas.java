package pt.iade.andre.diogo.cartrackapp.Models;

import java.io.Serializable;
import java.util.ArrayList;

public class ClassMultas implements Serializable {
    private final String Marca;
    private final String Matricula;
    private final String Tipo;
    private final String Descricao;
    private final String Valor;
    private final String Data;

    public ClassMultas(String Marca,String Matricula, String Tipo, String Descricao,
                       String Valor, String Data) {
        this.Marca = Marca;
        this.Matricula = Matricula;
        this.Tipo = Tipo;
        this.Descricao = Descricao;
        this.Valor = Valor;
        this.Data = Data;
    }

    public static ArrayList<ClassMultas> List() {
        ArrayList<ClassMultas> multas = new ArrayList<ClassMultas>();

        multas.add(new ClassMultas("Mercedes", "03-AA-03","Leve", "Estacionamento Proibido",
                "$50", "01/01/2023"));
        multas.add(new ClassMultas("Ford", "03-AA-03","Grave", "Excesso de Velocidade",
                "$150", "05/02/2023"));
        multas.add(new ClassMultas("Opel", "03-AA-03","Muito Grave", "Dirigir Embriagado",
                "$500", "10/03/2023"));

        return multas;
    }

    public static ClassMultas GetByMarca(String Marca) {
        return new ClassMultas(Marca, "03-AA-03","Grave", "Estacionamento Proibido",
                "$100", "20/04/2023");
    }

    public String getMarca() {
        return Marca;
    }
    public String getMatricula() {
        return Matricula;
    }

    public String getTipo() {
        return Tipo;
    }

    public String getDescricao() {
        return Descricao;
    }

    public String getValor() {
        return Valor;
    }

    public String getData() {
        return Data;
    }
}
