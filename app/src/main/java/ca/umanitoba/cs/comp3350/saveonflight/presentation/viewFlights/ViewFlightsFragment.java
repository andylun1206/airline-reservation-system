package ca.umanitoba.cs.comp3350.saveonflight.presentation.viewFlights;

/**
 * ViewFlightsFragment.java
 * <p>
 * Fragment for the view flights page of the application
 *
 * @author Andy Lun
 */

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ca.umanitoba.cs.comp3350.saveonflight.R;
import ca.umanitoba.cs.comp3350.saveonflight.business.AccessFlightsImpl;
import ca.umanitoba.cs.comp3350.saveonflight.presentation.searchFlights.SearchCriteriaHandler;
import ca.umanitoba.cs.comp3350.saveonflight.business.SortFlights;
import ca.umanitoba.cs.comp3350.saveonflight.business.SortFlightsImpl;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Flight;
import ca.umanitoba.cs.comp3350.saveonflight.objects.SearchCriteria;
import ca.umanitoba.cs.comp3350.saveonflight.presentation.FragmentNavigation;
import ca.umanitoba.cs.comp3350.saveonflight.presentation.searchFlights.SearchCriteriaArrayAdapter;

import java.util.ArrayList;

public class ViewFlightsFragment extends ListFragment {
    private static Activity activity;
    private static View view;

    private static ViewFlightsArrayAdapter flightAdapter;
    private static ArrayList<ViewFlightsListViewEntry> flightList;
    private static ArrayList<Flight> flights;
    private static SearchCriteria searchCriteria;
    private static ArrayList<String> chosenFlights = new ArrayList<>();

    private static String title;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle saveInstanceState) {
        if (container != null) {
            container.removeAllViews();
        }

        activity = getActivity();
        view = inflater.inflate(R.layout.fragment_view_flights, container, false);

        flightList = new ArrayList<>();
        flightAdapter = new ViewFlightsArrayAdapter(getActivity(), R.layout.list_item_view_flights, flightList);
        setListAdapter(flightAdapter);

        searchCriteria = SearchCriteriaArrayAdapter.getCriteria();
        flights = new AccessFlightsImpl().search(searchCriteria);

        title = getString(R.string.view_flights_flight_path, "$0", "$1");

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle saveInstanceState) {
        super.onViewCreated(view, saveInstanceState);

        if (flights != null && flights.size() != 0) {
            updateFlightList();
            setTitle(flights.get(0).getOrigin().getAirportCode(), flights.get(0).getDestination().getAirportCode());
        }

        view.findViewById(R.id.button_view_flight_sort_duration).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new SortFlightsImpl().sortFlightsBy(flights, SortFlights.SortParameter.DURATION);
                updateFlightList();
            }
        });

        view.findViewById(R.id.button_view_flight_sort_price).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new SortFlightsImpl().sortFlightsBy(flights, SortFlights.SortParameter.PRICE);
                updateFlightList();
            }
        });

        view.findViewById(R.id.button_view_flight_sort_time).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new SortFlightsImpl().sortFlightsBy(flights, SortFlights.SortParameter.TIME);
                updateFlightList();
            }
        });
    }

    /**
     * Refreshes the list of flights shown on screen.
     */
    private static void updateFlightList() {
        flightList.clear();

        for (Flight f : flights) {
            flightList.add(new ViewFlightsListViewEntry(f.getFlightTime(), f.getPrice(), f.getAirline(), f.getFlightCode(), f.getFlightDuration()));
        }

        flightAdapter.notifyDataSetChanged();
    }

    /**
     * Creates a list of flights chosen by the traveller.
     *
     * @param flightId flight identification number
     */

    public static void addChosenFlight(String flightId) {
        for (Flight flight : flights) {
            if (flight.getFlightCode().equals(flightId)) {
                chosenFlights.add(flight.getFlightCode());
            }
        }
    }

    /**
     * Navigates to the next screen
     * <p>
     * (1) If it is a return trip and showing departure flights --> searches for return flights
     * (2) Otherwise --> go to the flight summary screen
     */

    public static void navgiateNextStep() {
        if (searchCriteria.isReturnTrip() && chosenFlights.size() == 1) {
            searchCriteria = SearchCriteriaHandler.reverseFlightDirection(searchCriteria);
            flights = new AccessFlightsImpl().search(searchCriteria);

            if (flights != null && !flights.isEmpty()) {
                updateFlightList();
                setTitle(flights.get(0).getOrigin().getAirportCode(), flights.get(0).getDestination().getAirportCode());
            }
        } else if (!searchCriteria.isReturnTrip() || (searchCriteria.isReturnTrip() && chosenFlights.size() == 2)) {
            FragmentNavigation.flightSummary(chosenFlights);
            chosenFlights = new ArrayList<>();
        }
    }

    private static void setTitle(String origin, String destination) {
        activity.setTitle(title.replace("$0", origin).replace("$1", destination));
    }

}
