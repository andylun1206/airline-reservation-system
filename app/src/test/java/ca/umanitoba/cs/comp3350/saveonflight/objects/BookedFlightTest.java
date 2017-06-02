package ca.umanitoba.cs.comp3350.saveonflight.objects;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static junit.framework.Assert.*;

public class BookedFlightTest {
    private final Traveller JULIA= new Traveller(1,"Julia Stoyko");
    private final Traveller JENNIE = new Traveller(2, "Jennie Seo");
    private Flight flight1;
    private Flight flight2;
    private BookedFlight booked1;
    private BookedFlight booked2;

    @Before
    public void setUp() {
        flight1 = new Flight("AC01", new Date(),new Airline("Air Canada"),
                new Airport("WPG"), new Airport("YVR"), 599.99, 200, 2, FlightClassEnum.FIRST_CLASS);
        flight2 = new Flight("WJ01", new Date(), new Airline("WestJet"),
                new Airport("TRT"), new Airport("WPG"), 300.00, 150, 0, FlightClassEnum.BUSINESS);
        booked1 = new BookedFlight(JULIA, flight1);
        booked2 = new BookedFlight(JENNIE, flight2);
    }

    @After
    public void tearDown() {
        flight1 = null;
        flight2 = null;
        booked1 = null;
        booked2 = null;
    }

    @Test
    public void testEqualsMethod(){
        assertFalse(booked1.equals(booked2));
        booked2.setTraveller(JULIA);
        assertFalse(booked1.equals(booked2));
        booked2.setFlight(flight1);
        assertTrue(booked1.equals(booked2));
    }
}
