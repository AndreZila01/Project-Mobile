package pt.iade.andre.diogo.cartrackapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DetalhesCarroActivity extends AppCompatActivity {
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_detalhes_carro);


            Intent intent = getIntent();
            String Marca = intent.getStringExtra("Marca");
            String Modelo = intent.getStringExtra("Modelo");
            String Matricula = intent.getStringExtra("Matricula");
            String Cilindrda = intent.getStringExtra("modelo");
            String Sistema_de_tracao = intent.getStringExtra("Sistema de Tração");
            String Potencia = intent.getStringExtra("Potencia");
            String Peso = intent.getStringExtra("Peso");
            String Consumo = intent.getStringExtra("Consumo");


            @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView textViewMarca = findViewById(R.id.textViewMarca);
            @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView textViewModelo = findViewById(R.id.textViewModelo);
            @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView textViewMatricula = findViewById(R.id.textViewMatricula);
            @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView textViewCilindrada = findViewById(R.id.textViewCilindrada);
            @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView textViewSistema_de_tracao = findViewById(R.id.textViewSistema_de_tracao);
            @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView textViewPotencia = findViewById(R.id.textViewPotencia);
            @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView textViewPeso = findViewById(R.id.textViewPeso);
            @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView textViewConsumo = findViewById(R.id.textViewConsumo);

            textViewMarca.setText("Marca: " + Marca);
            textViewModelo.setText("Modelo: " + Modelo);
            textViewModelo.setText("Matricula: " + Matricula);
            textViewModelo.setText("Cilindrada: " + Cilindrda);
            textViewModelo.setText("Sistema de traçao: " + Sistema_de_tracao);
            textViewModelo.setText("Potencia: " + Potencia);
            textViewModelo.setText("Peso: " + Peso);
            textViewModelo.setText("Consumo: " + Consumo);

        }
    }
