package ca.umanitoba.cs.comp3350.saveonflight.persistence;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import ca.umanitoba.cs.comp3350.saveonflight.application.Main;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Airport;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

/**
 * Created by longyu on 2017-06-29.
 */

public class AirportTableSqlTest {
    private static List<Airport> original;
    private static AirportTableSql airportTable;

    @BeforeClass
    public static void setUp() {
        airportTable = new AirportTableSql();
        airportTable.initialize(Main.getDBPathName());
        original = airportTable.getAirports();
    }

    @Test
    public void testFindAirportNull() {
        assertEquals("Test null fail", null, airportTable.findAirport(null));
    }

    @Test
    public void testFindAirportEmpty() {
        assertEquals("Test empty fail", null, airportTable.findAirport(""));
    }

    @Test
    public void testExistence() {
        assertNotNull("Initialize is not work", airportTable);
    }

}
