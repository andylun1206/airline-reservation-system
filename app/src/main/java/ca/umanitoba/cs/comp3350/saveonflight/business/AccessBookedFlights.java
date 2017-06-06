package ca.umanitoba.cs.comp3350.saveonflight.business;

import java.util.ArrayList;
import java.util.List;

import ca.umanitoba.cs.comp3350.saveonflight.objects.BookedFlight;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Flight;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Traveller;

/**
 * AccessBookedFlights.java
 * <p>
 * Interface for the database access object for the BookedFlights table.
 *
 * @author Kenny Zhang
 */

interface AccessBookedFlights {
    List<BookedFlight> getFlights();

    boolean addBookedFlight(BookedFlight bf);

    boolean updateBookedFlight(BookedFlight bf, Traveller t, Flight f);

    boolean deleteFlight(BookedFlight bf);

    ArrayList<BookedFlight> getBookedFlightsOf(Traveller t);

    ArrayList<BookedFlight> getTravellersOnFlight(Flight f);
}
