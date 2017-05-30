package ca.umanitoba.cs.comp3350.saveonflight.business;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;

import java.util.ArrayList;
import java.util.List;

import ca.umanitoba.cs.comp3350.saveonflight.application.Main;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Airline;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Airport;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Flight;

public class FindFlightsTest extends TestCase {
    private FindFlights findFlights;

    public FindFlightsTest(String arg0) {
        super(arg0);
    }

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

    public void testSearch() {
        final Airline AIRLINE = new Airline("WestJet");
        final String DATE = "2017/11/11";
        final Airport DEPARTS = new Airport("YWG");
        final Airport ARRIVES = new Airport("YVR");

        final Airline OTHER_AIRLINE = new Airline("Air Canada");
        final String OTHER_DATE = "2017/11/12";

        List<Flight> searchResults = findFlights.search(AIRLINE, DATE, DEPARTS, ARRIVES);

        assertNotNull(searchResults);
        for (Flight f : searchResults) {
            assertTrue(f.getAirline().equals(AIRLINE));
            assertFalse(f.getAirline().equals(OTHER_AIRLINE));

            assertTrue(f.getDate().equals(DATE));
            assertFalse(f.getDate().equals(OTHER_DATE));

            assertTrue(f.getDepart().equals(DEPARTS));
            assertFalse(f.getDepart().equals(ARRIVES));

            assertTrue(f.getArrival().equals(ARRIVES));
            assertFalse(f.getArrival().equals(DEPARTS));
        }
    }




}
