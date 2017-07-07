package ca.umanitoba.cs.comp3350.saveonflight.integration;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import ca.umanitoba.cs.comp3350.saveonflight.application.Main;
import ca.umanitoba.cs.comp3350.saveonflight.business.AccessAirports;
import ca.umanitoba.cs.comp3350.saveonflight.business.AccessAirportsImpl;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Airport;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

public class AirportsBusinessPersistenceSeamTest {
    private static AccessAirports access;

    @BeforeClass
    public static void setUp() {
        Main.startUp(Main.DatabaseType.HSQL);
        access = new AccessAirportsImpl(Main.getAirportAccess());
    }

    @AfterClass
    public static void tearDown() {
        access = null;
    }

    @Test
    public void testGetAirports() {
        List<Airport> airports = access.getAirports();
        assertNotNull(airports);
        for (Airport a : airports) {
            assertNotNull(a);
            assertNotNull(a.getAirportCode());
            assertFalse(a.getAirportCode().isEmpty());
        }
    }

    @Test
    public void testFindAirportByName() {
        // Valid searches
        Airport ywg = access.findAirportByName("Winnipeg YWG");
        assertNotNull(ywg);
        assertEquals("Winnipeg YWG", ywg.getAirportCode());

        Airport yyz = access.findAirportByName("Toronto YYZ");
        assertNotNull(yyz);
        assertEquals("Toronto YYZ", yyz.getAirportCode());

        Airport yyc = access.findAirportByName("Calgary YYC");
        assertNotNull(yyz);
        assertEquals("Calgary YYC", yyc.getAirportCode());

        // Invalid searches - not in db
        Airport pek = access.findAirportByName("Beijing PEK");
        assertTrue(pek == null);

        Airport ywgMisspelled = access.findAirportByName("Winipeg YWG");
        assertTrue(ywgMisspelled == null);

        Airport yyzMisspelled = access.findAirportByName("Toaronto YYZ");
        assertTrue(yyzMisspelled == null);

        Airport resultOfNullSearch = access.findAirportByName(null);
        assertTrue(resultOfNullSearch == null);

        Airport resultOfEmptySearch = access.findAirportByName("");
        assertTrue(resultOfEmptySearch == null);
    }

}
