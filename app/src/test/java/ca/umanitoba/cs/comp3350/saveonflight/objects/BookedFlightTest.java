package ca.umanitoba.cs.comp3350.saveonflight.objects;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import ca.umanitoba.cs.comp3350.saveonflight.R;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

public class BookedFlightTest {
    private final Traveller JULIA = new Traveller(1, "Julia Stoyko");
    private final Traveller JENNIE = new Traveller(2, "Jennie Seo");
    private Flight flight1;
    private Flight flight2;
    private BookedFlight booked1;
    private BookedFlight booked2;
    private BookedFlight booked3;
    private BookedFlight booked4;

    @Before
    public void setUp() {
        Flight.FlightBuilder builder = new Flight.FlightBuilder("AC01", new Airport("WPG"), new Airport("YVR"));
        flight1 = builder.setAirline(new Airline("Air Canada"))
                .setPrice(599.99)
                .setCapacity(200)
                .setSeatsTaken(2)
                .setFlightClass(FlightClassEnum.FIRST_CLASS)
                .build();
        flight2 = builder.setFlightId("WJ01")
                .setAirline(new Airline("WestJet"))
                .setOrigin(new Airport("TRT"))
                .setDestination(new Airport("WPG"))
                .setPrice(300.00)
                .setCapacity(150)
                .setSeatsTaken(0)
                .setFlightClass(FlightClassEnum.BUSINESS)
                .build();

        booked1 = new BookedFlight(JULIA, flight1, "A1");
        booked2 = new BookedFlight(JENNIE, flight2, "A1");
        booked3 = new BookedFlight(JULIA, flight2, "A2");
        booked4 = new BookedFlight(JULIA, flight1, "A2");
        assertNotNull(booked1);
        assertNotNull(booked2);
        assertNotNull(booked3);
        assertNotNull(booked4);
    }

    @After
    public void tearDown() {
        flight1 = null;
        flight2 = null;
        booked1 = null;
        booked2 = null;
    }

    @Test
    public void testGetTraveller() {
        assertEquals(booked1.getTraveller(), JULIA);
        assertFalse(JENNIE.equals(booked1.getTraveller()));
        assertEquals(booked1.getTraveller().getName(), "Julia Stoyko");
    }

    @Test
    public void testEqualsMethod() {
        //normal cases
        assertFalse(booked1.equals(booked2));//julia != jennie
        assertFalse(booked1.equals(booked3));//wpg != trt

        assertTrue(booked1.equals(booked4));//julia+AC01 == julia+AC01
        //error cases
        assertFalse(booked1.equals(null));
    }

    @Test
    public void testSetTravellerMethod() {
        booked2.setTraveller(JULIA);
        assertEquals(booked2, booked3);
        booked3.setTraveller(JENNIE);
        assertFalse(booked3.equals(booked2));
    }

    @Test
    public void testGetFlight() {
        assertFalse(flight1.equals(booked2.getFlight()));
        assertEquals(flight1, booked1.getFlight());
        assertEquals(booked1.getFlight(), booked4.getFlight());
    }

    @Test
    public void testSetFlight() {
        booked1.setFlight(flight2);
        assertEquals(booked1, booked3);
        booked3.setFlight(flight1);
        assertFalse(booked1.equals(booked3));
    }

}
