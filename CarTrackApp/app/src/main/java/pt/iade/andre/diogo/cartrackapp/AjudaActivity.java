package pt.iade.andre.diogo.cartrackapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class AjudaActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.activity_ajuda);
        Background_DoWork();
    }

    private void Background_DoWork() {
            findViewById(R.id.button3).setOnClickListener(this::onClick);
            findViewById(R.id.btnemail).setOnClickListener(this::onClick);

        ImageButton imgbtnBack = findViewById(R.id.BackActivity);
        imgbtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void onClick(View v){

        int s = v.getId();
        if(s == R.id.button3) {
            String phone = "+34666777888";
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
            startActivity(intent);
        }
        else {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("plain/text");
            intent.putExtra(Intent.EXTRA_EMAIL, new String[] { "trackcarsupport@gmail.com" });
            intent.putExtra(Intent.EXTRA_SUBJECT, "Problemas tecnicos");
            intent.putExtra(Intent.EXTRA_TEXT, "mail body");
            startActivity(Intent.createChooser(intent, ""));
        }
    }

}
