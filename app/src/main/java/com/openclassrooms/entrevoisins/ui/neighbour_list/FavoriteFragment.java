package com.openclassrooms.entrevoisins.ui.neighbour_list;

import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.DummyNeighbourApiService;

import java.util.List;

public class FavoriteFragment extends ListFragment {

    private List<Neighbour> mFavorites;

    /**
     * Create and return a new instance
     * @return @{@link FavoriteFragment}
     */
    public static FavoriteFragment newInstance() {
        FavoriteFragment fragment = new FavoriteFragment();
        return fragment;
    }

    /**
     * Init the List of favorites
     * GET from ApiService
     */
    @Override
    protected void initList() {
        if(this.mApiService instanceof DummyNeighbourApiService) {
            DummyNeighbourApiService dummyService = (DummyNeighbourApiService) this.mApiService;
            this.mFavorites = dummyService.getNeighbourFavorites();
        }
        this.mRecyclerView.setAdapter(new MyNeighbourRecyclerViewAdapter(mFavorites));
    }
}
