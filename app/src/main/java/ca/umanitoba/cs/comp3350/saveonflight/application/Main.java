package ca.umanitoba.cs.comp3350.saveonflight.application;

public class Main {
    public static final String DB_NAME = "SAVE ON FLIGHT";

    public static void startUp() {
        Services.createDataAccess(DB_NAME);
    }

    public static void shutDown() {
        Services.closeDataAccess();
    }
}
