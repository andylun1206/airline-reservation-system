package ca.umanitoba.cs.comp3350.saveonflight.business;

import java.util.List;

import ca.umanitoba.cs.comp3350.saveonflight.objects.Traveller;

/**
 * AccessTravellers.java
 * <p>
 * Interface for the database access object responsible for accessing the Travellers table.
 *
 * @author Kenny Zhang
 */

interface AccessTravellers {
    List<Traveller> getTravellers();

    boolean insertTraveller(Traveller traveller);

    boolean updateTraveller(Traveller traveller, int newID, String newName);

    boolean deleteTraveller(Traveller traveller);
}
