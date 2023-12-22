package pt.iade.andre.diogo.cartrackapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        prefs = getSharedPreferences("CarTrackApp", MODE_PRIVATE);
        if (prefs.getBoolean("firstrun", false)) {
            Intent s = new Intent(WelcomeActivity.this, LoginActivity.class);
            s.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(s);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcomecartrack);
        background_doWork();
    }

    SharedPreferences prefs = null;

    private void background_doWork() {
        prefs.edit().putBoolean("firstrun", true).commit();
        (findViewById(R.id.btnRegistWelAct)).setOnClickListener(this::Onclick);
        (findViewById(R.id.btnLoginWelAct)).setOnClickListener(this::Onclick);
    }

    private void Onclick(View v){
        int s = v.getId();

        if(s == R.id.btnRegistWelAct){
            Intent ss = new Intent(WelcomeActivity.this, RegistarActivity.class);
            startActivity(ss);
        }else {
            Intent ss = new Intent(WelcomeActivity.this, LoginActivity.class);
            ss.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(ss);
        }
    }
}
