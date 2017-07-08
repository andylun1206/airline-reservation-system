package ca.umanitoba.cs.comp3350.saveonflight.business;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import ca.umanitoba.cs.comp3350.saveonflight.business.comparators.DepartureTimeComparator;
import ca.umanitoba.cs.comp3350.saveonflight.business.comparators.DurationComparator;
import ca.umanitoba.cs.comp3350.saveonflight.business.comparators.PriceComparator;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Flight;

/**
 * SortFlightsImpl.java
 * <p>
 * Implementation of the business logic of sorting Flights based on various parameters.
 *
 * @author Kenny Zhang
 */

public class SortFlightsImpl implements SortFlights {

    /**
     * Sorts the list of Flights passed in based on the specified SortParameter. List elements with
     * invalid fields will be placed at the end of the list.
     *
     * @param flights List of Flights to sort
     * @param sortBy  Field to sort Flights by
     */
    public void sortFlightsBy(ArrayList<Flight> flights, SortParameter sortBy) {
        if (flights == null) {
            return;
        }
        // Remove any null objects from the list prior to sorting
        int numNull = validateInput(flights);

        Comparator<Flight> comparator = null;

        switch (sortBy) {
            case TIME:
                comparator = new DepartureTimeComparator();
                break;
            case DURATION:
                comparator = new DurationComparator();
                break;
            case PRICE:
                comparator = new PriceComparator();
                break;
        }

        Collections.sort(flights, comparator);
        // Re-insertBookedFlight the null elements to the end of the list
        for (int i = 0; i < numNull; i++) {
            flights.add(null);
        }
    }

    /**
     * Checks each Flight in the list passed in for validity.
     *
     * @param flights The list of Flights to check
     * @return The number of null elements in the list
     */
    private int validateInput(ArrayList<Flight> flights) {
        int count = 0;

        int i = 0;
        while (i < flights.size()) {
            if (flights.get(i) == null) {
                flights.remove(i);
                count++;
            } else {
                i++;
            }
        }

        return count;
    }

}
