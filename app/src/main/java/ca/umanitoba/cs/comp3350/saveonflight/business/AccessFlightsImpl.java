package ca.umanitoba.cs.comp3350.saveonflight.business;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ca.umanitoba.cs.comp3350.saveonflight.application.Main;
import ca.umanitoba.cs.comp3350.saveonflight.application.Services;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Airline;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Airport;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Flight;
import ca.umanitoba.cs.comp3350.saveonflight.objects.FlightClassEnum;
import ca.umanitoba.cs.comp3350.saveonflight.objects.SearchCriteria;
import ca.umanitoba.cs.comp3350.saveonflight.persistence.DataAccessStub;

/**
 * AccessFlightsImpl.java
 * <p>
 * Implementation of the database access object responsible for accessing the Flights table.
 *
 * @author Kenny Zhang
 */

public class AccessFlightsImpl implements AccessFlights {
    private DataAccessStub dataAccess;

    public AccessFlightsImpl() {
        dataAccess = Services.getDataAccess(Main.DB_NAME);
    }

    @Override
    public ArrayList<Flight> getFlights() {
        return dataAccess.getFlights();
    }

    @Override
    public boolean insertFlight(Flight flight) {
        return dataAccess.insertFlight(flight);
    }

    @Override
    public boolean updateFlight(Flight flight, String flightID, Date departureDate, Date arrivalDate, Airline airline, Airport origin,
                                Airport dest, double price, int capacity, int seatsTaken, FlightClassEnum flightClass) {
        return dataAccess.updateFlight(flight, flightID, departureDate, arrivalDate, airline, origin, dest, price, capacity, seatsTaken, flightClass);

    }

    @Override
    public boolean deleteFlight(Flight flight) {
        return dataAccess.deleteFlight(flight);
    }

    @Override
    public ArrayList<Flight> search(SearchCriteria searchCriteria) {
        return dataAccess.searchByCriteria(searchCriteria);
    }

}


