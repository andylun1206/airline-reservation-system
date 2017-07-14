package ca.umanitoba.cs.comp3350.saveonflight.persistence;

/**
 * Created by zhengyugu on 2017-06-08.
 */

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import ca.umanitoba.cs.comp3350.saveonflight.objects.Airport;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;


public class AirportTableTest {
    private static List<Airport> original;
    private static AirportAccess airportTable;

    public static void setAirportAccess(AirportAccess a) {
        airportTable = a;
    }

    @BeforeClass
    public static void setUp() {
        if (airportTable == null) {
            airportTable = new AirportTable();
            airportTable.initialize();
            original = airportTable.getAirports();
        }
    }

    @Test
    public void testExistence() {
        assertNotNull("Initialize is not work", airportTable);
    }

    @Test
    public void testInitialize() {
        assertEquals("Initialize is not work", original, airportTable.getAirports());
    }
}
