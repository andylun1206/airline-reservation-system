package ca.umanitoba.cs.comp3350.saveonflight.persistence;

/**
 * Created by zhengyugu on 2017-06-08.
 */

import org.junit.Before;
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
    ArrayList<Traveller> travellers = new TravellerTable().getTravellers();
    FlightTable flightTable=new FlightTable();
    ArrayList<Flight> flights = flightTable.getFlights();
    private BookedFlight validCase = new BookedFlight(travellers.get(2), flights.get(7));

    @BeforeClass
    public static void setUp() {
        bookedFlightTable = new BookedFlightTable();
        new TravellerTable().initialize("");
        new FlightTable().initialize("");
        bookedFlightTable.initialize("");
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
    }

    @Test
    public void testAddDuplicate() {
        BookedFlight bf = new BookedFlight(travellers.get(2), flights.get(8));
        assertTrue("Failed to add unique airline 'dup'", bookedFlightTable.add(bf));
        assertFalse("Succeeded adding a duplicate.", bookedFlightTable.add(bf));
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
}
