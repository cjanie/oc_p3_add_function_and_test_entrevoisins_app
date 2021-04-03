package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.events.AddNeighbourToFavoritesEvent;
import com.openclassrooms.entrevoisins.events.DeleteNeighbourEvent;
import com.openclassrooms.entrevoisins.events.RemoveNeighbourFromFavoritesEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

public abstract class ListNeighbourFragment extends Fragment {

    protected List<Neighbour> list;
    protected NeighbourApiService neighbourApiService;
    protected RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        neighbourApiService = DI.getNeighbourApiService();
    }




    /**
     * initList
     * Abstract method to instantiate the list, getting the related list from API
     */
    protected abstract void initList();

    /**
     * initListView
     * Method to link the list to the view
     * Called in onResume
     * Also called to refresh the view after events have affected the API
     *
     */
    private void initListView() {
        this.initList(); // instantiate the list
        this.recyclerView.setAdapter(new ListNeighbourRecyclerViewAdapter(this.list));
    }

    @Override
    public void onResume() {
        super.onResume();
        this.initListView();
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    /**
     * Fired if the user clicks on a delete button
     * @param event
     */
    @Subscribe
    public void onDeleteNeighbour(DeleteNeighbourEvent event) {
        neighbourApiService.deleteNeighbour(event.neighbour);
        this.initListView(); // to refresh the view after deleting
    }

    @Subscribe
    public void onAddNeighbourToFavorites(AddNeighbourToFavoritesEvent event) {
        neighbourApiService.addToFavorites(event.neighbour);
        this.initListView(); // to refresh the view after adding
    }

    @Subscribe
    public void onRemoveNeighbourFromFavorites(RemoveNeighbourFromFavoritesEvent event) { // TODO Test
        neighbourApiService.removeFromFavorites(event.neighbour);
        this.initListView(); // to refresh the view after removing
    }
}
