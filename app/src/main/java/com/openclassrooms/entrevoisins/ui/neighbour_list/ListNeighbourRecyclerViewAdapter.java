package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.events.AddNeighbourToFavoritesEvent;
import com.openclassrooms.entrevoisins.events.DeleteNeighbourEvent;
import com.openclassrooms.entrevoisins.events.RemoveNeighbourFromFavoritesEvent;
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

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_neighbour, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Neighbour neighbour = this.neighbours.get(position);
        holder.neighbourName.setText(neighbour.getName());
        Glide.with(holder.neighbourAvatar.getContext())
                .load(neighbour.getAvatarUrl())
                .apply(RequestOptions.circleCropTransform())
                .into(holder.neighbourAvatar);

        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new DeleteNeighbourEvent(neighbour));
            }
        });

        holder.addToFavoritesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(new AddNeighbourToFavoritesEvent(neighbour));
            }
        });

        holder.removeFromFavoritesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(new RemoveNeighbourFromFavoritesEvent(neighbour));
            }
        });

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

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            this.viewDetailButton.setOnClickListener(this);
        }

        private void toggleFavorites() {
            // TODO in RecyclerView adapter
        }

        private void setNeighbour(Neighbour neighbour) {
            this.neighbour = neighbour;
        }

        @Override
        public void onClick(View view) {
            if(view.equals(this.viewDetailButton)) {
                Intent intent = new Intent(view.getContext(), DetailNeighbourActivity.class);
                intent.putExtra("id", this.neighbour.getId());
                view.getContext().startActivity(intent);

            }
        }

    }

    // https://stackoverflow.com/questions/27559021/recyclerview-onlistitemclick-to-create-new-intent


}
