package com.openclassrooms.entrevoisins.service;

import java.util.List;

public interface FavoriteHandler {
    void resetFavoriteList();
    void addToFavorites(Favorite favorite);
    void removeFromFavorites(Favorite favorite);
    List<Favorite> getFavorites();

}
