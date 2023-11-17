package pt.iade.andre.diogo.cartrackapp;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

public class NoticiasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        background_doWork(); // função para incrementar todos os valores aqui!!
    }

    private void background_doWork(){

        RelativeLayout et = (RelativeLayout)findViewById(R.id.pnlNoticias);

        for(int i = 0; i<4; i++){

            /*
            <RelativeLayout
            android:layout_width="match_parent"
            android:layout_height="120dp"
            android:layout_marginTop="15dp"
            android:background="@color/MenuPItems"
            >

            * */

            RelativeLayout rl = new RelativeLayout(this);
}
    }
}
