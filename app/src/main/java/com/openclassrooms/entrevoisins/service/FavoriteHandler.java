package com.openclassrooms.entrevoisins.service;

import android.arch.lifecycle.MutableLiveData;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.List;

public interface FavoriteHandler {
    void resetFavoriteList();
    void addToFavorites(Favorite favorite);
    void removeFromFavorites(Favorite favorite);
    List<Favorite> getFavorites();
}
