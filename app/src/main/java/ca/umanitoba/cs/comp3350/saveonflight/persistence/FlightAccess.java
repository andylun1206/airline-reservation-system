package ca.umanitoba.cs.comp3350.saveonflight.persistence;

import java.util.ArrayList;

import ca.umanitoba.cs.comp3350.saveonflight.objects.Flight;
import ca.umanitoba.cs.comp3350.saveonflight.objects.SearchCriteria;

/**
 * Created by longyu on 2017-06-07.
 */

public interface FlightAccess extends DataAccess<Flight> {
    public ArrayList<Flight> findBySearchCriteria(SearchCriteria criteria);

    public Flight findByFlightCode(String flightCode);
}
