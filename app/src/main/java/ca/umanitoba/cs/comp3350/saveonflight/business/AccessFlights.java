package ca.umanitoba.cs.comp3350.saveonflight.business;

import java.util.List;

import ca.umanitoba.cs.comp3350.saveonflight.application.Main;
import ca.umanitoba.cs.comp3350.saveonflight.application.Services;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Airline;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Flight;
import ca.umanitoba.cs.comp3350.saveonflight.persistence.DataAccessStub;

public class AccessFlights {
    private DataAccessStub dataAccess;
    private List<Flight> flights;

    // For iterating through all Flights
    private Flight currentFlight;
    private int currentIndex;

    public AccessFlights() {
        dataAccess = Services.getDataAccess(Main.DB_NAME);
        flights = null;
        currentFlight = null;
        currentIndex = 0;
    }

    public List<Flight> getFlights() {
        return dataAccess.getFlights();
    }

    public Flight getNext() {
        if (flights == null) {
            flights = dataAccess.getFlights();
            currentIndex = 0;
        }

        if (currentIndex < flights.size()) {
            currentFlight = flights.get(currentIndex);
            currentIndex++;
        } else {
            // Finished iterating, so reset everything
            flights = null;
            currentFlight = null;
            currentIndex = 0;
        }

        return currentFlight;
    }

    public boolean insertFlight(Flight flight) {
        return dataAccess.insertFlight(flight);
    }

    public boolean updateFlight() {
        return dataAccess.updateFlight();
    }

    public boolean deleteFlight(Flight flight) {
        return dataAccess.deleteFlight(flight);
    }

}


