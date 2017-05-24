package ca.umanitoba.cs.comp3350.saveonflight.business;

import java.util.List;

import ca.umanitoba.cs.comp3350.saveonflight.objects.Flight;
import ca.umanitoba.cs.comp3350.saveonflight.persistence.DataAccessStub;

public class AccessFlights {
    private DataAccessStub dataAccess;
    private List<Flight> flights;

    // For iterating through all Flights
    private Flight currentFlight;
    private int currentIndex;

    public AccessFlights() {

    }

}


