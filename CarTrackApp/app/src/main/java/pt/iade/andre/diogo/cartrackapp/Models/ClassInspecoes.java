package pt.iade.andre.diogo.cartrackapp.Models;

public class ClassInspecoes {
    private int idCentro;
    private String NomeCentro;
    private String Morada;
    private String Telefone;
    private String Email;
    private String ChefeDoCentro;

    public ClassInspecoes() {
        this(0, "", "", "", "", "");
    }

    public ClassInspecoes(int id, String nome, String morada, String telefone, String email, String chefeDoCentro) {
        this.idCentro = id;
        this.Telefone = telefone;
        this.NomeCentro = nome;
        this.Morada = morada;
        this.Email = email;
        this.ChefeDoCentro = chefeDoCentro;
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

    public void setMorada(String Morada) {
        this.Morada = Morada;
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

}

