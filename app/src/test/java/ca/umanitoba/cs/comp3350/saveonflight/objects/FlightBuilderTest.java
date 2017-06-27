package ca.umanitoba.cs.comp3350.saveonflight.objects;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class FlightBuilderTest {
    private Flight.FlightBuilder builder;

    private final String FLIGHT_ID = "WJ101";
    private final Airport ORIGIN = new Airport("YWG Winnipeg");
    private final Airport DEST = new Airport("YYZ Toronto");

    @Before
    public void setUp() {
        builder = new Flight.FlightBuilder(FLIGHT_ID, ORIGIN, DEST);
    }

    @After
    public void tearDown() {
        builder = null;
    }

    @Test
    public void testBuild() {
        Flight f = builder.build();
        assertEquals(FLIGHT_ID, f.getFlightID());
        assertEquals(ORIGIN, f.getOrigin());
        assertEquals(DEST, f.getDestination());
        assertSame(null, f.getAirline());
        assertSame(null, f.getDepartureTime());
        assertSame(null, f.getArrivalTime());
        assertEquals(Long.MIN_VALUE, f.getDateDiff(TimeUnit.MILLISECONDS));
        assertEquals
    }

}