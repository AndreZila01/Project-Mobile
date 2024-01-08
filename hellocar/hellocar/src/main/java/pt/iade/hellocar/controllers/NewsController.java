package pt.iade.hellocar.controllers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import pt.iade.hellocar.models.ClassNewsWeb;

@RestController
@RequestMapping(path = "/News")
public class NewsController {
    private Logger logger = LoggerFactory.getLogger(NewsController.class);

    @GetMapping(path = "/Car", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getNews() {
        logger.info("news");

        HttpURLConnection connection = null;

        try {
            //https://www.newsapi.ai/dashboard?tab=home
            URL urls = new URL(
                    "https://newsapi.org/v2/everything?q=car&from=2024-01-01&sortBy=publishedAt&apiKey=58708b776c204b3f87a7b1aa484cdc9d");
            // Create connection
            // URL ursl = new URL(urls);
            connection = (HttpURLConnection) urls.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");

            connection.setRequestProperty("Content-Language", "en-US");
            connection.setRequestProperty("content-type", "application/json;  charset=UTF-8");

            connection.setUseCaches(false);
            connection.setDoOutput(true);

            // Get Response
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            StringBuilder response = new StringBuilder(); // or StringBuffer if Java version 5+
            String line;
            while ((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();

            JSONObject jsonInput = new JSONObject(new JSONTokener(new StringReader(response.toString())));
            JSONArray array = jsonInput.getJSONArray("articles");

            ArrayList<ClassNewsWeb> lstNews = new ArrayList<ClassNewsWeb>();
            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);

                ClassNewsWeb s = new ClassNewsWeb();

                s.setAuthor(obj.get("author").toString());
                s.setId(i);
                if (obj.get("title").toString().length() > 44)
                    s.setTitlesh(obj.get("title").toString().substring(0, 45));
                else
                    s.setTitlesh(obj.get("title").toString());

                s.setTitleLon(obj.get("title").toString());
                s.setUrlImg(obj.get("urlToImage").toString());
                s.setTextOfNews(obj.get("content").toString());

                s.setDate(obj.get("publishedAt").toString());// all done

                lstNews.add(s);
                // implement here your logic on obj
            }

            
            if (lstNews.size() > 15)
                for (int count = lstNews.size()-1; count > 15; count--)
                    lstNews.remove(count);

            String json = new Gson().toJson(lstNews);
            return json;
        } catch (Exception ex) {
            return ex.getMessage();
        }
    }
}