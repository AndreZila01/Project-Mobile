package pt.iade.andre.diogo.cartrackapp;

import android.content.Intent;
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
        itemsList.add(new ClassNews(1, new GregorianCalendar(), "Concorrência da Tesla", "Quem são os chineses da BYD que vendem quase tantos carros elétricos como a Tesla e que já estão em Portugal?", "https://static.vecteezy.com/system/resources/previews/000/426/079/original/vector-car-icon.jpg", "De janeiro a outubro de 2023 as vendas acumuladas da BYD Auto ultrapassaram 2,3 milhões de unidades a nível global. Em Portugal, em poucos meses, já foram vendidos 400 carros\n", "Eu"));
        itemsList.add(new ClassNews(2, new GregorianCalendar(), "Dispara número de carros a circularem sem seguro", "Dispara número de carros a circularem sem seguro", "https://static.vecteezy.com/system/resources/previews/000/426/079/original/vector-car-icon.jpg", "A portaria que eliminou a obrigação de ter o dístico do seguro automóvel só entrou em vigor a 11 de junho, mas não foi só o pequeno papel colado no vidro do carro que ‘desapareceu’ de muitos automóveis. Dados da Autoridade Nacional de Segurança Rodoviária (ANSR) revelados esta quinta-feira demonstram que nos primeiros sete meses deste ano o número de viaturas a circular sem seguro disparou quase 20% face a 2022.", "Eu"));

        background_doWork(); // função para incrementar todos os valores aqui!!
        }

    private void background_doWork(){

        //RelativeLayout et = (RelativeLayout)findViewById(R.id.pnlNoticias);

        ntcItemRowAdpdater = new NoticiasItemRowAdapter(this, itemsList);
        ntcItemRowAdpdater.setOnClickListener(new NoticiasItemRowAdapter.ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(NoticiasActivity.this, DetalhesNewsActivity.class);
                intent.putExtra("idNew", position);
                intent.putExtra("Noticia", itemsList.get(position));

                startActivityForResult(intent, 1);
                return;
            }
        });

        itemsListView = (RecyclerView) findViewById(R.id.pnlNoticias);
        itemsListView.setLayoutManager(new LinearLayoutManager(this));
        itemsListView.setAdapter(ntcItemRowAdpdater);
    }
}
