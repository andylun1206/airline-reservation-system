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

    /**
     * Open connection to the database.
     * @param dbName Name of the database to open.
     * @return Returns an instance of the DB.
     */
    public static DataAccessStub createDataAccess(String dbName) {
        if (dataAccessService == null) {
            dataAccessService = new DataAccessStub(dbName);
            dataAccessService.open(Main.DB_NAME);
        }
        return dataAccessService;
    }

    /**
     * @param dbName The name of the DB to open.
     * @return Returns an instance of the current DB instance.
     */
    public static DataAccessStub getDataAccess(String dbName) {
        if (dataAccessService == null) {
            System.out.println("Connection to data access has not been established.");
            System.exit(1);
        }
        return dataAccessService;
    }

    /**
     * Close the connection to the DB.
     */
    public static void closeDataAccess() {
        if (dataAccessService != null) {
            dataAccessService.close();
        }
        dataAccessService = null;
    }
}
