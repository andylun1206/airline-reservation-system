package ca.umanitoba.cs.comp3350.saveonflight.application;

import ca.umanitoba.cs.comp3350.saveonflight.persistence.DataAccessStub;

/**
 * Services.java
 * <p>
 * Provides facilities for opening and closing the connection the the database.
 *
 * @author Kenny Zhang
 */

public class Services {
    private static DataAccessStub dataAccessService = null;

    public static DataAccessStub createDataAccess(String dbName) {
        if (dataAccessService == null) {
            dataAccessService = new DataAccessStub(dbName);
            dataAccessService.open(Main.DB_NAME);
        }
        return dataAccessService;
    }

    public static DataAccessStub getDataAccess(String dbName) {
        if (dataAccessService == null) {
            System.out.println("Connection to data access has not been established.");
            System.exit(1);
        }
        return dataAccessService;
    }

    public static void closeDataAccess() {
        if (dataAccessService != null) {
            dataAccessService.close();
        }
        dataAccessService = null;
    }
}
