package pt.iade.andre.diogo.cartrackapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class carActivity extends AppCompatActivity {

    ImageButton first_car;
    ImageButton second_car;
    ImageButton third_car;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle bundle){
        Toast.makeText(getApplicationContext(), "Clicado o 2", Toast.LENGTH_LONG).show();
        super.onCreate(bundle);
        setContentView(R.layout.activity_car);
        Background_DoWork();

        first_car = findViewById(R.id.first_car);
        second_car = findViewById(R.id.second_car);
        third_car = findViewById(R.id.third_car);

        first_car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Clicado ", "Primeiro carro");
            }
        });
        second_car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Clicado ", "Segundo carro");
            }
        });
        third_car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Clicado ", "Terceiro carro");
            }
        });



    }

    private void Background_DoWork() {

    }
}
