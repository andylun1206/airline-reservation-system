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
    /**
     * @return a List of all Travellers in the database
     */
    List<Traveller> getTravellers();

    Traveller getTraveller(int travellerId);

    /**
     * Inserts a new Traveller into the database.
     *
     * @param traveller the Traveller to insert
     * @return true if the Traveller was inserted; false otherwise
     */
    boolean insertTraveller(Traveller traveller);

    /**
     * Removes a Traveller from the database.
     *
     * @param traveller the Traveller to remove
     * @return true if the Traveller was removed; false otherwise
     */
    boolean removeTraveller(Traveller traveller);

    /**
     * @return the max value in the ID column of the Traveller table
     */
    int getMaxId();
}
