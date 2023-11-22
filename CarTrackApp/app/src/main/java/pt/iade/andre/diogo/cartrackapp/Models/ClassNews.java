package pt.iade.andre.diogo.cartrackapp.Models;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class ClassNews implements Serializable {
    private int id;
    private Calendar date;
    private String Shorttitle;
    private String LongTitle;
    private String UrlImg;
    private String TextOfNews;
    private String Author;


    public ClassNews() {
        this(0, new GregorianCalendar(), "", "", "", "", "");
    }

    public ClassNews(int id, Calendar date, String Shortitle, String LongTitle, String UrlImg, String TextOfNews, String Author) {
        this.id = id;
        this.date = date;
        this.Shorttitle = Shortitle;
        this.LongTitle = LongTitle;
        this.UrlImg = UrlImg;
        this.Author = Author;
        this.TextOfNews = TextOfNews;
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
    public static ClassNews GetById(int id) {

        return new ClassNews(id, new GregorianCalendar(), "", "", "", "",
                "Some description that we also get from the web server");
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


  /*  public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
*/
    public String getTitlesh() {
        return Shorttitle;
    }

    public String getTitleLo() {
        return LongTitle;
    }

    public void setTitlesh(String title) {
        this.Shorttitle = title;
    }

    public void setTitleLon(String title) {
        this.LongTitle = title;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }


    public String getUrlImg() {
        return UrlImg;
    }

    public void setUrlImg(String urlImg) {
        this.UrlImg = urlImg;
    }

    public String getTextOfNews() {
        return TextOfNews;
    }

    public void setTextOfNews(String TextOfNews) {
        this.TextOfNews = TextOfNews;
    }


    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String Author) {
        this.Author = Author;
    }
}