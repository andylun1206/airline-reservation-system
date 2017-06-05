package ca.umanitoba.cs.comp3350.saveonflight.objects;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import ca.umanitoba.cs.comp3350.saveonflight.R;

import static junit.framework.Assert.*;

public class SearchCriteriaTest {
    private SearchCriteria searchCriteria1;
    private SearchCriteria searchCriteria2;

    private final Airport ORIGIN = new Airport("YWG");
    private final Airport DEST = new Airport("YVR");
    private final Date DEPARTURE_DATE = new Date(117, 5, 6, 14, 40);
    private final int NUM_TRAVELLERS = 3;
    private final double MAX = 1000.00;
    private final Airline AIRLINE = new Airline("WestJet", R.mipmap.ic_westjet);
    private final FlightClassEnum PREFERRED_CLASS = FlightClassEnum.FIRST_CLASS;

    @Before
    public void setUp() {
        searchCriteria1 = new SearchCriteria(new Airport("YWG"), new Airport("YVR"), new Date(117, 5, 6, 14, 40), 3, 1000, new Airline("WestJet", R.mipmap.ic_westjet), FlightClassEnum.FIRST_CLASS, true, true);
        searchCriteria2 = new SearchCriteria(null, null, null, 0, 0, null, null, false, false);

    }

    @After
    public void tearDown() {
        searchCriteria1 = null;
    }

    @Test
    public void testMatches() {
        System.out.println("\nStarting test searchCriteria");

        assertNotNull(searchCriteria1);
        assertTrue(ORIGIN.equals(searchCriteria1.getOrigin()));
        assertTrue(DEST.equals(searchCriteria1.getDestination()));
        assertTrue(DEPARTURE_DATE.equals(searchCriteria1.getDepartureDate()));
        assertTrue(NUM_TRAVELLERS == searchCriteria1.getNumTravellers());
        assertTrue(MAX == searchCriteria1.getMaxPrice());
        assertTrue(AIRLINE.equals(searchCriteria1.getPreferredAirlines()));
        assertTrue(PREFERRED_CLASS.equals(searchCriteria1.getPreferredClass()));
        assertTrue(searchCriteria1.isNonstop());
        assertTrue(searchCriteria1.isRefundable());


    }

    @Test
    public void testEmpty() {
        System.out.println("\nStarting test Empty searchCriteria");
        assertNotNull(searchCriteria2);
        assertNull(searchCriteria2.getOrigin());
        assertNull(searchCriteria2.getDestination());
        assertNull(searchCriteria2.getDepartureDate());
        assertNull(searchCriteria2.getPreferredAirlines());
        assertNull(searchCriteria2.getPreferredClass());
    }


}
