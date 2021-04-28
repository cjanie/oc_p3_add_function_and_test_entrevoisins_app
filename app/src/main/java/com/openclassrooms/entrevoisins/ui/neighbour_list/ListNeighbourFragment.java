package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.events.DeleteNeighbourEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

public abstract class ListNeighbourFragment extends Fragment {

    private NeighbourApiService neighbourApiService;
    private RecyclerView recyclerView;

    /**
     * Getter for API
     * API is needed in only one child class.
     * I make it private to the super class and the child that needs it has to use the getter of the super class
     * @return
     */
    public NeighbourApiService getNeighbourApiService() {
        return this.neighbourApiService;
    }

    /**
     * initLayout
     * Abstract method to define the layout to inflate in onCreateView
     * One layout for each Child fragment. Otherwise integrated tests are failing with an AmbiguousViewMatcherException:
     * androidx.test.espresso.AmbiguousViewMatcherException: 'with id is <com.openclassrooms.entrevoisins:id/list_neighbours>' matches multiple views in the hierarchy.
     */
    protected abstract int initLayout();

    /**
     * initList
     * Abstract method to instantiate list for recycler view adapter
     */
    protected abstract List<Neighbour> initList();

    /**
     * initListView
     * Method to link the list to the view
     * Called in onResume
     * Also called to refresh the view after events have affected the API
     */
    private void initListView() {
        List<Neighbour> list = this.initList(); // instantiate list for recycler view adapter
        if(list != null) {
            this.recyclerView.setAdapter(new ListNeighbourRecyclerViewAdapter(list));
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.neighbourApiService = DI.getNeighbourApiService();
        this.initLayout(); // to define this.layout to inflate
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(this.initLayout(), container, false);
        Context context = view.getContext();
        this.recyclerView = (RecyclerView) view;
        this.recyclerView.setLayoutManager(new LinearLayoutManager(context));
        this.recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        this.initListView();
    }

    /**
     * Subscriptions
     */
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

}
