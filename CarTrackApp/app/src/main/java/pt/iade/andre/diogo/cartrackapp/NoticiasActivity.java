package pt.iade.andre.diogo.cartrackapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class NoticiasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        background_doWork(); // função para incrementar todos os valores aqui!!
    }

    private void background_doWork(){

    }
}
