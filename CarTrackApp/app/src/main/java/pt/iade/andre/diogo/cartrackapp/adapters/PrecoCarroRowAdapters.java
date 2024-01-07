package pt.iade.andre.diogo.cartrackapp.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import pt.iade.andre.diogo.cartrackapp.Models.ClassPrecoCarro;
import pt.iade.andre.diogo.cartrackapp.R;

public class PrecoCarroRowAdapters extends RecyclerView.Adapter<PrecoCarroRowAdapters.ViewHolder> {

    private ArrayList<ClassPrecoCarro> ClassPrecoCarro;
    private LayoutInflater inflater;

    public PrecoCarroRowAdapters(ArrayList<ClassPrecoCarro> carros, Object context) {
        inflater = LayoutInflater.from((Context) context);
        ClassPrecoCarro = carros;
    }

    @Override
    public PrecoCarroRowAdapters.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row_preco_carros, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull PrecoCarroRowAdapters.ViewHolder holder, int position) {
        ClassPrecoCarro carros = ClassPrecoCarro.get(position);
        holder.txtPrecosCarros.setText(inflater.getContext().getString(R.string.lblPreco) + carros.getPreco());
        holder.txtCarMarca.setText(inflater.getContext().getString(R.string.lblModelo) + carros.getMarca());
    }

    @Override
    public int getItemCount() {
        return ClassPrecoCarro.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtPrecosCarros;
        public TextView txtCarMarca;

        public ViewHolder(View view) {
            super(view);
            txtPrecosCarros = view.findViewById(R.id.txtPrecosCarros);
            txtCarMarca = view.findViewById(R.id.txtCarMarca);
        }
    }
}