package ca.umanitoba.cs.comp3350.saveonflight.business;

import java.util.ArrayList;
import java.util.List;

import ca.umanitoba.cs.comp3350.saveonflight.objects.BookedFlight;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Flight;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Traveller;
import ca.umanitoba.cs.comp3350.saveonflight.persistence.BookedFlightAccess;
import ca.umanitoba.cs.comp3350.saveonflight.persistence.BookedFlightTable;
import ca.umanitoba.cs.comp3350.saveonflight.persistence.DataAccess;

/**
 * AccessBookedFlightImpl.java
 * <p>
 * Implementation of the database access object for the Flights table.
 *
 * @author Shenyun Wang
 */

public class AccessBookedFlightsImpl implements AccessBookedFlights {
    private static BookedFlightAccess bookedFlightsDB;

    public AccessBookedFlightsImpl() {
        if (bookedFlightsDB == null) {
            bookedFlightsDB = new BookedFlightTable();
            bookedFlightsDB.initialize();
        }
    }

    @Override
    public List<BookedFlight> getFlights() {
        return BookedFlightTable.getBookedFlights();
    }

    @Override
    public boolean addBookedFlight(BookedFlight bf) {
        return bookedFlightsDB.add(bf);
    }

    @Override
    public boolean updateBookedFlight(BookedFlight bf) {
        return bookedFlightsDB.update(bf);
    }

    @Override
    public boolean deleteFlight(BookedFlight bf) {
        return bookedFlightsDB.remove(bf);
    }

    public ArrayList<BookedFlight> searchByTraveller(Traveller t) {
        return bookedFlightsDB.searchByTraveller(t);
    }

    @Override
    public ArrayList<BookedFlight> searchByFlight(Flight f) {
        return bookedFlightsDB.searchByFlight(f);
    }

}
