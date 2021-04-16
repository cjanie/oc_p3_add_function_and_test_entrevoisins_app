package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import static com.openclassrooms.entrevoisins.service.DummyNeighbourGenerator.DUMMY_NEIGHBOURS;

import java.util.stream.Collectors;

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

    private void fillFavoritesListWithNeighbours1And2() {
        this.favoriteNeighbourHandler.addToFavorites(this.neighbour1);
        this.favoriteNeighbourHandler.addToFavorites(this.neighbour2);
    }

    @Test
    public void favoriteListShouldNotBeNull() {
        Assert.assertNotNull(this.favoriteNeighbourHandler.getFavoriteNeighbours());
    }

    @Test
    public void getFavoritesWithSuccess() {
        this.fillFavoritesListWithNeighbours1And2();
        assert(this.favoriteNeighbourHandler.getFavoriteNeighbours().contains(this.neighbour1));
        assert(this.favoriteNeighbourHandler.getFavoriteNeighbours().contains(this.neighbour2));
        assert(this.favoriteNeighbourHandler.getFavoriteNeighbours().size() == 2);
    }

    @Test
    public void addNeighbourToFavoritesWithSuccess() {
        this.favoriteNeighbourHandler.addToFavorites(this.neighbour1);
        assert(this.favoriteNeighbourHandler.getFavoriteNeighbours().contains(this.neighbour1));
    }

    @Test
    public void favoriteNeighbourShouldNotBeAddedASecondTimeInTheList() {
        this.fillFavoritesListWithNeighbours1And2();
        this.favoriteNeighbourHandler.addToFavorites(neighbour1);
        assert(this.favoriteNeighbourHandler.getFavoriteNeighbours().size() == 2);
    }

    @Test
    public void removeNeighbourFromFavoritesWithSuccess() {
        this.fillFavoritesListWithNeighbours1And2();
        this.favoriteNeighbourHandler.removeFromFavorites(neighbour1);
        Assert.assertFalse(this.favoriteNeighbourHandler.getFavoriteNeighbours().contains(neighbour1));
        assert(this.favoriteNeighbourHandler.getFavoriteNeighbours().size() == 1);
    }

}
