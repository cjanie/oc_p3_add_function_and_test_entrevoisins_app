package com.openclassrooms.entrevoisins.events;

import com.openclassrooms.entrevoisins.model.Neighbour;

public class RemoveNeighbourFromFavoritesEvent {

    public Neighbour neighbour;

    public RemoveNeighbourFromFavoritesEvent(Neighbour neighbour) {
        this.neighbour = neighbour;
    }
}
