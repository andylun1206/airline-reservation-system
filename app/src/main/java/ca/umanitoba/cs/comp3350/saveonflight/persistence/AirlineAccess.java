package ca.umanitoba.cs.comp3350.saveonflight.persistence;

import java.util.ArrayList;

import ca.umanitoba.cs.comp3350.saveonflight.objects.Airline;

/**
 * Created by longyu on 2017-06-29.
 */

public interface AirlineAccess extends DataAccess<Airline> {
    Airline findAirline(String airlineName);
    ArrayList<Airline> getAirlines();
    boolean add(Airline airline);
}
