package ca.umanitoba.cs.comp3350.saveonflight.persistence;

/**
 * Created by zhengyugu on 2017-06-08.
 */

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import ca.umanitoba.cs.comp3350.saveonflight.R;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Airline;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Airport;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Flight;
import ca.umanitoba.cs.comp3350.saveonflight.objects.FlightClassEnum;
import ca.umanitoba.cs.comp3350.saveonflight.objects.SearchCriteria;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

public class FlightTableTest {
    private static ArrayList<Flight> original;
    private static FlightAccess flightTable;

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CANADA);
    private static Flight emptyNameCase;
    private static Flight validCase;

    private static Flight.FlightBuilder builder;

    public static void setFlightAccess(FlightAccess f) {
        flightTable = f;
    }

    @BeforeClass
    public static void setUp() {
        if (flightTable == null) {
            flightTable = new FlightTable();
            flightTable.initialize();
        }

        original = flightTable.getFlights();
        AirportAccess airportTable = new AirportTable();
        airportTable.initialize();

        AirlineAccess airlineTable = new AirlineTable();
        airlineTable.initialize();

        Airline westJet = airlineTable.findAirline("westjet");
        Airport yyz = airportTable.findAirport("YYZ");
        Airport ywg = airportTable.findAirport("YWG");

        builder = new Flight.FlightBuilder("", yyz, ywg);
        try {
            emptyNameCase = builder.setDepartureTime(sdf.parse("2017-11-11 05:30"))
                    .setArrivalTime(sdf.parse("2017-11-11 08:51"))
                    .setAirline(westJet)
                    .setPrice(350.52)
                    .setCapacity(200)
                    .build();
            validCase = builder.setFlightId("WJ 009").build();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testExistence() {
        assertNotNull("Initialize is not work", flightTable);
    }

    @Test
    public void testInitialize() {
        assertEquals("Initialize is not work", original, flightTable.getFlights());
    }

    @Test
    public void testSearchBySearchCriteria() {
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

        searchCriteria.setMaxPrice(400.50);
        Airline westJet = new Airline("WestJet");
        searchCriteria.setPreferredAirlines(westJet);
        searchCriteria.setPreferredClass(FlightClassEnum.ECONOMY);

        ArrayList<Flight> flights = flightTable.findBySearchCriteria(searchCriteria);
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

        ArrayList<Flight> returnFlights = flightTable.findBySearchCriteria(searchCriteria);
        assertNotNull(returnFlights);
        for (Flight f : flights) {
            assertEquals(yyz, f.getOrigin());
            assertEquals(ywg, f.getDestination());
            assertTrue(f.getSeatsRemaining() >= 1);
            assertTrue(f.getPrice() <= 400.50);
            assertTrue(f.getAirline().equals(westJet));
            assertEquals(FlightClassEnum.ECONOMY, f.getFlightClass());
        }
    }
}
