package ca.umanitoba.cs.comp3350.saveonflight.persistence;

import org.junit.BeforeClass;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import ca.umanitoba.cs.comp3350.saveonflight.application.Main;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Airline;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Airport;
import ca.umanitoba.cs.comp3350.saveonflight.objects.BookedFlight;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Flight;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Traveller;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.*;

/**
 * Created by longyu on 2017-06-29.
 */


public class BookedFlightTableSqlTest {
    private static List<BookedFlight> original;
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CANADA);
    private static BookedFlightTableSql bookedFlightTableSql;
    private static TravellerTable travellerTable = new TravellerTable();
    private static FlightTable flightTable = new FlightTable();
    private static BookedFlight emptyCase, validCase;
    private static Flight emptyFlight;
    private static BookedFlightTableSql mockedList;
    private static Flight.FlightBuilder builder;

    @BeforeClass
    public static void setUp() {
        Main.startUp(Main.DatabaseType.HSQL);
        bookedFlightTableSql = new BookedFlightTableSql();
        bookedFlightTableSql.initialize();
        BookedFlightTable bookedFlightTable = new BookedFlightTable();
        bookedFlightTable.initialize();
        travellerTable.initialize();
        flightTable.initialize();
        ArrayList<Traveller> travellers = travellerTable.getTravellers();
        ArrayList<Flight> flights = flightTable.getFlights();
        AirlineTableSql airlineTableSql = new AirlineTableSql();
        airlineTableSql.initialize();
        AirportTableSql airportTableSql = new AirportTableSql();
        airportTableSql.initialize();
        Airline westJet = airlineTableSql.findAirline("westjet");
        Airport yyz = airportTableSql.findAirport("YYZ");
        Airport ywg = airportTableSql.findAirport("YWG");
        builder = new Flight.FlightBuilder("", yyz, ywg);
        try {
            emptyFlight = builder.setDepartureTime(sdf.parse("2017-11-11 05:30"))
                    .setArrivalTime(sdf.parse("2017-11-11 08:51"))
                    .setAirline(westJet)
                    .setPrice(350.52)
                    .setCapacity(200)
                    .build();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        emptyCase = new BookedFlight(new Traveller(100, ""), emptyFlight, "");
        validCase = new BookedFlight(travellers.get(1), flights.get(7), "8A");
        original = bookedFlightTableSql.getAll();
        mockedList = mock(BookedFlightTableSql.class);

        try {
            mockedList.add(null);
            mockedList.add(emptyCase);
            mockedList.add(validCase);
            mockedList.remove(null);
            mockedList.remove(emptyCase);
            mockedList.remove(validCase);
            mockedList.searchByTraveller(null);
            mockedList.searchByTraveller(new Traveller(10, ""));
            mockedList.searchByTraveller(new Traveller(0, "Jack"));

            verify(mockedList).add(null);
            verify(mockedList).add(emptyCase);
            verify(mockedList).add(validCase);
            verify(mockedList).remove(null);
            verify(mockedList).remove(emptyCase);
            verify(mockedList).remove(validCase);
            verify(mockedList).searchByTraveller(null);
            verify(mockedList).searchByTraveller(new Traveller(10, ""));

            when(mockedList.add(null)).thenReturn(false);
            when(mockedList.add(emptyCase)).thenReturn(true);
            when(mockedList.add(validCase)).thenReturn(true);
        } catch (Exception e) {
            e.printStackTrace();
        }

        when(mockedList.remove(null)).thenReturn(false);
        when(mockedList.remove(emptyCase)).thenReturn(false);
        when(mockedList.remove(validCase)).thenReturn(true);

        when(mockedList.searchByTraveller(null)).thenReturn(null);
        when(mockedList.searchByTraveller(new Traveller(10, ""))).thenReturn(null);

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
        try {
            assertFalse("Test add null fail", mockedList.add(null));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAddEmpty() {
        try {
            assertTrue("Test add empty fail", mockedList.add(emptyCase));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAddValid() {
        try {
            assertTrue("Failed to add validCase to BookedFlightTable.", mockedList.add(validCase));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testRemoveNull() {
        assertFalse("Test remove null fail", mockedList.remove(null));
    }

    @Test
    public void testRemoveEmpty() {
        assertFalse("Test remove empty fail", mockedList.remove(emptyCase));
    }

    @Test
    public void testRemoveValid() {
        assertTrue("Test remove valid fail", mockedList.remove(validCase));
    }

    @Test
    public void testSearchByTravellerNull() {
        assertEquals("Test search null fail", null, mockedList.searchByTraveller(null));
    }

    @Test
    public void testSearchByTravellerEmpty() {
        assertEquals("Test search null fail", null, mockedList.searchByTraveller(new Traveller(10, "")));
    }
}