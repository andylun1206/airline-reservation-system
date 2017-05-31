package ca.umanitoba.cs.comp3350.saveonflight;

import junit.framework.Test;
import junit.framework.TestSuite;

import ca.umanitoba.cs.comp3350.saveonflight.business.FindFlightsTest;
import ca.umanitoba.cs.comp3350.saveonflight.objects.TravellerTest;

public class AllTests {

    public static TestSuite suite;

    public static Test suite() {
        suite = new TestSuite("All tests");
        testObjects();
        testBusiness();
        return suite;
    }

    private static void testObjects() {
        // TODO
        //suite.addTestSuite(AirlineTest.class);
        //suite.addTestSuite(AirportTest.class);
        //suite.addTestSuite(BookedFlightTest.class);
        //suite.addTestSuite(FlightTest.class);
        suite.addTestSuite(TravellerTest.class);
    }

    private static void testBusiness() {
        suite.addTestSuite(FindFlightsTest.class);
    }

}

