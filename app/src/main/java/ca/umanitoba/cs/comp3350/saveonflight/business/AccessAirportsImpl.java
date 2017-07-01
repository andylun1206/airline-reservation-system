package ca.umanitoba.cs.comp3350.saveonflight.business;

import java.util.List;

import ca.umanitoba.cs.comp3350.saveonflight.application.Main;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Airport;
import ca.umanitoba.cs.comp3350.saveonflight.persistence.AirportAccess;
import ca.umanitoba.cs.comp3350.saveonflight.persistence.AirportTableSql;

/**
 * AccessAirportsImpl.java
 * <p>
 * Implementation of the database access object for the Airports table.
 *
 * @author Shenyun Wang
 * @author Andy Lun
 */

public class AccessAirportsImpl implements AccessAirports {
    private static AirportAccess airportDB;

    public AccessAirportsImpl() {
        if (airportDB == null) {
            airportDB = new AirportTableSql();
            airportDB.initialize(Main.getDBPathName());
        }
    }

    @Override
    public List<Airport> getAirports() {
        return airportDB.getAirports();
    }

    @Override
    public Airport findAirportByName(String airport) {
        return  airportDB.findAirport(airport);
    }
}
