package ca.umanitoba.cs.comp3350.saveonflight.persistence;

import java.util.ArrayList;

import ca.umanitoba.cs.comp3350.saveonflight.objects.Traveller;

/**
 * Created by zhang on 2017-06-29.
 */

public interface TravellerAccess extends DataAccess<Traveller> {
    public ArrayList<Traveller> getTravellers();
    boolean add(Traveller t);
}
