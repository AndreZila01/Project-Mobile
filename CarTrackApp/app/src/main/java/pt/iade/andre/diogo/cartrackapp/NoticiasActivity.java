package pt.iade.andre.diogo.cartrackapp;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
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

        ImageButton imgbtnBack = findViewById(R.id.BackActivity);
        imgbtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

        itemsList = new ArrayList<ClassNews>();
        itemsList.add(new ClassNews(1, new GregorianCalendar(), "T123", "Titulo", "https://images6.fanpop.com/image/photos/36900000/Blathers-animal-crossing-new-leaf-36917846-1836-1945.png", "dssdsd", "Eu"));
        itemsList.add(new ClassNews(2, new GregorianCalendar(), "T12", "Tiulo", "https://images6.fanpop.com/image/photos/36900000/Blathers-animal-crossing-new-leaf-36917846-1836-1945.png", "312", "Eu"));

        background_doWork(); // função para incrementar todos os valores aqui!!
        }

    private void background_doWork(){

        //RelativeLayout et = (RelativeLayout)findViewById(R.id.pnlNoticias);

        ntcItemRowAdpdater = new NoticiasItemRowAdapter(this, itemsList);

        itemsListView = (RecyclerView) findViewById(R.id.pnlNoticias);
        itemsListView.setLayoutManager(new LinearLayoutManager(this));
        itemsListView.setAdapter(ntcItemRowAdpdater);
    }
}
