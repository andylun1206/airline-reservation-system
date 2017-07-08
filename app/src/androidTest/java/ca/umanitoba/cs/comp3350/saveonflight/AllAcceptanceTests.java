package ca.umanitoba.cs.comp3350.saveonflight;

import junit.framework.Test;
import junit.framework.TestSuite;

import ca.umanitoba.cs.comp3350.saveonflight.acceptance.ViewBookedFlightsTest;
import ca.umanitoba.cs.comp3350.saveonflight.acceptance.ViewTripSummaryTest;

public class AllAcceptanceTests {
    public static TestSuite suite;

    public static Test suite() {
        suite = new TestSuite("Acceptance Tests");
//        suite.addTestSuite(ViewBookedFlightsTest.class);
        suite.addTestSuite(ViewTripSummaryTest.class);
        return suite;
    }
}
