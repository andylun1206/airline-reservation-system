package ca.umanitoba.cs.comp3350.saveonflight.business;

import java.util.List;

import ca.umanitoba.cs.comp3350.saveonflight.objects.Traveller;
import ca.umanitoba.cs.comp3350.saveonflight.persistence.DataAccess;
import ca.umanitoba.cs.comp3350.saveonflight.persistence.TravellerAccess;
import ca.umanitoba.cs.comp3350.saveonflight.persistence.TravellerTable;

/**
 * AccessTravellersImpl.java
 * <p>
 * Implementation for the database access object responsible for accessing the Travellers table.
 *
 * @author Kenny Zhang
 */

public class AccessTravellersImpl implements AccessTravellers {
    private static TravellerAccess travellerDB;

    public AccessTravellersImpl() {
        if (travellerDB == null) {
            travellerDB = new TravellerTable();
            travellerDB.initialize();
        }
    }

    @Override
    public List<Traveller> getTravellers() {
        return TravellerTable.getTravellers();
    }

    @Override
    public boolean insertTraveller(Traveller traveller) {
        return travellerDB.add(traveller);
    }
}
