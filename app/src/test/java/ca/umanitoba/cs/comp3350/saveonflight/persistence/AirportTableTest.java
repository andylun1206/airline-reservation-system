package ca.umanitoba.cs.comp3350.saveonflight.persistence;

/**
 * Created by zhengyugu on 2017-06-08.
 */

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;

import ca.umanitoba.cs.comp3350.saveonflight.objects.Airport;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;


public class AirportTableTest {
    private static ArrayList<Airport> original;
    private static DataAccess<Airport> airportTable;

    @BeforeClass
    public static void setUp() {
        airportTable = new AirportTable();
        airportTable.initialize("");
        original = AirportTable.getAirports();
    }

    @Test
    public void testExistence() {
        assertNotNull("Initialize is not work", airportTable);
    }

    @Test
    public void testInitialize() {
        assertEquals("Initialize is not work", original, AirportTable.getAirports());
    }
}
