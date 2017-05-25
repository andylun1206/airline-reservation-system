package ca.umanitoba.cs.comp3350.saveonflight.business;

import java.util.List;

import ca.umanitoba.cs.comp3350.saveonflight.application.Main;
import ca.umanitoba.cs.comp3350.saveonflight.application.Services;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Airline;
import ca.umanitoba.cs.comp3350.saveonflight.persistence.DataAccessStub;

public class AccessAirlines {
    private DataAccessStub dataAccess;
    private List<Airline> airlines;

    // For iterating through all Airlines
    private Airline currentAirline;
    private int currentIndex;

    public AccessAirlines() {
        dataAccess = Services.getDataAccess(Main.DB_NAME);
        airlines = null;
        currentAirline = null;
        currentIndex = 0;
    }

    public List<Airline> getAirlines() {
        return dataAccess.getAirlines();
    }

    public Airline getNext() {
        if (airlines == null) {
            airlines = dataAccess.getAirlines();
            currentIndex = 0;
        }

        if (currentIndex < airlines.size()) {
            currentAirline = airlines.get(currentIndex);
            currentIndex++;
        } else {
            airlines = null;
            currentAirline = null;
            currentIndex = 0;
        }

        return currentAirline;
    }

    public boolean insertAirline(Airline airline) {
        return dataAccess.insertAirline(airline);
    }

    public boolean updateAirline() {
        return dataAccess.updateAirline();
    }

    public boolean deleteAirline(Airline airline) {
        return dataAccess.deleteAirline(airline);
    }
}
