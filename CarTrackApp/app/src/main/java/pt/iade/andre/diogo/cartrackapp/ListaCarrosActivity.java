package pt.iade.andre.diogo.cartrackapp;

    import static pt.iade.andre.diogo.cartrackapp.R.layout.activity_lista_carros;

    import android.annotation.SuppressLint;
    import android.content.Intent;
    import android.os.Bundle;
    import android.widget.ArrayAdapter;
    import android.widget.ListView;

    import androidx.appcompat.app.AppCompatActivity;
    import androidx.recyclerview.widget.LinearLayoutManager;
    import androidx.recyclerview.widget.RecyclerView;
    import java.util.ArrayList;

    import java.util.List;

    import pt.iade.andre.diogo.cartrackapp.adapeters.carrosrowadapters;

public class ListaCarrosActivity extends AppCompatActivity {
     protected RecyclerView carroslistview;
     protected carrosrowadapters CarroRowAdapter;

     protected ArrayList<AdicionarCarro> carroslist;

     private ListaDeCarros listaDeCarros;



    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.id.activity_lista_carros);

        ArrayList<AdicionarCarro> carros = AdicionarCarro.List();

        ListView listViewCarros = findViewById(R.id.carros_listview);

        ArrayAdapter<AdicionarCarro> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                carros
        );

        listViewCarros.setAdapter(adapter);

        listViewCarros.setOnItemClickListener((adapterView, view, position, id) -> {
            AdicionarCarro carroSelecionado = carros.get(position);
            abrirDetalhesCarro(carroSelecionado);
        });
        setupComponents();
    }

    private void setupComponents() {
        setSupportActionBar(findViewById(R.id.toolbar2));

        CarroRowAdapter = new carrosrowadapters(this, carroslist);

        carroslistview = (RecyclerView)findViewById(R.id.carros_listview);
        carroslistview.setLayoutManager(new LinearLayoutManager(this));
        carroslistview.setAdapter(CarroRowAdapter);
    }

    private void abrirDetalhesCarro(AdicionarCarro carro) {
        Intent intent = new Intent(this, DetalhesCarroActivity.class);
        intent.putExtra("marca", carro.getMarca());
        intent.putExtra("modelo", carro.getModelo());
        intent.putExtra("Matricula", carro.getMatricula());
        intent.putExtra("Cilindrada", carro.getCilindrada());
        intent.putExtra("Sistema de tração", carro.getsistema_de_tracao());
        intent.putExtra("Potencia", carro.getPotencia());
        intent.putExtra("Peso", carro.getPeso());
        intent.putExtra("Consumo", carro.getConsumo());
        startActivity(intent);
    }

    private static class ListaDeCarros {
        private List<Object> listaDeCarros;

        public void adicionarCarro(AdicionarCarro carro) {
        }

        public List<Object> getListaDeCarros() {
            return listaDeCarros;
        }

        public void setListaDeCarros(List<Object> listaDeCarros) {
            this.listaDeCarros = listaDeCarros;
        }
    }
}





