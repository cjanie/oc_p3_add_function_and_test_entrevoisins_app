package com.openclassrooms.entrevoisins.service;

import java.util.ArrayList;
import java.util.List;

public interface FavoriteHandler {

    void addToFavorites(Favorite favorite);

    void removeFromFavorites(Favorite favorite);

    List<Favorite> getFavorites();

}
