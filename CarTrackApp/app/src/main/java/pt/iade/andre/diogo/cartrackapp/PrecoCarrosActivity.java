package pt.iade.andre.diogo.cartrackapp;


import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import pt.iade.andre.diogo.cartrackapp.Models.ClassPrecoCarro;
import pt.iade.andre.diogo.cartrackapp.adapters.PrecoCarroRowAdapters;


public class PrecoCarrosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preco_carros);

        ImageButton imgbtnBack = findViewById(R.id.BackActivity);
        imgbtnBack.setOnClickListener(v -> finish());

        RecyclerView recyclerView = findViewById(R.id.preco_listview);

        ArrayList<ClassPrecoCarro> carros = new ArrayList<>();
        carros.add(new ClassPrecoCarro("$25.000", "Ford",2));
        carros.add(new ClassPrecoCarro("$30.000", "Mercedes",2));


        PrecoCarroRowAdapters adapter = new PrecoCarroRowAdapters(carros, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}
