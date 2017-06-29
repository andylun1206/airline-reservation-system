package ca.umanitoba.cs.comp3350.saveonflight.persistence;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import ca.umanitoba.cs.comp3350.saveonflight.R;
import ca.umanitoba.cs.comp3350.saveonflight.application.Main;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Airline;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

/**
 * Created by longyu on 2017-06-29.
 */

public class AirlineTableSqlTest {

    private static ArrayList<Airline> original;
    private static AirlineTableSql airlineTable;

    @BeforeClass
    public static void setUp() {
        airlineTable = new AirlineTableSql();
        airlineTable.initialize(Main.getDBPathName());
        //original = airlineTable.getAirlines();
    }

    @Test
    public void testFindAirlineNull() {
        assertEquals("Test null fail", null, airlineTable.findAirline(null));
    }

    @Test
    public void testFindAirlineEmpty() {
        assertEquals("Test empty fail", null, airlineTable.findAirline(""));
    }

    @Test
    public void testFindAirlineSample() {
        assertEquals("Test sample fail", null, airlineTable.findAirline("AirCanada"));
    }

    @Test
    public void testAddNull() {
        assertEquals("Test null fail", null, airlineTable.add(null));
        Airline airline;
    }

    @Test
    public void testAddEmpty() {
        assertEquals("Test empty fail", null, airlineTable.add(new Airline("")));
        Airline airline;
    }

    @Test
    public void testAddSample() {
        assertTrue("Test sample fail", airlineTable.add(new Airline("WinnipegAirline")));
        Airline airline;
    }

    @Test
    public void testExistence() {
        assertNotNull(airlineTable);
    }

    @Test
    public void testInitialize() {
        assertEquals("Initialize is not work", original, AirlineTable.getAirlines());
    }

}
