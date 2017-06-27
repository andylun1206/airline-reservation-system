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

    @Before
    public void setUp() {
        comparator = new DepartureTimeComparator();
        builder = new Flight.FlightBuilder("WJ 101", new Airport("YWG Winnipeg"), new Airport("YYZ Toronto"));
    }

    @After
    public void tearDown() {
        comparator = null;
        builder = null;
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
        Calendar cal = new GregorianCalendar();
        

    }


}
