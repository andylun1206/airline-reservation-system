package ca.umanitoba.cs.comp3350.saveonflight.business;


import ca.umanitoba.cs.comp3350.saveonflight.objects.Flight;
import ca.umanitoba.cs.comp3350.saveonflight.objects.SearchCriteria;

import java.util.ArrayList;

public class FindFlightsImpl implements FindFlights {
    private AccessFlights accessFlights;

    public FindFlightsImpl() {
        accessFlights = new AccessFlights();
    }

    public ArrayList<Flight> getAllFlights() {
        // If no parameters, just return all the Flights in the DB
        accessFlights = new AccessFlights();
        ArrayList<Flight> flights = accessFlights.getFlights();
        return flights;
    }
    
    public ArrayList<Flight> search(SearchCriteria criteria) {
        return accessFlights.search(criteria);
    }
}
