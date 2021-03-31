package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.List;

public class FavoriteNeighbourHandler implements FavoriteHandler {

    private List<Neighbour> favorites;

    public FavoriteNeighbourHandler() {
        this.resetFavoriteList();
    }
    @Override
    public void resetFavoriteList() { // TODO: Test
        this.favorites = new ArrayList<>();
    }

    @Override
    public void addToFavorites(Favorite favorite) {
        if(!this.favorites.contains(favorite)) {
            this.favorites.add((Neighbour) favorite);
        }
    }

    @Override
    public void removeFromFavorites(Favorite favorite) { // TODO: Test
        if(this.favorites.contains(favorite)) {
            this.favorites.remove(favorite);
        }
    }

    @Override
    public List<Favorite> getFavorites() { // TODO: Test
        return new ArrayList<Favorite>(this.favorites);
    }
}
