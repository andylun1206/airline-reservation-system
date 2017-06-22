package ca.umanitoba.cs.comp3350.saveonflight.business;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import ca.umanitoba.cs.comp3350.saveonflight.R;
import ca.umanitoba.cs.comp3350.saveonflight.business.comparators.DepartureTimeComparator;
import ca.umanitoba.cs.comp3350.saveonflight.business.comparators.DurationComparator;
import ca.umanitoba.cs.comp3350.saveonflight.business.comparators.PriceComparator;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Airline;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Airport;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Flight;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;

public class SortFlightsTest {
    private SortFlights sortFlights;
    private ArrayList<Flight> flights;
    Flight f1, f2, f3, f4, f5;

    @Before
    public void setUp() {
        sortFlights = new SortFlightsImpl();
        flights = new ArrayList<>();

        String flightID1 = "WJ101";
        String flightID2 = "WJ102";
        String flightID3 = "AC1";

        Date d1 = null;
        Date a1 = null;
        Date d2 = null;
        Date a2 = null;
        Date d3 = null;
        Date a3 = null;
        Date d4 = null;
        Date a4 = null;
        Date d5 = null;
        Date a5 = null;
        try {
            d1 = Flight.SIMPLE_DATE_FORMAT.parse("2017, 08, 07, 06, 54"); // 3 hours
            a1 = Flight.SIMPLE_DATE_FORMAT.parse("2017, 08, 07, 09, 54");

            d2 = Flight.SIMPLE_DATE_FORMAT.parse("2017, 08, 08, 07, 30"); // 4 hours 24 min
            a2 = Flight.SIMPLE_DATE_FORMAT.parse("2017, 08, 08, 11, 54");

            d3 = Flight.SIMPLE_DATE_FORMAT.parse("2017, 08, 09, 08, 24"); // 4 hours 30 min
            a3 = Flight.SIMPLE_DATE_FORMAT.parse("2017, 08, 09, 12, 54");

            d4 = Flight.SIMPLE_DATE_FORMAT.parse("2017, 09, 01, 13, 00"); // 2 hours 54 min
            a4 = Flight.SIMPLE_DATE_FORMAT.parse("2017, 08, 07, 15, 54");

            d5 = Flight.SIMPLE_DATE_FORMAT.parse("2017, 08, 07, 06, 55"); // 3 hours 1 min
            a5 = Flight.SIMPLE_DATE_FORMAT.parse("2017, 08, 07, 09, 56");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Airline airCanada = new Airline("Air Canada", R.mipmap.ic_aircanada);
        Airline fake = new Airline("Fake", R.mipmap.ic_launcher);
        Airline fake2 = new Airline("Fake2", R.mipmap.ic_launcher);
        Airline gordAirlines = new Airline("Gord Airlines", R.mipmap.ic_launcher);
        Airline westJet = new Airline("WestJet", R.mipmap.ic_westjet);

        Airport ywg = new Airport("YWG");
        Airport yvr = new Airport("YVR");
        Airport yyc = new Airport("YYC");

        f1 = new Flight(flightID1, d1, a1, westJet, ywg, yvr, 100.0, 100, 100);
        f2 = new Flight(flightID1, d2, a2, fake, yvr, ywg, 200.0, 101, 100);
        f3 = new Flight(flightID2, d3, a3, fake2, ywg, yyc, 300.0, 250);
        f4 = new Flight(flightID2, d4, a4, gordAirlines, yyc, ywg, 350.0, 200);
        f5 = new Flight(flightID3, d5, a5, airCanada, yvr, ywg, 100000.0, 5);

        flights.add(f1);
        flights.add(f2);
        flights.add(f3);
        flights.add(f4);
        flights.add(f5);
    }

    @After
    public void tearDown() {
        sortFlights = null;
        flights = null;
        f1 = null;
        f2 = null;
        f3 = null;
        f4 = null;
        f5 = null;
    }

    @Test
    public void testNullList() {
        try {
            // Nothing should happen if we pass null instead of an ArrayList of Flights
            sortFlights.sortFlightsBy(null, new DepartureTimeComparator());
        } catch (NullPointerException e) {
            fail();
        }

    }

    @Test
    public void testEmptyList() {
        // Nothing should happen
        ArrayList<Flight> emptyList = new ArrayList<>();
        sortFlights.sortFlightsBy(emptyList, new DepartureTimeComparator());
        assertTrue(emptyList.isEmpty()); // List should still be empty
    }

    @Test
    public void testNullElements() {
        // Try sorting an ArrayList where all elements are null
        ArrayList<Flight> nullFlights = new ArrayList<>();
        nullFlights.add(null);
        nullFlights.add(null);
        sortFlights.sortFlightsBy(nullFlights, new DepartureTimeComparator());
        for (Flight f : nullFlights) {
            // All elements should still just be null
            assertNull(f);
        }
    }

    @Test
    public void testSomeValid() {
        // Some elements are valid, while some are null
        ArrayList<Flight> someNull = new ArrayList<>();
        someNull.add(null);
        someNull.add(null);
        someNull.add(f1);

        // The null elements should be put at the end of the list
        sortFlights.sortFlightsBy(someNull, new DepartureTimeComparator());
        assertNotNull(someNull.get(0));
        assertEquals(f1, someNull.get(0));
        assertNull(someNull.get(1));
        assertNull(someNull.get(2));
    }

    @Test
    public void testInvalidField() {
        ArrayList<Flight> someInvalidFields = new ArrayList<>();
        someInvalidFields.add(f1);
        f2.setDepartureTime(null);
        someInvalidFields.add(f2);
        someInvalidFields.add(f3);
        sortFlights.sortFlightsBy(someInvalidFields, new DepartureTimeComparator());

        // Flights with invalid fields should be put at the end of the
        assertEquals(f1, someInvalidFields.get(0));
        assertEquals(f3, someInvalidFields.get(1));
        assertEquals(f2, someInvalidFields.get(2));
    }

    @Test
    public void testSortByDate() {
        ArrayList<Flight> flightsByDepartureTime = new ArrayList<>();
        flightsByDepartureTime.add(f1);
        flightsByDepartureTime.add(f5);
        flightsByDepartureTime.add(f2);
        flightsByDepartureTime.add(f3);
        flightsByDepartureTime.add(f4);

        sortFlights.sortFlightsBy(flights, new DepartureTimeComparator());
        for (int i = 0; i < flights.size(); i++) {
            assertEquals(flightsByDepartureTime.get(i), flights.get(i));
        }
    }

    @Test
    public void testSortByPrice() {
        ArrayList<Flight> flightsByPrice = new ArrayList<>();
        flightsByPrice.add(f1);
        flightsByPrice.add(f2);
        flightsByPrice.add(f3);
        flightsByPrice.add(f4);
        flightsByPrice.add(f5);

        sortFlights.sortFlightsBy(flights, new PriceComparator());
        for (int i = 0; i < flights.size(); i++) {
            assertEquals(flightsByPrice.get(i), flights.get(i));
        }
    }

    @Test
    public void testSortByDuration() {
        ArrayList<Flight> flightsByDuration = new ArrayList<>();
        flightsByDuration.add(f4);
        flightsByDuration.add(f1);
        flightsByDuration.add(f5);
        flightsByDuration.add(f2);
        flightsByDuration.add(f3);

        sortFlights.sortFlightsBy(flights, new DurationComparator());
        for (int i = 0; i < flights.size(); i++) {
            assertEquals(flightsByDuration.get(i), flights.get(i));
        }
    }
}
