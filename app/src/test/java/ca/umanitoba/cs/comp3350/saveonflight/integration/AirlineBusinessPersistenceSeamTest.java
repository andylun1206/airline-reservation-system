package ca.umanitoba.cs.comp3350.saveonflight.integration;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import ca.umanitoba.cs.comp3350.saveonflight.application.Main;
import ca.umanitoba.cs.comp3350.saveonflight.business.AccessAirlines;
import ca.umanitoba.cs.comp3350.saveonflight.business.AccessAirlinesImpl;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Airline;

import static org.junit.Assert.*;

public class AirlineBusinessPersistenceSeamTest {
    private static AccessAirlines access;

    @BeforeClass
    public static void setUp() {
        Main.startUp(Main.DatabaseType.HSQL);
        access = new AccessAirlinesImpl(Main.getAirlineAccess());
    }

    @AfterClass
    public static void tearDown() {
        access = null;
    }

    @Test
    public void testGetAirlines() {
        List<Airline> airlines = access.getAirlines();
        assertNotNull(airlines);
        for (Airline a : airlines) {
            assertNotNull(a);
            assertEquals(Airline.class, a.getClass());
            assertNotNull(a.getName());
            assertFalse(a.getName().isEmpty());
        }
    }

    @Test
    public void testFindAirlineByName() {
        // Valid searches
        Airline westJet = access.getAirlineByName("WestJet");
        assertNotNull(westJet);
        assertNotNull(westJet.getName());
        assertEquals("WestJet", westJet.getName());

        Airline airCanada = access.getAirlineByName("Air Canada");
        assertNotNull(airCanada);
        assertNotNull(airCanada.getName());
        assertEquals("Air Canada", airCanada.getName());

        // Invalid searches
        Airline shouldBeNull = access.getAirlineByName(null);
        assertTrue(shouldBeNull == null);

        shouldBeNull = access.getAirlineByName("");
        assertTrue(shouldBeNull == null);

        shouldBeNull = access.getAirlineByName("sadasjkhfawew4we989898");
        assertTrue(shouldBeNull == null);

        shouldBeNull = access.getAirlineByName("AirJet");
        assertTrue(shouldBeNull == null);
    }

}
