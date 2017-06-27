package ca.umanitoba.cs.comp3350.saveonflight.presentation;

/**
 * FragmentNavigation.java
 * <p>
 * Handles all fragment navigation events.
 *
 * @author Andy Lun
 */


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import ca.umanitoba.cs.comp3350.saveonflight.R;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Flight;

import java.util.ArrayList;

public class FragmentNavigation {
    private static FragmentManager fragmentManager;

    public static void setFragmentManager(FragmentManager newFragmentManager) {
        fragmentManager = newFragmentManager;
    }

    /**
     * Switch context to viewing the search fragment
     */
    public static void flightSearch() {
        fragmentManager.beginTransaction()
                .replace(R.id.content_frame, new SearchFragment())
                .commit();
    }

    /**
     * Switch context to viewing flights fragment
     */
    public static void viewFlights() {
        fragmentManager
                .beginTransaction()
                .replace(R.id.content_frame, new ViewFlightsFragment())
                .commit();
    }

    /**
     * Switch context to flight summary fragment
     */
    public static void flightSummary(ArrayList<Flight> flights) {
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("chosen_flights", flights);

        Fragment viewFlights = new ViewFlightsSummary();
        viewFlights.setArguments(bundle);
        fragmentManager.beginTransaction()
                .replace(R.id.content_frame, viewFlights)
                .commit();
    }

    /**
     * Switch context to payment fragment.
     */
    public static void viewPayment(ArrayList<Flight> flights) {
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("flights_to_book", flights);

        Fragment payment = new PaymentFragment();
        payment.setArguments(bundle);
        fragmentManager.beginTransaction()
                .replace(R.id.content_frame, payment)
                .commit();
    }

}
