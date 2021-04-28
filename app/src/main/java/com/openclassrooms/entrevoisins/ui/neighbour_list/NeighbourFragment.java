package com.openclassrooms.entrevoisins.ui.neighbour_list;


import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.List;

public class NeighbourFragment extends ListNeighbourFragment {

    /**
     * Create and return a new instance
     * @return @{@link NeighbourFragment}
     */
    public static NeighbourFragment newInstance() {
        NeighbourFragment fragment = new NeighbourFragment();
        return fragment;
    }

    /**
     * To define the layout to inflate in onCreateView
     */
    @Override
    protected int initLayout() {
        return R.layout.fragment_neighbour_list;
    }

    /**
     * Instantiate the list of neighbours from api
     */
    @Override
    protected List<Neighbour> initList() {
        return super.getNeighbourApiService().getNeighbours();
    }

}
