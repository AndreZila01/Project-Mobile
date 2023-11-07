package pt.iade.andre.diogo.cartrackapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.activity_login);
        Background_DoWork();
    }

    private void Background_DoWork(){


        Button btn = findViewById(R.id.btnLogin);
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                // TODO : Checkar valores e se estão na database!

                Toast.makeText(getApplicationContext(), "Clicado o botão", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);

                startActivity(intent);
            }
        });
    }
}
