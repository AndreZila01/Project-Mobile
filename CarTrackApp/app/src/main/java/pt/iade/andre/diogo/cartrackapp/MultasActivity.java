package pt.iade.andre.diogo.cartrackapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import java.util.ArrayList;

import pt.iade.andre.diogo.cartrackapp.Models.ClassMultas;
import pt.iade.andre.diogo.cartrackapp.adapters.MultaRowAdapters;

public class MultasActivity extends AppCompatActivity {
    protected RecyclerView itemsListViewCar;
    protected ArrayList<ClassMultas> itemsListMultas;
    protected MultaRowAdapters carItemRowAdpdaterCar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multas);

        ImageButton imgbtnBack = findViewById(R.id.BackActivity);
        imgbtnBack.setOnClickListener(v -> finish());

        itemsListMultas = new ArrayList<>();
        itemsListMultas.add(new ClassMultas("Mercedes", "RM-19-AD", "Leve", "Estacionamento Proibido",
                "$50", "01/01/2023"));
        itemsListMultas.add(new ClassMultas("Opel", "85-DA-QE", "Muito Grave", "Dirigir Embriagado",
                "$500", "10/03/2023"));

        setupRecyclerView();
    }

    private void setupRecyclerView() {
        carItemRowAdpdaterCar = new MultaRowAdapters(this, itemsListMultas);
        carItemRowAdpdaterCar.setOnClickListener((view, position) -> {
            Intent intent = new Intent(MultasActivity.this, DetalhesMultaActivity.class);
            intent.putExtra("idNew", position);
            intent.putExtra("Multa", itemsListMultas.get(position));
            startActivityForResult(intent, 1);
        });

        itemsListViewCar = findViewById(R.id.multas_listview);
        itemsListViewCar.setLayoutManager(new LinearLayoutManager(this));
        itemsListViewCar.setAdapter(carItemRowAdpdaterCar);
    }
}
