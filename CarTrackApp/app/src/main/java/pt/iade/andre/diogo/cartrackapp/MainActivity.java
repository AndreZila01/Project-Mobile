package pt.iade.andre.diogo.cartrackapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
<<<<<<< HEAD
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
=======
>>>>>>> MenuCar
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    DrawerLayout drawerLayout;
    Toolbar toolBar;
    ActivityOnClickListener onClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        onClickListener = new ActivityOnClickListener();

        background_doWork(); // função para incrementar todos os valores aqui!!
    }

    public void background_doWork(){

        //region Funcionalidades
        toolBar = findViewById(R.id.ToolbarMain); // procura no projeto um id com ToolbarMain e envia para toolBar
        setSupportActionBar(toolBar); //

        drawerLayout = findViewById(R.id.DrawerLayout); // procura no projeto um id DrawerLayout e envia para o drawerLayout
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolBar, R.string.menu_open, R.string.menu_close); // dá propriedades ao toggle de fechar e abrir navbar
        drawerLayout.addDrawerListener(toggle); // adiciona o evento drawerlistenner ao toggle.
        toggle.syncState(); // chamar quando houver alterações

        NavigationView navigationView = findViewById(R.id.nav_view);// procura no projeto um id nav_view e envia para o drawerLayout
        navigationView.setNavigationItemSelectedListener(this);

        ImageButton imgUser = findViewById(R.id.iconUser);

        ImageView imgBomba = findViewById(R.id.pctBdComb);

        ImageView imgNoticias = findViewById(R.id.pctNoticias);


        ImageView imgPrice = findViewById(R.id.pctPrecosCarros);

        ImageButton imgbtnAdd = findViewById(R.id.btnCarPayvalue);
        //endregion

        //region Events
        imgUser.setOnClickListener(onClickListener);

        //imgUser.setOnClickListener(this::onClick);
//TODO: Check https://stackoverflow.com/questions/32216629/how-to-handle-multiple-click-events-in-android


        imgbtnAdd.setOnClickListener(onClickListener);

        // TODO Checkar se o user tem algum evento ou agendamento marcado
        // Caso tenha faz isto: https://stackoverflow.com/questions/11368462/how-to-create-edittext-in-java-code-for-android


        imgBomba.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                // TODO: Adicionar a view activity adicionar bombas google maps.

            }
        });


        imgNoticias.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                // TODO: Adicionar a view activity adicionar noticias.
                Intent intent = new Intent(MainActivity.this, NoticiasActivity.class);
                startActivity(intent); // Start the CarrosActivity

            }
        });

        imgPrice.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                // TODO: Adicionar a view activity adicionar fatura ou carro.

            }
        });

        //endregion
    }
    public void onClick(View v)
    {
        /*
        if(v == button1)
        {
            //play button1 sound
        }

        else if(v == button2)
        {
            //play button2 sound
        }

        else
        {

        }*/
    }

    @Override
    public void onBackPressed(){
        if (drawerLayout.isDrawerOpen((GravityCompat.START)))
            drawerLayout.closeDrawer(GravityCompat.START);
        else
            super.onBackPressed();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int s = item.getItemId();

        if(s == R.id.nav_car)
        {
            Intent intent = new Intent(MainActivity.this, carActivity.class);
            startActivity(intent); // Start the CarrosActivity

            Toast.makeText(getApplicationContext(), "Clicado o 1", Toast.LENGTH_LONG).show();

        }


        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
<<<<<<< HEAD
}
=======

    private class ActivityOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.nav_car) {

            }
        }
    }
}
>>>>>>> 9dbfedc ( Corrigido alguns erros no codigo e começo de implementação do codigo na realidade)
