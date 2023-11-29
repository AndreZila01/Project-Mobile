package pt.iade.andre.diogo.cartrackapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import pt.iade.andre.diogo.cartrackapp.Models.ClassNews;

public class DetalhesNewsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.activity_detalhesnews);
        Background_DoWork();
    }

    private void Background_DoWork(){
        ImageButton imgbtnBack = findViewById(R.id.BackActivity);
        imgbtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Intent intent = getIntent();
        int s = intent.getIntExtra("position", 1);
        ClassNews item = (ClassNews) intent.getSerializableExtra("Noticia");

        Log.d("IdNew", ""+s );

        ((TextView) findViewById(R.id.txtDNews)).setText(item.getTitleLo());
        ((TextView) findViewById(R.id.txtDInfo)).setText(item.getTextOfNews());
        ((TextView) findViewById(R.id.txtDAuthor)).setText(item.getAuthor());
        ((TextView) findViewById(R.id.txtDData)).setText(""+item.getDate());// TODO: NATHAN perguntar o estranho formato das horas e como meter HH:MM DD:MM:YYYY
        Picasso.get().load(item.getUrlImg()).into(((ImageView) findViewById(R.id.pctDImagem)));


    }
}
