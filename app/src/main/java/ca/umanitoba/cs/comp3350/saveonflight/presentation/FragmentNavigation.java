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
import ca.umanitoba.cs.comp3350.saveonflight.presentation.flightSummary.ViewFlightsSummaryFragment;
import ca.umanitoba.cs.comp3350.saveonflight.presentation.payment.PaymentFragment;
import ca.umanitoba.cs.comp3350.saveonflight.presentation.searchFlights.SearchFragment;
import ca.umanitoba.cs.comp3350.saveonflight.presentation.viewBookedFlights.ViewBookedFlightFragment;
import ca.umanitoba.cs.comp3350.saveonflight.presentation.viewFlights.ViewFlightsFragment;

import java.util.ArrayList;

public class FragmentNavigation {
    private static FragmentManager fragmentManager;

    public static void setFragmentManager(FragmentManager newFragmentManager) {
        fragmentManager = newFragmentManager;
    }

    /**
     * Switch context to the homepage fragment
     */
    public static void homepage() {
        fragmentManager.beginTransaction()
                .replace(R.id.content_frame, new HomeFragment())
                .commit();
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
    public static void flightSummary(ArrayList<String> flights) {
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("chosen_flights", flights);

        Fragment viewFlights = new ViewFlightsSummaryFragment();
        viewFlights.setArguments(bundle);
        fragmentManager.beginTransaction()
                .replace(R.id.content_frame, viewFlights)
                .commit();
    }

    /**
     * Switch context to payment fragment
     */
    public static void viewPayment(ArrayList<String> flights) {
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("flights_to_book", flights);

        Fragment payment = new PaymentFragment();
        payment.setArguments(bundle);
        fragmentManager.beginTransaction()
                .replace(R.id.content_frame, payment)
                .commit();
    }

    /**
     * Switch context to view booked flights fragment
     */
    public static void viewBookedFlights() {
        fragmentManager.beginTransaction()
                .replace(R.id.content_frame, new ViewBookedFlightFragment())
                .commit();
    }

}
