package ca.umanitoba.cs.comp3350.saveonflight.business;

import java.util.List;

import ca.umanitoba.cs.comp3350.saveonflight.application.Main;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Airline;
import ca.umanitoba.cs.comp3350.saveonflight.persistence.AirlineAccess;
import ca.umanitoba.cs.comp3350.saveonflight.persistence.AirlineTableSql;

/**
 * AccessAirlinesImpl.java
 * <p>
 * Implementation for database access object for the Airlines table.
 *
 * @author Shenyun Wang
 * @author Andy Lun
 */

public class AccessAirlinesImpl implements AccessAirlines {
    private static AirlineAccess airlineDB;

    public AccessAirlinesImpl(AirlineAccess access) {
        airlineDB = access;
    }

    @Override
    public List<Airline> getAirlines() {
        return airlineDB.getAirlines();
    }

    @Override
    public Airline getAirlineByName(String airline) {
        return airlineDB.findAirline(airline);
    }
}
