package ca.umanitoba.cs.comp3350.saveonflight.objects;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import ca.umanitoba.cs.comp3350.saveonflight.R;

import static junit.framework.Assert.*;

public class SearchCriteriaTest {
    private SearchCriteria searchCriteria;

    private final Airport ORIGIN = new Airport("YWG");
    private final Airport DEST = new Airport("YVR");
    private final Date DEPARTURE_DATE = new Date(117,5,6,14,40);
    private final int NUM_TRAVELLERS = 3;
    private final double  MAX = 1000.00;
    private final Airline AIRLINE = new Airline("WestJet", R.mipmap.ic_westjet);
    private final FlightClassEnum PREFERRED_CLASS = FlightClassEnum.FIRST_CLASS;
    private final boolean NONSTOP = true;
    private final boolean REFUNDABLE = true;

    @Before
    public void setUp() {
        searchCriteria = new SearchCriteria(ORIGIN,DEST,DEPARTURE_DATE,NUM_TRAVELLERS,MAX,AIRLINE,PREFERRED_CLASS,NONSTOP,REFUNDABLE);
    }

    @After
    public void tearDown() {
        searchCriteria = null;
    }

    @Test
    public void testMatches() {
        System.out.println("\nStarting test searchCriteria");

        assertNotNull(searchCriteria);
        assertTrue(ORIGIN.equals(searchCriteria.getOrigin()));
        assertTrue(DEST.equals(searchCriteria.getDestination()));
        assertTrue(DEPARTURE_DATE.equals(searchCriteria.getDepartureDate()));
        assertTrue(NUM_TRAVELLERS == searchCriteria.getNumTravellers());
        assertTrue(MAX == searchCriteria.getMaxPrice());
        assertTrue(AIRLINE.equals(searchCriteria.getPreferredAirlines()));
        assertTrue(PREFERRED_CLASS.equals(searchCriteria.getPreferredClass()));
        assertTrue(searchCriteria.isNonstop());
        assertTrue(searchCriteria.isRefundable());
    }
}
