package ca.umanitoba.cs.comp3350.saveonflight.business;

import java.util.List;

import ca.umanitoba.cs.comp3350.saveonflight.objects.Airport;

/**
 * AccessAirports.java
 * <p>
 * Interface for the database access object for the Airports table.
 *
 * @author Kenny Zhang
 */
interface AccessAirports {
    List<Airport> getAirports();

    boolean addAirport(Airport a);

    boolean updateAirport(Airport a, String airportCode);

    boolean deleteAirport(Airport a);
}
