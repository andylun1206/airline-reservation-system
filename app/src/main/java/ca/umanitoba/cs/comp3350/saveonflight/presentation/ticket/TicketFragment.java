package ca.umanitoba.cs.comp3350.saveonflight.presentation.ticket;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import ca.umanitoba.cs.comp3350.saveonflight.R;
import ca.umanitoba.cs.comp3350.saveonflight.application.Main;
import ca.umanitoba.cs.comp3350.saveonflight.business.AccessBookedFlightsImpl;
import ca.umanitoba.cs.comp3350.saveonflight.business.AccessFlightsImpl;
import ca.umanitoba.cs.comp3350.saveonflight.business.AccessTravellersImpl;
import ca.umanitoba.cs.comp3350.saveonflight.objects.BookedFlight;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Flight;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Traveller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Andy on 2017-07-08.
 */

public class TicketFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ticket, container, false);

        int travellerId = getArguments().getInt("traveller_id");
        String flightCode = getArguments().getString("flight_code");

        List<BookedFlight> bookedFlights = new AccessBookedFlightsImpl(Main.getBookedFlightAccess()).searchByTraveller(new Traveller(travellerId, null));

        for (BookedFlight bookedFlight : bookedFlights) {
            if (bookedFlight.getFlight().getFlightCode().equalsIgnoreCase(flightCode)) {
                ((TextView) view.findViewById(R.id.textView_ticket_passenger_name))
                        .setText(new AccessTravellersImpl(Main.getTravellerAccess()).getTraveller(travellerId).getName());

                Flight flight = new AccessFlightsImpl(Main.getFlightAccess()).getFlightByCode(flightCode);
                ((TextView) view.findViewById(R.id.textView_ticket_flight_code)).setText(flightCode);
                ((TextView) view.findViewById(R.id.textView_ticket_from)).setText(flight.getOriginAirportCode());
                ((TextView) view.findViewById(R.id.textView_ticket_to)).setText(flight.getDestinationAirportCode());
                ((TextView) view.findViewById(R.id.textView_ticket_departure_time)).setText(parseDate(flight.getDepartureTime()));
                ((TextView) view.findViewById(R.id.textView_ticket_arrival_time)).setText(parseDate(flight.getArrivalTime()));
                ((TextView) view.findViewById(R.id.textView_ticket_duration)).setText(flight.getFlightDuration());
                ((TextView) view.findViewById(R.id.textView_ticket_boarding_time)).setText(getBoardingTime(flight.getDepartureTime()));

//                ((TextView) view.findViewById(R.id.textView_ticket_seat)).setText(bookedFlight.getSeat());
            }
        }

        return view;
    }

    private String parseDate(Date date) {
        return new SimpleDateFormat("MMM dd HH:mm").format(date);
    }

    private String getBoardingTime(Date date) {
        final long ONE_MINUTE_IN_MILLIS = 60000;
        final int BOARDING_MINS = 45;

        return parseDate(new Date(date.getTime() - (BOARDING_MINS * ONE_MINUTE_IN_MILLIS)));
    }
}
