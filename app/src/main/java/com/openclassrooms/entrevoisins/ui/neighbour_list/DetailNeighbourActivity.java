package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.events.AddNeighbourToFavoritesEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailNeighbourActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    private Neighbour neighbour;
    private NeighbourApiService neighbourApiService;


    @BindView(R.id.detail_neighbour_name)
    TextView neighbourName;
    @BindView(R.id.detail_neighbour_switch_favorite_toggle)
    Switch switchFavoriteToggle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.fragment_detail_neighbour);
        ButterKnife.bind(this); // way to instanciate the binded views

        this.neighbourApiService = DI.getNeighbourApiService(); // way to instanciate the api service
        this.initDataView();
        this.initFavoriteToggle();
        this.switchFavoriteToggle.setOnCheckedChangeListener(this);
    }

    /**
     * Get data from api
     *
     */
    private void initData() {
        if(this.getIntent() != null) {
            long id = this.getIntent().getLongExtra("id", 0); //TODO: Unit Test and Instrumented Test
            if( id > 0) {
                this.neighbour = neighbourApiService.getNeighbourById(id);
            }
        }
    }

    /**
     * Set data into the view
     */
    private void initDataView() {
        this.initData();
        if(this.neighbour != null) {
            this.neighbourName.setText(this.neighbour.getName());
        }
    }

    private void initFavoriteToggle() {
        if(this.neighbourApiService.getFavorites().contains(this.neighbour)) {
            this.switchFavoriteToggle.setChecked(true);
        } else {
            this.switchFavoriteToggle.setChecked(false);
        }
    }


    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        if(!isChecked) {
            neighbourApiService.removeFromFavorites(neighbour);
        } else {
            neighbourApiService.addToFavorites(neighbour);
        }
    }
}
