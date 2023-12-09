package pt.iade.andre.diogo.cartrackapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
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

        background_dowork((ClassCar) intent.getSerializableExtra("Carro"));
    }

    private void background_dowork(ClassCar item){
        ImageButton imgbtnBack = findViewById(R.id.BackActivity);
        imgbtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        ((TextView) findViewById(R.id.lblModelo)).setText(getString(R.string.lblModelo)+ "  " + item.getModelo());
        ((TextView) findViewById(R.id.lblMatricula)).setText(getString(R.string.lblMatri)+ "  " + item.getMatricula());
        ((TextView) findViewById(R.id.lblCilindrada)).setText(getString(R.string.lblCilindrada)+ "  " + item.getCC());
        ((TextView) findViewById(R.id.lblConsumo)).setText(getString(R.string.lblConsumo)+ "  " + item.getConsumo());
        ((TextView) findViewById(R.id.lblMesAno)).setText(getString(R.string.lblMesAno)+ "  " + item.getMesAno());
        ((TextView) findViewById(R.id.lblKm)).setText(getString(R.string.lblKm)+ "  " + item.getKmfeitos());
        ((TextView) findViewById(R.id.lblLastLocation)).setText(getString(R.string.lbllastLoc)+ "  " + item.getUltimaLocalizacao());
        Picasso.get().load(item.getUrlImg()).into(((ImageView) findViewById(R.id.pctImageCar)));

    }
}
