package pt.iade.andre.diogo.cartrackapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;


import pt.iade.andre.diogo.cartrackapp.Models.ClassMultas;

public class DetalhesMultaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_multa);

        Intent intent = getIntent();

        setupRecyclerView((ClassMultas) intent.getSerializableExtra("Multa"));
    }

    private void setupRecyclerView(ClassMultas item){
        ImageButton imgbtnBack = findViewById(R.id.BackActivity);
        imgbtnBack.setOnClickListener(v -> finish());

        ((TextView) findViewById(R.id.textViewMarca4)).setText(getString(R.string.lblMarca) + item.getMarca());
        ((TextView) findViewById(R.id.textVieMatricula)).setText(getString(R.string.lblMatricula) + item.getMatricula());
        ((TextView) findViewById(R.id.textViewTipo)).setText(getString(R.string.lblTipo) + item.getTipo());
        ((TextView) findViewById(R.id.textViewDescricao)).setText(getString(R.string.lblDescriacao) + item.getDescricao());
        ((TextView) findViewById(R.id.textViewValor)).setText(getString(R.string.lblValor) + item.getValor());
        ((TextView) findViewById(R.id.textViewData)).setText(getString(R.string.lblData) + item.getData());

    }
}