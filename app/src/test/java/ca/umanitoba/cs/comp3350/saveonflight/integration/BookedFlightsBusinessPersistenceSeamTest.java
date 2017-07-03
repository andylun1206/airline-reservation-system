package ca.umanitoba.cs.comp3350.saveonflight.integration;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import ca.umanitoba.cs.comp3350.saveonflight.application.Main;
import ca.umanitoba.cs.comp3350.saveonflight.business.AccessBookedFlights;
import ca.umanitoba.cs.comp3350.saveonflight.business.AccessBookedFlightsImpl;
import ca.umanitoba.cs.comp3350.saveonflight.objects.BookedFlight;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Traveller;

import static org.junit.Assert.*;

public class BookedFlightsBusinessPersistenceSeamTest {
    private AccessBookedFlights access;

    @Before
    public void setUp() {
        Main.startUp(Main.DatabaseType.HSQL);
        access = new AccessBookedFlightsImpl(Main.getBookedFlightAccess());
    }

    @After
    public void tearDown() {
        Main.shutDown();
        access = null;
    }

    @Test
    public void testSearchByTraveller() {
        // The default data in the database includes BookedFlights for passengers with id's 0, 1, and 2
        for (int i = 0; i < 3; i++) {
            Traveller t = new Traveller(i, "name doesn't matter");
            List<BookedFlight> bfs = access.searchByTraveller(t);

            assertNotNull(bfs);
            for (BookedFlight bf : bfs) {
                assertNotNull(bf.getTraveller());
                assertEquals(t, bf.getTraveller());
            }
        }
    }
}
