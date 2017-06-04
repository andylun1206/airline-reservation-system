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
    
//    public List<Flight> search(Airline airline, Date date, Airport departs, Airport arrives) {
//        List<Flight> flights;
//        AccessFlights accessFlights = new AccessFlights();
//
//        // Get all flights in the DB
//        flights = accessFlights.getFlights();
//        int i = 0;
//        while (i < flights.size()) {
//            Flight f = flights.get(i);
//            // For each Flight, remove it if it doesn't meet any of the search criteria
//            if (!f.getAirline().equals(airline) || !f.get.equals(date) || !f.getOrigin().equals(departs)
//                    || !f.getDestination().equals(arrives)) {
//                flights.remove(i);
//            }
//            else {
//                i++;
//            }
//        }
//
//        return flights;
//    }
    
    public ArrayList<Flight> search(SearchCriteria criteria) {
        /*
        ArrayList<Flight> flights = null;

        if (criteria != null && criteria.getOrigin().toString().equalsIgnoreCase("Winnipeg")
                && criteria.getDestination().toString().equalsIgnoreCase("Toronto")) {
            flights = new ArrayList<>();
            flights.add(new Flight("AC 256", new Date(),
                    new Date(), new Airline("Air Canada", R.mipmap.ic_aircanada),
                    new Airport("Winnipeg YWG"), new Airport("Toronto YYZ"), 419.08, 100));
            flights.add(new Flight("WJ 258", new Date(),
                    new Date(), new Airline("West Jet", R.mipmap.ic_westjet),
                    new Airport("Winnipeg YWG"), new Airport("Toronto YYZ"), 190, 200));
        }*/

        return accessFlights.search(criteria);
    }
}
