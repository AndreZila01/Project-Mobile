package pt.iade.andre.diogo.cartrackapp.Models;

import androidx.media3.common.Format;

import com.google.gson.JsonObject;

import java.io.Serializable;

public class ClassCar implements Serializable {
    private int IdCar;
    private String MatriculaCarro;
    private String Modelo;
    private String MesAno;
    private int CV;
    private String Consumo;
    private String Marca;
    private String UrlImg;
    private Float Kmfeitos;
    private String LastLocalization;

    public ClassCar(JsonObject json) {
        this.IdCar = json.get("IdCar").getAsInt();
        this.CV = Integer.parseInt(json.get("CV").getAsString());
        this.MatriculaCarro = json.get("MatriculaCarro").getAsString();
        this.LastLocalization = json.get("LastLocalization").getAsString();
        this.Consumo= json.get("Consumo").getAsString();
        this.Kmfeitos = Float.parseFloat(json.get("KmFeitos").getAsString());
        this.Modelo = json.get("Modelo").getAsString();
        this.UrlImg = "https://img.freepik.com/premium-vector/car-icon-vehicle-icon-car-vector-icon_564974-1452.jpg";
    }


    public ClassCar() {
        this(0, "", "", "", "", 0, 0.0f, "");
    }

    public ClassCar(int id, String Matricula, String Modelo, String MesAno, String Consumo, int CV, Float Kmfeitos, String UltimaLocalizacao) {
        this.IdCar = id;
        this.MatriculaCarro = Matricula;
        this.Modelo = Modelo;
        this.MesAno = MesAno;
        this.CV = CV;
        this.Consumo = Consumo;
        this.Kmfeitos = Kmfeitos;
        this.LastLocalization = UltimaLocalizacao;
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

        return new ClassCar(id, "", "", "", "",
                0, 0.0f, "");
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
        return this.IdCar;
    }

    public String getMatricula() {
        return this.MatriculaCarro;
    }
    public String getModelo() {
        return this.Modelo;
    }

    /*  public boolean isDone() {
          return done;
      }

      public void setDone(boolean done) {
          this.done = done;
      }
  */
    public String getMesAno() {
        return this.MesAno;
    }

    public String getConsumo() {
        return Consumo;
    }
    public Float getKmfeitos() {
        return this.Kmfeitos;
    }

    public String getUrlImg() {
        return this.UrlImg;
    }

    public String getUltimaLocalizacao() {
        return this.LastLocalization;
    }

    public void setMatricula(String Matricula) {
        this.MatriculaCarro = Matricula;
    }

    public void setTitleLon(String Modelo) {
        this.Modelo = Modelo;
    }

    public int getid() {
        return this.IdCar;
        //return (new SimpleDateFormat("dd-MM-YYYY").setCalendar(date));//("dd-MM-YYYY");
    }

    public void setid(int id) {
        this.IdCar = id;
    }


    public void setMesAno(String MesAno) {
        this.MesAno = MesAno;
    }

    public void setUrlImg(String urlImg) {
        this.UrlImg = urlImg;
    }

    public int getCC(){
        return CV;
    }
    public void setAtualizacao(String UrlImg, String Consumo, Float Kmfeitos, String Ultima){
        setUrlImg(UrlImg);
        setConsumo(Consumo);
        setKmfeitos(Kmfeitos);
        setUltimaLocalizacao(Ultima);
    }

    public String getResumo(){
        int CC = getCC();
        String Consumo = getConsumo().toString();
        String Km = getKmfeitos().toString();
        String Loc = getUltimaLocalizacao().toString();

        return String.format("Tem %s cavalos, combustivel do tipo %s, já percorreu %s kms e está de momento em %s", CC, Consumo, Km, Loc);
    }

    public void setCC(int CC) {
        this.CV = CC;
    }

    public void setConsumo(String Consumo) {
        this.Consumo = Consumo;
    }

    public void setKmfeitos(Float Kmfeitos) {
        this.Kmfeitos = Kmfeitos;
    }

    public void setUltimaLocalizacao(String UltimaLocalizacao) {
        this.LastLocalization = UltimaLocalizacao;
    }
}