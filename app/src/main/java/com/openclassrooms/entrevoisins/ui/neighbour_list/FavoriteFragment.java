package com.openclassrooms.entrevoisins.ui.neighbour_list;

import com.openclassrooms.entrevoisins.service.DummyNeighbourApiService;

public class FavoriteFragment extends ListNeighbourFragment {

    /**
     * Create and return a new instance
     * @return @{@link FavoriteFragment}
     */
    public static FavoriteFragment newInstance() {
        FavoriteFragment fragment = new FavoriteFragment();
        return fragment;
    }

    /**
     * Instantiate the list of favorites from API
     * Link the list to the view using adapter
     */
    @Override
    protected void initList() {
        if(this.neighbourApiService instanceof DummyNeighbourApiService) {
            DummyNeighbourApiService dummyService = (DummyNeighbourApiService) this.neighbourApiService;
            this.list = dummyService.getNeighbourFavorites();
        }
    }
}
