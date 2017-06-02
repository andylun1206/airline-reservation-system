package ca.umanitoba.cs.comp3350.saveonflight.objects;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

public class TravellerTest {
    private Traveller traveller;
    private final int ID = 123;
    private final String NAME = "Bob Bob";

    @Before
    public void setUp() {
        traveller = new Traveller(ID, NAME);
    }

    @After
    public void tearDown() {
        traveller = null;
    }

    @Test
    public void testExistence() {
        assertNotNull(traveller);
    }

    @Test
    public void testGetID() {
        assertTrue(ID == traveller.getTravellerID());
        assertFalse(321 == traveller.getTravellerID());
    }

    @Test
    public void testGetName() {
        assertTrue(NAME.equals(traveller.getName()));
    }

    @Test
    public void testSetName() {
        final String NEW_NAME = "John John";
        traveller.setName(NEW_NAME);
        assertTrue(traveller.getName().equals(NEW_NAME));
        assertFalse(traveller.getName().equals(NAME));
    }

    @Test
    public void testEquals() {
        Traveller sameID = new Traveller(traveller.getTravellerID(), traveller.getName());
        Traveller diffID = new Traveller(traveller.getTravellerID() + 1, traveller.getName());
        assertTrue(traveller.equals(sameID));
        assertFalse(traveller.equals(diffID));
    }
}
