package ca.umanitoba.cs.comp3350.saveonflight.presentation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import ca.umanitoba.cs.comp3350.saveonflight.R;
import ca.umanitoba.cs.comp3350.saveonflight.business.AccessBookedFlights;
import ca.umanitoba.cs.comp3350.saveonflight.business.AccessBookedFlightsImpl;
import ca.umanitoba.cs.comp3350.saveonflight.objects.BookedFlight;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Flight;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Traveller;
import ca.umanitoba.cs.comp3350.saveonflight.objects.ViewFlightsListViewEntry;

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

        accessBookedFlights = new AccessBookedFlightsImpl();

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
                final int PASSENGER_ID = Integer.parseInt(etPassengerId.getText().toString());
                ArrayList<BookedFlight> bfs = accessBookedFlights.searchByTraveller(new Traveller(PASSENGER_ID, null));

                flights.clear();
                for (BookedFlight bf : bfs) {
                    flights.add(bf.getFlight());
                }

                flightList.clear();
                for (Flight f : flights) {
                    flightList.add(new ViewFlightsListViewEntry(f.getFlightTime(), f.getPrice(), f.getAirline().getIcon(), f.getFlightID(), f.getFlightDuration()));
                }

                flightAdapter.notifyDataSetChanged();
                break;
        }
    }
}
