package pt.iade.andre.diogo.cartrackapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PlusActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plus);

        background_doWork(); // função para incrementar todos os valores aqui!!
    }

    private void background_doWork() {
        (findViewById(R.id.btnAddInspction)).setOnClickListener(this::onclick);
        (findViewById(R.id.btnAddCar)).setOnClickListener(this::onclick);
        (findViewById(R.id.btnAddCoime)).setOnClickListener(this::onclick);
        (findViewById(R.id.btnSaveAddValue)).setOnClickListener(this::onclick);

    }

    private void onclick(View v) {

        int s = v.getId();

        if (!(s == R.id.btnSaveAddValue)) {

            (findViewById(R.id.pnlNewCoime)).setVisibility(View.INVISIBLE);
            (findViewById(R.id.pnlNewCar)).setVisibility(View.INVISIBLE);
            (findViewById(R.id.pnlNewInspection)).setVisibility(View.INVISIBLE);

            if (s == R.id.btnAddCar) {
                (findViewById(R.id.pnlNewCar)).setVisibility(View.VISIBLE);
            } else if (s == R.id.btnAddCar) {
                (findViewById(R.id.pnlNewInspection)).setVisibility(View.VISIBLE);
            }
            if (s == R.id.btnAddCoime) {
                (findViewById(R.id.pnlNewCoime)).setVisibility(View.VISIBLE);
            }
        }
        else {
            //Imaginar uma maneira de criar um calendar no meio do ecra, "apagar tudo" enquanto o calendario está on e quando clicar desaparecer e enviar para a textbox.
            Toast.makeText(getApplicationContext(), "Clicado o botão", Toast.LENGTH_LONG).show();
            String value = ((Spinner)findViewById(R.id.spinner1)).getSelectedItem().toString();
        }
    }
}
