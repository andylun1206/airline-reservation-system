package ca.umanitoba.cs.comp3350.saveonflight.business;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import ca.umanitoba.cs.comp3350.saveonflight.application.Main;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Airline;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Airport;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Flight;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Traveller;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

public class FindFlightsTest {
    private FindFlights findFlights;

    private Date date = null;
    private Date otherDate = null;
    private Airline airline;
    private Airline otherAirline;
    private Airport origin;
    private Airport destination;

    @Before
    public void setUp() {
        Main.startUp();
        findFlights = new FindFlightsImpl();

        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy, MM, dd", Locale.CANADA);
            date = simpleDateFormat.parse("2017, 11, 11");
            otherDate = simpleDateFormat.parse("2017, 11, 12");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        airline = new Airline("WestJet");
        otherAirline = new Airline("Air Canada");
        origin = new Airport("YWG");
        destination = new Airport("YVR");
    }

    @After
    public void tearDown() {
        findFlights = null;
        Main.shutDown();
    }


    @Test
    public void testGetAllFlights() {
        final Flight f = new Flight("WJ 101", otherDate, airline, origin, destination, 200.00, 50);
        AccessFlights accessFlights = new AccessFlights();
        accessFlights.insertFlight(f);

        List<Flight> flights = findFlights.getAllFlights();

        assertNotNull(flights);
        assertTrue(flights.size() > 0);

        accessFlights.deleteFlight(f);
    }

    @Test
    public void testSearch() {
        List<Flight> searchResults = findFlights.search(airline, date, origin, destination);

        assertNotNull(searchResults);
        for (Flight f : searchResults) {
            assertTrue(f.getAirline().equals(airline));
            assertFalse(f.getAirline().equals(otherAirline));

            assertTrue(f.getDate().equals(date));
            assertFalse(f.getDate().equals(otherDate));

            assertTrue(f.getOrigin().equals(origin));
            assertFalse(f.getOrigin().equals(destination));

            assertTrue(f.getDestination().equals(destination));
            assertFalse(f.getDestination().equals(origin));
        }
    }

}
