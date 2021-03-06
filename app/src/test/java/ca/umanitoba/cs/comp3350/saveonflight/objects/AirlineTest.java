package ca.umanitoba.cs.comp3350.saveonflight.objects;

import ca.umanitoba.cs.comp3350.saveonflight.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.*;

public class AirlineTest {
    private final String AC = "Air Canada";
    private final String WJ = "WestJet";
    private final Airline AIRLINE = new Airline(AC);
    private Airline airline2;
    private String wrongObject;

    @Before
    public void setUp() {
        airline2 = new Airline(WJ);
        wrongObject = "Air Canada";
    }

    @After
    public void tearDown() {
        airline2 = null;
    }

    @Test
    public void testName() {
        assertEquals(AC, AIRLINE.getName());
        assertEquals(WJ, airline2.getName());
        airline2.setName(AC);
        assertEquals(AC, airline2.getName());
    }

    @Test
    public void testEqualsMethod() {
        assertFalse(AIRLINE.equals(airline2));    //westjet != air canada
        airline2.setName(AC);
        assertTrue(AIRLINE.equals(airline2));     //air canada = air canada
        airline2.setName("");
        assertFalse(AIRLINE.equals(airline2));    //air canada !=
        assertFalse(AIRLINE.equals(wrongObject)); //air canada != "air canada"
        airline2 = null;
        assertFalse(AIRLINE.equals(airline2));    //air canada != null
    }

    @Test
    public void testCompareTo() {
        assertTrue(AIRLINE.compareTo(AIRLINE) == 0);
        assertTrue(AIRLINE.compareTo(airline2) < 0);
        assertTrue(airline2.compareTo(AIRLINE) > 0);
        assertTrue(AIRLINE.compareTo(wrongObject) == 0);
        assertTrue(AIRLINE.compareTo(null) == 0);
    }
}
