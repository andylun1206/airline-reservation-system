package ca.umanitoba.cs.comp3350.saveonflight;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import ca.umanitoba.cs.comp3350.saveonflight.business.FindFlightsTest;
import ca.umanitoba.cs.comp3350.saveonflight.objects.AirlineTest;
import ca.umanitoba.cs.comp3350.saveonflight.objects.AirportTest;
import ca.umanitoba.cs.comp3350.saveonflight.objects.BookedFlightTest;
import ca.umanitoba.cs.comp3350.saveonflight.objects.FlightTest;
import ca.umanitoba.cs.comp3350.saveonflight.objects.TravellerTest;


@RunWith(Suite.class)
@Suite.SuiteClasses({
        //AirlineTest.class,
        //AirportTest.class,
        //BookedFlightTest.class,
        //FlightTest.class,
        TravellerTest.class,
        FindFlightsTest.class
})

public class AllTests {
}

