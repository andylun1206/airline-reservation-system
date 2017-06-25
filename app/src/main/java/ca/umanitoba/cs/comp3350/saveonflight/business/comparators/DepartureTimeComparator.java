package ca.umanitoba.cs.comp3350.saveonflight.business.comparators;

import android.support.annotation.NonNull;

import java.util.Comparator;

import ca.umanitoba.cs.comp3350.saveonflight.objects.Flight;

/**
 * DepartureTimeComparator.java
 * <p>
 * Comparator for comparing Flights by departure times.
 *
 * @author Kenny Zhang
 */

public class DepartureTimeComparator implements Comparator<Flight> {
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
