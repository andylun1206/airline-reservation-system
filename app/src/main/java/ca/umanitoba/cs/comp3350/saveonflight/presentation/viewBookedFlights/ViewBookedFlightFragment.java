package ca.umanitoba.cs.comp3350.saveonflight.presentation.viewBookedFlights;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import ca.umanitoba.cs.comp3350.saveonflight.R;
import ca.umanitoba.cs.comp3350.saveonflight.application.Main;
import ca.umanitoba.cs.comp3350.saveonflight.business.AccessBookedFlights;
import ca.umanitoba.cs.comp3350.saveonflight.business.AccessBookedFlightsImpl;
import ca.umanitoba.cs.comp3350.saveonflight.objects.BookedFlight;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Flight;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Traveller;
import ca.umanitoba.cs.comp3350.saveonflight.presentation.ToastHandler;
import ca.umanitoba.cs.comp3350.saveonflight.presentation.viewFlights.ViewFlightsListViewEntry;

/**
 * ViewBookedFlightFragment.java
 * <p>
 * Fragment for the view booked flights page of the application
 *
 * @author Kenny Zhang
 */

public class ViewBookedFlightFragment extends ListFragment implements View.OnClickListener {
    private EditText etPassengerId;
    private Button buttonFindFlights;

    private ArrayList<Flight> flights;
    private ArrayList<ViewFlightsListViewEntry> flightList;
    private ViewBookedFlightArrayAdapter flightAdapter;

    private AccessBookedFlights accessBookedFlights;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (container != null) {
            container.removeAllViews();
        }
        getActivity().setTitle("View Booked Flights");

        View view = inflater.inflate(R.layout.fragment_view_bookedflight, container, false);
        etPassengerId = (EditText) view.findViewById(R.id.editText_passenger_id);
        buttonFindFlights = (Button) view.findViewById(R.id.button_find_booked_flights);
        buttonFindFlights.setOnClickListener(this);

        accessBookedFlights = new AccessBookedFlightsImpl(Main.getBookedFlightAccess());

        flights = new ArrayList<>();
        flightList = new ArrayList<>();
        flightAdapter = new ViewBookedFlightArrayAdapter(getActivity(), R.layout.list_item_bookedflight, flightList);
        setListAdapter(flightAdapter);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_find_booked_flights:
                // Get all BookedFlights associated with the given passenger ID
                String passengerId = etPassengerId.getText().toString();

                if (passengerId.isEmpty()) {
                    ToastHandler.toastShowShortText(getActivity(), "Please enter a passenger ID");
                } else {
                    final int PASSENGER_ID = Integer.parseInt(etPassengerId.getText().toString());
                    ViewBookedFlightArrayAdapter.setPassengerId(PASSENGER_ID);
                    List<BookedFlight> bfs = accessBookedFlights.searchByTraveller(new Traveller(PASSENGER_ID, null));

                    // Update the list
                    flights.clear();
                    for (BookedFlight bf : bfs) {
                        flights.add(bf.getFlight());
                    }
                    flightList.clear();
                    for (Flight f : flights) {
                        flightList.add(new ViewFlightsListViewEntry(f.getFlightTime(), f.getPrice(), f.getAirline(), f.getFlightCode(), f.getFlightDuration()));
                    }
                    flightAdapter.notifyDataSetChanged();

                    if (flightList.isEmpty()) {
                        ToastHandler.toastShowShortText(getActivity(), "No booked flights were found for this passenger");
                    }
                }

                break;
        }
    }
}
