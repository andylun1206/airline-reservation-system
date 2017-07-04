package ca.umanitoba.cs.comp3350.saveonflight.application;

import ca.umanitoba.cs.comp3350.saveonflight.objects.Airport;
import ca.umanitoba.cs.comp3350.saveonflight.persistence.AirlineAccess;
import ca.umanitoba.cs.comp3350.saveonflight.persistence.AirlineTable;
import ca.umanitoba.cs.comp3350.saveonflight.persistence.AirlineTableSql;
import ca.umanitoba.cs.comp3350.saveonflight.persistence.AirportAccess;
import ca.umanitoba.cs.comp3350.saveonflight.persistence.AirportTable;
import ca.umanitoba.cs.comp3350.saveonflight.persistence.AirportTableSql;
import ca.umanitoba.cs.comp3350.saveonflight.persistence.BookedFlightAccess;
import ca.umanitoba.cs.comp3350.saveonflight.persistence.BookedFlightTable;
import ca.umanitoba.cs.comp3350.saveonflight.persistence.BookedFlightTableSql;
import ca.umanitoba.cs.comp3350.saveonflight.persistence.DataAccess;
import ca.umanitoba.cs.comp3350.saveonflight.persistence.FlightAccess;
import ca.umanitoba.cs.comp3350.saveonflight.persistence.FlightTable;
import ca.umanitoba.cs.comp3350.saveonflight.persistence.FlightTableSql;
import ca.umanitoba.cs.comp3350.saveonflight.persistence.TravellerAccess;
import ca.umanitoba.cs.comp3350.saveonflight.persistence.TravellerTable;
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

    /**
     * Initializes the stub database
     */
    public static void initializeStubDatabase() {
        new AirlineTable().initialize("");
        new AirportTable().initialize("");
        new FlightTable().initialize("");
        new TravellerTable().initialize("");
        new BookedFlightTable().initialize("");
    }

    /**
     * Creates connection to the Flight table if it has not been created yet.
     *
     * @return the connection to the Flight table
     */
    public static FlightAccess createFlightAccess() {
        if (flightAccessService == null) {
            flightAccessService = new FlightTableSql();
            flightAccessService.initialize(Main.getDBPathName());
        }
        return flightAccessService;
    }

    /**
     * Creates connection to the Airport table if it has not been created yet.
     *
     * @return the connection to the Airport table.
     */
    public static AirportAccess createAirportAccess() {
        if (airportAccessService == null) {
            airportAccessService = new AirportTableSql();
            airportAccessService.initialize(Main.getDBPathName());
        }
        return airportAccessService;
    }

    /**
     * Creates the connection to the Airline table if it has not been created yet.
     *
     * @return the connection to the Airline Table.
     */
    public static AirlineAccess createAirlineAccess() {
        if (airlineAccessService == null) {
            airlineAccessService = new AirlineTableSql();
            airlineAccessService.initialize(Main.getDBPathName());
        }
        return airlineAccessService;
    }

    /**
     * Creates the connection to the Traveller table if it has not been created yet.
     *
     * @return the connection to the Traveller table.
     */
    public static TravellerAccess createTravellerAccess() {
        if (travellerAccessService == null) {
            travellerAccessService = new TravellerTableSql();
            travellerAccessService.initialize(Main.getDBPathName());
        }
        return travellerAccessService;
    }

    /**
     * Creates the connection to the BookedFlight table if it has not been created yet.
     *
     * @return the connection to the BookedFlight table.
     */
    public static BookedFlightAccess createBookedFlightAccess() {
        if (bookedFlightAccessService == null) {
            bookedFlightAccessService = new BookedFlightTableSql();
            bookedFlightAccessService.initialize(Main.getDBPathName());
        }
        return bookedFlightAccessService;
    }

    /**
     * Closes the connection to the Flight table.
     */
    public static void closeFlightAccess() {
        if (flightAccessService != null) {
            flightAccessService.close();
        }
        flightAccessService = null;
    }

    /**
     * Closes the connection to the Airport table.
     */
    public static void closeAirportAccess() {
        if (airportAccessService != null) {
            airportAccessService.close();
        }
        airportAccessService = null;
    }

    /**
     * Closes the connection to the Airline table.
     */
    public static void closeAirlineAccess() {
        if (airlineAccessService != null) {
            airlineAccessService.close();
        }
        airlineAccessService = null;
    }

    /**
     * Closes the connection to the Traveller table.
     */
    public static void closeTravellerAccess() {
        if (travellerAccessService != null) {
            travellerAccessService.close();
        }
        travellerAccessService = null;
    }

    /**
     * Closes the connection to the BookedFlight table.
     */
    public static void closeBookedFlightAccess() {
        if (bookedFlightAccessService != null) {
            bookedFlightAccessService.close();
        }
        bookedFlightAccessService = null;
    }

}
