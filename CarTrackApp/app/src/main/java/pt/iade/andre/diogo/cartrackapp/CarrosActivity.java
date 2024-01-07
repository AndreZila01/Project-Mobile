package pt.iade.andre.diogo.cartrackapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import pt.iade.andre.diogo.cartrackapp.Models.ClassBombas;
import pt.iade.andre.diogo.cartrackapp.Models.ClassCar;
import pt.iade.andre.diogo.cartrackapp.Models.ClassNews;
import pt.iade.andre.diogo.cartrackapp.adapters.CarItemRowAdapter;


public class CarrosActivity extends AppCompatActivity {
    protected RecyclerView itemsListViewCar;

    protected ArrayList<ClassCar> itemsListCar;

    protected CarItemRowAdapter carItemRowAdpdaterCar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car);

        ImageButton imgbtnBack = findViewById(R.id.BackActivity);
        imgbtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

        background_doWork(); // função para incrementar todos os valores aqui!!
    }


    protected ArrayList<ClassCar> lstCar;
    ArrayList<ClassBombas> myClassList;
    private void background_doWork() {

        //RelativeLayout et = (RelativeLayout)findViewById(R.id.pnlNoticias);

        itemsListCar = new ArrayList<ClassCar>();

        String path = "http://10.0.2.2:8080/car/" + MainActivity.IdUser+"/details";

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

                    String value = response.body().string().toString();

                    if (!value.equals("vazio!!")) {

                        lstCar = new ArrayList<ClassCar>();
                        JsonArray arr = new Gson().fromJson(value, JsonArray.class);
                        for (JsonElement elem : arr) {
                            lstCar.add(new ClassCar(elem.getAsJsonObject()));
                        }


                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                carItemRowAdpdaterCar = new CarItemRowAdapter(CarrosActivity.this, lstCar);
                                carItemRowAdpdaterCar.setOnClickListener(new CarItemRowAdapter.ItemClickListener() {
                                    @Override
                                    public void onItemClick(View view, int position) {
                                        Intent intent = new Intent(CarrosActivity.this, DetalhesCarroActivity.class);
                                        intent.putExtra("idNew", position);
                                        intent.putExtra("Carro", lstCar.get(position));

                                        startActivityForResult(intent, 1);
                                        return;
                                    }
                                });

                                itemsListViewCar = (RecyclerView)

                                        findViewById(R.id.pnlCar);
                                itemsListViewCar.setLayoutManager(new

                                        LinearLayoutManager(CarrosActivity.this));
                                itemsListViewCar.setAdapter(carItemRowAdpdaterCar);
                            }
                        });
                    }
                }
            });
        } catch (Exception ex) {
            Log.d("s", "" + ex);
        } finally {


        }


    }
}





