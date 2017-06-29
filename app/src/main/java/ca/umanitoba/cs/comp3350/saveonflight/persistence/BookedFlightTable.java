package ca.umanitoba.cs.comp3350.saveonflight.persistence;

import java.util.ArrayList;

import ca.umanitoba.cs.comp3350.saveonflight.objects.BookedFlight;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Flight;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Traveller;


/**
 * BookedFlightTable.java
 * <p>
 * Database table for BookedFlight.
 *
 * @author Long Yu
 */

public class BookedFlightTable implements BookedFlightAccess {
    private static ArrayList<BookedFlight> bookedFlights = null;

    public BookedFlightTable() {
    }

    public void initialize(String dbPath) {
        if (bookedFlights == null) {
            bookedFlights = new ArrayList<BookedFlight>();

            if (TravellerTable.getTravellers() == null) {
                new TravellerTable().initialize("");
            }
            ArrayList<Traveller> travellers = TravellerTable.getTravellers();
            FlightTable flightTable=new FlightTable();
            ArrayList<Flight> flights=flightTable.getFlights();
            if (flights == null) {
                new FlightTable().initialize("");
            }
            flights = flightTable.getFlights();
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
        boolean result = true;
        if (bookedFlight != null && bookedFlight.getTraveller().getTravellerID() != 0 && (!bookedFlight.getFlight().getFlightCode().isEmpty() && !bookedFlight.getFlight().getDepartureTime().equals(null))) {
            for (BookedFlight bookedFlight1 : bookedFlights) {
                if (bookedFlight.equals(bookedFlight1))
                    result = false;
            }
            if (result)
                result = bookedFlights.add(bookedFlight);
        } else {
            result = false;
        }
        return result;
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

    @Override
    public ArrayList<BookedFlight> searchByTraveller(Traveller t) {
        ArrayList<BookedFlight> matches = new ArrayList<>();
        for (BookedFlight bf : bookedFlights) {
            if (bf.getTraveller().equals(t)) {
                matches.add(bf);
            }
        }
        return matches;
    }

    @Override
    public ArrayList<BookedFlight> searchByFlight(Flight f) {
        ArrayList<BookedFlight> matches = new ArrayList<>();
        for (BookedFlight bf : bookedFlights) {
            if (bf.getFlight().equals(f)) {
                matches.add(bf);
            }
        }
        return matches;
    }
    public void close()
    {
        System.out.println("Closed  database " );
    }

}
