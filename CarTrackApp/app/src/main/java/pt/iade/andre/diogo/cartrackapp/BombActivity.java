package pt.iade.andre.diogo.cartrackapp;

import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

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
    private final int Fine_Permision_Code = 1;
    private GoogleMap myMap;
    Location location;
    FusedLocationProviderClient fs;
    private void Background_DoWork() {
        ImageButton imgbtnBack = findViewById(R.id.BackActivity);
        imgbtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        //TODO: ver com o prof. nathan: https://youtu.be/XimcwP-OzFg?t=280
        //Localizar o utilizar pegar nessas coordenadas como "float" e enviar para a futura api...
        //ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, Fine_Permision_Code);

       /* Task<Location> task = fs.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                Log.d("Message Tag", location+"");
            }
        }*/
    }
}
