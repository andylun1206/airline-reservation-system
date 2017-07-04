package ca.umanitoba.cs.comp3350.saveonflight.application;

import ca.umanitoba.cs.comp3350.saveonflight.persistence.AirlineAccess;
import ca.umanitoba.cs.comp3350.saveonflight.persistence.AirlineTable;
import ca.umanitoba.cs.comp3350.saveonflight.persistence.AirportAccess;
import ca.umanitoba.cs.comp3350.saveonflight.persistence.AirportTable;
import ca.umanitoba.cs.comp3350.saveonflight.persistence.BookedFlightAccess;
import ca.umanitoba.cs.comp3350.saveonflight.persistence.BookedFlightTable;
import ca.umanitoba.cs.comp3350.saveonflight.persistence.FlightAccess;
import ca.umanitoba.cs.comp3350.saveonflight.persistence.FlightTable;
import ca.umanitoba.cs.comp3350.saveonflight.persistence.TravellerAccess;
import ca.umanitoba.cs.comp3350.saveonflight.persistence.TravellerTable;

import static ca.umanitoba.cs.comp3350.saveonflight.application.Main.DatabaseType.HSQL;
import static ca.umanitoba.cs.comp3350.saveonflight.application.Main.DatabaseType.STUB;

/**
 * Created by zhengyugu on 2017-06-29.
 */

public class Main {
    public enum DatabaseType {
        STUB, HSQL
    }

    public static DatabaseType dbType;
    public static final String dbName = "SOF";
    private static String dbPathName = "database/SOF";

    public static void main(String[] args) {
        startUp(HSQL);
        shutDown();
        System.out.println("all done");
    }

    /**
     * Opens the connection to either the stub database or the real HSQL database.
     *
     * @param databaseType the type of database to use (stub database or real HSQL database)
     */
    public static void startUp(DatabaseType databaseType) {
        dbType = databaseType;
        if (databaseType == STUB) {
            Services.initializeStubDatabase();
        } else {
            Services.createFlightAccess();
            Services.createAirlineAccess();
            Services.createAirportAccess();
            Services.createTravellerAccess();
            Services.createBookedFlightAccess();
        }
    }

    /**
     * @return the connection to the Flight table.
     */
    public static FlightAccess getFlightAccess() {
        FlightAccess access = null;
        switch (dbType) {
            case STUB:
                access = new FlightTable();
                break;
            case HSQL:
                access = Services.createFlightAccess();
                break;
        }
        return access;
    }

    /**
     * @return the connection to the Airline table.
     */
    public static AirlineAccess getAirlineAccess() {
        AirlineAccess access = null;
        switch (dbType) {
            case STUB:
                access = new AirlineTable();
                break;
            case HSQL:
                access = Services.createAirlineAccess();
                break;
        }
        return access;
    }

    /**
     * @return the connection to the Airport table.
     */
    public static AirportAccess getAirportAccess() {
        AirportAccess access = null;
        switch (dbType) {
            case STUB:
                access = new AirportTable();
                break;
            case HSQL:
                access = Services.createAirportAccess();
                break;
        }
        return access;
    }

    /**
     * @return the connection to the Traveller table.
     */
    public static TravellerAccess getTravellerAccess() {
        TravellerAccess access = null;
        switch (dbType) {
            case STUB:
                access = new TravellerTable();
                break;
            case HSQL:
                access = Services.createTravellerAccess();
                break;
        }
        return access;
    }

    /**
     * @return the connection to the BookedFlight table.
     */
    public static BookedFlightAccess getBookedFlightAccess() {
        BookedFlightAccess access = null;

        switch (dbType) {
            case STUB:
                access = new BookedFlightTable();
                break;
            case HSQL:
                access = Services.createBookedFlightAccess();
                break;
        }

        return access;
    }


    /**
     * Closes the connection to the database.
     */
    public static void shutDown() {
        Services.closeFlightAccess(); // TODO: refactor close() method (we only need one of them)
        //Services.closeAirlineAccess();
        //Services.closeAirportAccess();
        //Services.closeTravellerAccess();
        //Services.closeBookedFlightAccess();
    }

    /**
     * @return the path to the database
     */
    public static String getDBPathName() {
        return dbPathName;
    }

    /**
     * Sets the database path.
     *
     * @param pathName the path to the database
     */
    public static void setDBPathName(String pathName) {
        System.out.println("Setting DB path to: " + pathName);
        dbPathName = pathName;
    }

}
