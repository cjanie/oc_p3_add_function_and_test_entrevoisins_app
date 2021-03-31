package com.openclassrooms.entrevoisins.ui.neighbour_list;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.List;


public class NeighbourFragment extends ListFragment {

    private List<Neighbour> mNeighbours;

    /**
     * Create and return a new instance
     * @return @{@link NeighbourFragment}
     */
    public static NeighbourFragment newInstance() {
        NeighbourFragment fragment = new NeighbourFragment();
        return fragment;
    }

    /**
     * Init the List of neighbours
     */
    @Override
    protected void initList() {
        this.mNeighbours = this.mApiService.getNeighbours();
        this.mRecyclerView.setAdapter(new MyNeighbourRecyclerViewAdapter(this.mNeighbours));
    }
}
