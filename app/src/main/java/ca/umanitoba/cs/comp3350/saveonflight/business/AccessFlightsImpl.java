package ca.umanitoba.cs.comp3350.saveonflight.business;

import java.util.ArrayList;

import ca.umanitoba.cs.comp3350.saveonflight.objects.Flight;
import ca.umanitoba.cs.comp3350.saveonflight.objects.SearchCriteria;
import ca.umanitoba.cs.comp3350.saveonflight.persistence.FlightAccess;
import ca.umanitoba.cs.comp3350.saveonflight.persistence.FlightTable;

/**
 * AccessFlightsImpl.java
 * <p>
 * Implementation of the database access object responsible for accessing the Flights table.
 *
 * @author Kenny Zhang
 */

public class AccessFlightsImpl implements AccessFlights {
    private static FlightAccess flightsDB;

    public AccessFlightsImpl() {
        if (flightsDB == null) {
            flightsDB = new FlightTable();
            flightsDB.initialize();
        }
    }

    @Override
    public ArrayList<Flight> getFlights() {
        return FlightTable.getFlights();
    }

    @Override
    public Flight getFlightByCode(String flightCode) {
        return flightsDB.findByFlightCode(flightCode);
    }

    @Override
    public boolean insertFlight(Flight flight) {
        return flightsDB.add(flight);
    }

    @Override
    public boolean updateFlight(Flight flight) {
        return flightsDB.update(flight);

    }

    @Override
    public boolean deleteFlight(Flight flight) {
        return flightsDB.remove(flight);
    }

    @Override
    public ArrayList<Flight> search(SearchCriteria searchCriteria) {
        return flightsDB.findBySearchCriteria(searchCriteria);
    }

}


