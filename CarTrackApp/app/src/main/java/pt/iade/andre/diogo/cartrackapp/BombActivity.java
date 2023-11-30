package pt.iade.andre.diogo.cartrackapp;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.util.List;
import java.util.function.Consumer;

public class BombActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle bundle) {
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


        ActivityResultLauncher<String[]> locationPermissionRequest =
                registerForActivityResult(new ActivityResultContracts
                        .RequestMultiplePermissions(), result -> {
                    Boolean fineLocationGranted = result.getOrDefault(
                            android.Manifest.permission.ACCESS_FINE_LOCATION, false);
                    Boolean coarseLocationGranted = result.getOrDefault(
                            android.Manifest.permission.ACCESS_COARSE_LOCATION, false);
                    if ((fineLocationGranted != null && fineLocationGranted) || (coarseLocationGranted != null && coarseLocationGranted)) {

                        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                        List<String> providers = lm.getProviders(true);
                        Location location = null;

                        for (int i = providers.size() - 1; i >= 0; i--) {

                            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                                // TODO: Consider calling
                                //    ActivityCompat#requestPermissions
                                // here to request the missing permissions, and then overriding
                                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                //                                          int[] grantResults)
                                // to handle the case where the user grants the permission. See the documentation
                                // for ActivityCompat#requestPermissions for more details.
                                return;
                            }

                            lm.requestSingleUpdate(LocationManager.GPS_PROVIDER, new LocationListener() {
                                @Override
                                public void onLocationChanged(@NonNull Location location) {
                                    double lat = location.getLatitude();
                                    Log.d("lat: ", ""+lat);
                                    double lng = location.getLongitude();
                                    Log.d("lng: ", ""+lng);
                                }
                            }, null);

                                }


                            } else {
                                // No location access granted.
                            }
                        }
                );

        locationPermissionRequest.launch(new String[] {
                android.Manifest.permission.ACCESS_FINE_LOCATION,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
        });



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
