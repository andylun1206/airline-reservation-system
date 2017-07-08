package ca.umanitoba.cs.comp3350.saveonflight.persistence;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;

import ca.umanitoba.cs.comp3350.saveonflight.application.Main;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Traveller;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
/**
 * Created by longyu on 2017-06-29.
 */

public class TravellerTableSqlTest {
    private static ArrayList<Traveller> original;
    private static TravellerTableSql travellerTable;
    private Traveller emptyNameCase = new Traveller(0, "");
    private Traveller validCase = new Traveller(10, "Amir");

    @BeforeClass
    public static void setUp() {
        travellerTable = new TravellerTableSql();
        travellerTable.initialize(Main.getDBPathName());
        original = travellerTable.getTravellers();
    }

    @Test
    public void testExistence() {
        assertNotNull("Initialize is not work ALL NULL", travellerTable);
    }

    @Test
    public void testInitialize() {
        assertEquals("Initialize is not work", original, travellerTable.getTravellers());
    }

    @Test
    public void testAddNull() {
        assertFalse("insertBookedFlight null but actually insertBookedFlight something", travellerTable.add(null));
    }

    @Test
    public void testAddEmptyName() {
        travellerTable.add(emptyNameCase);
        assertEquals("adding none since object but it shouldn't insertBookedFlight", original, travellerTable.getTravellers());
    }

    @Test
    public void testAddValid() {
        assertTrue(travellerTable.add(validCase));
    }
}
