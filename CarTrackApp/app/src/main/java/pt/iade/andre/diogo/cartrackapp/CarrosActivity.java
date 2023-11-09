package pt.iade.andre.diogo.cartrackapp;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CarrosActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle bundle){
        Toast.makeText(getApplicationContext(), "Clicado o 2", Toast.LENGTH_LONG).show();
        super.onCreate(bundle);
        setContentView(R.layout.activity_car);
        Background_DoWork();

    }

    private void Background_DoWork() {

    }
}
