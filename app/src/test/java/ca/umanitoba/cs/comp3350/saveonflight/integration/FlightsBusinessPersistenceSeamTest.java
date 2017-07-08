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
        cal.set(2017, 11, 11);
        Date departureDate = cal.getTime();
        searchCriteria.setDepartureDate(departureDate);

        cal.set(2017, 12, 11);
        Date returnDate = cal.getTime();
        searchCriteria.setReturnDate(returnDate);

        searchCriteria.setNumTravellers(1);
        searchCriteria.setMaxPrice(400.50);
        Airline westJet = new Airline("WestJet");
        searchCriteria.setPreferredAirlines(westJet);
        searchCriteria.setPreferredClass(FlightClassEnum.ECONOMY);

        ArrayList<Flight> flights = access.search(searchCriteria);
        assertNotNull(flights);
        for (Flight f : flights) {
            assertEquals(ywg, f.getOrigin());
            assertEquals(yyz, f.getDestination());
            assertEquals(departureDate, f.getDepartureTime());
            assertTrue(f.getSeatsRemaining() >= 1);
            assertTrue(f.getPrice() <= 400.50);
            assertTrue(f.getAirline().equals(westJet));
            assertEquals(FlightClassEnum.ECONOMY, f.getFlightClass());
        }

        ArrayList<Flight> returnFlights = access.search(searchCriteria);
        assertNotNull(returnFlights);
        for (Flight f : flights) {
            assertEquals(yyz, f.getOrigin());
            assertEquals(ywg, f.getDestination());
            assertTrue(f.getSeatsRemaining() >= 1);
            assertTrue(f.getPrice() <= 400.50);
            assertTrue(f.getAirline().equals(westJet));
            assertEquals(FlightClassEnum.ECONOMY, f.getFlightClass());
        }

        ArrayList<Flight> shouldBeEmpty = access.search(null);
        assertTrue(shouldBeEmpty.isEmpty());
    }
}
