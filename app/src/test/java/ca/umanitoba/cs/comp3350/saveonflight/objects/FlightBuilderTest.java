package ca.umanitoba.cs.comp3350.saveonflight.objects;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

import ca.umanitoba.cs.comp3350.saveonflight.R;

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
    public void testSetFlightId() {
        assertEquals(FLIGHT_ID, builder.build().getFlightID());
        Flight f = builder.setFlightId("ABC").build();
        assertEquals("ABC", f.getFlightID());
    }

    @Test
    public void testSetAirline() {
        assertEquals(null, builder.build().getAirline());
        Airline a = new Airline("Gord Airliens", R.mipmap.ic_launcher);
        assertEquals(a, builder.setAirline(a).build().getAirline());
    }

    @Test
    public void testSetDepartureTime() {
        assertEquals(null, builder.build().getDepartureTime());
        Calendar cal = new GregorianCalendar();
        cal.set(2020, 12, 1, 12, 30);
        Date date = cal.getTime();
        assertEquals(date, builder.setDepartureTime(date).build().getDepartureTime());
    }

    @Test
    public void testSetArrivalTime() {
        assertEquals(null, builder.build().getArrivalTime());
        Calendar cal = new GregorianCalendar();
        cal.set(2020, 12, 1, 12, 30);
        Date date = cal.getTime();
        assertEquals(date, builder.setArrivalTime(date).build().getArrivalTime());
    }

    @Test
    public void testSetOrigin() {
        assertEquals(ORIGIN, builder.build().getOrigin());
        Airport ywg = new Airport("YVR Vancouver");
        assertEquals(ywg, builder.setOrigin(ywg).build().getOrigin());
    }

    @Test
    public void testSetDestination() {
        assertEquals(DEST, builder.build().getDestination());
        Airport ywg = new Airport("YVR Vancouver");
        assertEquals(ywg, builder.setDestination(ywg).build().getDestination());
    }

    @Test
    public void testSetPrice() {
        assertTrue(-1 == builder.build().getPrice());
        assertTrue(100 == builder.setPrice(100.).build().getPrice());
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
        assertEquals(-1, f.getCapacity());
        assertEquals(0, f.getSeatsTaken());
        assertEquals(FlightClassEnum.ECONOMY, f.getFlightClass());
        assertTrue(-1 == f.getPrice());
    }

}