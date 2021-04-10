package com.openclassrooms.entrevoisins.service;

import java.util.ArrayList;
import java.util.List;

public class FavoriteHandlerImpl implements FavoriteHandler {

    protected List<Favorite> favorites;

    public FavoriteHandlerImpl() {
        this.favorites = new ArrayList<>();
    }

    @Override
    public void addToFavorites(Favorite favorite) {
        if(!this.favorites.contains(favorite)) {
            this.favorites.add(favorite);
        }
    }

    @Override
    public void removeFromFavorites(Favorite favorite) {
        this.favorites.remove(favorite);
    }

    @Override
    public List<Favorite> getFavorites() {
        return this.favorites;
    }
}
