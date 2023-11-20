package pt.iade.andre.diogo.cartrackapp;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import pt.iade.andre.diogo.cartrackapp.Models.ClassNews;
import pt.iade.andre.diogo.cartrackapp.adapters.NoticiasItemRowAdapter;

public class NoticiasActivity extends AppCompatActivity {
    protected RecyclerView itemsListView;

    protected ArrayList<ClassNews> itemsList;

    protected NoticiasItemRowAdapter ntcItemRowAdpdater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        background_doWork(); // função para incrementar todos os valores aqui!!
        ClassNews clnews;
        clnews = new ClassNews(1, new GregorianCalendar(), "T", "Titulo", "https://images6.fanpop.com/image/photos/36900000/Blathers-animal-crossing-new-leaf-36917846-1836-1945.png", "dssdsd", "Eu");
        itemsList.add(clnews);
    }

    private void background_doWork(){

        //RelativeLayout et = (RelativeLayout)findViewById(R.id.pnlNoticias);

        ntcItemRowAdpdater = new NoticiasActivity(this, itemsList);

        itemsListView = (RecyclerView) findViewById(R.id.pnlNoticias);
        itemsListView.setLayoutManager(new LinearLayoutManager(this));
        itemsListView.setAdapter(ntcItemRowAdpdater);
    }
}
