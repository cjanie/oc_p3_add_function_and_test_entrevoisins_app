package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.List;

@RunWith(JUnit4.class)
public class FavoriteNeighbourHandlerTest {

    private FavoriteNeighbourHandler favoriteNeighbourHandler;

    private Neighbour neighbour1;
    private Neighbour neighbour2;

    @Before
    public void setUp() {
        this.favoriteNeighbourHandler = FavoriteNeighbourHandler.getInstance();
        this.neighbour1 = DI.getNeighbourApiService().getNeighbourById(1);
        this.neighbour2 = DI.getNeighbourApiService().getNeighbourById(2);
    }

    @Test
    public void addNeighbourToFavoritesWithSuccess() {
        // Add a favorite
        this.favoriteNeighbourHandler.addToFavorites(this.neighbour1);
        // Check that the favorites list contains the favorite
        assert(this.favoriteNeighbourHandler.getFavoriteNeighbours().contains(this.neighbour1));
        // Add the same favorite
        this.favoriteNeighbourHandler.addToFavorites(this.neighbour1);
        // Since the favorite is already in the list, it should not appear a second time in the list
        assert(this.favoriteNeighbourHandler.getFavoriteNeighbours().size() == 1);
        // Add a new favorite
        this.favoriteNeighbourHandler.addToFavorites(neighbour2);
        assert(this.favoriteNeighbourHandler.getFavoriteNeighbours().contains(neighbour2));
        assert(this.favoriteNeighbourHandler.getFavoriteNeighbours().size() == 2);
    }

    @Test
    public void removeNeighbourFromFavoritesWithSuccess() {
        this.favoriteNeighbourHandler.addToFavorites(this.neighbour1);
        this.favoriteNeighbourHandler.addToFavorites(this.neighbour2);
        this.favoriteNeighbourHandler.removeFromFavorites(neighbour1);
        Assert.assertFalse(favoriteNeighbourHandler.getFavoriteNeighbours().contains(neighbour1));
        this.favoriteNeighbourHandler.removeFromFavorites(neighbour1);
    }

    @Test
    public void getFavoriteNeighboursWithSuccess() {
        Assert.assertNotNull(this.favoriteNeighbourHandler.getFavorites());
        this.favoriteNeighbourHandler.addToFavorites(this.neighbour1);
        this.favoriteNeighbourHandler.addToFavorites(this.neighbour1);
        assert(this.favoriteNeighbourHandler.getFavoriteNeighbours().contains(this.neighbour1));
        assert(this.favoriteNeighbourHandler.getFavoriteNeighbours().size() == 1);
        this.favoriteNeighbourHandler.removeFromFavorites(neighbour1);
        assert(this.favoriteNeighbourHandler.getFavoriteNeighbours().isEmpty());
        this.favoriteNeighbourHandler.removeFromFavorites(this.neighbour2);
    }
}
