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
     * @param flights List of Flights to sort
     * @param sortBy Field to sort Flights by
     */
    @Override
    public void sortFlightsBy(ArrayList<Flight> flights, SortParameter sortBy) {
        if (flights == null) {
            return;
        }
        // Remove any null objects from the list prior to sorting
        int numNull = validateInput(flights);

        Comparator<Flight> comparator;
        switch (sortBy) {
            case DATE:
                comparator = new DepartureTimeComparator();
                break;
            case AIRLINE:
                comparator = new AirlineComparator();
                break;
            case PRICE:
                comparator = new PriceComparator();
                break;
            case CAPACITY:
                comparator = new CapacityComparator();
                break;
            case SEATS_AVAILABLE:
                comparator = new SeatsAvailableComparator();
                break;
            case DURATION:
                comparator = new DurationComparator();
                break;
            default:
                comparator = new DepartureTimeComparator();
        }

        Collections.sort(flights, comparator);
        // Re-add the null elements to the end of the list
        for (int i = 0; i < numNull; i++) {
            flights.add(null);
        }
    }

    // Removes and returns the number of null elements in the List

    /**
     * Checks each Flight in the list passed in for validity.
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

    /**
     * Comparator for sorting by departure date.
     */
    private class DepartureTimeComparator implements Comparator<Flight> {
        @Override
        public int compare(@NonNull Flight f1, @NonNull Flight f2) {
            int result;

            if (f1.getDepartureTime() == null) {
                result = Integer.MAX_VALUE;
            } else if (f2.getDepartureTime() == null) {
                result = Integer.MIN_VALUE;
            } else {
                result = f1.getDepartureTime().compareTo(f2.getDepartureTime());
            }

            return result;
        }
    }

    /**
     * Comparator for sorting by Airline.
     */
    private class AirlineComparator implements Comparator<Flight> {
        @Override
        public int compare(@NonNull Flight f1, @NonNull Flight f2) {
            int result;

            if (f1.getAirline() == null) {
                result = Integer.MAX_VALUE;
            } else if (f2.getAirline() == null) {
                result = Integer.MIN_VALUE;
            } else {
                result = f1.getAirline().compareTo(f2.getAirline());
            }

            return result;
        }
    }

    /**
     * Comparator for sorting by price.
     */
    private class PriceComparator implements Comparator<Flight> {
        @Override
        public int compare(@NonNull Flight f1, @NonNull Flight f2) {
            int res = 0;

            double diff = f1.getPrice() - f2.getPrice();
            if (diff > 0) {
                res = (int) Math.ceil(diff);
            } else if (diff < 0) {
                res = (int) Math.floor(diff);
            }

            return res;
        }
    }

    /**
     * Comparator for searching by capacity.
     */
    private class CapacityComparator implements Comparator<Flight> {
        @Override
        public int compare(@NonNull Flight f1, @NonNull Flight f2) {
            return f1.getCapacity() - f2.getCapacity();
        }
    }

    /**
     * Comparator for searching by the number of seats available.
     */
    private class SeatsAvailableComparator implements Comparator<Flight> {
        @Override
        public int compare(@NonNull Flight f1, @NonNull Flight f2) {
            // Note: We want descending order here
            return f2.getSeatsRemaining() - f1.getSeatsRemaining();
        }
    }

    private class DurationComparator implements Comparator<Flight> {
        @Override
        public int compare(@NonNull Flight f1, @NonNull Flight f2) {
            return (int) (f1.getDateDiff(TimeUnit.MINUTES) - f2.getDateDiff(TimeUnit.MINUTES));
        }
    }
}
