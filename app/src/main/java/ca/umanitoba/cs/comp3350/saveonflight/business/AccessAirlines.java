package ca.umanitoba.cs.comp3350.saveonflight.business;

import ca.umanitoba.cs.comp3350.saveonflight.objects.Airline;

import java.util.List;

/**
 * AccessAirlines.java
 * <p>
 * Interface for database access object for the Airlines table.
 *
 * @author Kenny Zhang
 */

public interface AccessAirlines {
    List<Airline> getAirlines();

    boolean insertAirline(Airline airline);

    boolean updateAirline(Airline airline);

    boolean deleteAirline(Airline airline);
}
