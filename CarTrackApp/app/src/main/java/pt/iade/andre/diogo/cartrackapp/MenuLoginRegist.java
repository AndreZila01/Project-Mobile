package pt.iade.andre.diogo.cartrackapp;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MenuLoginRegist extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        background_doWork(); // função para incrementar todos os valores aqui!!

    }
    private void background_doWork(){
        Button btnLogin = findViewById(R.id.btnLogin);
        Button btnRegist = findViewById(R.id.btnRegist);

        btnLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(MenuLoginRegist.this, LoginActivity.class);
                startActivity(intent);

            }
        });

        btnRegist.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                // TODO : Ligar ao RegistActivity
            }
        });
    }
}
