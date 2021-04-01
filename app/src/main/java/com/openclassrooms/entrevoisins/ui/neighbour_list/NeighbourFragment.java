package com.openclassrooms.entrevoisins.ui.neighbour_list;


public class NeighbourFragment extends NeighboursListFragment {

    /**
     * Create and return a new instance
     * @return @{@link NeighbourFragment}
     */
    public static NeighbourFragment newInstance() {
        NeighbourFragment fragment = new NeighbourFragment();
        return fragment;
    }

    /**
     * Instantiate the list of neighbours from api
     */
    @Override
    protected void initList() {
        this.list = this.mApiService.getNeighbours();
    }
}
