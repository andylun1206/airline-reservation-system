package ca.umanitoba.cs.comp3350.saveonflight.business;

import ca.umanitoba.cs.comp3350.saveonflight.objects.BookedFlight;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Flight;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Traveller;
import ca.umanitoba.cs.comp3350.saveonflight.persistence.BookedFlightTable;
import ca.umanitoba.cs.comp3350.saveonflight.persistence.DataAccess;

import java.util.ArrayList;
import java.util.List;

/**
 * AccessBookedFlightImpl.java
 * <p>
 * Implementation of the database access object for the Flights table.
 *
 * @author Shenyun Wang
 */

public class AccessBookedFlightsImpl implements AccessBookedFlights {
    private static DataAccess<BookedFlight> bookedFlightsDB;

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

    @Override
    public ArrayList<BookedFlight> getBookedFlightsOf(Traveller t) {
//        return bookedFlightsDB.getTravellersBookedFlights(t);
        return null;
    }

    @Override
    public ArrayList<BookedFlight> getTravellersOnFlight(Flight f) {
//        return bookedFlightsDB.getTravellersOnFlight(f);
        return null;
    }

}
