package ca.umanitoba.cs.comp3350.saveonflight.business;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.TimeUnit;

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
     * Sorts the list of Flights passed in based on the specified SortParameter. List elements will
     * invalid fields and null elements are placed at the end of the list.
     *
     * @param flights List of Flights to sort
     * @param comparator Comparator to be used for sorting the list of Flights
     */
    @Override
    public void sortFlightsBy(ArrayList<Flight> flights, Comparator<Flight> comparator) {
        if (flights == null) {
            return;
        }
        // Remove any null objects from the list prior to sorting
        int numNull = validateInput(flights);

        Collections.sort(flights, comparator);
        // Re-add the null elements to the end of the list
        for (int i = 0; i < numNull; i++) {
            flights.add(null);
        }
    }

    // Removes and returns the number of null elements in the List

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

    private class DurationComparator implements Comparator<Flight> {
        @Override
        public int compare(@NonNull Flight f1, @NonNull Flight f2) {
            return (int) (f1.getDateDiff(TimeUnit.MINUTES) - f2.getDateDiff(TimeUnit.MINUTES));
        }
    }
}
