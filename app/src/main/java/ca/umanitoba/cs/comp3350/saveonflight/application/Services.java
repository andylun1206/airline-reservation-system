package ca.umanitoba.cs.comp3350.saveonflight.application;

import ca.umanitoba.cs.comp3350.saveonflight.business.AccessAirlinesImpl;
import ca.umanitoba.cs.comp3350.saveonflight.business.AccessAirportsImpl;
import ca.umanitoba.cs.comp3350.saveonflight.business.AccessBookedFlightsImpl;
import ca.umanitoba.cs.comp3350.saveonflight.business.AccessFlightsImpl;
import ca.umanitoba.cs.comp3350.saveonflight.business.AccessTravellersImpl;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Airline;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Airport;
import ca.umanitoba.cs.comp3350.saveonflight.persistence.DataAccess;
import ca.umanitoba.cs.comp3350.saveonflight.persistence.FlightAccess;
import ca.umanitoba.cs.comp3350.saveonflight.persistence.TravellerAccess;
import ca.umanitoba.cs.comp3350.saveonflight.persistence.BookedFlightAccess;

import ca.umanitoba.cs.comp3350.saveonflight.persistence.FlightTableSql;
/**
 * Created by Kenny on 2017-06-23.
 */

public class Services {
    private static FlightAccess flightAccessService = null;
    private static DataAccess<Airport> airportAccessService = null;
    private static DataAccess<Airline> airlineAccessService = null;
    private static TravellerAccess travellerAccessService = null;
    private static BookedFlightAccess bookedFlightAccessService = null;
    public static void openDatabase() {
        new AccessAirlinesImpl();
        new AccessAirportsImpl();
        new AccessFlightsImpl();
        new AccessTravellersImpl();
        new AccessBookedFlightsImpl();
    }
    public static FlightAccess createFlightAccess(String dbName)
    {
        if (flightAccessService == null)
        {
            flightAccessService = new FlightTableSql();
            flightAccessService.initialize(Main.getDBPathName());
            //(Main.getDBPathName());
        }
        return flightAccessService;
    }

    public static void closeFlightAccess()
    {
        if (flightAccessService != null)
        {
            flightAccessService.close();
        }
        flightAccessService = null;
    }

}
