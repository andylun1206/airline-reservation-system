package ca.umanitoba.cs.comp3350.saveonflight.business;

import java.util.List;

import ca.umanitoba.cs.comp3350.saveonflight.objects.BookedFlight;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Traveller;
import ca.umanitoba.cs.comp3350.saveonflight.persistence.BookedFlightAccess;

/**
 * AccessBookedFlightImpl.java
 * <p>
 * Implementation of the database access object for the Flights table.
 *
 * @author Shenyun Wang
 */

public class AccessBookedFlightsImpl implements AccessBookedFlights {
    private static BookedFlightAccess bookedFlightsDB;

    public AccessBookedFlightsImpl(BookedFlightAccess access) {
        bookedFlightsDB = access;
    }

    /**
     * Adds a BookedFlight to the database.
     *
     * @param bf the BookedFlight to add
     * @return true if the BookedFlight was added; false if not
     */
    @Override
    public boolean addBookedFlight(BookedFlight bf) {
        return bookedFlightsDB.add(bf);
    }

    /**
     * Searches for BookedFlights that are associated with the specified Traveller.
     *
     * @param t the Traveller to search by
     * @return all the BookedFlights that are associated with the given Traveller
     */
    public List<BookedFlight> searchByTraveller(Traveller t) {
        return bookedFlightsDB.searchByTraveller(t);
    }
}
