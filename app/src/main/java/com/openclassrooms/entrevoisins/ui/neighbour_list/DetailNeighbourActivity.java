package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;

import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.FavoriteNeighbourHandler;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailNeighbourActivity extends AppCompatActivity implements View.OnClickListener {

    private Neighbour neighbour;
    private NeighbourApiService neighbourApiService;


    @BindView((R.id.detail_neighbour_image))
    ImageView neighbourImage;
    @BindView(R.id.detail_neighbour_name)
    TextView neighbourName;
    @BindView(R.id.detail_neighbour_button_back)
    ImageView backButton;
    @BindView(R.id.detail_neighbour_favorite_button)
    ImageView favoriteButton;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.fragment_detail_neighbour);
        ButterKnife.bind(this); // To instantiate the bind views

        this.neighbourApiService = DI.getNeighbourApiService(); // To instantiate the API service
        this.initData(); // To get data from the API service
        if(this.neighbour != null) { // To set data to the bind views
            this.neighbourName.setText(this.neighbour.getName());
            Glide.with(this.neighbourImage.getContext())
                    .load(this.neighbour.getAvatarUrl()).into(neighbourImage);
        }

        this.backButton.setOnClickListener(this);
        this.favoriteButton.setOnClickListener(this);
    }

    private void initData() {
        if(this.getIntent() != null) {
            long id = this.getIntent().getLongExtra("id", 0); //TODO: Unit Test and Instrumented Test
            if( id > 0) {
                this.neighbour = neighbourApiService.getNeighbourById(id);
            }
        }
    }

    @Override
    public void onClick(View view) {
        if(view.equals(this.backButton)) {
            Intent intent = new Intent(view.getContext(), ListNeighbourActivity.class);
            startActivity(intent);
            finish();
        } else if(view.equals(this.favoriteButton)) {
            FavoriteNeighbourHandler.getInstance().addToFavorites(this.neighbour);
        }
    }


}
