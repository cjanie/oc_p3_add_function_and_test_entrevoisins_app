package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Intent;
import android.support.annotation.NonNull;
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
import com.openclassrooms.entrevoisins.events.DeleteNeighbourEvent;
import com.openclassrooms.entrevoisins.events.RemoveNeighbourFromFavoritesEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListFavoriteRecyclerViewAdapter extends RecyclerView.Adapter<ListFavoriteRecyclerViewAdapter.ViewHolder>{

    private final List<Neighbour> favorites;

    public ListFavoriteRecyclerViewAdapter(List<Neighbour> favorites) {
        this.favorites = favorites;
    }

    @NonNull
    @Override
    public ListFavoriteRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_favorite_list_item, parent, false); // TODO fragment_favorite
        return new ListFavoriteRecyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int position) {
        if(!this.favorites.isEmpty()) {
            Neighbour favorite = this.favorites.get(position);
            viewHolder.favoriteName.setText(favorite.getName());
            Glide.with(viewHolder.favoriteAvatar.getContext())
                    .load(favorite.getAvatarUrl())
                    .apply(RequestOptions.circleCropTransform())
                    .into(viewHolder.favoriteAvatar);
            viewHolder.setFavorite(favorite);
        }

    }

    @Override
    public int getItemCount() {
        return this.favorites.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Neighbour favorite;

        @BindView(R.id.item_favorites_avatar)
        ImageView favoriteAvatar;
        @BindView(R.id.item_favorites_name)
        TextView favoriteName;
        @BindView(R.id.item_favorites_view_detail_button)
        ImageButton viewDetailButton;
        @BindView(R.id.item_favorites_remove_from_favorites_button)
        ImageButton removeFromFavoritesButton;
        @BindView(R.id.item_favorites_delete_neighbour_button)
        ImageButton deleteNeighbourButton;

        private void setFavorite(Neighbour neighbour) {
            this.favorite = neighbour;
        }

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.viewDetailButton.setOnClickListener(this);
            this.removeFromFavoritesButton.setOnClickListener(this);
            this.deleteNeighbourButton.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            if(view.equals(this.viewDetailButton)) {
                Intent intent = new Intent(view.getContext(), DetailNeighbourActivity.class);
                intent.putExtra("id", this.favorite.getId());
                view.getContext().startActivity(intent);
            } else if(view.equals(this.removeFromFavoritesButton)) {
                EventBus.getDefault().post(new RemoveNeighbourFromFavoritesEvent(this.favorite));
            } else if(view.equals(this.deleteNeighbourButton)) {
                EventBus.getDefault().post(new DeleteNeighbourEvent(this.favorite));
            }
        }
    }
}
