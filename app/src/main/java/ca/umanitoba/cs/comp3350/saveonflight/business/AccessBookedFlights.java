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

public interface AccessBookedFlights {
    boolean addBookedFlight(BookedFlight bf);

    ArrayList<BookedFlight> searchByTraveller(Traveller t);
}
