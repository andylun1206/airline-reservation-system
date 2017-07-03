package ca.umanitoba.cs.comp3350.saveonflight.business;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ca.umanitoba.cs.comp3350.saveonflight.objects.BookedFlight;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Traveller;

/**
 * AccessBookedFlights.java
 * <p>
 * Interface for the database access object for the BookedFlights table.
 *
 * @author Kenny Zhang
 */

public interface AccessBookedFlights {
    boolean add(BookedFlight bf);

    boolean remove(BookedFlight bf);

    List<BookedFlight> searchByTraveller(Traveller t);
}
