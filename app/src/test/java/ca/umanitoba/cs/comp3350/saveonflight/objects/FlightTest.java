package ca.umanitoba.cs.comp3350.saveonflight.objects;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.GregorianCalendar;

import ca.umanitoba.cs.comp3350.saveonflight.R;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

public class FlightTest {
    private Flight flight;
    private final String FLIGHT_ID = "WJ 101";
    private final Airline AIRLINE = new Airline("WestJet");
    private final Airport ORIGIN = new Airport("YWG");
    private final Airport DEST = new Airport("YVR");
    private final double PRICE = 300.00;
    private final int CAP = 500;
    private final FlightClassEnum FLIGHT_CLASS = FlightClassEnum.BUSINESS;

    private Date departureTime;
    private Date arrivalTime;
    private GregorianCalendar cal = new GregorianCalendar();

    private Flight.FlightBuilder builder;

    @Before
    public void setUp() {
        cal = new GregorianCalendar();
        cal.set(2017, 1, 1, 12, 0);
        departureTime = cal.getTime();
        cal.set(2017, 1, 1, 15, 0);
        arrivalTime = cal.getTime();

        builder = new Flight.FlightBuilder(FLIGHT_ID, ORIGIN, DEST);
        flight = builder.setDepartureTime(departureTime)
                .setArrivalTime(arrivalTime)
                .setAirline(AIRLINE)
                .setPrice(PRICE)
                .setCapacity(CAP)
                .setFlightClass(FLIGHT_CLASS)
                .build();
    }

    @After
    public void tearDown() {
        flight = null;
    }

    @Test
    public void testSellSeats() {
        assertEquals(0, flight.getSeatsTaken()); // Initially, no seats should be sold
        assertFalse(flight.sellSeats(-1));       // Should not be able to sell negative seats
        assertTrue(flight.sellSeats(0));         // Selling 0 seats is allowed, but nothing happens
        assertEquals(0, flight.getSeatsTaken()); // There should still be no seats taken

        // Sell seats 1 by 1 until the Flight is full
        int seatsSold = 0;
        while (!flight.isFull()) {
            assertTrue(flight.sellSeats(1));
            seatsSold++;
            assertEquals(flight.getSeatsTaken(), seatsSold);
        }
        assertEquals(CAP, seatsSold);            // The number of seats we sell should equal the capacity of the flight

        assertFalse(flight.sellSeats(1));        // After the Flight is full, trying to sell anymore seats should not work
    }

    @Test
    public void testSeatsRemaining() {
        assertEquals(CAP, flight.getSeatsRemaining());
        flight.sellSeats(5);
        assertEquals(CAP - 5, flight.getSeatsRemaining());
    }

    @Test
    public void testIsFull() {
        assertFalse(flight.isFull());
        assertTrue(flight.sellSeats(1));
        assertFalse(flight.isFull());
        assertTrue(flight.sellSeats(flight.getSeatsRemaining()));
        assertTrue(flight.isFull());
    }

    @Test
    public void testEquals() {
        cal.set(2017, 1, 1, 12, 1);
        final Date DEPART = cal.getTime();
        cal.set(2017, 1, 1, 15, 1);
        final Date ARRIVE = cal.getTime();


        Flight otherFlight = builder.setFlightId("AC101")
                .setDepartureTime(DEPART)
                .setArrivalTime(ARRIVE)
                .build();

        assertFalse(null == flight);              // null
        assertFalse(flight.equals(FLIGHT_ID));    // Not same class
        assertFalse(otherFlight.equals(flight));  // Same class, but not same Flight

        otherFlight.setFlightCode(FLIGHT_ID);
        assertEquals(otherFlight, flight);        // Same Flight
    }


    @Test
    public void testFlightId() {
        final String NEW_ID = "ABCDEFG";
        assertEquals(FLIGHT_ID, flight.getFlightCode());
        assertFalse(flight.getFlightCode().equals(NEW_ID));
        flight.setFlightCode(NEW_ID);
        assertEquals(NEW_ID, flight.getFlightCode());
    }

    @Test
    public void testAirline() {
        final Airline NEW_AIRLINE = new Airline("Air Spain");
        assertEquals(AIRLINE, flight.getAirline());
        assertFalse(flight.getAirline().equals(NEW_AIRLINE));
        flight.setAirline(NEW_AIRLINE);
        assertEquals(NEW_AIRLINE, flight.getAirline());
    }

    @Test
    public void testOrigin() {
        final Airport NEW_ORIGIN = new Airport("Flin Flon International YYY");
        assertEquals(ORIGIN, flight.getOrigin());
        assertFalse(flight.getOrigin().equals(NEW_ORIGIN));
        flight.setOrigin(NEW_ORIGIN);
        assertEquals(NEW_ORIGIN, flight.getOrigin());
    }

    @Test
    public void testDestination() {
        final Airport NEW_DEST = new Airport("The Moon");
        assertEquals(DEST, flight.getDestination());
        assertFalse(flight.getDestination().equals(NEW_DEST));
        flight.setDestination(NEW_DEST);
        assertEquals(NEW_DEST, flight.getDestination());
    }

    @Test
    public void testCapacity() {
        final int OTHER_CAP = 1;
        assertEquals(CAP, flight.getCapacity());
        assertTrue(flight.getCapacity() != OTHER_CAP);
        flight.setCapacity(OTHER_CAP);
        assertEquals(OTHER_CAP, flight.getCapacity());
    }

    @Test
    public void testSeatsTaken() {
        final int SEATS_SOLD = 5;
        assertEquals(0, flight.getSeatsTaken());
        assertTrue(flight.sellSeats(SEATS_SOLD));
        assertEquals(SEATS_SOLD, flight.getSeatsTaken());
        assertFalse(flight.sellSeats(flight.getCapacity())); // Selling more seats than are available should not work
        assertEquals(SEATS_SOLD, flight.getSeatsTaken());    // getSeatsTaken() should still return the same number as before
    }

    @Test
    public void testFlightClass() {
        assertEquals(FLIGHT_CLASS, flight.getFlightClass());
        assertTrue(FlightClassEnum.ECONOMY != flight.getFlightClass());
        flight.setFlightClass(FlightClassEnum.ECONOMY);
        assertEquals(FlightClassEnum.ECONOMY, flight.getFlightClass());
    }

    @Test
    public void testDepartureTime() {
        cal.set(2017, 1, 1, 12, 2);
        final Date NEW_DEPARTURE_TIME = cal.getTime();
        assertEquals(departureTime, flight.getDepartureTime());
        assertFalse(flight.getDepartureTime().equals(NEW_DEPARTURE_TIME));
        flight.setDepartureTime(NEW_DEPARTURE_TIME);
        assertEquals(NEW_DEPARTURE_TIME, flight.getDepartureTime());
    }

    @Test
    public void testArrivalTime() {
        cal.set(2017, 1, 1, 15, 2);
        final Date NEW_ARRIVAL_TIME = cal.getTime();
        assertEquals(arrivalTime, flight.getArrivalTime());
        assertFalse(flight.getArrivalTime().equals(NEW_ARRIVAL_TIME));
        flight.setArrivalTime(NEW_ARRIVAL_TIME);
        assertEquals(NEW_ARRIVAL_TIME, flight.getArrivalTime());
    }

    @Test
    public void testGetFlightDuration() {
        final String EXPECTED = "3h 0m";
        assertEquals(EXPECTED, flight.getFlightDuration());
    }

    @Test
    public void testGetFlightTime() {
        final String EXPECTED = "12:00 - 15:00";
        assertEquals(EXPECTED, flight.getFlightTime());
    }



}
