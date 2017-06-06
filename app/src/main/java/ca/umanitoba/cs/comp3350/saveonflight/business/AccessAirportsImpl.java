package ca.umanitoba.cs.comp3350.saveonflight.business;

import java.util.List;

import ca.umanitoba.cs.comp3350.saveonflight.application.Main;
import ca.umanitoba.cs.comp3350.saveonflight.application.Services;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Airport;
import ca.umanitoba.cs.comp3350.saveonflight.persistence.DataAccessStub;

public class AccessAirportsImpl implements AccessAirports {
    private DataAccessStub dataAccess;

    AccessAirportsImpl() {
        dataAccess = (DataAccessStub) Services.getDataAccess(Main.DB_NAME);
    }

    @Override
    public List<Airport> getAirports() {
        return dataAccess.getAirports();
    }

    @Override
    public boolean addAirport(Airport a) {
        return dataAccess.insertAirport(a);
    }

    @Override
    public boolean updateAirport(Airport a, String airportCode) {
        return dataAccess.updateAirport(a, airportCode);
    }

    @Override
    public boolean deleteAirport(Airport a) {
        return dataAccess.deleteAirport(a);
    }
}
