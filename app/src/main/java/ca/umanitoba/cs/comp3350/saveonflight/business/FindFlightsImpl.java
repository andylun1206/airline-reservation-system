package ca.umanitoba.cs.comp3350.saveonflight.business;


import java.util.Date;
import java.util.List;

import ca.umanitoba.cs.comp3350.saveonflight.objects.Airline;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Airport;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Flight;

public class FindFlightsImpl implements FindFlights {

    @Override
    public List<Flight> getAllFlights() {
        // If no parameters, just return all the Flights in the DB
        AccessFlights accessFlights = new AccessFlights();
        List<Flight> flights = accessFlights.getFlights();
        return flights;
    }

    @Override
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

    @Override
    public List<Flight> search(Airport departs, Airport arrives, Date departureDate, Date returnDate,
                        int numTravellers, int maxPrice, Airline airline, Flight.FlightClass flightClass) {

        return null;
    }
}
