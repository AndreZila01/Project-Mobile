package pt.iade.andre.diogo.cartrackapp.Models;

import java.io.Serializable;

public class ClassInspecoes implements Serializable {
    private int idCentro;
    private String NomeCentro;
    private String Morada;
    private String Telefone;
    private String Email;
    private String ChefeDoCentro;
    private final String Marca;
    private final String Matricula;
    private final String ProximaInspecao;
    private final String UltimaInspecao;
    private final String Motor;
    private final String Freios;
    private final String SuspensaoDirecao;
    private final String SistemaEletrico;
    private final String TransmissaoEmbreagem;
    private final String EstruturaCarroceria;
    private final String DocumentacaoPlacas;
    private final String Emissoes;


    public ClassInspecoes() {
        this(0, "", "","", "", "", "","","","","","","","","","","","");
    }

    public ClassInspecoes(int id, String nome, String morada, String telefone, String email, String chefeDoCentro,String Marca,String Matricula, String ProximaInspecao, String UltimaInspecao,
                          String Motor, String Freios, String SuspensaoDirecao, String SistemaEletrico,
                          String TransmissaoEmbreagem, String EstruturaCarroceria, String DocumentacaoPlacas,
                          String Emissao) {
        this.idCentro = id;
        this.Telefone = telefone;
        this.NomeCentro = nome;
        this.Morada = morada;
        this.Email = email;
        this.ChefeDoCentro = chefeDoCentro;
        this.Marca = Marca;
        this.Matricula = Matricula;
        this.ProximaInspecao = ProximaInspecao;
        this.UltimaInspecao = UltimaInspecao;
        this.Motor = Motor;
        this.Freios = Freios;
        this.SuspensaoDirecao = SuspensaoDirecao;
        this.SistemaEletrico = SistemaEletrico;
        this.TransmissaoEmbreagem = TransmissaoEmbreagem;
        this.EstruturaCarroceria = EstruturaCarroceria;
        this.DocumentacaoPlacas = DocumentacaoPlacas;
        this.Emissoes = Emissao;
    }

    public void setIdCentro(int id) {
        this.idCentro = id;
    }

    public void setTelefone(String username) {
        this.Telefone = username;
    }

    public void setNomeCentro(String Nome) {
        this.NomeCentro = Nome;
    }

    public void setMorada(String Morada) {this.Morada = Morada;
    }

    public void setChefeDoCentro(String ChefeDoCentro) {
        this.ChefeDoCentro = ChefeDoCentro;
    }

    public int getIdCentro() {
        return this.idCentro;
    }

    public String getTelefone() {
        return this.Telefone;
    }

    public String getNomeCentro() {
        return this.NomeCentro;
    }

    public String getEmail() {
        return this.Email;
    }

    public String getChefeDoCentro() {
        return this.ChefeDoCentro;
    }
    public String getMorada(){return this.Morada;}

    public String getMarca() {
        return Marca;
    }
    public String getMatricula() {return Matricula;}

    public String getProximaInspecao() {
        return ProximaInspecao;
    }

    public String getUltimaInspecao() {
        return UltimaInspecao;
    }

    public String getMotor() {
        return Motor;
    }

    public String getFreios() {
        return Freios;
    }

    public String getSuspensaoDirecao() {
        return SuspensaoDirecao;
    }

    public String getSistemaEletrico() {
        return SistemaEletrico;
    }

    public String getTransmissaoEmbreagem() {
        return TransmissaoEmbreagem;
    }

    public String getEstruturaCarroceria() {
        return EstruturaCarroceria;
    }

    public String getDocumentacaoPlacas() {
        return DocumentacaoPlacas;
    }

    public String getEmissoes() {
        return Emissoes;
    }
}




