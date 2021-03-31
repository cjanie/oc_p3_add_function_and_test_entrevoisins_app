package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.List;

public interface FavoriteService {
    void resetService();
    void addToFavorites(Favorite favorite);
    void removeFromFavorites(Favorite favorite);
    List<Favorite> getFavorites();
}
