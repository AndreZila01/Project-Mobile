package pt.iade.andre.diogo.cartrackapp;

    import android.content.Intent;
    import android.os.Bundle;
    import android.view.View;
    import android.widget.ImageButton;

    import androidx.appcompat.app.AppCompatActivity;
    import androidx.recyclerview.widget.LinearLayoutManager;
    import androidx.recyclerview.widget.RecyclerView;
    import java.util.ArrayList;

    import pt.iade.andre.diogo.cartrackapp.Models.ClassCar;
    import pt.iade.andre.diogo.cartrackapp.adapters.CarItemRowAdapter;


public class CarrosActivity extends AppCompatActivity {
    protected RecyclerView itemsListViewCar;

    protected ArrayList<ClassCar> itemsListCar;

    protected CarItemRowAdapter carItemRowAdpdaterCar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car);

        ImageButton imgbtnBack = findViewById(R.id.BackActivity);
        imgbtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

        itemsListCar = new ArrayList<ClassCar>();
        itemsListCar.add(new ClassCar(1, "RM-19-AD", "Fiat", "99-04", "https://cdn.motor1.com/images/mgl/JqRqM/s1/fiat-tipo-cross.jpg", "Eletrico", "150", "50", "37.421998333333335x-122.084" ));
        itemsListCar.add(new ClassCar(2, "85-DA-QE", "Peugeot", "22-12", "https://img.estadao.com.br/resources/jpg/6/5/1511721627356.jpg", "Gasoleo", "100", "05", "37.421998333333335x-122.084"));

        background_doWork(); // função para incrementar todos os valores aqui!!
    }

    private void background_doWork(){

        //RelativeLayout et = (RelativeLayout)findViewById(R.id.pnlNoticias);

        carItemRowAdpdaterCar = new CarItemRowAdapter(this, itemsListCar);
        carItemRowAdpdaterCar.setOnClickListener(new CarItemRowAdapter.ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(CarrosActivity.this, DetalhesCarroActivity.class);
                intent.putExtra("idNew", position);
                intent.putExtra("Carro", itemsListCar.get(position));

                startActivityForResult(intent, 1);
                return;
            }
        });

        itemsListViewCar = (RecyclerView) findViewById(R.id.pnlCar);
        itemsListViewCar.setLayoutManager(new LinearLayoutManager(this));
        itemsListViewCar.setAdapter(carItemRowAdpdaterCar);
    }
}





