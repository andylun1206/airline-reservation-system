package ca.umanitoba.cs.comp3350.saveonflight.business;

import java.util.ArrayList;
import java.util.List;

import ca.umanitoba.cs.comp3350.saveonflight.application.Main;
import ca.umanitoba.cs.comp3350.saveonflight.objects.BookedFlight;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Flight;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Traveller;
import ca.umanitoba.cs.comp3350.saveonflight.persistence.BookedFlightAccess;
import ca.umanitoba.cs.comp3350.saveonflight.persistence.BookedFlightTable;
import ca.umanitoba.cs.comp3350.saveonflight.persistence.BookedFlightTableSql;
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
            bookedFlightsDB = new BookedFlightTableSql();
            bookedFlightsDB.initialize(Main.getDBPathName());
        }
    }

    @Override
    public boolean addBookedFlight(BookedFlight bf) {
        return bookedFlightsDB.add(bf);
    }

    public ArrayList<BookedFlight> searchByTraveller(Traveller t) {
        return bookedFlightsDB.searchByTraveller(t);
    }
}
