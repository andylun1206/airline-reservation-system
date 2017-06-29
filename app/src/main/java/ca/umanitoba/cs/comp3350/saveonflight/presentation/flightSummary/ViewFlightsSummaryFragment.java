package ca.umanitoba.cs.comp3350.saveonflight.presentation.flightSummary;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import ca.umanitoba.cs.comp3350.saveonflight.R;
import ca.umanitoba.cs.comp3350.saveonflight.business.AccessFlights;
import ca.umanitoba.cs.comp3350.saveonflight.business.AccessFlightsImpl;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Flight;
import ca.umanitoba.cs.comp3350.saveonflight.presentation.FragmentNavigation;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by Shenyun Wang on 2017-06-24.
 */

public class ViewFlightsSummaryFragment extends Fragment implements View.OnClickListener {
    private ArrayList<String> flights;
    private Flight depFlight;
    private Flight retFlight;
    private static DateFormat DATE = new SimpleDateFormat("MM/dd/yyy HH:mm");
    private static double total;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle saveInstanceState) {
        if (container != null) {
            container.removeAllViews();
        }
        View view = inflater.inflate(R.layout.fragment_trip_summary, container, false);
        flights = getArguments().getStringArrayList("chosen_flights");
        AccessFlightsImpl flightAccess = new AccessFlightsImpl();
        depFlight = flightAccess.getFlightByCode(flights.get(0));

        total = depFlight.getPrice();

        getActivity().setTitle(R.string.flight_summary_title);

        ((TextView) view.findViewById(R.id.departurePrice)).setText(String.format("$%.2f", depFlight.getPrice()));
        ((TextView) view.findViewById(R.id.trip_summary_from1)).setText(depFlight.getDepartAirportCode());
        ((TextView) view.findViewById(R.id.trip_summary_to1)).setText(depFlight.getArrivalAirportCode());
        ((TextView) view.findViewById(R.id.trip_summary_dateDep1)).setText(DATE.format(depFlight.getDepartureTime()));
        ((TextView) view.findViewById(R.id.trip_summary_dateArrive1)).setText(DATE.format(depFlight.getArrivalTime()));
        ((TextView) view.findViewById(R.id.trip_summary_depDuration)).setText(depFlight.getFlightDuration());
        ((ImageView) view.findViewById(R.id.imageview_tripSummary1)).setImageResource(depFlight.getAirline().getIcon());
        if (flights.size() == 2) {
            retFlight = flightAccess.getFlightByCode(flights.get(1));
            total = depFlight.getPrice() + retFlight.getPrice();
            //return flight
            ((TextView) view.findViewById(R.id.retPrice)).setText(String.format("$%.2f", retFlight.getPrice()));
            ((TextView) view.findViewById(R.id.trip_summary_from2)).setText(retFlight.getDepartAirportCode());
            ((TextView) view.findViewById(R.id.trip_summary_to2)).setText(retFlight.getArrivalAirportCode());
            ((TextView) view.findViewById(R.id.trip_summary_dateDep2)).setText(DATE.format(retFlight.getDepartureTime()));
            ((TextView) view.findViewById(R.id.trip_summary_dateArrive2)).setText(DATE.format(retFlight.getArrivalTime()));
            ((TextView) view.findViewById(R.id.trip_summary_retDuration)).setText(retFlight.getFlightDuration());
            ((ImageView) view.findViewById(R.id.imageview_tripSummary2)).setImageResource(retFlight.getAirline().getIcon());
        } else {
            (view.findViewById(R.id.trip_summary_retFlight)).setVisibility(View.GONE);
        }
        ((TextView) view.findViewById(R.id.view_trip_price)).setText(String.format("$%.2f", total));
        ((Button) view.findViewById(R.id.button_go_payment)).setOnClickListener(this);
        ((Button) view.findViewById(R.id.button_modify_search)).setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_go_payment:
                FragmentNavigation.viewPayment(flights);
                break;
            case R.id.button_modify_search:
                FragmentNavigation.flightSearch();
                break;
        }
    }
}
