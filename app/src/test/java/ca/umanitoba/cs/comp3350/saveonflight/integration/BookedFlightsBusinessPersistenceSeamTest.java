package ca.umanitoba.cs.comp3350.saveonflight.integration;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import ca.umanitoba.cs.comp3350.saveonflight.application.Main;
import ca.umanitoba.cs.comp3350.saveonflight.business.AccessBookedFlights;
import ca.umanitoba.cs.comp3350.saveonflight.business.AccessBookedFlightsImpl;
import ca.umanitoba.cs.comp3350.saveonflight.objects.BookedFlight;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Flight;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Traveller;

import static org.junit.Assert.*;

public class BookedFlightsBusinessPersistenceSeamTest {
    private static AccessBookedFlights accessBf;

    @BeforeClass
    public static void setUp() {
        Main.startUp(Main.DatabaseType.HSQL);
        accessBf = new AccessBookedFlightsImpl(Main.getBookedFlightAccess());
    }

    @AfterClass
    public static void tearDown() {
        accessBf = null;
    }

    @Test
    public void testAddAndRemove() {
        BookedFlight bf = accessBf.searchByTraveller(new Traveller(1, "name doesn't matter")).get(0);
        Flight f = bf.getFlight();
        Traveller t = bf.getTraveller();

        // Try to adding null
        assertFalse(accessBf.insertBookedFlight(null));

        // Try adding a BookedFlight with a no Flight
        bf.setFlight(null);
        assertFalse(accessBf.insertBookedFlight(bf));

        // Try adding a BookedFlight with a no Traveller
        bf.setFlight(f);
        bf.setTraveller(null);
        assertFalse(accessBf.insertBookedFlight(bf));

        // Return the BookedFlight to its original state
        bf.setTraveller(t);

        // Add new row to table
        bf.getFlight().setFlightCode("WJ 123");
        assertTrue(accessBf.insertBookedFlight(bf));

        // Add another row
        bf.getFlight().setFlightCode("AC 123");
        assertTrue(accessBf.insertBookedFlight(bf));

        // Remove the row just added
        assertTrue(accessBf.removeBookedFlight(bf));

        // Remove the first row that was added
        bf.getFlight().setFlightCode("WJ 123");
        assertTrue(accessBf.removeBookedFlight(bf));

        // Try to removeBookedFlight a row not in the table
        assertFalse(accessBf.removeBookedFlight(bf));

        // Try passing null to the remove() method
        assertFalse(accessBf.removeBookedFlight(null));

        // Try removing BookedFlights with a missing Flight and/or Traveller
        bf.setFlight(null);
        assertFalse(accessBf.removeBookedFlight(bf));
        bf.setTraveller(null);
        assertFalse(accessBf.removeBookedFlight(bf));
        bf.setFlight(f);
        assertFalse(accessBf.removeBookedFlight(bf));
    }

    @Test
    public void testSearchByTraveller() {
        // The default data in the database includes BookedFlights for passengers with id's 0, 1, and 2
        for (int i = 0; i < 3; i++) {
            Traveller t = new Traveller(i, "name doesn't matter");
            List<BookedFlight> bfs = accessBf.searchByTraveller(t);

            assertNotNull(bfs);
            for (BookedFlight bf : bfs) {
                assertNotNull(bf.getTraveller());
                assertEquals(t, bf.getTraveller());
            }
        }

        // These searches should give empty results
        for (int i = 4; i < 100; i++) {
            Traveller t = new Traveller(i, "name doesn't matter");
            List<BookedFlight> bfs = accessBf.searchByTraveller(t);

            assertNotNull(bfs);
            assertTrue(bfs.isEmpty());
        }
    }
}
