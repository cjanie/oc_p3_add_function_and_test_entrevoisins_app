package com.openclassrooms.entrevoisins.ui.neighbour_list;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.FavoriteNeighbourHandler;

import java.util.List;


public class FavoriteFragment extends ListNeighbourFragment {

    /**
     * To create and return a new instance
     * @return @{@link FavoriteFragment}
     */
    public static FavoriteFragment newInstance() {
        FavoriteFragment fragment = new FavoriteFragment();
        return fragment;
    }

    /**
     * To define the layout to inflate in onCreateView
     */
    @Override
    protected int initLayout() {
        return R.layout.fragment_favorite_list;
    }

    /**
     * To instantiate the list from FavoriteNeighbourHandler
     */
    @Override
    protected List<Neighbour> initList() {
        return FavoriteNeighbourHandler.getInstance().getFavoriteNeighbours();
    }
}
