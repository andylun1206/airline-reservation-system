package ca.umanitoba.cs.comp3350.saveonflight.persistence;

/**
 * Created by zhengyugu on 2017-06-08.
 */

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import ca.umanitoba.cs.comp3350.saveonflight.objects.Traveller;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

public class TravellerTableTest {
    private static List<Traveller> original;
    private static TravellerAccess travellerTable;
    private Traveller emptyNameCase = new Traveller(0, "");
    private Traveller validCase = new Traveller(10, "Amir");

    public static void setTravellerAccess(TravellerAccess t) {
        travellerTable = t;
    }

    @BeforeClass
    public static void setUp() {
        if (travellerTable == null) {
            travellerTable = new TravellerTable();
            travellerTable.initialize();
            original = travellerTable.getTravellers();
        }
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
        travellerTable.add(null);
        assertEquals("insertBookedFlight null but actually insertBookedFlight something", original, travellerTable.getTravellers());
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

    @Test
    public void testAddDuplicate() {
        Traveller t = new Traveller(15, "Amir");
        assertTrue(travellerTable.add(t));
    }
}
