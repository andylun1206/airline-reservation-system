package ca.umanitoba.cs.comp3350.saveonflight.business;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ca.umanitoba.cs.comp3350.saveonflight.objects.Airline;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Airport;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Flight;
import ca.umanitoba.cs.comp3350.saveonflight.objects.SearchCriteria;

public class FindFlightsImpl implements FindFlights {
    
    public List<Flight> getAllFlights() {
        // If no parameters, just return all the Flights in the DB
        AccessFlights accessFlights = new AccessFlights();
        List<Flight> flights = accessFlights.getFlights();
        return flights;
    }
    
    public List<Flight> search(Airline airline, Date date, Airport departs, Airport arrives) {
        List<Flight> flights;
        AccessFlights accessFlights = new AccessFlights();

        // Get all flights in the DB
        flights = accessFlights.getFlights();
        int i = 0;
        while (i < flights.size()) {
            Flight f = flights.get(i);
            // For each Flight, remove it if it doesn't meet any of the search criteria
            if (!f.getAirline().equals(airline) || !f.getDate().equals(date) || !f.getOrigin().equals(departs)
                    || !f.getDestination().equals(arrives)) {
                flights.remove(i);
            }
            else {
                i++;
            }
        }

        return flights;
    }
    
    public ArrayList<Flight> search(SearchCriteria criteria) {
        ArrayList<Flight> flights = new ArrayList<Flight>();
        flights.add(new Flight("asdf", new Date(), new Airline("asdf"),
                new Airport("Winnipeg"), new Airport("Toronto"), 1.0, 1, 0, Flight.FlightClass.FIRST_CLASS));
        return flights;
    }
}
