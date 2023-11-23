package pt.iade.andre.diogo.cartrackapp;

    import android.content.Intent;
    import android.os.Bundle;
    import android.widget.ArrayAdapter;
    import android.widget.ListView;
    import androidx.appcompat.app.AppCompatActivity;
    import java.util.ArrayList;
    import java.util.List;

public class ListaCarrosActivity extends AppCompatActivity {
    private ListaDeCarros listaDeCarros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_carros);

        listaDeCarros = new ListaDeCarros();
        AdicionarCarro carro1 = new AdicionarCarro("Toyota", "Corolla","AA-01-AA","1497cm^3","Frente","150CV","1443","6.7km/L");
        AdicionarCarro carro2 = new AdicionarCarro("Ford", "Focus","AA-02-AA","999cm^3","Traz","125CV","1379KG","6.7km/L");
        listaDeCarros.adicionarCarro(carro1);
        listaDeCarros.adicionarCarro(carro2);

        ListView listViewCarros = findViewById(R.id.listViewCarros);
        ArrayAdapter<AdicionarCarro> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaDeCarros.getListaDeCarros());
        listViewCarros.setAdapter(adapter);

        listViewCarros.setOnItemClickListener((adapterView, view, position, id) -> {
            AdicionarCarro carroSelecionado = (AdicionarCarro) listaDeCarros.getListaDeCarros().get(position);
            abrirDetalhesCarro(carroSelecionado);
        });
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



