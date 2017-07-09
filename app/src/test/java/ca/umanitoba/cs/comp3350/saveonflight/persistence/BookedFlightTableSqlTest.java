package ca.umanitoba.cs.comp3350.saveonflight.persistence;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import ca.umanitoba.cs.comp3350.saveonflight.application.Main;
import ca.umanitoba.cs.comp3350.saveonflight.objects.BookedFlight;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Flight;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Traveller;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

/**
 * Created by longyu on 2017-06-29.
 */


public class BookedFlightTableSqlTest {
    private static List<BookedFlight> original;
    private static BookedFlightTableSql bookedFlightTableSql;
    private static TravellerTable travellerTable = new TravellerTable();
    private static FlightTable flightTable = new FlightTable();
    private static BookedFlight validCase;

    @BeforeClass
    public static void setUp() {
        Main.startUp(Main.DatabaseType.HSQL);
        bookedFlightTableSql = new BookedFlightTableSql();
        bookedFlightTableSql.initialize(Main.getDBPathName());
        BookedFlightTable bookedFlightTable = new BookedFlightTable();
        bookedFlightTable.initialize("");
        travellerTable.initialize("");
        flightTable.initialize("");
        ArrayList<Traveller> travellers = travellerTable.getTravellers();
        ArrayList<Flight> flights = flightTable.getFlights();
        validCase = new BookedFlight(travellers.get(1), flights.get(7));
        original = bookedFlightTableSql.getAll();
    }

    @Test
    public void testExistence() {
        assertNotNull("Initialize is not work", bookedFlightTableSql);
    }

    @Test
    public void testInitialize() {
        assertEquals("Initialize is not work", original, bookedFlightTableSql.getAll());
    }

    @Test
    public void testAddNull() {
        bookedFlightTableSql.add(null);
        assertEquals("Add null but actually add something", original, bookedFlightTableSql.getAll());
    }

    @Test
    public void testAddDuplicate() {
        //TODO:fix the delete old bookedFlight 2017-07-04
        bookedFlightTableSql.add(validCase);
        assertFalse("Failed to add duplicate to BookedFlightTable.", bookedFlightTableSql.add(validCase));
        bookedFlightTableSql.remove(validCase);
        bookedFlightTableSql.remove(validCase);
    }

    @Test
    public void testAddValid() {
        assertTrue("Failed to add validCase to BookedFlightTable.", bookedFlightTableSql.add(validCase));
        bookedFlightTableSql.remove(validCase);
    }


    @Test
    public void testSearchByTraveller() {
        Traveller t = BookedFlightTable.getBookedFlights().get(0).getTraveller();
        ArrayList<BookedFlight> bfs = bookedFlightTableSql.searchByTraveller(t);
        assertNotNull(bfs);
        for (BookedFlight bf : bfs) {
            assertEquals(t, bf.getTraveller());
        }
    }
}