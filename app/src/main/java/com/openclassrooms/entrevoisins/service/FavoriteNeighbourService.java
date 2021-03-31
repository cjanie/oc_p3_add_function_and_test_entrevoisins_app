package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.List;

public class FavoriteNeighbourService implements FavoriteService {

    private List<Neighbour> favorites;

    public FavoriteNeighbourService() {
        this.resetFavoriteService();
    }
    @Override
    public void resetFavoriteService() { // TODO: Test
        this.favorites = new ArrayList<>();
    }

    @Override
    public void addToFavorites(Favorite favorite) { // TODO: Test
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
