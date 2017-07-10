package ca.umanitoba.cs.comp3350.saveonflight;

import junit.framework.Test;
import junit.framework.TestSuite;

import ca.umanitoba.cs.comp3350.saveonflight.acceptance.PaymentTest;
import ca.umanitoba.cs.comp3350.saveonflight.acceptance.ViewBookedFlightsTest;
import ca.umanitoba.cs.comp3350.saveonflight.acceptance.ViewTripSummaryTest;

public class AllAcceptanceTests {
    public static TestSuite suite;

    public static Test suite() {
        suite = new TestSuite("Acceptance Tests");
        suite.addTestSuite(ViewTripSummaryTest.class);
//        suite.addTestSuite(ViewBookedFlightsTest.class);
//        suite.addTestSuite(PaymentTest.class);
        return suite;
    }
}
