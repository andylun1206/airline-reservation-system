package ca.umanitoba.cs.comp3350.saveonflight.business;

import ca.umanitoba.cs.comp3350.saveonflight.objects.Airport;

import java.util.List;

/**
 * AccessAirports.java
 * <p>
 * Interface for the database access object for the Airports table.
 *
 * @author Kenny Zhang
 */
public interface AccessAirports {
    List<Airport> getAirports();

    boolean addAirport(Airport a);

    boolean updateAirport(Airport a);

    boolean deleteAirport(Airport a);
}
