package ca.umanitoba.cs.comp3350.saveonflight.application;


import ca.umanitoba.cs.comp3350.saveonflight.persistence.DataAccessStub;

public class Services {
    private static DataAccessStub dataAccessService = null;

    public static DataAccessStub createDataAccess(String dbName) {
        if (dataAccessService == null) {
            // TODO: Note that the DataAccessStub is currently not implemented
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
            // TODO: implement close() method
            dataAccessService.close();
        }
        dataAccessService = null;
    }
}
