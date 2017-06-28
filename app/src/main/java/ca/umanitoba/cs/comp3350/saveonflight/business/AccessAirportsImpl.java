package ca.umanitoba.cs.comp3350.saveonflight.business;

import java.util.List;

import ca.umanitoba.cs.comp3350.saveonflight.objects.Airport;
import ca.umanitoba.cs.comp3350.saveonflight.persistence.AirportTable;
import ca.umanitoba.cs.comp3350.saveonflight.persistence.DataAccess;

import static ca.umanitoba.cs.comp3350.saveonflight.persistence.AirportTable.findAirport;

/**
 * AccessAirportsImpl.java
 * <p>
 * Implementation of the database access object for the Airports table.
 *
 * @author Shenyun Wang
 * @author Andy Lun
 */

public class AccessAirportsImpl implements AccessAirports {
    private static DataAccess<Airport> airportDB;

    public AccessAirportsImpl() {
        if (airportDB == null) {
            airportDB = new AirportTable();
            airportDB.initialize();
        }
    }

    @Override
    public List<Airport> getAirports() {
        return AirportTable.getAirports();
    }

    @Override
    public boolean addAirport(Airport a) {
        return airportDB.add(a);
    }

    @Override
    public boolean updateAirport(Airport a) {
        return airportDB.update(a);
    }

    @Override
    public boolean deleteAirport(Airport a) {
        return airportDB.remove(a);
    }

    public boolean airportExists(String s){
        Airport a = null;
        boolean found = false;
        a = findAirport(s);
        if(a != null)
            found = true;
        return found;
    }
}
