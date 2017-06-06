package ca.umanitoba.cs.comp3350.saveonflight.business;

import java.util.ArrayList;

import ca.umanitoba.cs.comp3350.saveonflight.objects.Flight;

/**
 * SortFlights.java
 * <p>
 * Interface for the business logic of sorting Flights based on various parameters.
 *
 * @author Kenny Zhang
 */

interface SortFlights {
    // Sorts the List of Flights passed in based on the specified SortParameter.
    // If any elements are null, they  are placed at the end of the List.
    void sortFlightsBy(ArrayList<Flight> flights, SortParameter sortBy);

    enum SortParameter {
        DATE, AIRLINE, PRICE, CAPACITY, SEATS_AVAILABLE
    }
}
