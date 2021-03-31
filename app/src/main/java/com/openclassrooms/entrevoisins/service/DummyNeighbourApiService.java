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

    @Override
    public void addNeighbourToFavorites(Neighbour neighbour) {
        this.favoriteNeighbourService.addToFavorites(neighbour);
        System.out.println(this.favoriteNeighbourService.getFavorites().size());
    }

    @Override
    public void removeNeighbourFromFavorites(Neighbour neighbour) {
        this.favoriteNeighbourService.removeFromFavorites(neighbour);
        System.out.println(this.favoriteNeighbourService.getFavorites().size());
    }
}
