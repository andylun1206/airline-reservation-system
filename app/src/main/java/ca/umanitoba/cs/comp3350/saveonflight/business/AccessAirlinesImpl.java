package ca.umanitoba.cs.comp3350.saveonflight.business;

import java.util.List;

import ca.umanitoba.cs.comp3350.saveonflight.application.Main;
import ca.umanitoba.cs.comp3350.saveonflight.application.Services;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Airline;
import ca.umanitoba.cs.comp3350.saveonflight.persistence.DataAccessStub;

/**
 * AccessAirlinesImpl.java
 * <p>
 * Implementation for database access object for the Airlines table.
 *
 * @author Shenyun Wang
 */

public class AccessAirlinesImpl implements AccessAirlines {
    private DataAccessStub dataAccess;

    public AccessAirlinesImpl() {
        dataAccess = Services.getDataAccess(Main.DB_NAME);
    }

    @Override
    public List<Airline> getAirlines() {
        return dataAccess.getAirlines();
    }

    @Override
    public boolean insertAirline(Airline airline) {
        return dataAccess.insertAirline(airline);
    }

    @Override
    public boolean updateAirline(Airline airline, String name) {
        return dataAccess.updateAirline(airline, name);
    }

    @Override
    public boolean deleteAirline(Airline airline) {
        return dataAccess.deleteAirline(airline);
    }
}
