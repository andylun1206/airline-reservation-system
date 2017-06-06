package ca.umanitoba.cs.comp3350.saveonflight.objects;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.*;

public class AirportTest {
    private final Airport AIRPORT1 = new Airport("WPG01");
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
    public void testEqualsMethod() {
        assertFalse(AIRPORT1.equals(airport2)); //WPG01 != WPG02
        airport2.setAirportCode("WPG01");
        assertTrue(AIRPORT1.equals(airport2)); //WPG01 = WPG01
    }

}
