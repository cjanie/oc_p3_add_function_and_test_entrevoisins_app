package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Intent;
import android.graphics.Color;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.events.AddNeighbourToFavoritesEvent;
import com.openclassrooms.entrevoisins.events.DeleteNeighbourEvent;
import com.openclassrooms.entrevoisins.events.RemoveNeighbourFromFavoritesEvent;
import com.openclassrooms.entrevoisins.events.ToggleFavoriteEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListNeighbourRecyclerViewAdapter extends RecyclerView.Adapter<ListNeighbourRecyclerViewAdapter.ViewHolder> {

    private final List<Neighbour> neighbours;

    public ListNeighbourRecyclerViewAdapter(List<Neighbour> items) {
        this.neighbours = items;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_neighbour, parent, false); // TODO fragment_favorite
        return new ListNeighbourRecyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Neighbour neighbour = this.neighbours.get(position);
        holder.neighbourName.setText(neighbour.getName());
        Glide.with(holder.neighbourAvatar.getContext())
                .load(neighbour.getAvatarUrl())
                .apply(RequestOptions.circleCropTransform())
                .into(holder.neighbourAvatar);

        holder.setNeighbour(neighbour);
    }

    @Override
    public int getItemCount() {
        return this.neighbours.size();
    }

    @Override
    public long getItemId(int position) {
        return this.neighbours.get(position).getId();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        /**
         * One neighbour from the list of the recycler view adapter
         * Neighbour is set from the recycler view adapter
         * Neighbour data is needed to implement all click actions on this neighbour
         */
        private Neighbour neighbour;

        @BindView(R.id.item_list_avatar)
        public ImageView neighbourAvatar;
        @BindView(R.id.item_list_name)
        public TextView neighbourName;
        @BindView(R.id.item_list_view_detail_button)
        public ImageButton viewDetailButton;
        @BindView(R.id.item_list_delete_button)
        public ImageButton deleteButton;
        @BindView(R.id.item_list_add_to_favorites_button)
        public ImageButton addToFavoritesButton;
        @BindView(R.id.item_list_remove_from_favorites_button)
        public ImageButton removeFromFavoritesButton;

        private boolean isChecked;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view); // to instantiate the views
            this.viewDetailButton.setOnClickListener(this);
            this.deleteButton.setOnClickListener(this);
            this.addToFavoritesButton.setOnClickListener(this);
            this.removeFromFavoritesButton.setOnClickListener(this);
        }

        /**
         * Setter for neighbour
         * @param neighbour
         */
        private void setNeighbour(Neighbour neighbour) {
            this.neighbour = neighbour;
        }

        @Override
        public void onClick(View view) {
            if(view.equals(this.viewDetailButton)) {
                Intent intent = new Intent(view.getContext(), DetailNeighbourActivity.class);
                intent.putExtra("id", this.neighbour.getId());
                view.getContext().startActivity(intent);
            } else if(view.equals(this.deleteButton)) {
                EventBus.getDefault().post(new DeleteNeighbourEvent(this.neighbour));
            } else if(view.equals((this.addToFavoritesButton))) {
                EventBus.getDefault().post(new AddNeighbourToFavoritesEvent(this.neighbour));
            } else if(view.equals(this.removeFromFavoritesButton)) {
                EventBus.getDefault().post(new RemoveNeighbourFromFavoritesEvent(this.neighbour));
            }
        }
    }
}
