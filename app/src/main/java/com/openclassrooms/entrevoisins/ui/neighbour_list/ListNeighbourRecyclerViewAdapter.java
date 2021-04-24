package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.events.DeleteNeighbourEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListNeighbourRecyclerViewAdapter extends RecyclerView.Adapter<ListNeighbourRecyclerViewAdapter.ViewHolder> {

    private final List<Neighbour> neighbours;

    public ListNeighbourRecyclerViewAdapter(List<Neighbour> neighbours) {
        this.neighbours = neighbours;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_neighbour_list_item, parent, false);
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
        @BindView(R.id.item_list_delete_button)
        public ImageButton deleteButton;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view); // to instantiate the views
            this.neighbourAvatar.setOnClickListener(this);
            this.neighbourName.setOnClickListener(this);
            this.deleteButton.setOnClickListener(this);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), DetailNeighbourActivity.class);
                    intent.putExtra("id", neighbour.getId());
                    view.getContext().startActivity(intent);
                }
            });
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
            if(view.equals(this.neighbourAvatar) || view.equals(this.neighbourName)) {
                Intent intent = new Intent(view.getContext(), DetailNeighbourActivity.class);
                intent.putExtra("id", this.neighbour.getId());
                view.getContext().startActivity(intent);
            } else if(view.equals(this.deleteButton)) {
                EventBus.getDefault().post(new DeleteNeighbourEvent(this.neighbour));
            }
        }

    }
}
