package ca.umanitoba.cs.comp3350.saveonflight.business;

import java.util.List;

import ca.umanitoba.cs.comp3350.saveonflight.application.Main;
import ca.umanitoba.cs.comp3350.saveonflight.application.Services;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Traveller;
import ca.umanitoba.cs.comp3350.saveonflight.persistence.DataAccessStub;

public class AccessTravellers {
    private DataAccessStub dataAccess;
    private List<Traveller> travellers;

    // For iterating through all Travellers
    private Traveller currentTraveller;
    private int currentIndex;

    public AccessTravellers() {
        dataAccess = Services.getDataAccess(Main.DB_NAME);
        travellers = null;
        currentTraveller = null;
        currentIndex = 0;
    }

    public List<Traveller> getTravellers() {
        return dataAccess.getTravellers();
    }

    public Traveller getNext() {
        if (travellers == null) {
            travellers = dataAccess.getTravellers();
            currentIndex = 0;
        }

        if (currentIndex < travellers.size()) {
            currentTraveller = travellers.get(currentIndex);
            currentIndex++;
        }
        else {
            travellers = null;
            currentTraveller = null;
            currentIndex = 0;
        }

        return currentTraveller;
    }

    public boolean insertTravller(Traveller traveller) {
        return dataAccess.insertTraveller(traveller);
    }

    public boolean updateTraveller() {
        // TODO: Change method parameter list
        return dataAccess.updateTraveller();
    }

    public boolean deleteTraveller(Traveller traveller) {
        return dataAccess.deleteTraveller(traveller);
    }
}
