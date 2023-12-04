package pt.iade.andre.diogo.cartrackapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import pt.iade.andre.diogo.cartrackapp.Models.ClassCar;

public class DetalhesCarroActivity extends AppCompatActivity {
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_detalhes_carro);

            Intent intent = getIntent();
            int s = intent.getIntExtra("position", 1);
            ClassCar item = (ClassCar) intent.getSerializableExtra("Carro");

            ((TextView) findViewById(R.id.textViewMarca)).setText(item.getModelo());
            ((TextView) findViewById(R.id.textViewMatricula)).setText(item.getMatricula());
            ((TextView) findViewById(R.id.textViewCilindrada)).setText(item.getCC());
            ((TextView) findViewById(R.id.textViewConsumo)).setText(item.getConsumo());
            ((TextView) findViewById(R.id.textViewModelo)).setText(item.getMesAno());
            ((TextView) findViewById(R.id.textViewPotencia)).setText(item.getKmfeitos());
            ((TextView) findViewById(R.id.textViewSistema_de_tracao)).setText(item.getUltimaLocalizacao());
            Picasso.get().load(item.getUrlImg()).into(((ImageView) findViewById(R.id.pctImageCar)));

        }
    }
