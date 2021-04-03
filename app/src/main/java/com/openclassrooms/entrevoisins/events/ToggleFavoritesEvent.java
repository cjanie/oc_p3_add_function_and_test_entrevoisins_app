package com.openclassrooms.entrevoisins.events;

import com.openclassrooms.entrevoisins.model.Neighbour;

public class ToggleFavoritesEvent {

    public Neighbour neighbour;

    public ToggleFavoritesEvent(Neighbour neighbour) {
        this.neighbour = neighbour;
    }

}
