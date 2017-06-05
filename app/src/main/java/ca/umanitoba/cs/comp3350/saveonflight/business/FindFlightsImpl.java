package ca.umanitoba.cs.comp3350.saveonflight.business;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ca.umanitoba.cs.comp3350.saveonflight.R;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Airline;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Airport;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Flight;
import ca.umanitoba.cs.comp3350.saveonflight.objects.FlightClassEnum;
import ca.umanitoba.cs.comp3350.saveonflight.objects.SearchCriteria;

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
