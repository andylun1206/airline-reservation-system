package ca.umanitoba.cs.comp3350.saveonflight.business;

import java.util.List;

import ca.umanitoba.cs.comp3350.saveonflight.application.Main;
import ca.umanitoba.cs.comp3350.saveonflight.application.Services;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Traveller;
import ca.umanitoba.cs.comp3350.saveonflight.persistence.DataAccessStub;

/**
 * AccessTravellersImpl.java
 * <p>
 * Implementation for the database access object responsible for accessing the Travellers table.
 *
 * @author Kenny Zhang
 */

public class AccessTravellersImpl implements AccessTravellers {
    private DataAccessStub dataAccess;

    public AccessTravellersImpl() {
        dataAccess = Services.getDataAccess(Main.DB_NAME);
    }

    @Override
    public List<Traveller> getTravellers() {
        return dataAccess.getTravellers();
    }

    @Override
    public boolean insertTraveller(Traveller traveller) {
        return dataAccess.insertTraveller(traveller);
    }

    @Override
    public boolean updateTraveller(Traveller traveller, int newID, String newName) {
        return dataAccess.updateTraveller(traveller, newID, newName);
    }

    @Override
    public boolean deleteTraveller(Traveller traveller) {
        return dataAccess.deleteTraveller(traveller);
    }
}
