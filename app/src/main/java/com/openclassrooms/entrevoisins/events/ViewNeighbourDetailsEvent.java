package com.openclassrooms.entrevoisins.events;

import com.openclassrooms.entrevoisins.model.Neighbour;

public class ViewNeighbourDetailsEvent {

    public Neighbour neighbour;

    public ViewNeighbourDetailsEvent(Neighbour neighbour) {
        this.neighbour = neighbour;
    }
}
