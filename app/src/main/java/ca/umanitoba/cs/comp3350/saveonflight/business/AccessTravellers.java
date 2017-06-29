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

public interface AccessTravellers {
    List<Traveller> getTravellers();

    boolean insertTraveller(Traveller traveller);
}
