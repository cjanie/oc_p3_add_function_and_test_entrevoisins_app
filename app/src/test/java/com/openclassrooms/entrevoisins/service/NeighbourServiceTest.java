package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;
import java.util.stream.Collectors;

import static com.openclassrooms.entrevoisins.service.DummyNeighbourGenerator.DUMMY_NEIGHBOURS;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Unit test on Neighbour service
 */
@RunWith(JUnit4.class)
public class NeighbourServiceTest {

    private NeighbourApiService service;

    @Before
    public void setup() {
        this.service = DI.getNewInstanceApiService();
    }

    @Test
    public void getNeighboursWithSuccess() {
        List<Neighbour> neighbours = service.getNeighbours();
        List<Neighbour> expectedNeighbours = DUMMY_NEIGHBOURS;
        assertThat(neighbours, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedNeighbours.toArray()));
    }

    @Test
    public void deleteNeighbourWithSuccess() {
        Neighbour neighbourToDelete = service.getNeighbours().get(0);
        service.deleteNeighbour(neighbourToDelete);
        assertFalse(service.getNeighbours().contains(neighbourToDelete));
        //assertFalse(service.getFavorites().contains(neighbourToDelete));
    }

    @Test
    public void getNeighbourByIdWithSuccess() {
        Neighbour neighbourToGet = service.getNeighbours().get(0);
        service.getNeighbourById(neighbourToGet.getId());
        assert(service.getNeighbourById(neighbourToGet.getId()).equals(neighbourToGet));
    }
/*
    @Test
    public void GetFavoritesWithSuccess() {
        List<Neighbour> favorites = service.getFavorites();
        assertNotNull(favorites);
        assertTrue(favorites.isEmpty());
        Neighbour neighbour = service.getNeighbours().get(0);

        // Test add to favorites
        service.addToFavorites(neighbour);
        favorites = service.getFavorites();
        assert(favorites.contains(neighbour));
        assertEquals(1, favorites.size());

        // If neighbour is already in the list of favorites, it has not be added again
        service.addToFavorites(neighbour);
        favorites = service.getFavorites();
        assertEquals(1, favorites.size());

        // Check data of added favorite
        assertTrue(DUMMY_NEIGHBOURS.stream().map(Neighbour::getAvatarUrl).collect(Collectors.toList()).contains(neighbour.getAvatarUrl()));
        assertTrue(DUMMY_NEIGHBOURS.stream().map(Neighbour::getId).collect(Collectors.toList()).contains(neighbour.getId()));
        assertTrue(DUMMY_NEIGHBOURS.stream().map(Neighbour::getName).collect(Collectors.toList()).contains(neighbour.getName()));

        // Test remove from favorites
        service.removeFromFavorites(neighbour);
        favorites = service.getFavorites();
        assertFalse(favorites.contains(neighbour));
        assert(favorites.isEmpty());
    }

 */
}
