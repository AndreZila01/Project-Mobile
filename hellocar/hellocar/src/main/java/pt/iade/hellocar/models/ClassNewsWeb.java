package pt.iade.hellocar.models;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

public class ClassNewsWeb {
    private int id;
    private String date;
    private String Shorttitle;
    private String LongTitle;
    private String UrlImg;
    private String TextOfNews;
    private String Author;

    public ClassNewsWeb() {
        this(0, new GregorianCalendar(), "", "", "", "", "");
    }

    public ClassNewsWeb(int id, Calendar date, String Shortitle, String LongTitle, String UrlImg, String TextOfNews,
            String Author) {
        this.id = id;
        this.date = date.toString();
        this.Shorttitle = Shortitle;
        this.LongTitle = LongTitle;
        this.UrlImg = UrlImg;
        this.Author = Author;
        this.TextOfNews = TextOfNews;
    }

    /**
     * Saves the object information to the database.
     */
    /*
     * public void save() {
     * if (id == 0) {
     * // This is a brand new object and must be a INSERT in the database.
     * 
     * // Simulate doing an insert and getting an ID back from the web server.
     * id = new Random().nextInt(1000) + 1;
     * } else {
     * // This is an update to an existing object and must use UPDATE in the
     * database.
     * }
     * }
     */

    public int getId() {
        return id;
    }

    /*
     * public boolean isDone() {
     * return done;
     * }
     * 
     * public void setDone(boolean done) {
     * this.done = done;
     * }
     */
    public String getTitlesh() {
        return Shorttitle;
    }

        public void setId(int Id) {
        this.id = Id;
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

    public String getDate() {
        return (new SimpleDateFormat("dd/MM/yyyy").format(this.date)).toString();
        // return (new
        // SimpleDateFormat("dd-MM-YYYY").setCalendar(date));//("dd-MM-YYYY");
    }

    public void setDate(String data) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH);
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));

        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(sdf.parse(data));

            SimpleDateFormat sdfs = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.ENGLISH);
            var s = sdfs.format(calendar.getTime());
            this.date = s;

        } catch (Exception ex) {
            this.date = new GregorianCalendar().toString();//.getInstance().setTime(sdfs.format(calendar.getTime()));;
        }

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
