package pt.iade.andre.diogo.cartrackapp.Models;

import androidx.media3.common.Format;

import java.io.Serializable;

public class ClassCar implements Serializable {
    private int id;
    private String Matricula;
    private String Marca;
    private String Modelo;
    private String MesAno;
    private String UrlImg;
    private String CC;
    private String Consumo;
    private String Kmfeitos;
    private String UltimaLocalizacao;


    public ClassCar() {
        this(0, "", "", "", "", "", "", "", "");
    }

    public ClassCar(int id, String Matricula, String Modelo, String MesAno, String UrlImg, String Consumo, String CC, String Kmfeitos, String UltimaLocalizacao) {
        this.id = id;
        this.Matricula = Matricula;
        this.Modelo = Modelo;
        this.MesAno = MesAno;
        this.UrlImg = UrlImg;
        this.CC = CC;
        this.Consumo = Consumo;
        this.Kmfeitos = Kmfeitos;
        this.UltimaLocalizacao = UltimaLocalizacao;
    }

    /**
     * Gets a list of items from the web server.
     *
     * @return List of items fetched from the web server.
     */

    /**
     * Gets the object from the web server by its ID in the database.
     *
     * @param id ID of the item in the database.
     *
     * @return Object with data from our web server.
     */
    public static ClassCar GetById(int id) {

        return new ClassCar(id, "", "", "", "", "",
                "Some description that we also get from the web server", "", "");
    }

    /**
     * Saves the object information to the database.
     */
    /*public void save() {
        if (id == 0) {
            // This is a brand new object and must be a INSERT in the database.

            // Simulate doing an insert and getting an ID back from the web server.
            id = new Random().nextInt(1000) + 1;
        } else {
            // This is an update to an existing object and must use UPDATE in the database.
        }
    }*/

    public int getId() {
        return id;
    }

    public String getMatricula() {
        return Matricula;
    }
    public String getModelo() {
        return Modelo;
    }

  /*  public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
*/
    public String getMesAno() {
        return MesAno;
    }

    public String getConsumo() {
        return Consumo;
    }
    public String getKmfeitos() {
        return Kmfeitos;
    }

    public String getUrlImg() {
        return UrlImg;
    }

    public String getUltimaLocalizacao() {
        return UltimaLocalizacao;
    }

    public void setMatricula(String Matricula) {
        this.Matricula = Matricula;
    }

    public void setTitleLon(String Modelo) {
        this.Modelo = Modelo;
    }

    public int getid() {
        return id;
        //return (new SimpleDateFormat("dd-MM-YYYY").setCalendar(date));//("dd-MM-YYYY");
    }

    public void setid(int id) {
        this.id = id;
    }


    public void setMesAno(String MesAno) {
        this.MesAno = MesAno;
    }

    public void setUrlImg(String urlImg) {
        this.UrlImg = urlImg;
    }

    public String getCC(){
        return CC;
    }
    public void setAtualizacao(String UrlImg, String Consumo, String Kmfeitos, String Ultima){
        setUrlImg(UrlImg);
        setConsumo(Consumo);
        setKmfeitos(Kmfeitos);
        setUltimaLocalizacao(Ultima);
    }

    public String getResumo(){
        String CC = getCC().toString();
        String Consumo = getConsumo().toString();
        String Km = getKmfeitos().toString();
        String Loc = getUltimaLocalizacao().toString();

        return String.format("Tem %s cavalos, combustivel do tipo %s, já percorreu %s kms e está de momento em %s", CC, Consumo, Km, Loc);
    }

    public void setCC(String CC) {
        this.CC = CC;
    }

    public void setConsumo(String Consumo) {
        this.Consumo = Consumo;
    }

    public void setKmfeitos(String Kmfeitos) {
        this.Kmfeitos = Kmfeitos;
    }

    public void setUltimaLocalizacao(String UltimaLocalizacao) {
        this.UltimaLocalizacao = UltimaLocalizacao;
    }
}