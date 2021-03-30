package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements  NeighbourApiService {

    private List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();


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

    @Override
    public Neighbour getNeighbour(Neighbour neighbour) {
        // unit test OK
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
    public void addNeighbourToFavorites(Neighbour neighbour) {
        // TODO
    }

    @Override
    public void removeNeighbourFromFavorites(Neighbour neighbour) {
        // TODO
    }
}
