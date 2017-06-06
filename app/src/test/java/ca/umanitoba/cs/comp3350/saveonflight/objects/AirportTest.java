package ca.umanitoba.cs.comp3350.saveonflight.objects;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static junit.framework.Assert.*;

public class AirportTest {
    private final Airport AIRPORT1 = new Airport("WPG01");
    private Airport airport2;
    private Airport airport3;

    @Before
    public void setUp() {
        airport2 = new Airport("WPG02");
        airport3 = new Airport("WPG01");
        assertNotNull(airport3);
        assertNotNull(airport2);
        assertNotNull(AIRPORT1);
    }

    @After
    public void tearDown() {
        airport2 = null;
        airport3 = null;
    }

    @Test
    public void testEqualsMethod(){
        //normal cases
        assertFalse(AIRPORT1.equals(airport2)); //WPG01 != WPG02
        assertTrue(AIRPORT1.equals(airport3)); //WPG01 = WPG01
        //error cases
        assertFalse(AIRPORT1.equals("WPG01"));
        assertFalse(AIRPORT1.equals(null));

    }
    @Test
    public void testGetAirportCode(){
        assertEquals(AIRPORT1.getAirportCode(), "WPG01");
        assertEquals(AIRPORT1.getAirportCode(),airport3.getAirportCode());
        //error cases
        assertFalse((AIRPORT1.getAirportCode()).equals(airport2.getAirportCode()));
        assertFalse((AIRPORT1.getAirportCode()).equals("wpg01"));
        assertFalse((AIRPORT1.getAirportCode()).equals(""));
    }

}
