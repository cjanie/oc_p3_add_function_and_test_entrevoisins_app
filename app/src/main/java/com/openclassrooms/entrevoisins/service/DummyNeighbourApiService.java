package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements  NeighbourApiService {

    private List<Neighbour> neighbours;

    /**
     * Favorite handler is needed in the delete method:
     * Deleted neighbour has to be removed from the favorites list as well
     */
    private FavoriteNeighbourHandler favoriteNeighbourHandler;


    public DummyNeighbourApiService() {
        this.neighbours = DummyNeighbourGenerator.generateNeighbours();
        this.favoriteNeighbourHandler = FavoriteNeighbourHandler.getInstance();
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getNeighbours() {
        return this.neighbours;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteNeighbour(Neighbour neighbour) {
        neighbours.remove(neighbour);
        this.favoriteNeighbourHandler.removeFromFavorites(neighbour);

    }

    /**
     * {@inheritDoc}
     * @param neighbour
     */
    @Override
    public void createNeighbour(Neighbour neighbour) {
        this.neighbours.add(neighbour);
    }


    /**
     *
     * @param id
     * @return
     */
    @Override
    public Neighbour getNeighbourById(long id) {
        Neighbour neighbour = null;
        for(int i=0; i<this.neighbours.size(); i++) {
            if(this.neighbours.get(i).getId() == id) {
                neighbour = this.neighbours.get(i);
                break;
            }
        }
        return neighbour;
    }

}
