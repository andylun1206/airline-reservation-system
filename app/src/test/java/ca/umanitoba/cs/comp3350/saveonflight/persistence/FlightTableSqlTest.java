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
import static org.mockito.Mockito.*;

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
    private static FlightTableSql mockedList;

    @BeforeClass
    public static void setUp() throws ParseException {
        Main.startUp(Main.DatabaseType.HSQL);
        flightTable = new FlightTableSql();
        flightTable.initialize();
        original = flightTable.getFlights();
        AirlineTableSql airlineTableSql = new AirlineTableSql();
        airlineTableSql.initialize();
        AirportTableSql airportTableSql = new AirportTableSql();
        airportTableSql.initialize();

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
        mockedList = mock(FlightTableSql.class);
        mockedList.add(null);
        mockedList.add(emptyNameCase);
        mockedList.add(validCase);
        mockedList.findByFlightCode(null);
        mockedList.findByFlightCode("");
        mockedList.findByFlightCode("WJ 009");
        mockedList.findFlight(null, null);
        mockedList.findFlight("", "");
        mockedList.findFlight("WJ 009", "2017-11-11 05:30");

        verify(mockedList).add(null);
        verify(mockedList).add(emptyNameCase);
        verify(mockedList).add(validCase);
        verify(mockedList).findByFlightCode(null);
        verify(mockedList).findByFlightCode("");
        verify(mockedList).findByFlightCode("WJ 009");
        verify(mockedList).findFlight(null, null);
        verify(mockedList).findFlight("", "");
        verify(mockedList).findFlight("WJ 009", "2017-11-11 05:30");

        when(mockedList.add(null)).thenReturn(false);
        when(mockedList.add(emptyNameCase)).thenReturn(true);
        when(mockedList.add(validCase)).thenReturn(true);

        when(mockedList.findByFlightCode(null)).thenReturn(null);
        when(mockedList.findByFlightCode("")).thenReturn(null);
        when(mockedList.findByFlightCode("WJ 009")).thenReturn(validCase);

        when(mockedList.findFlight(null, null)).thenReturn(null);
        when(mockedList.findFlight("", "")).thenReturn(null);
        when(mockedList.findFlight("WJ 009", "2017-11-11 05:30")).thenReturn(validCase);
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
    public void testAddNull() {
        assertEquals("Test adding null fail", false, mockedList.add(null));
    }

    @Test
    public void testAddEmpty() {
        assertEquals("Test adding empty fail", true, mockedList.add(emptyNameCase));
    }

    @Test
    public void testAddSample() {
        assertEquals("Test adding sample fail", true, mockedList.add(validCase));
    }

    @Test
    public void testFindByFlightCodeNull() {
        assertEquals("Test findByFlight null fail", null, mockedList.findByFlightCode(null));
    }

    @Test
    public void testFindByFlightCodeEmpty() {
        assertEquals("Test findByFlight empty fail", null, mockedList.findByFlightCode(""));
    }

    @Test
    public void testFindByFlightCodeSample() {
        assertEquals("Test findByFlight sample fail", validCase, mockedList.findByFlightCode("WJ 009"));
    }

    @Test
    public void testFindFlightNull() throws ParseException {
        assertEquals("Test findFlight null fail", null, mockedList.findFlight(null, null));
    }

    @Test
    public void testFindFlightEmpty() throws ParseException {
        assertEquals("Test findFlight empty fail", null, mockedList.findFlight("", ""));
    }

    @Test
    public void testFindFlightSample() throws ParseException {
        assertEquals("Test findFlight sample fail", validCase, mockedList.findFlight("WJ 009", "2017-11-11 05:30"));
    }

}
