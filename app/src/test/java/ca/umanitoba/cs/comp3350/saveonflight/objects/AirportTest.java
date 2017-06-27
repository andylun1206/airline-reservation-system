package ca.umanitoba.cs.comp3350.saveonflight.objects;

import android.os.Parcel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AirportTest {
    private final String CODE1 = "WPG01";
    private final String CITY1 = "Winnipeg01";
    private final String CODE2 = "WPG02";
    private final String CITY2 = "Winnipeg02";
    private final Airport AIRPORT1 = new Airport(CODE1, CITY1);
    private Airport airport2;
    private Airport airport3;


    @Before
    public void setUp() {
        airport2 = new Airport(CODE2, CITY2);
        airport3 = new Airport(CODE1, CITY1);
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
    public void testgetCode() {
        assertEquals(AIRPORT1.getCode(), CODE1);
        assertEquals(AIRPORT1.getCode(), airport3.getCode());
        //error cases
        assertFalse((AIRPORT1.getCode()).equals(airport2.getCode()));
        assertFalse((AIRPORT1.getCode()).equals("wpg01"));
        assertFalse((AIRPORT1.getCode()).equals(""));
    }

    @Test
    public void testsetCode() {
        airport2.setCode("hello");
        assertEquals(airport2.getCode(), "hello");
        airport3.setCode("happy");
        assertFalse(airport3.equals(AIRPORT1));
    }

    @Test
    public void testContains() {
        assertFalse(AIRPORT1.contains(null));
        assertFalse(AIRPORT1.contains("ASDJIASJ"));
        assertFalse(AIRPORT1.contains(airport2));
        Airport match = new Airport(CODE1, CITY1);
        assertTrue(AIRPORT1.contains(match));                // Exact match should return true
        Airport subset = new Airport(CODE1.substring(0, 2), CITY1.substring(0, 7));
        assertTrue(AIRPORT1.contains(subset));               // Subset match should return true
    }

    @Test
    public void testParcelable() {
        Parcel parcel = mock(Parcel.class);
        when(parcel.readString()).thenReturn(AIRPORT1.getCode());
        assertNotNull(parcel);
        AIRPORT1.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        Airport parceledAirport = (Airport) Airport.CREATOR.createFromParcel(parcel);
        assertEquals(parceledAirport, AIRPORT1);
        parcel.recycle();
    }
}
