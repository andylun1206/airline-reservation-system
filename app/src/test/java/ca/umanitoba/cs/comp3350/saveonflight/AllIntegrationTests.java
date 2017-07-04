package ca.umanitoba.cs.comp3350.saveonflight;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import ca.umanitoba.cs.comp3350.saveonflight.integration.BookedFlightsBusinessPersistenceSeamTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    BookedFlightsBusinessPersistenceSeamTest.class
})

public class AllIntegrationTests {
}
