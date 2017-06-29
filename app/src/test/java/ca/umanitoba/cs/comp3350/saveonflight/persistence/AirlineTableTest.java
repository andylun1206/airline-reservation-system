package ca.umanitoba.cs.comp3350.saveonflight.persistence;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;

import ca.umanitoba.cs.comp3350.saveonflight.R;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Airline;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;


/**
 * Created by zhengyugu on 2017-06-08.
 */

public class AirlineTableTest {
    private static ArrayList<Airline> original;
    private static DataAccess<Airline> airlineTable;

    @BeforeClass
    public static void setUp() {
        airlineTable = new AirlineTable();
        airlineTable.initialize();
        original = AirlineTable.getAirlines();
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
