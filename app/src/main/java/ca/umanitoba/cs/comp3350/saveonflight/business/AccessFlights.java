package ca.umanitoba.cs.comp3350.saveonflight.business;

import java.util.ArrayList;
import java.util.Date;

import ca.umanitoba.cs.comp3350.saveonflight.objects.Airline;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Airport;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Flight;
import ca.umanitoba.cs.comp3350.saveonflight.objects.FlightClassEnum;
import ca.umanitoba.cs.comp3350.saveonflight.objects.SearchCriteria;

/**
 * AccessFlights.java
 * <p>
 * Database access object for the Flights table.
 *
 * @author Kenny Zhang
 */

interface AccessFlights {
    ArrayList<Flight> getFlights();

    boolean insertFlight(Flight flight);

    boolean updateFlight(Flight flight, String flightID, Date departureDate, Date arrivalDate, Airline airline, Airport origin,
                         Airport dest, double price, int capacity, int seatsTaken, FlightClassEnum flightClass);

    boolean deleteFlight(Flight flight);

    ArrayList<Flight> search(SearchCriteria searchCriteria);
}
