package pt.iade.andre.diogo.cartrackapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolBar;
    ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        background_doWork();
    }

    public void background_doWork(){
        drawerLayout = findViewById(R.id.DrawerLayout);
        navigationView = findViewById(R.id.NavigationView);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.menu_open, R.string.menu_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                /*switch (item.getItemId()){
                    case R.id.nav_car:
                        Log.i("MENU_DRAWER_TAG", "Home item is clicked");
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                }*/
                return true;
            }
        });

            /*
            * <item android:id="@+id/nav_car" android:title="Carros" android:icon="@drawable/car"></item>
    <item android:id="@+id/nav_schedule" android:title="Agendamento" android:icon="@drawable/schedule"></item>
    <item android:id="@+id/nav_screwdriver" android:title="Inspeções" android:icon="@drawable/screwdriver"></item>
    <item android:id="@+id/nav_wallet" android:title="Carteira" android:icon="@drawable/wallet"></item>
            * */
    }
}