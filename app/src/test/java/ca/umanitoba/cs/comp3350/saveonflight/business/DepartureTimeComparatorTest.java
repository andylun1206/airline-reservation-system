package ca.umanitoba.cs.comp3350.saveonflight.business;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ca.umanitoba.cs.comp3350.saveonflight.business.comparators.DepartureTimeComparator;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Flight;

public class DepartureTimeComparatorTest {
    private DepartureTimeComparator comparator;


    @Before
    public void setUp() {
        comparator = new DepartureTimeComparator();
    }

    @After
    public void tearDown() {
        comparator = null;
    }

    @Test
    public void testNullFlight() {
        Flight f1 = null;
        //Flight f2 = new Flight();
    }


}
