package ca.umanitoba.cs.comp3350.saveonflight.objects;

import junit.framework.TestCase;

public class TravellerTest extends TestCase {

    public TravellerTest(String arg0) {
        super(arg0);
    }

    public void testTraveller1() {
        Traveller traveller;
        final int ID = 123;
        final String NAME = "Bob Bob";

        System.out.println("\nStarting testTraveller");

        traveller = new Traveller(ID, NAME);
        assertNotNull(traveller);
        assertTrue(ID == traveller.getTravellerID());
        assertTrue(NAME.equals(traveller.getName()));

        System.out.println("Finished testTraveller");
    }
}
