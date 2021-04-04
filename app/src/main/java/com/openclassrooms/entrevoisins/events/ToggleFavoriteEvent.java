package com.openclassrooms.entrevoisins.events;

import com.openclassrooms.entrevoisins.model.Neighbour;

public class ToggleFavoriteEvent {

    public Neighbour neighbour;

    public ToggleFavoriteEvent(Neighbour neighbour) {
        this.neighbour = neighbour;
    }

}
