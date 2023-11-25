package pt.iade.andre.diogo.cartrackapp;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class BombActivity  extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle bundle){
        //Toast.makeText(getApplicationContext(), "Clicado o 2", Toast.LENGTH_LONG).show();
        super.onCreate(bundle);
        setContentView(R.layout.activity_bombas);
        Background_DoWork();

    }

    /*
     * https://cdn.dribbble.com/users/2366356/screenshots/6766094/dribbble_shot_4x.png
     * */
    private void Background_DoWork() {
        ImageButton imgbtnBack = findViewById(R.id.BackActivity);
        imgbtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
    }
}
