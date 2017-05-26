package ca.umanitoba.cs.comp3350.saveonflight.business;

import java.util.List;

import ca.umanitoba.cs.comp3350.saveonflight.objects.Flight;

public class FindFlightsImpl implements FindFlights {

    @Override
    public List<Flight> search() {
        // TODO: make this method do an actual search

        // For now, just return
        AccessFlights dbAccess = new AccessFlights();
        List<Flight> flights = dbAccess.getFlights();
        return flights;
    }
}
