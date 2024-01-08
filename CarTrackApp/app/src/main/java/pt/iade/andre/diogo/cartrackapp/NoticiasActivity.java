package pt.iade.andre.diogo.cartrackapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
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

        background_doWork(); // função para incrementar todos os valores aqui!!
        //itemsList = new ArrayList<ClassNews>();
        //itemsList.add(new ClassNews(1, new GregorianCalendar(), "Concorrência da Tesla", "Quem são os chineses da BYD que vendem quase tantos carros elétricos como a Tesla e que já estão em Portugal?", "https://static.vecteezy.com/system/resources/previews/000/426/079/original/vector-car-icon.jpg", "De janeiro a outubro de 2023 as vendas acumuladas da BYD Auto ultrapassaram 2,3 milhões de unidades a nível global. Em Portugal, em poucos meses, já foram vendidos 400 carros\n", "Eu"));
        //itemsList.add(new ClassNews(2, new GregorianCalendar(), "Dispara número de carros a circularem sem seguro", "Dispara número de carros a circularem sem seguro", "https://static.vecteezy.com/system/resources/previews/000/426/079/original/vector-car-icon.jpg", "A portaria que eliminou a obrigação de ter o dístico do seguro automóvel só entrou em vigor a 11 de junho, mas não foi só o pequeno papel colado no vidro do carro que ‘desapareceu’ de muitos automóveis. Dados da Autoridade Nacional de Segurança Rodoviária (ANSR) revelados esta quinta-feira demonstram que nos primeiros sete meses deste ano o número de viaturas a circular sem seguro disparou quase 20% face a 2022.", "Eu"));

    }

    private void background_doWork() {

        //RelativeLayout et = (RelativeLayout)findViewById(R.id.pnlNoticias);
                        itemsList = new ArrayList<ClassNews>();

        String path = "http://10.0.2.2:8080/News/Car";

        try {
            Request rq = new Request.Builder().url(path).build();
            OkHttpClient client = new OkHttpClient();

            client.newCall(rq).enqueue(new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                    Log.d("Error", "" + e);
                }

                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                    //Log.d("Message", "" + response.body().string());

                    try {
                        JsonArray arr = new Gson().fromJson(response.body().string(), JsonArray.class);
                        for (JsonElement elem : arr) {
                            itemsList.add(new ClassNews(elem.getAsJsonObject()));
                        }

                    } catch (Exception ex) {
                        Log.d("", "" + ex.getMessage());
                    }
                    finally {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                collect(itemsList);
                            }
                        });
                    }
                }
            });
        } catch (Exception ex) {
            Log.d("s", "" + ex);
        }
    }

    private void collect(ArrayList<ClassNews> items) {

        ntcItemRowAdpdater = new NoticiasItemRowAdapter(NoticiasActivity.this, items);

        itemsListView = (RecyclerView) findViewById(R.id.pnlNoticias);
        itemsListView.setLayoutManager(new LinearLayoutManager(NoticiasActivity.this));
        itemsListView.setAdapter(ntcItemRowAdpdater);
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

    }
}
