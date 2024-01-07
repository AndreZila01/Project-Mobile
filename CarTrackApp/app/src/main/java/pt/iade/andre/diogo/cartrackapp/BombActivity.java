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
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import pt.iade.andre.diogo.cartrackapp.Models.ClassBombas;
import pt.iade.andre.diogo.cartrackapp.Models.ClassCar;


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
    //private final int Fine_Permision_Code = 1;
    //private GoogleMap myMap;
    //Location location;
    //FusedLocationProviderClient fs;
    private int index = 0;
    ArrayList<ClassBombas> myClassList;

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
                                Log.d("lat: ", "" + lat);
                                double lng = location.getLongitude();
                                Log.d("lng: ", "" + lng);

                                //http://localhost:8080/bomb/localizacao/-13.66406/132.08234
                                String path = "http://10.0.2.2:8080/bomb/localizacao/" + lat + "/" + lng + "/bombas";

                                try {
                                    Request rq = new Request.Builder().url(path).build();
                                    OkHttpClient client = new OkHttpClient();

                                    client.newCall(rq).enqueue(new Callback() {
                                        @Override
                                        public void onFailure(@NonNull Call call, @NonNull IOException e) {
                                            Log.d("Error", "" + e);
                                        }

                                        @Override
                                        public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                            String s = response.body().string().toString();

                                            index = 0;
                                            myClassList = new Gson().fromJson(s, new TypeToken<List<ClassBombas>>() {
                                            }.getType());
                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {

                                                    ((TextView) findViewById(R.id.lblGasoleoE)).setText("GasoleoE");
                                                    ((TextView) findViewById(R.id.lblGasoleoEPrice)).setText("" + myClassList.get(0).getGasoleoE());

                                                    ((TextView) findViewById(R.id.lblGasoleoS)).setText("GasoleoS");
                                                    ((TextView) findViewById(R.id.lblGasoleoSPrice)).setText("" + myClassList.get(0).getGasoleoS());

                                                    ((TextView) findViewById(R.id.lblGasolinaE95)).setText("GasolinaE95");
                                                    ((TextView) findViewById(R.id.lblGasolinaE95Price)).setText("" + myClassList.get(0).getGasolinaE95());

                                                    ((TextView) findViewById(R.id.lblGasolinaS95)).setText("GasolinaS95");
                                                    ((TextView) findViewById(R.id.lblGasolinaS95Price)).setText("" + myClassList.get(0).getGasolinaS95());

                                                    ((TextView) findViewById(R.id.lblGasolinaE98)).setText("GasolinaE98");
                                                    ((TextView) findViewById(R.id.lblGasolinaE98Price)).setText("" + myClassList.get(0).getGasolinaE98());

                                                    ((TextView) findViewById(R.id.lblGPLAuto)).setText("GPLAuto");
                                                    ((TextView) findViewById(R.id.lblGPLAutoPrice)).setText("" + myClassList.get(0).getGPLAuto());


                                                    ((TextView) findViewById(R.id.lblLocation)).setText("" + myClassList.get(0).getBomba());

                                                    Log.d("Message", "");
                                                }
                                            });
                                        }
                                    });
                                } catch (Exception ex) {
                                    Log.d("s", "" + ex);
                                } finally {
                                    //urlConnection.disconnect();
                                }

                            }
                        }, null);

                    }
                });

        locationPermissionRequest.launch(new String[]{
                android.Manifest.permission.ACCESS_FINE_LOCATION,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
        });

        ((Button) findViewById(R.id.btnAnterior)).setOnClickListener(this::Click);
        ((Button) findViewById(R.id.btnNext)).setOnClickListener(this::Click);
    }

    private void Click(View v) {
        int value = v.getId();

        if (value == R.id.btnAnterior) {

            if (index == 0)
                index = myClassList.size() - 1;
            else
                index--;


            ((TextView) findViewById(R.id.lblGasoleoEPrice)).setText("" + myClassList.get(index).getGasoleoE());

            ((TextView) findViewById(R.id.lblGasoleoSPrice)).setText("" + myClassList.get(index).getGasoleoS());

            ((TextView) findViewById(R.id.lblGasolinaE95Price)).setText("" + myClassList.get(index).getGasolinaE95());


            ((TextView) findViewById(R.id.lblGasolinaS95Price)).setText("" + myClassList.get(index).getGasolinaS95());

            ((TextView) findViewById(R.id.lblGasolinaE98Price)).setText("" + myClassList.get(index).getGasolinaE98());

            ((TextView) findViewById(R.id.lblGPLAutoPrice)).setText("" + myClassList.get(index).getGPLAuto());


            ((TextView) findViewById(R.id.lblLocation)).setText("" + myClassList.get(index).getBomba());
        } else if (value == R.id.btnNext) {
            if (index == myClassList.size() - 1)
                index = 0;
            else
                index++;


            ((TextView) findViewById(R.id.lblGasoleoEPrice)).setText("" + myClassList.get(index).getGasoleoE());

            ((TextView) findViewById(R.id.lblGasoleoSPrice)).setText("" + myClassList.get(index).getGasoleoS());

            ((TextView) findViewById(R.id.lblGasolinaE95Price)).setText("" + myClassList.get(index).getGasolinaE95());


            ((TextView) findViewById(R.id.lblGasolinaS95Price)).setText("" + myClassList.get(index).getGasolinaS95());

            ((TextView) findViewById(R.id.lblGasolinaE98Price)).setText("" + myClassList.get(index).getGasolinaE98());

            ((TextView) findViewById(R.id.lblGPLAutoPrice)).setText("" + myClassList.get(index).getGPLAuto());


            ((TextView) findViewById(R.id.lblLocation)).setText("" + myClassList.get(index).getBomba());
        }
    }
}
