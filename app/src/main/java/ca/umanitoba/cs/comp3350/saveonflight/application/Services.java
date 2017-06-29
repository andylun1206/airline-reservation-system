package ca.umanitoba.cs.comp3350.saveonflight.application;

import ca.umanitoba.cs.comp3350.saveonflight.business.AccessAirlines;
import ca.umanitoba.cs.comp3350.saveonflight.business.AccessAirlinesImpl;
import ca.umanitoba.cs.comp3350.saveonflight.business.AccessAirports;
import ca.umanitoba.cs.comp3350.saveonflight.business.AccessAirportsImpl;
import ca.umanitoba.cs.comp3350.saveonflight.business.AccessBookedFlightsImpl;
import ca.umanitoba.cs.comp3350.saveonflight.business.AccessFlightsImpl;
import ca.umanitoba.cs.comp3350.saveonflight.business.AccessTravellersImpl;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Flight;
import ca.umanitoba.cs.comp3350.saveonflight.persistence.DataAccess;
import ca.umanitoba.cs.comp3350.saveonflight.persistence.FlightAccess;
import ca.umanitoba.cs.comp3350.saveonflight.persistence.FlightTableSql;
/**
 * Created by Kenny on 2017-06-23.
 */

public class Services {
    private static FlightAccess dataAccessService = null;

    public static void openDatabase() {
        new AccessAirlinesImpl();
        new AccessAirportsImpl();
        new AccessFlightsImpl();
        new AccessTravellersImpl();
        new AccessBookedFlightsImpl();
    }
    public static FlightAccess createFlightAccess(String dbName)
    {
        if (dataAccessService == null)
        {
            dataAccessService = new FlightTableSql();
            dataAccessService.initialize(Main.getDBPathName());
            //(Main.getDBPathName());
        }
        return dataAccessService;
    }

    public static void closeFlightAccess()
    {
        if (dataAccessService != null)
        {
            dataAccessService.close();
        }
        dataAccessService = null;
    }

}
