package ca.umanitoba.cs.comp3350.saveonflight.business;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import ca.umanitoba.cs.comp3350.saveonflight.business.comparators.DurationComparator;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Airport;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Flight;

import static org.junit.Assert.*;

public class DurationComparatorTest {
    private DurationComparator comparator;
    private Flight.FlightBuilder builder;
    private Calendar cal;

    @Before
    public void setUp() {
        comparator = new DurationComparator();
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
    public void testSameDuration() {
        cal.set(2017, 1, 1, 1, 1);
        builder.setDepartureTime(cal.getTime());
        cal.set(2017, 1, 1, 3, 1);
        builder.setArrivalTime(cal.getTime());
        Flight f1 = builder.build();
        Flight f2 = builder.build();
        assertEquals(0, comparator.compare(f1, f2));
    }

    @Test
    public void testDifferentDurations() {
        cal.set(2017, 1, 1, 1, 1);
        builder.setDepartureTime(cal.getTime());
        cal.set(2017, 1, 1, 3, 1);
        builder.setArrivalTime(cal.getTime());
        Flight f1 = builder.build();

        cal.set(2017, 1, 1, 4, 1);
        builder.setArrivalTime(cal.getTime());
        Flight f2 = builder.build();

        assertTrue(comparator.compare(f1, f2) < 0);
        assertTrue(comparator.compare(f2, f1) > 0);
    }
}
