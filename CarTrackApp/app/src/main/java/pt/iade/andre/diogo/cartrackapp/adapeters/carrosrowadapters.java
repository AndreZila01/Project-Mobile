package pt.iade.andre.diogo.cartrackapp.adapeters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import pt.iade.andre.diogo.cartrackapp.AdicionarCarro;
import pt.iade.andre.diogo.cartrackapp.R;

public class carrosrowadapters extends RecyclerView.Adapter<carrosrowadapters.ViewHolder> {
    private ArrayList<AdicionarCarro> AdicionarCarro;
    private LayoutInflater inflater;
    private CarroClickListener clickListener;

    public carrosrowadapters(Context context, ArrayList<AdicionarCarro> carros) {
        inflater = LayoutInflater.from(context);
        AdicionarCarro = carros;
        clickListener = null;
    }

    public void setOnClickListener(CarroClickListener listener) {
        clickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        @SuppressLint("ResourceType") View view =inflater.inflate(R.id.row_lista_carros,parent,false);
        return new ViewHolder(view) {
            @Override
            public void onClick(View v) {
            }
        };
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        AdicionarCarro carros = AdicionarCarro.get(position);

        holder.textViewMarca1.setText(carros.getMarca());
    }

    @Override
    public int getItemCount() {
        return AdicionarCarro.size();
    }




    public abstract class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, pt.iade.andre.diogo.cartrackapp.adapeters.ViewHolder {
        public TextView textViewMarca1;

        public ViewHolder(View carroView) {
            super(carroView);

            textViewMarca1 = carroView.findViewById(R.id.textviewMarca1);

            carroView.setOnClickListener(this);
        }

        @Override
        public void OnClick(View view) {

            if (clickListener != null) {
                clickListener.onCarroClick(view, getAdapterPosition());
            }
        }
    }

    public interface CarroClickListener {
        void onCarroClick(View view, int position);

    }
}

