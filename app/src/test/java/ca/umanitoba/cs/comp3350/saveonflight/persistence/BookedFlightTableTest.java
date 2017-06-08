package ca.umanitoba.cs.comp3350.saveonflight.persistence;

/**
 * Created by zhengyugu on 2017-06-08.
 */

import org.junit.BeforeClass;
import org.junit.Test;

import static junit.framework.Assert.*;

import java.util.ArrayList;

import ca.umanitoba.cs.comp3350.saveonflight.objects.BookedFlight;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Flight;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Traveller;

public class BookedFlightTableTest {
    private static ArrayList<BookedFlight> original;
    private static BookedFlightTable bookedFlightTable;
    ArrayList<Traveller> travellers = TravellerTable.getTravellers();
    ArrayList<Flight> flights = FlightTable.getFlights();
    private BookedFlight nullCase = new BookedFlight(null, null);
    private BookedFlight validCase = new BookedFlight(travellers.get(2), flights.get(7));

    @BeforeClass
    public static void setUp() {
        bookedFlightTable = new BookedFlightTable();
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
        bookedFlightTable.add(nullCase);
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
        assertFalse("update to null?", bookedFlightTable.update(nullCase));
    }


    @Test
    public void testUpdateValid() {
        bookedFlightTable.add(validCase);
        // well done it at next teration
        assertFalse("should update a null object", bookedFlightTable.update(nullCase));

        bookedFlightTable.remove(validCase);
    }
}
