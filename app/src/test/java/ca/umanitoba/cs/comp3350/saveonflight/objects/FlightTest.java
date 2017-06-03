package ca.umanitoba.cs.comp3350.saveonflight.objects;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import ca.umanitoba.cs.comp3350.saveonflight.R;

import static junit.framework.Assert.*;

public class FlightTest {
    private Flight flight;
    private final Airline AIRLINE = new Airline("WestJet", R.mipmap.ic_westjet);
    private final Airport ORIGIN = new Airport("YWG");
    private final Airport DEST = new Airport("YVR");
    private final double  PRICE = 300.00;
    private final int CAP = 500;
    private final FlightClassEnum FLIGHT_CLASS = FlightClassEnum.BUSINESS;

    @Before
    public void setUp() {
        flight = new Flight("WJ 101", new Date(), new Date(), AIRLINE, ORIGIN, DEST, PRICE, CAP, 0, FLIGHT_CLASS);
    }

    @After
    public void tearDown() {
        flight = null;
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
        flight.sellSeats(flight.getSeatsRemaining());
        assertTrue(flight.isFull());
    }

    @Test
    public void testSellSeats() {
        int seatsSold = 0;
        while (!flight.isFull()) {
            flight.sellSeats(1);
            seatsSold++;
            assertEquals(flight.getSeatsTaken(), seatsSold);
        }
        assertEquals(CAP, seatsSold);
    }

    @Test
    public void testSetFlightId() {
        
    }


}
