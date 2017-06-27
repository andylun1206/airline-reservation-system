package ca.umanitoba.cs.comp3350.saveonflight.business;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import ca.umanitoba.cs.comp3350.saveonflight.business.comparators.DepartureTimeComparator;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Airport;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Flight;

import static org.junit.Assert.*;

public class DepartureTimeComparatorTest {
    private DepartureTimeComparator comparator;
    private Flight.FlightBuilder builder;
    private Calendar cal;

    @Before
    public void setUp() {
        comparator = new DepartureTimeComparator();
        builder = new Flight.FlightBuilder("WJ 101", new Airport("YWG Winnipeg"), new Airport("YYZ Toronto"));
        cal = new GregorianCalendar();
    }

    @After
    public void tearDown() {
        comparator = null;
        builder = null;
        cal = null;
    }

    @Test
    public void testNullDate() {
        Flight f1 = builder.setDepartureTime(null).build();
        Flight f2 = builder.setDepartureTime(new Date()).build();
        assertTrue(comparator.compare(f1, f2) > 0);
        assertTrue(comparator.compare(f2, f1) < 0);
    }

    @Test
    public void testEqualDates() {
        cal.set(2019, 2, 2);
        Flight f1 = builder.setDepartureTime(cal.getTime()).build();
        Flight f2 = builder.setDepartureTime(cal.getTime()).build();
        assertEquals(0, comparator.compare(f1, f1));
        assertEquals(0, comparator.compare(f2, f1));
    }

    @Test
    public void testDifferentDates() {
        cal.set(2019, 2, 2);
        Flight f1 = builder.setDepartureTime(cal.getTime()).build();
        cal.set(2019, 2, 3);
        Flight f2 = builder.setDepartureTime(cal.getTime()).build();
        assertTrue(comparator.compare(f1, f2) < 0);
        assertTrue(comparator.compare(f2, f1) > 0);
    }

}
