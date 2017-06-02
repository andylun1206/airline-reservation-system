package ca.umanitoba.cs.comp3350.saveonflight.business;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @Override
    public ArrayList<Flight> search(Airline airline, Date date, Airport origin, Airport destination) {
        ArrayList<Flight> flights;
        AccessFlights accessFlights = new AccessFlights();

        // Get all flights in the DB
        flights = accessFlights.getFlights();
        int i = 0;
        while (i < flights.size()) {
            Flight f = flights.get(i);
            // For each Flight, remove it if it doesn't meet any of the search criteria
            if (!f.getAirline().equals(airline) || !f.getDate().equals(date) || !f.getOrigin().equals(origin)
                    || !f.getDestination().equals(destination)) {
                flights.remove(i);
            }
            else {
                i++;
            }
        }

        return flights;
    }

    @Override
    public ArrayList<Flight> search(SearchCriteria criteria) {
        ArrayList<Flight> flights = new ArrayList<Flight>();

        flights = accessFlights.search(criteria);
        flights.add(new Flight("asdf", new Date(), new Airline("asdf"),
                criteria.getOrigin(), criteria.getDestination(), 1.0, 1, 0, FlightClassEnum.FIRST_CLASS));

        // Get all Flights that match the search criteria

        return flights;
    }


}
