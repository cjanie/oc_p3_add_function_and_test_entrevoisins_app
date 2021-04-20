package com.openclassrooms.entrevoisins.ui.neighbour_list;


import com.openclassrooms.entrevoisins.R;

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
    protected void initLayout() {
        this.layout = R.layout.fragment_neighbour_list;
    }

    /**
     * Instantiate the list of neighbours from api
     */
    @Override
    protected void initList() {
        this.list = super.getNeighbourApiService().getNeighbours();
    }

}
