package pt.iade.andre.diogo.cartrackapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfiluser);

        background_doWork(); // função para incrementar todos os valores aqui!!
    }

    private void background_doWork(){
        (findViewById(R.id.pnlSetting)).setOnClickListener(this::onclick);
        (findViewById(R.id.txtSettings)).setOnClickListener(this::onclick);
        (findViewById(R.id.pctSetting)).setOnClickListener(this::onclick);
        (findViewById(R.id.pnlLogOut)).setOnClickListener(this::onclick);
        (findViewById(R.id.txtLogOut)).setOnClickListener(this::onclick);
        (findViewById(R.id.pctLogOut)).setOnClickListener(this::onclick);

        ImageButton imgbtnBack = findViewById(R.id.BackActivity);
        imgbtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
    }

    private void onclick(View v){

        int id = v.getId();

        if(id == R.id.pnlSetting || id == R.id.txtSettings || id == R.id.pctSetting)        {

        }
        else if (id == R.id.pnlLogOut || id == R.id.txtLogOut || id == R.id.pctLogOut)         {
            Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }
}
