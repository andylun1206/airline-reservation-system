package ca.umanitoba.cs.comp3350.saveonflight.persistence;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.JUnitCore;

import ca.umanitoba.cs.comp3350.saveonflight.application.Main;

public class RunPersistenceTestsSQL {

    @BeforeClass
    public static void setUp() {
        Main.startUp(Main.DatabaseType.HSQL);
        AirlineTableTest.setAirlineAccess(Main.getAirlineAccess());
        AirportTableTest.setAirportAccess(Main.getAirportAccess());
        BookedFlightTableTest.setBookedFlightAccess(Main.getBookedFlightAccess());
        FlightTableTest.setFlightAccess(Main.getFlightAccess());
        TravellerTableTest.setTravellerAccess(Main.getTravellerAccess());
    }

    @AfterClass
    public static void tearDown() {
        AirlineTableTest.setAirlineAccess(null);
        AirportTableTest.setAirportAccess(null);
        BookedFlightTableTest.setBookedFlightAccess(null);
        FlightTableTest.setFlightAccess(null);
        TravellerTableTest.setTravellerAccess(null);
    }

    @Test
    public void testAirlineTable() {
        JUnitCore.runClasses(AirlineTableTest.class);
    }

    @Test
    public void testAirportTable() {
        JUnitCore.runClasses(AirportTableTest.class);
    }

    @Test
    public void testBookedFlightTable() {
        JUnitCore.runClasses(BookedFlightTableTest.class);
    }

    @Test
    public void testFlightTable() {
        JUnitCore.runClasses(FlightTableTest.class);
    }

    @Test
    public void testTravellerTable() {
        JUnitCore.runClasses(TravellerTableTest.class);
    }

}
