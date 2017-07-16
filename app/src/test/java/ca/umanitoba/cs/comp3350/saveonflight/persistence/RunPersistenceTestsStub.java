package ca.umanitoba.cs.comp3350.saveonflight.persistence;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.JUnitCore;

import ca.umanitoba.cs.comp3350.saveonflight.application.Main;

public class RunPersistenceTestsStub {

    @Before
    public void setUp() {
        Main.startUp(Main.DatabaseType.STUB);
        AirlineTableTest.setAirlineAccess(Main.getAirlineAccess());
        AirportTableTest.setAirportAccess(Main.getAirportAccess());
        BookedFlightTableTest.setBookedFlightAccess(Main.getBookedFlightAccess());
        FlightTableTest.setFlightAccess(Main.getFlightAccess());
        TravellerTableTest.setTravellerAccess(Main.getTravellerAccess());
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
