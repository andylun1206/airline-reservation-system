package ca.umanitoba.cs.comp3350.saveonflight.integration;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import ca.umanitoba.cs.comp3350.saveonflight.application.Main;
import ca.umanitoba.cs.comp3350.saveonflight.business.AccessFlights;
import ca.umanitoba.cs.comp3350.saveonflight.business.AccessFlightsImpl;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Airline;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Airport;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Flight;
import ca.umanitoba.cs.comp3350.saveonflight.objects.FlightClassEnum;
import ca.umanitoba.cs.comp3350.saveonflight.objects.SearchCriteria;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.*;

public class FlightsBusinessPersistenceSeamTest {
    private static AccessFlights access;

    @BeforeClass
    public static void setUp() {
        Main.startUp(Main.DatabaseType.HSQL);
        access = new AccessFlightsImpl(Main.getFlightAccess());
    }

    @AfterClass
    public static void tearDown() {
        access = null;
    }

    @Test
    public void testGetByFlightCode() {
        // Valid searches
        String[] flightCodes = {"AC 256", "AC 260", "AC 264", "WJ 520"};
        for (String fc : flightCodes) {
            Flight f = access.getFlightByCode(fc);
            assertNotNull(f);
            assertEquals(fc, f.getFlightCode());
        }

        // Invalid searches
        String[] flightCodesInvalid = {"AC 257", "WJ 521", "ASDASAS", "", null};
        for (String fc : flightCodesInvalid) {
            Flight f = access.getFlightByCode(fc);
            assertTrue(f == null);
        }
    }

    @Test
    public void testSearch() {
        SearchCriteria searchCriteria = new SearchCriteria();
        Airport ywg = new Airport("Winnipeg YWG");
        Airport yyz = new Airport("Toronto YYZ");
        searchCriteria.setOrigin(ywg);
        searchCriteria.setDestination(yyz);

        Calendar cal = new GregorianCalendar();
        cal.set(2017, 10, 11);
        Date departureDate = cal.getTime();
        searchCriteria.setDepartureDate(departureDate);

        cal.set(2017, 11, 11);
        Date returnDate = cal.getTime();
        searchCriteria.setReturnDate(returnDate);

        searchCriteria.setMaxPrice(400.50);
        Airline airCanada = new Airline("Air Canada");
        searchCriteria.setPreferredAirlines(airCanada);

        ArrayList<Flight> flights = access.search(searchCriteria); // Valid search which should return results
        assertNotNull(flights);
        assertFalse(flights.isEmpty());
        for (Flight f : flights) {
            assertEquals(ywg, f.getOrigin());
            assertEquals(yyz, f.getDestination());

            Calendar c1 = new GregorianCalendar();
            c1.setTimeInMillis(f.getDepartureTime().getTime());
            Calendar c2 = new GregorianCalendar();
            c2.setTimeInMillis(departureDate.getTime());
            assertEquals(c1.get(Calendar.YEAR), c2.get(Calendar.YEAR));
            assertEquals(c1.get(Calendar.MONTH), c2.get(Calendar.MONTH));
            assertEquals(c1.get(Calendar.DAY_OF_MONTH), c2.get(Calendar.DAY_OF_MONTH));

            assertTrue(f.getSeatsRemaining() >= 1);
            assertTrue(f.getPrice() <= 400.50);
            assertTrue(f.getAirline().equals(airCanada));
        }

        ArrayList<Flight> shouldBeEmpty = access.search(null); // Searching for null should return an empty list
        assertTrue(shouldBeEmpty.isEmpty());

        searchCriteria.setOrigin(new Airport("Beijing PEK")); // No flights in db to/from Beijing so should return empty list
        ArrayList<Flight> flightsFromBeijing = access.search(searchCriteria);
        assertTrue(flightsFromBeijing.isEmpty());

        searchCriteria.setOrigin(ywg);
        cal.set(3576, 1, 11);
        searchCriteria.setDepartureDate(cal.getTime());
        ArrayList<Flight> flightsIn2019 = access.search(searchCriteria); // No flights in db for year 3576
        assertTrue(flightsIn2019.isEmpty());
    }
}
