package ca.umanitoba.cs.comp3350.saveonflight.business;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ca.umanitoba.cs.comp3350.saveonflight.business.comparators.PriceComparator;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Airport;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Flight;

import static org.junit.Assert.*;

public class PriceComparatorTest {
    private PriceComparator comparator;
    private Flight.FlightBuilder builder;

    @Before
    public void setUp() {
        comparator = new PriceComparator();
        builder = new Flight.FlightBuilder("WJ 101", new Airport("YWG Winnipeg"), new Airport("YYZ Toronto"));
    }

    @After
    public void tearDown() {
        comparator = null;
        builder = null;
    }

    @Test
    public void testSamePrice() {
        Flight f1 = builder.setPrice(100.0).build();
        Flight f2 = builder.build();
        assertEquals(0, comparator.compare(f1, f2));
        assertEquals(0, comparator.compare(f2, f1));
    }

    @Test
    public void testDifferentPrices() {
        Flight f1 = builder.setPrice(100.0).build();
        Flight f2 = builder.setPrice(101.0).build();
        assertTrue(comparator.compare(f1, f2) < 0);
        assertTrue(comparator.compare(f2, f1) > 0);
    }

}
