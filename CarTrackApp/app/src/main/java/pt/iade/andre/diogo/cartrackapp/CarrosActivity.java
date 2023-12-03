package pt.iade.andre.diogo.cartrackapp;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class CarrosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Toast.makeText(getApplicationContext(), "Clicado o 2", Toast.LENGTH_LONG).show();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_carros);


        List<String> listaDeCarros = obterListaDeCarros();

        ListView listViewCarros = findViewById(R.id.activity_lista_carros);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                R.layout.row_lista_carros, R.id.textviewMarca1, listaDeCarros);

        listViewCarros.setAdapter(adapter);
    }

        private List<String> obterListaDeCarros() {
        List<String> carros = new ArrayList<>();
        carros.add("Carro 1");
        carros.add("Carro 2");
        carros.add("Carro 3");

        return carros;
        }
    }



