package pt.iade.andre.diogo.cartrackapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import pt.iade.andre.diogo.cartrackapp.Models.ClassInspecoes;
import pt.iade.andre.diogo.cartrackapp.R;

public class InspecoesRowAdapters extends RecyclerView.Adapter<InspecoesRowAdapters.ViewHolder>  {
    private final ArrayList<ClassInspecoes> ClassInspecoes;
    private final LayoutInflater inflater;
    private CarroClickListener clickListener = null;

    public InspecoesRowAdapters(Context context, ArrayList<ClassInspecoes> carros) {
        inflater = LayoutInflater.from(context);
        ClassInspecoes = carros;
    }


    public void setOnClickListener(CarroClickListener listener) {
        clickListener = listener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row_lista_inspecoes,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ClassInspecoes carros = ClassInspecoes.get(position);

        holder.txtListaInspecoes.setText(carros.getMatricula());
        holder.txtCarMarca.setText(carros.getMarca());
    }

    @Override
    public int getItemCount() {
        return ClassInspecoes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView txtListaInspecoes;
        public TextView txtCarMarca;

        public ViewHolder(View carroView) {

            super(carroView);

            txtListaInspecoes = carroView.findViewById(R.id.txtPrecosCarros);
            txtCarMarca = carroView.findViewById(R.id.txtCarMarca);

            carroView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            if (clickListener != null) {
                clickListener.onCarroClick(view, getAdapterPosition());
            }
        }
    }

    public interface CarroClickListener {
        void onCarroClick(View view, int position);

    }
}


