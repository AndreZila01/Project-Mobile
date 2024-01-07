package pt.iade.andre.diogo.cartrackapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import pt.iade.andre.diogo.cartrackapp.Models.ClassCar;
import pt.iade.andre.diogo.cartrackapp.R;


public class CarItemRowAdapter extends RecyclerView.Adapter<CarItemRowAdapter.ViewHolder> {
    private ArrayList<ClassCar> itemscar;
    private RelativeLayout pnlCar;
    private TextView txtModelo;
    private ImageView imgs;
    private TextView txtdetalhescar;
    private LayoutInflater inflatercar;
    private ItemClickListener clickListenercar;

    public CarItemRowAdapter(Context context, ArrayList<ClassCar> items) {
        inflatercar = LayoutInflater.from(context);
        this.itemscar = items;
        clickListenercar = null;
    }

    /**
     * Sets the event listener when a row gets clicked by the user.
     *
     * @param listener Event handler for the click.
     */
    public void setOnClickListener(ItemClickListener listener) {
        clickListenercar = (ItemClickListener) listener;
    }

    /**
     * Inflates the layout of the row into the actual list.
     *
     * @param parent The ViewGroup into which the new View will be added after it is bound to
     *               an adapter position.
     * @param viewType The view type of the new View.
     *
     * @return Instantiated row layout.
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflatercar.inflate(R.layout.row_car_item, parent, false);
        return new ViewHolder(view);
    }

    /**
     * Binds the data from each item in the list to a row in the list.
     *
     * @param holder The ViewHolder which should be updated to represent the contents of the
     *        item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ClassCar item = itemscar.get(position);

        Picasso.get().load(item.getUrlImg()).into(holder.imgs);

        holder.txtModelo.setText(item.getModelo());
        holder.txtdetalhes.setText(item.getResumo());
    }

    /**
     * The RecyclerView needs to know the size of our list, this just provides that.
     *
     * @return Size of our data.
     */
    @Override
    public int getItemCount() {
        return itemscar.size();
    }

    /**
     * Class responsible for recycling the views as you scroll.
     */
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public RelativeLayout pnlp;
        public TextView txtModelo;
        public ImageView imgs;
        public TextView txtdetalhes;
        public TextView inflater;


        /**
         * Sets up the view that was inflated.
         *
         * @param itemView Layout view that was inflated.
         */
        public ViewHolder(View itemView) {
            super(itemView);

            // Get the components in the view.
            pnlp = itemView.findViewById(R.id.pnlCar);
            txtModelo = itemView.findViewById(R.id.txtCarTitle);
            imgs = itemView.findViewById(R.id.imgCar);
            //inflater = itemView.findViewById(R.id.pnlCar);
            txtdetalhes = itemView.findViewById(R.id.txtDetalhesCar);

            // Set what happens when the view gets clicked.
            itemView.setOnClickListener(this);
        }

        /**
         * Handles the onclick event of the view.
         */
        @Override
        public void onClick(View view) {
            // Pass the event to our custom listener in the activity.
            if (clickListenercar != null) {
                clickListenercar.onItemClick(view, getAdapterPosition());
            }
        }
    }

    /**
     * Defines what to do when a list item gets clicked.
     */
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}