package ca.umanitoba.cs.comp3350.saveonflight;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import ca.umanitoba.cs.comp3350.saveonflight.integration.AirportsBusinessPersistenceSeamTest;
import ca.umanitoba.cs.comp3350.saveonflight.integration.BookedFlightsBusinessPersistenceSeamTest;
import ca.umanitoba.cs.comp3350.saveonflight.integration.TravellerBusinessPersistenceSeamTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        BookedFlightsBusinessPersistenceSeamTest.class,
        TravellerBusinessPersistenceSeamTest.class,
        AirportsBusinessPersistenceSeamTest.class
})

public class AllIntegrationTests {
}
