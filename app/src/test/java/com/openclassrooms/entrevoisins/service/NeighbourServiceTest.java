package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static com.openclassrooms.entrevoisins.service.DummyNeighbourGenerator.DUMMY_NEIGHBOURS;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

/**
 * Unit test on Neighbour service
 */
@RunWith(JUnit4.class)
public class NeighbourServiceTest {

    private NeighbourApiService service;

    private FavoriteHandler favoriteHandler; // Needed to test delete neighbour since a deleted neighbour should be removed from the favorites list

    // To test delete neighbour at index 0
    private Neighbour neighbourAtIndex0;

    @Before
    public void setup() {
        this.service = DI.getNewInstanceApiService();
        this.favoriteHandler = FavoriteNeighbourHandler.getInstance();
        this.neighbourAtIndex0 = this.service.getNeighbours().get(0);
        this.favoriteHandler.addToFavorites(neighbourAtIndex0); // the neighbour is in the list of favorites
    }

    @Test
    public void getNeighboursWithSuccess() {
        List<Neighbour> neighbours = service.getNeighbours();
        List<Neighbour> expectedNeighbours = DUMMY_NEIGHBOURS;
        assertThat(neighbours, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedNeighbours.toArray()));
    }

    @Test
    public void deleteNeighbourWithSuccess() {
        service.deleteNeighbour(this.neighbourAtIndex0); // When the neighbour is deleted,
        assertFalse(service.getNeighbours().contains(this.neighbourAtIndex0)); // check that it is removed from the list of neighbours
        assertFalse(favoriteHandler.getFavorites().contains(this.neighbourAtIndex0)); // check that it is removed from the list of favorites
    }

    @Test
    public void getNeighbourByIdWithSuccess() {
        Neighbour neighbourToGet = service.getNeighbours().get(0);
        assert(service.getNeighbourById(1).equals(neighbourToGet));
    }
}
