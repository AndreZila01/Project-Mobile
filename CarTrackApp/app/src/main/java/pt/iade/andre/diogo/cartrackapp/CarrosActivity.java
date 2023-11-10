package pt.iade.andre.diogo.cartrackapp;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
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
        /*Cria um menu "classico" do layout top_menu_inflate*/
        // TODO: Checkar se funciona isto ... https://stackoverflow.com/a/2336047
        RelativeLayout item = (RelativeLayout)findViewById(R.id.MenuTop);
        View child = getLayoutInflater().inflate(R.layout.top_menu_inflate, null);
        item.addView(child);
    }
}
