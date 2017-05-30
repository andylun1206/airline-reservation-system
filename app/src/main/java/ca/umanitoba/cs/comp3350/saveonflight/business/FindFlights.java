package ca.umanitoba.cs.comp3350.saveonflight.business;

import java.util.List;

import ca.umanitoba.cs.comp3350.saveonflight.objects.Airline;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Airport;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Flight;

interface FindFlights {
    List<Flight> getAllFlights();
    List<Flight> search(Airline airline, String date, Airport departs, Airport arrives);
    List<Flight> search(Airline airline, String date, Airport departs, Airport arrives,
                        float maxPrice, int numTravellers);
}
