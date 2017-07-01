package ca.umanitoba.cs.comp3350.saveonflight.application;

import ca.umanitoba.cs.comp3350.saveonflight.objects.Airport;
import ca.umanitoba.cs.comp3350.saveonflight.persistence.AirlineAccess;
import ca.umanitoba.cs.comp3350.saveonflight.persistence.AirlineTableSql;
import ca.umanitoba.cs.comp3350.saveonflight.persistence.AirportAccess;
import ca.umanitoba.cs.comp3350.saveonflight.persistence.AirportTableSql;
import ca.umanitoba.cs.comp3350.saveonflight.persistence.BookedFlightAccess;
import ca.umanitoba.cs.comp3350.saveonflight.persistence.BookedFlightTableSql;
import ca.umanitoba.cs.comp3350.saveonflight.persistence.DataAccess;
import ca.umanitoba.cs.comp3350.saveonflight.persistence.FlightAccess;
import ca.umanitoba.cs.comp3350.saveonflight.persistence.FlightTableSql;
import ca.umanitoba.cs.comp3350.saveonflight.persistence.TravellerAccess;
import ca.umanitoba.cs.comp3350.saveonflight.persistence.TravellerTableSql;

/**
 * Created by Kenny on 2017-06-23.
 */

public class Services {
    private static FlightAccess flightAccessService = null;
    private static AirportAccess airportAccessService = null;
    private static AirlineAccess airlineAccessService = null;
    private static TravellerAccess travellerAccessService = null;
    private static BookedFlightAccess bookedFlightAccessService = null;

    public static FlightAccess createFlightAccess() {
        if (flightAccessService == null) {
            flightAccessService = new FlightTableSql();
            flightAccessService.initialize(Main.getDBPathName());
        }
        return flightAccessService;
    }

    public static AirportAccess createAirportAccess() {
        if (airportAccessService == null) {
            airportAccessService = new AirportTableSql();
            airportAccessService.initialize(Main.getDBPathName());
        }
        return airportAccessService;
    }

    public static AirlineAccess createAirlineAccess() {
        if (airlineAccessService == null) {
            airlineAccessService = new AirlineTableSql();
            airlineAccessService.initialize(Main.getDBPathName());
        }
        return airlineAccessService;
    }

    public static TravellerAccess createTravellerAccess() {
        if (travellerAccessService == null) {
            travellerAccessService = new TravellerTableSql();
            travellerAccessService.initialize(Main.getDBPathName());
        }
        return travellerAccessService;
    }

    public static BookedFlightAccess createBookedFlightAccess() {
        if (bookedFlightAccessService == null) {
            bookedFlightAccessService = new BookedFlightTableSql();
            bookedFlightAccessService.initialize(Main.getDBPathName());
        }
        return bookedFlightAccessService;
    }

    public static void closeFlightAccess() {
        if (flightAccessService != null) {
            flightAccessService.close();
        }
        flightAccessService = null;
    }

    public static void closeAirportAccess() {
        if (airportAccessService != null) {
            airportAccessService.close();
        }
        airportAccessService = null;
    }

    public static void closeAirlineAccess() {
        if (airlineAccessService != null) {
            airlineAccessService.close();
        }
        airlineAccessService = null;
    }

    public static void closeTravellerAccess() {
        if (travellerAccessService != null) {
            travellerAccessService.close();
        }
        travellerAccessService = null;
    }

    public static void closeBookedFlightAccess() {
        if (bookedFlightAccessService != null) {
            bookedFlightAccessService.close();
        }
        bookedFlightAccessService = null;
    }

}
