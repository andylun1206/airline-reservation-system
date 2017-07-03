package ca.umanitoba.cs.comp3350.saveonflight.persistence;

import org.junit.BeforeClass;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import ca.umanitoba.cs.comp3350.saveonflight.application.Main;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Airline;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Airport;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Flight;
import ca.umanitoba.cs.comp3350.saveonflight.objects.FlightClassEnum;
import ca.umanitoba.cs.comp3350.saveonflight.objects.SearchCriteria;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

/**
 * Created by longyu on 2017-06-29.
 */

public class FlightTableSqlTest {
    private static ArrayList<Flight> original;
    private static FlightAccess flightTable;

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CANADA);
    private static Flight emptyNameCase;
    private static Flight validCase;

    private static Flight.FlightBuilder builder;

    @BeforeClass
    public static void setUp() {
        flightTable = new FlightTableSql();
        flightTable.initialize(Main.getDBPathName());
        original = flightTable.getFlights();
        AirlineTableSql airlineTableSql = new AirlineTableSql();
        airlineTableSql.initialize(Main.getDBPathName());
        AirportTableSql airportTableSql = new AirportTableSql();
        airportTableSql.initialize(Main.getDBPathName());

        Airline westJet = airlineTableSql.findAirline("westjet");
        Airport yyz = airportTableSql.findAirport("YYZ");
        Airport ywg = airportTableSql.findAirport("YWG");

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

        searchCriteria.setNumTravellers(1);
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
