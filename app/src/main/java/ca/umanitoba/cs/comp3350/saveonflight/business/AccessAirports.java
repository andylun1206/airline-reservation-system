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
public interface AccessAirports {
    List<Airport> getAirports();
    Airport findAirportByName(String aiport);
}
