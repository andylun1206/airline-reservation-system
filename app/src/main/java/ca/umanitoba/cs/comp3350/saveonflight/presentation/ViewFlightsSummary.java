package ca.umanitoba.cs.comp3350.saveonflight.presentation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import ca.umanitoba.cs.comp3350.saveonflight.R;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Flight;

/**
 * Created by Shenyun Wang on 2017-06-24.
 */

public class ViewFlightsSummary extends ListFragment{
    private ArrayList<Flight> flights;
    private Flight depFlight;
    private Flight retFlight;
    private static DateFormat DATE = new SimpleDateFormat("MM/dd/yyy HH:mm");
    private static double total;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle saveInstanceState){
        if (container != null) {
            container.removeAllViews();
        }
        View view = inflater.inflate(R.layout.list_item_view_trip, container, false);
        flights = getArguments().getParcelableArrayList("chosen_flights");
        depFlight = flights.get(0);

        total = depFlight.getPrice()+ retFlight.getPrice();

//        if(flights.size() == 1) {
            //departure flight
            ((TextView) view.findViewById(R.id.departurePrice)).setText(Double.toString(depFlight.getPrice()));
            ((TextView) view.findViewById(R.id.trip_summary_from1)).setText(depFlight.getDepartAirportCode());
            ((TextView) view.findViewById(R.id.trip_summary_to1)).setText(depFlight.getArrivalAirportCode());
            ((TextView) view.findViewById(R.id.trip_summary_dateDep1)).setText(DATE.format(depFlight.getDepartureTime()));
            ((TextView) view.findViewById(R.id.trip_summary_dateArrive1)).setText(DATE.format(depFlight.getArrivalTime()));
            ((TextView) view.findViewById(R.id.trip_summary_depDuration)).setText(depFlight.getFlightDuration());
            ((ImageView) view.findViewById(R.id.imageview_tripSummary1)).setImageResource(depFlight.getAirline().getIcon());
        if(flights.size() == 2) {
            retFlight = flights.get(1);
            //return flight
            ((TextView) view.findViewById(R.id.retPrice)).setText(Double.toString(retFlight.getPrice()));
            ((TextView) view.findViewById(R.id.trip_summary_from2)).setText(retFlight.getDepartAirportCode());
            ((TextView) view.findViewById(R.id.trip_summary_to2)).setText(retFlight.getArrivalAirportCode());
            ((TextView) view.findViewById(R.id.trip_summary_dateDep2)).setText(DATE.format(retFlight.getDepartureTime()));
            ((TextView) view.findViewById(R.id.trip_summary_dateArrive2)).setText(DATE.format(retFlight.getArrivalTime()));
            ((TextView) view.findViewById(R.id.trip_summary_retDuration)).setText(retFlight.getFlightDuration());
            ((ImageView) view.findViewById(R.id.imageview_tripSummary2)).setImageResource(retFlight.getAirline().getIcon());
            ((TextView) view.findViewById(R.id.view_trip_price)).setText(String.format("$%.2f", total));
        }
        else{
            ((TextView)view.findViewById(R.id.returnFlight)).setVisibility(View.GONE);
            ((TextView) view.findViewById(R.id.retPrice)).setVisibility(View.GONE);
            ((TextView) view.findViewById(R.id.trip_summary_from2)).setVisibility(View.GONE);
            ((TextView) view.findViewById(R.id.trip_summary_to2)).setVisibility(View.GONE);
            ((TextView) view.findViewById(R.id.trip_summary_dateDep2)).setVisibility(View.GONE);
            ((TextView) view.findViewById(R.id.trip_summary_dateArrive2)).setVisibility(View.GONE);
            ((TextView) view.findViewById(R.id.trip_summary_retDuration)).setVisibility(View.GONE);
            ((ImageView) view.findViewById(R.id.imageview_tripSummary2)).setVisibility(View.GONE);
            ((TextView) view.findViewById(R.id.view_trip_price)).setText(String.format("$%.2f", depFlight.getPrice()));
        }
        return view;
    }
}
