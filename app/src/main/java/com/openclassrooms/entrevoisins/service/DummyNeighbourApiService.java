package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements  NeighbourApiService {

    private List<Neighbour> neighbours;

    private FavoriteNeighbourService favoriteNeighbourService;

    public DummyNeighbourApiService() {
        this.neighbours = DummyNeighbourGenerator.generateNeighbours();
        this.favoriteNeighbourService = new FavoriteNeighbourService();
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
    }

    /**
     * {@inheritDoc}
     * @param neighbour
     */
    @Override
    public void createNeighbour(Neighbour neighbour) {
        neighbours.add(neighbour);
    }


    public Neighbour getNeighbour(Neighbour neighbour) {
        // unit test OK TODO: remove method after writing unit test for getNeighbourgById(long id)
        Neighbour n = null;
        for(int i=0; i<this.neighbours.size(); i++) {
            if(this.neighbours.get(i).equals(neighbour)) {
                n = this.neighbours.get(i);
                break;
            }
        }
        System.out.println(n.getName());
        return n;
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
        List<Favorite> favorites = this.favoriteNeighbourService.getFavorites();
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
    public void resetFavoriteService() {
        this.favoriteNeighbourService.resetFavoriteService();
    }

    @Override
    public void addToFavorites(Favorite favorite) {
        this.favoriteNeighbourService.addToFavorites(favorite);
        System.out.println(this.favoriteNeighbourService.getFavorites().size()); // TODO test & remove
    }

    @Override
    public void removeFromFavorites(Favorite favorite) {
        this.favoriteNeighbourService.removeFromFavorites(favorite);
        System.out.println(this.favoriteNeighbourService.getFavorites().size()); // TODO test & remove
    }

    @Override
    public List<Favorite> getFavorites() {
        return this.favoriteNeighbourService.getFavorites();
    }
}
