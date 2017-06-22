package ca.umanitoba.cs.comp3350.saveonflight.business;

import java.util.ArrayList;
import java.util.Comparator;

import ca.umanitoba.cs.comp3350.saveonflight.objects.Flight;

/**
 * SortFlights.java
 * <p>
 * Interface for the business logic of sorting Flights.
 *
 * @author Kenny Zhang
 */

public interface SortFlights {

    /**
     * Sorts the list of Flights passed in based on the specified SortParameter. List elements will
     * invalid fields and null elements are placed at the end of the list.
     *
     * @param flights List of Flights to sort
     * @param comparator Comparator to be used for sorting the list of Flights
     */
    void sortFlightsBy(ArrayList<Flight> flights, Comparator<Flight> comparator);
}
