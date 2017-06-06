package ca.umanitoba.cs.comp3350.saveonflight.objects;

import android.os.Parcel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.*;

public class AirportTest {
    private final String CODE1 = "WPG01";
    private final Airport AIRPORT1 = new Airport(CODE1);
    private Airport airport2;

    @Before
    public void setUp() {
        airport2 = new Airport("WPG02");
    }

    @After
    public void tearDown() {
        airport2 = null;
    }

    @Test
    public void testEquals() {
        assertFalse(AIRPORT1.equals(null));
        assertFalse(AIRPORT1.equals("ABC"));
        assertFalse(AIRPORT1.equals(airport2)); //WPG01 != WPG02
        airport2.setAirportCode(CODE1);
        assertTrue(AIRPORT1.equals(airport2));  //WPG01 = WPG01
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

    @Test
    public void testAirportCode() {
        final String CODE2 = "Winnipeg YWG";
        assertEquals(CODE1, AIRPORT1.getAirportCode());
        assertNotSame("ASDASDS", AIRPORT1.getAirportCode());
        AIRPORT1.setAirportCode(CODE2);
        assertEquals(CODE2, AIRPORT1.getAirportCode());
    }

}
