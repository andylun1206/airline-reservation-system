package ca.umanitoba.cs.comp3350.saveonflight.business;

import java.util.List;

import ca.umanitoba.cs.comp3350.saveonflight.application.Main;
import ca.umanitoba.cs.comp3350.saveonflight.application.Services;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Airport;
import ca.umanitoba.cs.comp3350.saveonflight.persistence.DataAccessStub;

public class AccessAirports {
    private DataAccessStub dataAccess;
    private List<Airport> airports;

    // For iterating through all Airports
    private Airport currentAirport;
    private int currentIndex;

    AccessAirports(){
        dataAccess = (DataAccessStub) Services.getDataAccess(Main.DB_NAME);
        airports =  null;
        currentAirport = null;
        currentIndex = 0;
    }

    public List<Airport> getAirports(){
        return dataAccess.getAirports();
    }

    public Airport getNext() {
        if (airports == null) {
            airports = dataAccess.getAirports();
            currentIndex = 0;
        }

        if (currentIndex < airports.size()) {
            currentAirport = airports.get(currentIndex);
            currentIndex++;
        } else {
            airports = null;
            currentAirport = null;
            currentIndex = 0;
        }

        return currentAirport;
    }

    public boolean addAirport(Airport a){
        return dataAccess.insertAirport(a);
    }

    public boolean updateAirport(Airport a, String airportCode) {
        return dataAccess.updateAirport(a, airportCode);
    }

    public boolean deleteAirport(Airport a){
        return dataAccess.deleteAirport(a);
    }
}
