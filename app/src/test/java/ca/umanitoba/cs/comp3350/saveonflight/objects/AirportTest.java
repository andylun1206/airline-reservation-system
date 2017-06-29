package ca.umanitoba.cs.comp3350.saveonflight.objects;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.*;

public class AirportTest {
    private final String CODE1 = "WPG01";
    private final String CODE2 = "WPG02";
    private final Airport AIRPORT1 = new Airport(CODE1);
    private Airport airport2;
    private Airport airport3;


    @Before
    public void setUp() {
        airport2 = new Airport(CODE2);
        airport3 = new Airport(CODE1);
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
    public void testEqualsMethod() {
        //normal cases
        assertFalse(AIRPORT1.equals(airport2)); //WPG01 != WPG02
        assertTrue(AIRPORT1.equals(airport3)); //WPG01 = WPG01
        //error cases
        assertFalse(AIRPORT1.equals(CODE1));
        assertFalse(AIRPORT1.equals(null));
    }

    @Test
    public void testGetAirportCode() {
        assertEquals(AIRPORT1.getAirportCode(), CODE1);
        assertEquals(AIRPORT1.getAirportCode(), airport3.getAirportCode());
        //error cases
        assertFalse((AIRPORT1.getAirportCode()).equals(airport2.getAirportCode()));
        assertFalse((AIRPORT1.getAirportCode()).equals("wpg01"));
        assertFalse((AIRPORT1.getAirportCode()).equals(""));
    }

    @Test
    public void testSetAirportCode() {
        airport2.setAirportCode("hello");
        assertEquals(airport2.getAirportCode(), "hello");
        airport3.setAirportCode("happy");
        assertFalse(airport3.equals(AIRPORT1));
    }

    @Test
    public void testContains() {
        assertFalse(AIRPORT1.contains(null));
        assertFalse(AIRPORT1.contains("ASDJIASJ"));
        assertFalse(AIRPORT1.contains(airport2));
        Airport match = new Airport(CODE1);
        assertTrue(AIRPORT1.contains(match));                // Exact match should return true
        Airport subset = new Airport(CODE1.substring(0, 2));
        assertTrue(AIRPORT1.contains(subset));               // Subset match should return true
    }
}
