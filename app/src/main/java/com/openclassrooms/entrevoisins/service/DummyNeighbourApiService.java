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

    public void addNeighbourToFavorites(Neighbour neighbour) {
        this.addToFavorites(neighbour);
    }

    public void removeNeighbourFromFavorites(Neighbour neighbour) {
        this.removeNeighbourFromFavorites(neighbour);
    }

    public List<Neighbour> getNeighbourFavorites() {
        List<Neighbour> neighbourFavorites = new ArrayList<>();
        List<Favorite> favorites = this.favoriteNeighbourHandler.getFavorites();
        if(favorites.size() > 0) {
            for(int i=0; i<favorites.size(); i++) {
                if(favorites.get(i) instanceof Neighbour) {
                    neighbourFavorites.add((Neighbour)favorites.get(i));
                }
            }
        }
        return neighbourFavorites;
    }

    @Override
    public void resetFavoriteList() {
        this.favoriteNeighbourHandler.resetFavoriteList();
    }

    @Override
    public void addToFavorites(Favorite favorite) {
        this.favoriteNeighbourHandler.addToFavorites(favorite);
    }

    @Override
    public void removeFromFavorites(Favorite favorite) {
        this.favoriteNeighbourHandler.removeFromFavorites(favorite);
    }

    @Override
    public List<Favorite> getFavorites() {
        return this.favoriteNeighbourHandler.getFavorites();
    }
}
