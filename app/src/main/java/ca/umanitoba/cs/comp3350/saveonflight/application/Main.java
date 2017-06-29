package ca.umanitoba.cs.comp3350.saveonflight.application;

/**
 * Created by zhengyugu on 2017-06-29.
 */

public class Main {
    public static final String dbName = "SOF";
    private static String dbPathName = "database/SOF";
    public static void main(String[] args){
        startUp();
        shutDown();
        System.out.println("all done");
    }

    public static void startUp()
    {

        Services.createFlightAccess(dbName);
        Services.createAirlineAccess(dbName);
        Services.createAirportAccess(dbName);
        Services.createBookedFlightAccess(dbName);
        Services.createTravellerAccess(dbName);
    }

    public static void shutDown()
    {
        Services.closeFlightAccess();
    }

    public static String getDBPathName() {
        return dbPathName;
    }

    public static void setDBPathName(String pathName) {
        System.out.println("Setting DB path to: " + pathName);
        dbPathName = pathName;
    }

}
