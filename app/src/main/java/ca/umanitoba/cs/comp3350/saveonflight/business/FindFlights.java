package ca.umanitoba.cs.comp3350.saveonflight.business;

import java.util.Date;
import java.util.List;

import ca.umanitoba.cs.comp3350.saveonflight.objects.Airline;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Airport;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Flight;
import ca.umanitoba.cs.comp3350.saveonflight.objects.SearchCriteria;

public interface FindFlights {
    List<Flight> getAllFlights();

//    List<Flight> search(Airline airline, Date departureDate, Airport departs, Airport arrives);

    List<Flight> search(SearchCriteria criteria);
}
