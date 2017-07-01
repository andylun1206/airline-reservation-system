package ca.umanitoba.cs.comp3350.saveonflight.persistence;

import org.junit.Before;
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
    private static BookedFlightTableSql bookedFlightTable;
    ArrayList<Traveller> travellers = new TravellerTable().getTravellers();
    FlightTableSql flightTable = new FlightTableSql();
    ArrayList<Flight> flights = flightTable.getFlights();
    private BookedFlight validCase = new BookedFlight(travellers.get(2), flights.get(7));

    @BeforeClass
    public static void setUp() {
        bookedFlightTable = new BookedFlightTableSql();
        TravellerTableSql travellerTableSql = new TravellerTableSql();
        travellerTableSql.initialize(Main.getDBPathName());
        FlightTableSql flightTableSql = new FlightTableSql();
        flightTableSql.initialize(Main.getDBPathName());
        bookedFlightTable.initialize(Main.getDBPathName());
        original = bookedFlightTable.gets();

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