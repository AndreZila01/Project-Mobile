package pt.iade.andre.diogo.cartrackapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import java.util.ArrayList;

import pt.iade.andre.diogo.cartrackapp.Models.ClassInspecoes;
import pt.iade.andre.diogo.cartrackapp.adapters.InspecoesRowAdapters;

public class InspecoesActivity extends AppCompatActivity {
    protected RecyclerView itemsListViewCar;
    protected ArrayList<ClassInspecoes> itemsListCar;
    protected InspecoesRowAdapters RowAdapterCar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inspecoes);

        ImageButton imgbtnBack = findViewById(R.id.BackActivity);
        imgbtnBack.setOnClickListener(v -> finish());

        itemsListCar = new ArrayList<>();
        itemsListCar.add(new ClassInspecoes(1,"Sofia Mendes","Rua das Flores 123","+351 987 654 321","leonardo.costa@example.com","Gestor: Leonardo Costa","Ford", "AA-02-AA", "04-05-2024", "Aprovado", "Ok", "Ok", "Ok", "Ok", "Ok", "Ok", "Ok","ok"));
        itemsListCar.add(new ClassInspecoes(2, "Sofia Mendes","Rua das Flores 123","+351 987 654 321","leonardo.costa@example.com","Gestor: Leonardo Costa", "Peugeot", "85-DA-QE", "04-05-2024", "Aprovado", "Ok", "Ok", "Ok", "Ok", "Ok", "Ok", "Ok","ok"));

        setupRecyclerView();
    }

    private void setupRecyclerView() {

        RowAdapterCar = new InspecoesRowAdapters(this, itemsListCar);
        RowAdapterCar.setOnClickListener((view, position) -> {
            Intent intent = new Intent(InspecoesActivity.this, DetalhesInspecaoActivity.class);
            intent.putExtra("idNew", position);
            intent.putExtra("Carro", itemsListCar.get(position));
            startActivityForResult(intent, 1);
        });

        itemsListViewCar = findViewById(R.id.carros2_listview);
        itemsListViewCar.setLayoutManager(new LinearLayoutManager(this));
        itemsListViewCar.setAdapter(RowAdapterCar);
    }
}
