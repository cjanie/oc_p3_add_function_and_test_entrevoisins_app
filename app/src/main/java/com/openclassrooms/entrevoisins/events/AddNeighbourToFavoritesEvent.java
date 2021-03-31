package com.openclassrooms.entrevoisins.events;

import com.openclassrooms.entrevoisins.model.Neighbour;

public class AddNeighbourToFavoritesEvent {

    public Neighbour neighbour;

    public AddNeighbourToFavoritesEvent(Neighbour neighbour) {
        this.neighbour = neighbour;
    }
}
