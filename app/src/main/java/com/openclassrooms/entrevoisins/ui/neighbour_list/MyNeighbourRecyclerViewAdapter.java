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
import com.openclassrooms.entrevoisins.events.AddNeighbourToFavoritesEvent;
import com.openclassrooms.entrevoisins.events.DeleteNeighbourEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyNeighbourRecyclerViewAdapter extends RecyclerView.Adapter<MyNeighbourRecyclerViewAdapter.ViewHolder> {

    private final List<Neighbour> mNeighbours;

    public MyNeighbourRecyclerViewAdapter(List<Neighbour> items) {
        mNeighbours = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_neighbour, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Neighbour neighbour = mNeighbours.get(position);
        holder.mNeighbourName.setText(neighbour.getName());
        Glide.with(holder.mNeighbourAvatar.getContext())
                .load(neighbour.getAvatarUrl())
                .apply(RequestOptions.circleCropTransform())
                .into(holder.mNeighbourAvatar);

        holder.mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new DeleteNeighbourEvent(neighbour));
            }
        });

        holder.mAddToFavoritesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(new AddNeighbourToFavoritesEvent(neighbour));
            }
        });

        holder.setNeighbour(neighbour);


    }

    @Override
    public int getItemCount() {
        return mNeighbours.size();
    }

    @Override
    public long getItemId(int position) {
        return this.mNeighbours.get(position).getId();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.item_list_avatar)
        public ImageView mNeighbourAvatar;
        @BindView(R.id.item_list_name)
        public TextView mNeighbourName;
        @BindView(R.id.item_list_view_details_button)
        public ImageButton mViewDetailsButton;
        @BindView(R.id.item_list_add_to_favorites_button)
        public ImageButton mAddToFavoritesButton;
        @BindView(R.id.item_list_delete_button)
        public ImageButton mDeleteButton;

        private Neighbour neighbour;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            mViewDetailsButton.setOnClickListener(this);
        }

        public void setNeighbour(Neighbour neighbour) {
            this.neighbour = neighbour;
        }

        @Override
        public void onClick(View view) {
            if(view.equals(mViewDetailsButton)) {
                Intent intent = new Intent(view.getContext(), DetailNeighbourActivity.class);
                intent.putExtra("id", this.neighbour.getId());
                view.getContext().startActivity(intent);
            }
        }

    }

    // https://stackoverflow.com/questions/27559021/recyclerview-onlistitemclick-to-create-new-intent


}
