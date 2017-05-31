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

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

public class FindFlightsTest {
    private FindFlights findFlights;

    @Before
    public void setUp() {
        Main.startUp();
        findFlights = new FindFlightsImpl();
    }

    @After
    public void tearDown() {
        findFlights = null;
        Main.shutDown();
    }

    @Test
    public void testSearch() {
        final Airline AIRLINE = new Airline("WestJet");
        final Airline OTHER_AIRLINE = new Airline("Air Canada");
        final Airport DEPARTS = new Airport("YWG");
        final Airport ARRIVES = new Airport("YVR");
        Date DATE = null;
        Date OTHER_DATE = null;

        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy, MM, dd", Locale.CANADA);
            DATE = simpleDateFormat.parse("2017, 11, 11");
            OTHER_DATE = simpleDateFormat.parse("2017, 11, 12");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        List<Flight> searchResults = findFlights.search(AIRLINE, DATE, DEPARTS, ARRIVES);

        assertNotNull(searchResults);
        //assertTrue(searchResults.size() > 0);
        for (Flight f : searchResults) {
            assertTrue(f.getAirline().equals(AIRLINE));
            assertFalse(f.getAirline().equals(OTHER_AIRLINE));

            assertTrue(f.getDate().equals(DATE));
            assertFalse(f.getDate().equals(OTHER_DATE));

            assertTrue(f.getOrigin().equals(DEPARTS));
            assertFalse(f.getOrigin().equals(ARRIVES));

            assertTrue(f.getDestination().equals(ARRIVES));
            assertFalse(f.getDestination().equals(DEPARTS));
        }
    }

}
