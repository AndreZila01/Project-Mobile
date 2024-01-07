package pt.iade.andre.diogo.cartrackapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import pt.iade.andre.diogo.cartrackapp.Models.ClassMultas;
import pt.iade.andre.diogo.cartrackapp.R;

public class MultaRowAdapters extends RecyclerView.Adapter<MultaRowAdapters.ViewHolder> {

    private ArrayList<ClassMultas> ClassMultas;
    private LayoutInflater inflater;
    private CarroClickListener clickListener;

    public MultaRowAdapters(Context context, ArrayList<ClassMultas> multas) {
        inflater = LayoutInflater.from(context);
        ClassMultas = multas;
    }

    public void setOnClickListener(CarroClickListener listener) {
        clickListener = listener;
    }


    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row_lista_multas, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ClassMultas multas = ClassMultas.get(position);

        holder.textviewDescricao.setText(multas.getDescricao());
        holder.textviewData.setText(multas.getData());
    }


    @Override
    public int getItemCount() { return ClassMultas.size();}


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView textviewDescricao;
        public TextView textviewData;

        public ViewHolder(View multaView) {
            super(multaView);

            textviewDescricao = multaView.findViewById(R.id.textviewDescricao);
            textviewData = multaView.findViewById(R.id.textviewData);

            multaView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            if (clickListener != null) {
                clickListener.onMultaClick(view, getAdapterPosition());
            }
        }
    }

    public interface CarroClickListener {
        void onMultaClick(View view, int position);

    }
}

