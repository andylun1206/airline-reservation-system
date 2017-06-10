package ca.umanitoba.cs.comp3350.saveonflight.presentation;

/**
 * ViewFlightsFragment.java
 * <p>
 * Fragment for the view flights page of the application
 *
 * @author Andy Lun
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import ca.umanitoba.cs.comp3350.saveonflight.R;
import ca.umanitoba.cs.comp3350.saveonflight.business.AccessFlightsImpl;
import ca.umanitoba.cs.comp3350.saveonflight.business.SortFlights;
import ca.umanitoba.cs.comp3350.saveonflight.business.SortFlightsImpl;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Flight;
import ca.umanitoba.cs.comp3350.saveonflight.objects.SearchCriteria;
import ca.umanitoba.cs.comp3350.saveonflight.objects.ViewFlightsListViewEntry;

public class ViewFlightsFragment extends ListFragment {
    private static View view;
    private static ViewFlightsArrayAdapter flightAdapter;
    private static ArrayList<ViewFlightsListViewEntry> flightList;
    private static ArrayList<Flight> flights;
    private static SearchCriteria searchCriteria;
    private static String[] chosenFlights = new String[2];

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle saveInstanceState) {
        if (container != null) {
            container.removeAllViews();
        }

        view = inflater.inflate(R.layout.fragment_view_flights, container, false);
        flightList = new ArrayList<>();
        flightAdapter = new ViewFlightsArrayAdapter(getActivity(), R.layout.list_item_view_flights, flightList);
        setListAdapter(flightAdapter);

        searchCriteria = SearchCriteriaArrayAdapter.getCriteria();
        flights = new AccessFlightsImpl().search(searchCriteria);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle saveInstanceState) {
        super.onViewCreated(view, saveInstanceState);
        String title = getString(R.string.title_activity_view_flights);

        if (flights != null && flights.size() != 0) {
            title = getString(R.string.view_flights_flight_path, flights.get(0).getOrigin().toString(),
                    flights.get(0).getDestination().toString());

            updateFlightList();
        }

        getActivity().setTitle(title);

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
                new SortFlightsImpl().sortFlightsBy(flights, SortFlights.SortParameter.DATE);
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
            flightList.add(new ViewFlightsListViewEntry(f.getFlightTime(), f.getPrice(), f.getAirline().getIcon(), f.getFlightID(), f.getFlightDuration()));
        }

        flightAdapter.notifyDataSetChanged();
    }

    /**
     * Creates a list of flights chosen by the traveller.
     * @param flightId flight identification number
     */

    public static void addChosenFlight(String flightId) {
        int index;

        if (searchCriteria.isReturnTrip()) {
            index = (chosenFlights[0] == null || chosenFlights[0].isEmpty()) ? 0 : 1;
        } else {
            index = 0;
        }

        chosenFlights[index] = flightId;
    }

    /**
     * Navigates to the next screen
     *
     * (1) If it is a return trip and showing departure flights --> searches for return flights
     * (2) Otherwise --> go to the flight summary screen
     */

    public static void navgiateNextStep() {
        if (searchCriteria.isReturnTrip() && chosenFlights[0] != null && !chosenFlights[0].isEmpty() && chosenFlights[1] == null) {
            flights = new AccessFlightsImpl().search(searchCriteria);
            updateFlightList();
        } else if (!searchCriteria.isReturnTrip() || (searchCriteria.isReturnTrip() && chosenFlights[1] != null && !chosenFlights[1].isEmpty())) {
            ToastHandler.toastComingSoon(((MainActivity) view.getContext()), "Flight Summary");
        }
    }
}
