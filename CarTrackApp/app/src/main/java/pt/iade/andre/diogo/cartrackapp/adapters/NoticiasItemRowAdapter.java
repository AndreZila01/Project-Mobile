package pt.iade.andre.diogo.cartrackapp.adapters;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import pt.iade.andre.diogo.cartrackapp.Models.ClassNews;
import pt.iade.andre.diogo.cartrackapp.R;
import com.squareup.picasso.Picasso;


public class NoticiasItemRowAdapter extends RecyclerView.Adapter<NoticiasItemRowAdapter.ViewHolder> {
    private ArrayList<ClassNews> items;
    private RelativeLayout pnl;
    private TextView txtTitle;
    private ImageView img;
    private TextView txtmsg;
    private LayoutInflater inflater;
    private ItemClickListener clickListener;

    public NoticiasItemRowAdapter(Context context, ArrayList<ClassNews> items) {
        inflater = LayoutInflater.from(context);
        this.items = items;
        clickListener = null;
    }

    /**
     * Sets the event listener when a row gets clicked by the user.
     *
     * @param listener Event handler for the click.
     */
    public void setOnClickListener(ItemClickListener listener) {
        clickListener = listener;
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
        View view = inflater.inflate(R.layout.row_news_item, parent, false);
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
        ClassNews item = items.get(position);

        Picasso.get().load(item.getUrlImg()).into(holder.imgp);

        holder.txtmsgp.setText(item.getTextOfNews());
        holder.txtTitlep.setText(item.getTitlesh());
    }

    /**
     * The RecyclerView needs to know the size of our list, this just provides that.
     *
     * @return Size of our data.
     */
    @Override
    public int getItemCount() {
        return items.size();
    }

    /**
     * Class responsible for recycling the views as you scroll.
     */
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public RelativeLayout pnlp;
        public TextView txtTitlep;
        public ImageView imgp;
        public View inflaterp;
        public TextView txtmsgp;


        /**
         * Sets up the view that was inflated.
         *
         * @param itemView Layout view that was inflated.
         */
        public ViewHolder(View itemView) {
            super(itemView);

            // Get the components in the view.
            pnlp = itemView.findViewById(R.id.pnlNoticias);
            txtTitlep = itemView.findViewById(R.id.txtTitulo);
            imgp = itemView.findViewById(R.id.imgNoticia);
            inflaterp = itemView.findViewById(R.id.pnlNoticias);
            txtmsgp = itemView.findViewById(R.id.txtMensagem);

            // Set what happens when the view gets clicked.
            itemView.setOnClickListener(this);
        }

        /**
         * Handles the onclick event of the view.
         */
        @Override
        public void onClick(View view) {
            // Pass the event to our custom listener in the activity.
            if (clickListener != null) {
                clickListener.onItemClick(view, getAdapterPosition());
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