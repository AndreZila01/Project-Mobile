package pt.iade.andre.diogo.cartrackapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import pt.iade.andre.diogo.cartrackapp.Models.ClassInspecoes;
import pt.iade.andre.diogo.cartrackapp.R;

public class InspecoesListAdapters extends ArrayAdapter<String> {
    private int resourceLayout;
    private Context context;
    private String[] strings;

    public InspecoesListAdapters(Context context, int resource, String[] items) {
        super(context, resource, items);
        this.resourceLayout = resource;
        this.context = context;
        this.strings = items;
    }

    @Override
    public int getCount() { return strings.length; }

    @Override
    public View getView(int position, View current, ViewGroup parent) {
        View v = current;

        if (v == null) {
            LayoutInflater inflater;
            inflater = LayoutInflater.from(context);
            v = inflater.inflate(resourceLayout, null);
        }

        String string = getItem(position);

        if (string != null) {
            TextView titleView = v.findViewById(R.id.titulo_inspecoes);
            TextView description = v.findViewById(R.id.item_inspecoes);

            titleView.setText(string.split(": ")[0]);
            description.setText(string.split(": ")[1]);
        }

        return v;
    }
}


