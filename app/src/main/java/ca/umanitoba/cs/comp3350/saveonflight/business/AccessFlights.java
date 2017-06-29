package ca.umanitoba.cs.comp3350.saveonflight.business;

import java.util.ArrayList;

import ca.umanitoba.cs.comp3350.saveonflight.objects.Flight;
import ca.umanitoba.cs.comp3350.saveonflight.objects.SearchCriteria;

/**
 * AccessFlights.java
 * <p>
 * Interface for the database access object for the Flights table.
 *
 * @author Kenny Zhang
 */

public interface AccessFlights {
    ArrayList<Flight> getFlights();

    Flight getFlightByCode(String flightCode);

    boolean insertFlight(Flight flight);

    boolean updateFlight(Flight flight);

    boolean deleteFlight(Flight flight);

    ArrayList<Flight> search(SearchCriteria searchCriteria);
}
