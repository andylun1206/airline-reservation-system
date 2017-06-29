package ca.umanitoba.cs.comp3350.saveonflight.objects;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import ca.umanitoba.cs.comp3350.saveonflight.R;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertTrue;

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
        searchCriteria = new SearchCriteria();
        searchCriteria.setOrigin(ORIGIN);
        searchCriteria.setDestination(DEST);
        searchCriteria.setDepartureDate(DEPARTURE_DATE);
        searchCriteria.setNumTravellers(NUM_TRAVELLERS);
        searchCriteria.setMaxPrice(MAX);
        searchCriteria.setPreferredAirlines(AIRLINE);
        searchCriteria.setPreferredClass(PREFERRED_CLASS);
        searchCriteria.setRefundable(true);
        searchCriteria.setNonstop(true);

        searchCriteriaTest1 = new SearchCriteria();
        searchCriteriaTest1.setOrigin(new Airport("YWG"));
        searchCriteriaTest1.setDestination(new Airport("YVR"));
        searchCriteriaTest1.setDepartureDate(new Date(117, 5, 6, 14, 40));
        searchCriteriaTest1.setNumTravellers(3);
        searchCriteriaTest1.setMaxPrice(1000);
        searchCriteriaTest1.setPreferredAirlines(new Airline("WestJet", R.mipmap.ic_westjet));
        searchCriteriaTest1.setPreferredClass(FlightClassEnum.FIRST_CLASS);
        searchCriteriaTest1.setRefundable(true);
        searchCriteriaTest1.setNonstop(true);

        searchCriteriaTest2 = new SearchCriteria();
        searchCriteriaTest2.setOrigin(null);
        searchCriteriaTest2.setDestination(null);
        searchCriteriaTest2.setDepartureDate(null);
        searchCriteriaTest2.setNumTravellers(0);
        searchCriteriaTest2.setMaxPrice(0);
        searchCriteriaTest2.setPreferredAirlines(null);
        searchCriteriaTest2.setPreferredClass(null);
        searchCriteriaTest2.setRefundable(false);
        searchCriteriaTest2.setNonstop(false);
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
        assertTrue(AIRLINE.equals(searchCriteriaTest1.getPreferredAirline()));
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
        assertNull(searchCriteriaTest2.getPreferredAirline());
        assertNull(searchCriteriaTest2.getPreferredClass());
        assertFalse(searchCriteriaTest2.isNonstop());
        assertFalse(searchCriteriaTest2.isRefundable());
    }

    @Test
    public void testReturnTrip() {
        searchCriteria.setReturnTrip(true);
        assertTrue(searchCriteria.isReturnTrip());
        searchCriteria.setReturnTrip(false);
        assertFalse(searchCriteria.isReturnTrip());
    }

}
