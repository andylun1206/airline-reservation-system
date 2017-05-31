package ca.umanitoba.cs.comp3350.saveonflight.objects;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;

public class TravellerTest extends TestCase {
    private Traveller traveller;
    private final int ID = 123;
    private final String NAME = "Bob Bob";

    public TravellerTest(String arg0) {
        super(arg0);
    }

    @Before
    public void setUp() {
        traveller = new Traveller(ID, NAME);
    }

    @After
    public void tearDown() {
        traveller = null;
    }

    public void testExistence() {
        assertNotNull(traveller);
    }

    public void testGetID() {
        assertTrue(ID == traveller.getTravellerID());
        assertFalse(321 == traveller.getTravellerID());
    }

    public void testGetName() {
        assertTrue(NAME.equals(traveller.getName()));
    }

    public void testSetName() {
        final String NEW_NAME = "John John";
        traveller.setName(NEW_NAME);
        assertTrue(traveller.getName().equals(NEW_NAME));
        assertFalse(traveller.getName().equals(NAME));
    }

    public void testEquals() {
        Traveller sameID = new Traveller(traveller.getTravellerID(), traveller.getName());
        Traveller diffID = new Traveller(traveller.getTravellerID() + 1, traveller.getName());
        assertTrue(traveller.equals(sameID));
        assertFalse(traveller.equals(diffID));
    }
}
