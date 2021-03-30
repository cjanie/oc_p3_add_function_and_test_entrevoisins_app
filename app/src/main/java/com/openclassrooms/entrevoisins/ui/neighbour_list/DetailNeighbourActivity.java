package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.openclassrooms.entrevoisins.R;

import butterknife.BindView;

public class DetailNeighbourActivity extends AppCompatActivity {

    @BindView(R.id.detail_neighbour_name)
    TextView neighbourName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_detail_neighbour);
        //this.neighbourName.setText("bbbb"); // null pointer neighbourName is null
    }
}
