package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;

import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.FavoriteNeighbourHandler;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

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
    FloatingActionButton favoriteButton;

    @BindView(R.id.info_neighbour_name)
    TextView infoNeighbourName;
    @BindView(R.id.info_neighbour_address)
    TextView infoNeighbourAddress;
    @BindView(R.id.info_neighbour_phone_number)
    TextView infoNeighbourPhoneNumber;
    @BindView(R.id.info_neighbour_website)
    TextView infoNeighbourWebsite;
    @BindView(R.id.about_me_neighbour)
    TextView aboutNeighbour;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_detail_neighbour);
        getSupportActionBar().hide();
        ButterKnife.bind(this); // To instantiate the bind views

        this.neighbourApiService = DI.getNeighbourApiService(); // To instantiate the API service
        this.initData(); // To get data from the API service
        if(this.neighbour != null) { // To set data to the bind views
            this.neighbourName.setText(this.neighbour.getName());
            Glide.with(this.neighbourImage.getContext())
                    .load(this.neighbour.getAvatarUrl()).into(neighbourImage);
            this.infoNeighbourName.setText(this.neighbour.getName());
            this.infoNeighbourAddress.setText(this.neighbour.getAddress());
            this.infoNeighbourPhoneNumber.setText(this.neighbour.getPhoneNumber());
            this.infoNeighbourWebsite.setText("www.facebook.fr/" + this.neighbour.getName().toLowerCase());
            this.aboutNeighbour.setText(this.neighbour.getAboutMe());
        }

        this.backButton.setOnClickListener(this);
        this.initFavoriteButton();
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

    private void initFavoriteButton() {
        if(FavoriteNeighbourHandler.getInstance().getFavoriteNeighbours().contains(this.neighbour)) {
            this.favoriteButton.setActivated(false);
        } else {
            this.favoriteButton.setActivated(true);
        }
    }

    @Override
    public void onClick(View view) {
        if(view.equals(this.backButton)) {
            Intent intent = new Intent(view.getContext(), ListNeighbourActivity.class);
            startActivity(intent);
            finish();
        } else if(view.equals(this.favoriteButton)) {
            if(this.favoriteButton.isActivated()) {
                FavoriteNeighbourHandler.getInstance().addToFavorites(this.neighbour);
                this.favoriteButton.setActivated(false);
            } else {
                FavoriteNeighbourHandler.getInstance().removeFromFavorites(this.neighbour);
                this.favoriteButton.setActivated(true);
            }

        }
    }


}
