package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements  NeighbourApiService {

    private List<Neighbour> neighbours;

    private FavoriteNeighbourHandler favoriteNeighbourHandler;


    public DummyNeighbourApiService() {
        this.neighbours = DummyNeighbourGenerator.generateNeighbours();
        this.favoriteNeighbourHandler = new FavoriteNeighbourHandler();
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getNeighbours() {
        return neighbours;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteNeighbour(Neighbour neighbour) {
        neighbours.remove(neighbour);
        if(this.getFavorites().contains(neighbour)) {
            this.removeFromFavorites(neighbour);
        }

    }

    /**
     * {@inheritDoc}
     * @param neighbour
     */
    @Override
    public void createNeighbour(Neighbour neighbour) {
        neighbours.add(neighbour);
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

    // Methods to handle favorites


    /**
     *
     * @param neighbour
     */
    @Override
    public void addToFavorites(Neighbour neighbour) {
        this.favoriteNeighbourHandler.addToFavorites(neighbour);
    }

    /**
     *
     * @param neighbour
     */
    @Override
    public void removeFromFavorites(Neighbour neighbour) {
        this.favoriteNeighbourHandler.removeFromFavorites(neighbour);
    }

    /**
     *
     * @return
     */
    @Override
    public List<Neighbour> getFavorites() {
        List<Neighbour> favorites = new ArrayList<>();
        for(int i=0; i<this.favoriteNeighbourHandler.getFavorites().size(); i++) {
            if(this.favoriteNeighbourHandler.getFavorites().get(i) instanceof Neighbour) {
                favorites.add((Neighbour) this.favoriteNeighbourHandler.getFavorites().get(i));
            }
        }
        return favorites;
    }
}
