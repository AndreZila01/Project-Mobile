package pt.iade.andre.diogo.cartrackapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.reflect.Array;

import pt.iade.andre.diogo.cartrackapp.Models.ClassInspecoes;
import pt.iade.andre.diogo.cartrackapp.adapters.InspecoesListAdapters;

public class DetalhesInspecaoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_inspecao);

        Intent intent = getIntent();

        setupRecyclerView((ClassInspecoes) intent.getSerializableExtra("Carro"));
    }

    private void setupRecyclerView(ClassInspecoes item){
        ImageButton imgbtnBack = findViewById(R.id.BackActivity);
        imgbtnBack.setOnClickListener(v -> finish());
        
        ListView inspecoesListView=findViewById(R.id.inspecoes_listview);
        String stringArray[] = new String[]{
                getString(R.string.lblMarca) + item.getMarca(),
                getString(R.string.lblMatricula) + item.getMatricula(),
                getString(R.string.lblProximaInspecao) + item.getProximaInspecao(),
                getString(R.string.lblUltimaInspecao) + item.getUltimaInspecao(),
                getString(R.string.lblMotor) + item.getMotor(),
                getString(R.string.lblFreios) + item.getFreios(),
                getString(R.string.lblSuspensaoDirecao) + item.getSuspensaoDirecao(),
                getString(R.string.lblSistemaEletrico) + item.getSistemaEletrico(),
                getString(R.string.lblTransmissaoEmbreagem)+ item.getTransmissaoEmbreagem(),
                getString(R.string.lblEstruturaCarroceria) + item.getEstruturaCarroceria(),
                getString(R.string.lblDocumentacaoPlacas) + item.getDocumentacaoPlacas(),
                getString(R.string.lblEmissoes) + item.getEmissoes(),
                getString(R.string.lblNomeCentro) + item.getNomeCentro(),
                getString(R.string.lblTelefone) + item.getTelefone(),
                getString(R.string.lblEmailInspecao) + item.getEmail(),
                getString(R.string.lblChefeDoCentro) + item.getChefeDoCentro(),
                getString(R.string.lblMorada) + item.getMorada()
        };

        InspecoesListAdapters inspecoesListAdapter = new InspecoesListAdapters(this, R.layout.list_item_inspecoes, stringArray);
        inspecoesListView.setAdapter(inspecoesListAdapter);
    }
}

