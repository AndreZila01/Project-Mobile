package pt.iade.andre.diogo.cartrackapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    DrawerLayout drawerLayout;
    Toolbar toolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        background_doWork(); // função para incrementar todos os valores aqui!!
    }

    public void background_doWork(){
        toolBar = findViewById(R.id.ToolbarMain); // procura no projeto um id com ToolbarMain e envia para toolBar
        setSupportActionBar(toolBar); //

        drawerLayout = findViewById(R.id.DrawerLayout); // procura no projeto um id DrawerLayout e envia para o drawerLayout
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolBar, R.string.menu_open, R.string.menu_close); // dá propriedades ao toggle de fechar e abrir navbar
        drawerLayout.addDrawerListener(toggle); // adiciona o evento drawerlistenner ao toggle.
        toggle.syncState(); // chamar quando houver alterações

        NavigationView navigationView = findViewById(R.id.nav_view);// procura no projeto um id nav_view e envia para o drawerLayout
        navigationView.setNavigationItemSelectedListener(this);
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
            Intent intent = new Intent(MainActivity.this, CarrosActivity.class);
            startActivity(intent); // Start the CarrosActivity

            Toast.makeText(getApplicationContext(), "Clicado o 1", Toast.LENGTH_LONG).show();

        }


        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}