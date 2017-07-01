package ca.umanitoba.cs.comp3350.saveonflight.persistence;

import java.util.List;

import ca.umanitoba.cs.comp3350.saveonflight.objects.Traveller;

/**
 * Created by zhang on 2017-06-29.
 */

public interface TravellerAccess extends DataAccess<Traveller> {
    List<Traveller> getTravellers();

    /**
     * Insert a new Traveller into the database
     *
     * @param t the Traveller to insert into the database
     * @return the ID of the Traveller (this column is auto-incremented)
     */
    int add(Traveller t);
}
