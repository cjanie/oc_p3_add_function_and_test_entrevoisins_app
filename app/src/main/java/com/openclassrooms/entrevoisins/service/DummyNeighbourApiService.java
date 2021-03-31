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
        // TODO: use a Favorites service
    }

    @Override
    public void removeNeighbourFromFavorites(Neighbour neighbour) {
        // TODO: use the Favorites service
    }
}
