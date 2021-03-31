package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailNeighbourActivity extends AppCompatActivity {

    private NeighbourApiService mApiService;
    private Neighbour neighbour;

    @BindView(R.id.detail_neighbour_name)
    TextView neighbourName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_detail_neighbour);
        ButterKnife.bind(this); // way to instanciate the binded views

        this.mApiService = DI.getNeighbourApiService(); // way to instanciate the api service
        this.loadData();
        if(this.neighbour != null) {
            this.neighbourName.setText(this.neighbour.getName());
        }
    }

    private void loadData() {

        if(this.getIntent() != null) {
            long id = this.getIntent().getLongExtra("id", 5); //TODO: Modify
            if( id > 0) {
                this.neighbour = mApiService.getNeighbourById(id);
            }

        }

    }
}
