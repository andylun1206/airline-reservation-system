package ca.umanitoba.cs.comp3350.saveonflight.business;

import java.util.ArrayList;

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
     * Sorts the list of Flights passed in based on the specified SortParameter.
     * @param flights List of Flights to sort
     * @param sortBy Field to sort Flights by
     */
    void sortFlightsBy(ArrayList<Flight> flights, SortParameter sortBy);

    enum SortParameter {
        DATE, AIRLINE, PRICE, CAPACITY, SEATS_AVAILABLE, DURATION
    }
}
