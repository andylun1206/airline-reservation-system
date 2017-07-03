package ca.umanitoba.cs.comp3350.saveonflight.integration;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.sql.SQLException;
import java.util.List;

import ca.umanitoba.cs.comp3350.saveonflight.application.Main;
import ca.umanitoba.cs.comp3350.saveonflight.business.AccessBookedFlights;
import ca.umanitoba.cs.comp3350.saveonflight.business.AccessBookedFlightsImpl;
import ca.umanitoba.cs.comp3350.saveonflight.objects.BookedFlight;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Traveller;

import static org.junit.Assert.*;

public class BookedFlightsBusinessPersistenceSeamTest {
    private static AccessBookedFlights access;

    @BeforeClass
    public static void setUp() {
        Main.startUp(Main.DatabaseType.HSQL);
        access = new AccessBookedFlightsImpl(Main.getBookedFlightAccess());
    }

    @AfterClass
    public static void tearDown() {
        Main.shutDown();
        access = null;
    }

    @Test
    public void testAddAndRemove() {
        BookedFlight bf = access.searchByTraveller(new Traveller(1, "name doesn't matter")).get(0);

        // Try to adding null
        assertFalse(access.add(null));

        // Add new row to table
        bf.getFlight().setFlightCode("WJ 123");
        assertTrue(access.add(bf));

        // Add another row
        bf.getFlight().setFlightCode("AC 123");
        assertTrue(access.add(bf));

        // Remove the row just added
        assertTrue(access.remove(bf));

        // Remove the first row that was added
        bf.getFlight().setFlightCode("WJ 123");
        assertTrue(access.remove(bf));

        // Try to remove a row not in the table
        assertFalse(access.remove(bf));

        // Try to remove null
        assertFalse(access.remove(null));
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
