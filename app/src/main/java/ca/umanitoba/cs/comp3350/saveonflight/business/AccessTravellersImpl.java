package ca.umanitoba.cs.comp3350.saveonflight.business;

import java.util.List;

import ca.umanitoba.cs.comp3350.saveonflight.application.Main;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Traveller;
import ca.umanitoba.cs.comp3350.saveonflight.persistence.TravellerAccess;
import ca.umanitoba.cs.comp3350.saveonflight.persistence.TravellerTableSql;

/**
 * AccessTravellersImpl.java
 * <p>
 * Implementation for the database access object responsible for accessing the Travellers table.
 *
 * @author Kenny Zhang
 */

public class AccessTravellersImpl implements AccessTravellers {
    private static TravellerAccess travellerDB;

    public AccessTravellersImpl(TravellerAccess access) {
        travellerDB = access;
    }

    @Override
    public List<Traveller> getTravellers() {
        return travellerDB.getTravellers();
    }

    @Override
    public boolean insertTraveller(Traveller traveller) {
        return travellerDB.add(traveller);
    }

    @Override
    public Traveller getTraveller(int travellerId) {
        Traveller traveller = null;

        for (Traveller t : getTravellers()) {
            if (t.getTravellerID() == travellerId) {
                traveller = t;
            }
        }

        return traveller;
    }

    public boolean removeTraveller(Traveller traveller) {
        return travellerDB.remove(traveller);
    }

    @Override
    public int getMaxId() {
        return travellerDB.getMaxId();
    }
}
