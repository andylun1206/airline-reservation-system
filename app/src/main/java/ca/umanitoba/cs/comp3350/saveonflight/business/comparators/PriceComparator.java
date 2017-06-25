package ca.umanitoba.cs.comp3350.saveonflight.business.comparators;

import android.support.annotation.NonNull;

import java.util.Comparator;

import ca.umanitoba.cs.comp3350.saveonflight.objects.Flight;

/**
 * PriceComparator.java
 * <p>
 * Comparator for comparing Flights by price.
 *
 * @author Kenny Zhang
 */

public class PriceComparator implements Comparator<Flight> {
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
