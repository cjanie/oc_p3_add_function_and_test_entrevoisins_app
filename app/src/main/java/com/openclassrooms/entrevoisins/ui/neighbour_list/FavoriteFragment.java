package com.openclassrooms.entrevoisins.ui.neighbour_list;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.service.FavoriteNeighbourHandler;


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
    protected void initLayout() {
        this.layout = R.layout.fragment_favorite_list;
    }

    /**
     * To instantiate the list from FavoriteNeighbourHandler
     */
    @Override
    protected void initList() {
        this.list = FavoriteNeighbourHandler.getInstance().getFavoriteNeighbours();
    }
}
