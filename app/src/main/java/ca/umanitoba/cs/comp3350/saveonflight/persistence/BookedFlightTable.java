package ca.umanitoba.cs.comp3350.saveonflight.persistence;

import ca.umanitoba.cs.comp3350.saveonflight.objects.BookedFlight;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Flight;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Traveller;

import java.util.ArrayList;

/**
 * BookedFlightTable.java
 * <p>
 * Database table for BookedFlight.
 *
 * @author Long Yu
 */

public class BookedFlightTable implements DataAccessStub<BookedFlight> {
    private static ArrayList<BookedFlight> bookedFlights = null;

    public BookedFlightTable() {
    }

    public void initialize() {
        if (bookedFlights == null) {
            bookedFlights = new ArrayList<BookedFlight>();
            ArrayList<Traveller> travellers = TravellerTable.getTravellers();
            ArrayList<Flight> flights = FlightTable.getFlights();
            bookedFlights.add(new BookedFlight(travellers.get(0), flights.get(0)));
            bookedFlights.add(new BookedFlight(travellers.get(0), flights.get(1)));
            bookedFlights.add(new BookedFlight(travellers.get(1), flights.get(2)));
            bookedFlights.add(new BookedFlight(travellers.get(2), flights.get(3)));
        }
    }

    public static ArrayList<BookedFlight> getBookedFlights() {
        return bookedFlights;
    }

    public boolean add(BookedFlight bookedFlight) {
        boolean result = false;
        if (bookedFlight == null) {
            return result;
        }
        for (BookedFlight bookedFlight1 : bookedFlights) {
            if (bookedFlight.equals(bookedFlight1))
                return result;
        }
        return bookedFlights.add(bookedFlight);
    }

    public boolean update(BookedFlight bookedFlight) {
        boolean isUpdated = false;
        int travelId = bookedFlight.getTraveller().getTravellerID();
        String flightId = bookedFlight.getFlight().getFlightID();
        int changes = 0;
        int index = 0;
        BookedFlight temp;
        for (int i = 0; i < bookedFlights.size(); i++) {
            temp = bookedFlights.get(i);
            if (temp.getFlight().getFlightID().equals(flightId) && temp.getTraveller().getTravellerID() == travelId) {
                changes++;
                index = i;
            }
        }
        if (changes != 0) {
            bookedFlights.get(index).setFlight(bookedFlight.getFlight());
            bookedFlights.get(index).setTraveller(bookedFlight.getTraveller());
            isUpdated = true;
        }
        return isUpdated;
    }

    public boolean remove(BookedFlight bookedFlight) {
        boolean result = false;
        int index;
        index = bookedFlights.indexOf(bookedFlight);
        if (index >= 0) {
            bookedFlights.remove(index);
            result = true;
        }
        return result;

    }

}
