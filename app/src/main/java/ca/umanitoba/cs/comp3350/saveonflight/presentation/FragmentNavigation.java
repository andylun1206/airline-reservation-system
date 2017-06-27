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

}
