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
        flight1 = new Flight("AC01", new Date(), new Date(), new Airline("Air Canada", R.mipmap.ic_aircanada),
                new Airport("WPG"), new Airport("YVR"), 599.99, 200, 2, FlightClassEnum.FIRST_CLASS);
        flight2 = new Flight("WJ01", new Date(), new Date(), new Airline("WestJet", R.mipmap.ic_westjet),
                new Airport("TRT"), new Airport("WPG"), 300.00, 150, 0, FlightClassEnum.BUSINESS);
        booked1 = new BookedFlight(JULIA, flight1);
        booked2 = new BookedFlight(JENNIE, flight2);
        booked3 = new BookedFlight(JULIA,flight2);
        booked4 = new BookedFlight(JULIA,flight1);
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
    public void testGetTraveller(){
        assertEquals(booked1.getTraveller(), JULIA);
        assertFalse(JENNIE.equals(booked1.getTraveller()));
        assertEquals(booked1.getTraveller().getName(),"Julia Stoyko");
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
    public void testSetTravellerMethod(){
        booked2.setTraveller(JULIA);
        assertEquals(booked2,booked3);
        booked3.setTraveller(JENNIE);
        assertFalse(booked3.equals(booked2));
    }

    @Test
    public void testGetFlight(){
        assertFalse(flight1.equals(booked2.getFlight()));
        assertEquals(flight1, booked1.getFlight());
        assertEquals(booked1.getFlight(),booked4.getFlight());
    }
    @Test
    public void testSetFlight(){
        booked1.setFlight(flight2);
        assertEquals(booked1,booked3);
        booked3.setFlight(flight1);
        assertFalse(booked1.equals(booked3));
    }

}
