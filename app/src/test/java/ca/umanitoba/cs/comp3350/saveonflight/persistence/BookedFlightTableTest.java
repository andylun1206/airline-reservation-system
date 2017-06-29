package ca.umanitoba.cs.comp3350.saveonflight.persistence;

/**
 * Created by zhengyugu on 2017-06-08.
 */

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;

import ca.umanitoba.cs.comp3350.saveonflight.objects.BookedFlight;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Flight;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Traveller;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

public class BookedFlightTableTest {
    private static ArrayList<BookedFlight> original;
    private static BookedFlightAccess bookedFlightTable;
    ArrayList<Traveller> travellers = TravellerTable.getTravellers();
    ArrayList<Flight> flights = FlightTable.getFlights();
    private BookedFlight validCase = new BookedFlight(travellers.get(2), flights.get(7));

    @BeforeClass
    public static void setUp() {
        bookedFlightTable = new BookedFlightTable();
        new TravellerTable().initialize();
        new FlightTable().initialize();
        bookedFlightTable.initialize();
        original = BookedFlightTable.getBookedFlights();
    }

    @Test
    public void testExistence() {
        assertNotNull("Initialize is not work", bookedFlightTable);
    }

    @Test
    public void testInitialize() {
        assertEquals("Initialize is not work", original, BookedFlightTable.getBookedFlights());
    }

    @Test
    public void testAddNull() {
        bookedFlightTable.add(null);
        assertEquals("Add null but actually add something", original, BookedFlightTable.getBookedFlights());
    }

    @Test
    public void testAddValid() {
        bookedFlightTable.add(validCase);
        assertFalse("Failed to add validCase to BookedFlightTable.", bookedFlightTable.add(validCase));
        bookedFlightTable.remove(validCase);
    }

    @Test
    public void testAddDuplicate() {
        assertTrue("Failed to add unique airline 'dup'", bookedFlightTable.add(validCase));
        assertFalse("Succeeded adding a duplicate.", bookedFlightTable.add(validCase));
        bookedFlightTable.remove(validCase);
    }

    @Test
    public void testRemoveNull() {
        assertFalse("removed null?", bookedFlightTable.remove(null));
    }


    @Test
    public void testRemoveValid() {
        bookedFlightTable.add(validCase);
        assertTrue("fail to remove Valid object", bookedFlightTable.remove(validCase));
    }

    @Test
    public void testUpdateNull() {
        assertFalse("update to null?", bookedFlightTable.update(null));
    }


    @Test
    public void testUpdateValid() {
        bookedFlightTable.add(validCase);
        assertTrue("should update the booked flight", bookedFlightTable.update(validCase));
        bookedFlightTable.remove(validCase);
    }

    @Test
    public void testSearchByTraveller() {
        Traveller t = BookedFlightTable.getBookedFlights().get(0).getTraveller();
        ArrayList<BookedFlight> bfs = bookedFlightTable.searchByTraveller(t);
        assertNotNull(bfs);
        for (BookedFlight bf : bfs) {
            assertEquals(t, bf.getTraveller());
        }
    }

    @Test
    public void testSearchByFlight() {
        Flight f = BookedFlightTable.getBookedFlights().get(0).getFlight();
        ArrayList<BookedFlight> bfs = bookedFlightTable.searchByFlight(f);
        assertNotNull(bfs);
        for (BookedFlight bf : bfs) {
            assertEquals(f, bf.getFlight());
        }
    }
}
