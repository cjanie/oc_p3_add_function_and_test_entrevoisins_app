package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.List;

public class FavoriteNeighbourHandler extends FavoriteHandlerImpl {

    private static FavoriteNeighbourHandler INSTANCE;

    private FavoriteNeighbourHandler() {
    }

    public static FavoriteNeighbourHandler getInstance() {
        if(FavoriteNeighbourHandler.INSTANCE == null) {
            FavoriteNeighbourHandler.INSTANCE = new FavoriteNeighbourHandler();
        }
        return FavoriteNeighbourHandler.INSTANCE;
    }

    public List<Neighbour> getFavoriteNeighbours() {
        List<Neighbour> favoriteNeighbours = new ArrayList<>();
        if(this.favorites != null) {
            for(int i=0; i<favorites.size(); i++) {
                if(favorites.get(i) instanceof Neighbour) {
                    favoriteNeighbours.add((Neighbour) favorites.get(i));
                }
            }
        }
        return favoriteNeighbours;
    }


}
