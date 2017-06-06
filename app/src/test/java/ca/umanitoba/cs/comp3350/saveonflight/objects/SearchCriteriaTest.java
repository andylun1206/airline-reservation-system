package ca.umanitoba.cs.comp3350.saveonflight.objects;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import ca.umanitoba.cs.comp3350.saveonflight.R;

import static junit.framework.Assert.*;

public class SearchCriteriaTest {
    private SearchCriteria searchCriteria;

    private SearchCriteria searchCriteriaTest1;
    private SearchCriteria searchCriteriaTest2;
    private SearchCriteria searchCriteriaTest3;

    private final Airport ORIGIN = new Airport("YWG");
    private final Airport DEST = new Airport("YVR");
    private final Date DEPARTURE_DATE = new Date(117, 5, 6, 14, 40);
    private final int NUM_TRAVELLERS = 3;
    private final double MAX = 1000.00;
    private final Airline AIRLINE = new Airline("WestJet", R.mipmap.ic_westjet);
    private final FlightClassEnum PREFERRED_CLASS = FlightClassEnum.FIRST_CLASS;


    @Before
    public void setUp() {
        searchCriteria = new SearchCriteria(ORIGIN, DEST, DEPARTURE_DATE, NUM_TRAVELLERS, MAX, AIRLINE, PREFERRED_CLASS, true, true);
        searchCriteriaTest1 = new SearchCriteria(new Airport("YWG"), new Airport("YVR"), new Date(117, 5, 6, 14, 40), 3, 1000, new Airline("WestJet", R.mipmap.ic_westjet), FlightClassEnum.FIRST_CLASS, true, true);
        searchCriteriaTest2 = new SearchCriteria(null, null, null, 0, 0, null, null, false, false);
        searchCriteriaTest3 = null;

    }

    @After
    public void tearDown() {
        searchCriteriaTest1 = null;
        searchCriteriaTest2 = null;
    }

    @Test
    public void testMatches() {
        assertNotNull(searchCriteriaTest1);
        assertTrue(ORIGIN.equals(searchCriteriaTest1.getOrigin()));
        assertTrue(DEST.equals(searchCriteriaTest1.getDestination()));
        assertTrue(DEPARTURE_DATE.equals(searchCriteriaTest1.getDepartureDate()));
        assertTrue(NUM_TRAVELLERS == searchCriteriaTest1.getNumTravellers());
        assertTrue(MAX == searchCriteriaTest1.getMaxPrice());
        assertTrue(AIRLINE.equals(searchCriteriaTest1.getPreferredAirlines()));
        assertTrue(PREFERRED_CLASS.equals(searchCriteriaTest1.getPreferredClass()));
        assertTrue(searchCriteriaTest1.isNonstop());
        assertTrue(searchCriteriaTest1.isRefundable());

        assertFalse(searchCriteria.equals(searchCriteriaTest1));//same condition but different search


    }

    @Test
    public void testEmpty() {
        assertNotNull(searchCriteriaTest2);
        assertNull(searchCriteriaTest2.getOrigin());
        assertNull(searchCriteriaTest2.getDestination());
        assertNull(searchCriteriaTest2.getDepartureDate());
        assertNull(searchCriteriaTest2.getPreferredAirlines());
        assertNull(searchCriteriaTest2.getPreferredClass());
        assertFalse(searchCriteriaTest2.isNonstop());
        assertFalse(searchCriteriaTest2.isRefundable());
    }

    @Test
    public void testNullData() {
        assertNull(searchCriteriaTest3);
    }

}
