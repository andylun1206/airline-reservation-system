package ca.umanitoba.cs.comp3350.saveonflight.business;

import ca.umanitoba.cs.comp3350.saveonflight.objects.Traveller;
import ca.umanitoba.cs.comp3350.saveonflight.persistence.TravellerTable;

import java.util.List;

/**
 * AccessTravellersImpl.java
 * <p>
 * Implementation for the database access object responsible for accessing the Travellers table.
 *
 * @author Kenny Zhang
 */

public class AccessTravellersImpl implements AccessTravellers {
    private TravellerTable travellerDB;

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

    @Override
    public boolean updateTraveller(Traveller traveller) {
        return travellerDB.update(traveller);
    }

    @Override
    public boolean deleteTraveller(Traveller traveller) {
        return travellerDB.remove(traveller);
    }
}
