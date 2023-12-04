package pt.iade.andre.diogo.cartrackapp;

import static android.provider.CalendarContract.CalendarCache.URI;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


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


                        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                                return;
                        }

                        lm.requestSingleUpdate(LocationManager.GPS_PROVIDER, new LocationListener() {
                            @Override
                            public void onLocationChanged(@NonNull Location location) {
                                double lat = location.getLatitude();
                                Log.d("lat: ", ""+lat);
                                double lng = location.getLongitude();
                                Log.d("lng: ", ""+lng);

                                //http://localhost:8080/bomb/localizacao/-13.66406/132.08234
                                String path = "http://localhost:8080/bomb/localizacao/" + lat + "/" + lng;

                                try {
                                    Request rq = new Request.Builder().url(path).build();
                                    OkHttpClient client = new OkHttpClient();

                                    client.newCall(rq).enqueue(new Callback() {
                                        @Override
                                        public void onFailure(@NonNull Call call, @NonNull IOException e) {
                                            Log.d("Error", ""+e);
                                        }

                                        @Override
                                        public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                            Log.d("Message", ""+response.body().string());
                                        }
                                    });
                                }
                                catch (Exception ex) {
                                    Log.d("s", ""+ex);
                                }
                                finally {
                                    //urlConnection.disconnect();
                                }

                            }
                        }, null);

                    }
                });

        locationPermissionRequest.launch(new String[] {
                android.Manifest.permission.ACCESS_FINE_LOCATION,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
        });
    }
}
