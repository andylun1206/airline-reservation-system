package ca.umanitoba.cs.comp3350.saveonflight.objects;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ca.umanitoba.cs.comp3350.saveonflight.R;
import ca.umanitoba.cs.comp3350.saveonflight.presentation.viewFlights.ViewFlightsListViewEntry;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;

/**
 * Created by Shenyun Wang on 2017-06-06.
 */

public class ViewFlightsListViewEntryTest {
    private ViewFlightsListViewEntry entry1;
    private ViewFlightsListViewEntry entry2;
    private Airline a1;
    private Airline a2;

    @Before
    public void setup() {
        a1 = new Airline("WestJet");
        entry1 = new ViewFlightsListViewEntry("8:00am", 350.00, a1, "AC01", "3hr");
        entry2 = new ViewFlightsListViewEntry("9:00am", 400.00, a1, "AC02", "2hr");
        assertNotNull(entry1);
        assertNotNull(entry2);
    }

    @After
    public void tearDown() {
        entry1 = null;
        entry2 = null;
    }

    @Test
    public void testGetTimeMethod() {
        assertEquals("8:00am", entry1.getTime());
        assertFalse((entry1.getTime()).equals(entry2.getTime()));
    }

    @Test
    public void testGetPrice() {
        assertEquals(entry1.getPrice(), 350.0);
        assertFalse(entry1.getPrice() == entry2.getPrice());
    }

    @Test
    public void testGetAirline() {
        assertEquals(a1, entry1.getAirline());
        assertEquals(a1, entry2.getAirline());
        assertEquals(entry1.getAirline(), entry2.getAirline());
    }

    @Test
    public void testSetAirline() {
        entry1.setAirline(a2);
        assertEquals(entry1.getAirline(), a2);
        entry2.setAirline(a2);
        assertEquals(entry2.getAirline(), entry1.getAirline());
        assertFalse(entry2.getAirline() == a1);
    }

    @Test
    public void testGetFlightID() {
        assertFalse(entry1.getFlightId().equals(entry2.getFlightId()));
        assertEquals(entry1.getFlightId(), "AC01");
    }

    @Test
    public void testGetDuration() {
        assertFalse((entry1.getDuration()).equals(entry2.getDuration()));
        assertEquals(entry1.getDuration(), "3hr");
    }

}
