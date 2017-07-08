package ca.umanitoba.cs.comp3350.saveonflight.integration;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.notification.RunListener;
import org.junit.runner.notification.RunListener.ThreadSafe;

import java.util.List;

import ca.umanitoba.cs.comp3350.saveonflight.application.Main;
import ca.umanitoba.cs.comp3350.saveonflight.business.AccessTravellers;
import ca.umanitoba.cs.comp3350.saveonflight.business.AccessTravellersImpl;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Traveller;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

public class TravellerBusinessPersistenceSeamTest {
    private static AccessTravellers access;

    @BeforeClass
    public static void setUp() {
        Main.startUp(Main.DatabaseType.HSQL);
        access = new AccessTravellersImpl(Main.getTravellerAccess());
    }

    @AfterClass
    public static void tearDown() {
        access = null;
    }

    @Test
    public void testGetAll() {
        List<Traveller> travellers = access.getTravellers();
        assertNotNull(travellers);
        for (Traveller t : travellers) {
            assertTrue(t.getTravellerID() > -1);
            assertNotNull(t.getName());
            assertFalse(t.getName().isEmpty());
        }
    }

    @Test
    public void testAddAndRemove() {
        Traveller valid = new Traveller(access.getMaxId() + 1, "Bob Bob");
        Traveller invalid = new Traveller(5, null);
        Traveller alsoInvalid = new Traveller(6, "");

        // Additions
        assertTrue(access.insertTraveller(valid));
        assertFalse(access.insertTraveller(invalid));
        assertFalse(access.insertTraveller(null));
        assertFalse(access.insertTraveller(alsoInvalid));

        // Removals
        assertTrue(access.removeTraveller(new Traveller(access.getMaxId(), "name"))); // Remove the most recently added Traveller
        assertFalse(access.removeTraveller(invalid));
        assertFalse(access.removeTraveller(null));
        assertFalse(access.removeTraveller(alsoInvalid));
    }

    @Test
    public void testGetMaxId() {
        // In the default db, the max id should be 2
        assertEquals(2, access.getMaxId());

        // Adding a Traveller should increase the max id
        Traveller t = new Traveller(access.getMaxId() + 1, "Sam Sam");
        assertTrue(access.insertTraveller(t));
        assertEquals(3, access.getMaxId());

        // And removing a Traveller should decrease it
        assertTrue(access.removeTraveller(t));
        assertEquals(2, access.getMaxId());
    }
}
