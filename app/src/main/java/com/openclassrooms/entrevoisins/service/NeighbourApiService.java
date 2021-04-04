package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.List;


/**
 * Neighbour API client
 */
public interface NeighbourApiService {

    /**
     * Get all my Neighbours
     * @return {@link List}
     */
    List<Neighbour> getNeighbours();

    /**
     * Deletes a neighbour
     * @param neighbour
     */
    void deleteNeighbour(Neighbour neighbour);

    /**
     * Create a neighbour
     * @param neighbour
     */
    void createNeighbour(Neighbour neighbour);

    /**
     * Get a neighbour by id
     * @param id
     * @return
     */
    Neighbour getNeighbourById(long id);

    /**
     * Add a neighbour to the favorites
     * @param neighbour
     */
    void addToFavorites(Neighbour neighbour);

    /**
     * Remove a neighbour from the favorites
     * @param neighbour
     */
    void removeFromFavorites(Neighbour neighbour);

    /**
     * Get the favorites of neighbours
     * @return
     */
    List<Neighbour> getFavorites();


}
