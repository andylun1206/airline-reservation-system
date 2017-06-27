package ca.umanitoba.cs.comp3350.saveonflight.business;

import java.util.List;

import ca.umanitoba.cs.comp3350.saveonflight.objects.Airline;
import ca.umanitoba.cs.comp3350.saveonflight.persistence.AirlineTable;
import ca.umanitoba.cs.comp3350.saveonflight.persistence.DataAccess;

/**
 * AccessAirlinesImpl.java
 * <p>
 * Implementation for database access object for the Airlines table.
 *
 * @author Shenyun Wang
 * @author Andy Lun
 */

public class AccessAirlinesImpl implements AccessAirlines {
    private static DataAccess<Airline> airlineDB;

    public AccessAirlinesImpl() {
        if (airlineDB == null) {
            airlineDB = new AirlineTable();
            airlineDB.initialize();
        }
    }

    @Override
    public List<Airline> getAirlines() {
        return AirlineTable.getAirlines();
    }

    @Override
    public boolean insertAirline(Airline airline) {
        return airlineDB.add(airline);
    }

    @Override
    public boolean updateAirline(Airline airline) {
        return airlineDB.update(airline);
    }

    @Override
    public boolean deleteAirline(Airline airline) {
        return airlineDB.remove(airline);
    }
}
