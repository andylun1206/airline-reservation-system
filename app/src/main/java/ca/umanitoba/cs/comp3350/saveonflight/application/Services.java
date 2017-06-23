package ca.umanitoba.cs.comp3350.saveonflight.application;

import ca.umanitoba.cs.comp3350.saveonflight.business.AccessAirlines;
import ca.umanitoba.cs.comp3350.saveonflight.business.AccessAirlinesImpl;
import ca.umanitoba.cs.comp3350.saveonflight.business.AccessAirports;
import ca.umanitoba.cs.comp3350.saveonflight.business.AccessAirportsImpl;
import ca.umanitoba.cs.comp3350.saveonflight.business.AccessBookedFlightsImpl;
import ca.umanitoba.cs.comp3350.saveonflight.business.AccessFlightsImpl;
import ca.umanitoba.cs.comp3350.saveonflight.business.AccessTravellersImpl;

/**
 * Created by Kenny on 2017-06-23.
 */

public class Services {

    public static void openDatabase() {
        new AccessAirlinesImpl();
        new AccessAirportsImpl();
        new AccessFlightsImpl();
        new AccessTravellersImpl();
        new AccessBookedFlightsImpl();
    }
}
