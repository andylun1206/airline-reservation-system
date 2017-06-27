package ca.umanitoba.cs.comp3350.saveonflight.business.comparators;

import android.support.annotation.NonNull;

import java.util.Comparator;
import java.util.concurrent.TimeUnit;

import ca.umanitoba.cs.comp3350.saveonflight.objects.Flight;

/**
 * DurationComparator.java
 * <p>
 * Comparator for comparing Flights by duration.
 *
 * @author Kenny Zhang
 */

public class DurationComparator implements Comparator<Flight> {
    @Override
    public int compare(@NonNull Flight f1, @NonNull Flight f2) {
        return (int) (f1.getDateDiff(TimeUnit.MINUTES) - f2.getDateDiff(TimeUnit.MINUTES));
    }
}