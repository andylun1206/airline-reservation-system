package ca.umanitoba.cs.comp3350.saveonflight.persistence;

import java.util.List;

import ca.umanitoba.cs.comp3350.saveonflight.objects.Airport;

/**
 * Created by longyu on 2017-06-29.
 */

public interface AirportAccess extends DataAccess<Airport> {
    List<Airport> getAirports();

    Airport findAirport(String city);
}
