package ca.umanitoba.cs.comp3350.saveonflight.business;

import java.util.Date;
import java.util.List;

import ca.umanitoba.cs.comp3350.saveonflight.objects.Airline;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Airport;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Flight;

interface FindFlights {
    List<Flight> getAllFlights();

    List<Flight> search(Airline airline, Date departureDate, Airport departs, Airport arrives);

    List<Flight> search(Airport departs, Airport arrives, Date departureDate, Date returnDate,
                        int numTravellers, int maxPrice, Airline airline, Flight.FlightClass flightClass);
}
