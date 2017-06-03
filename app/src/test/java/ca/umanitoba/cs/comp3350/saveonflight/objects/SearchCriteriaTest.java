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
    private final Date departureDate = new Date(117,5,6,14,40);
    private final Date returnDate = new Date(117,5,16,14,40);
    private final int numTravellers = 3;
    private final double  MAX = 1000.00;
    private final Airline AIRLINE = new Airline("WestJet", R.mipmap.ic_westjet);
    private final FlightClassEnum preferredClass = FlightClassEnum.FIRST_CLASS;
    private final boolean nonstop = true;
    private final boolean refundable = true;

    @Before
    public void setUp() {

        searchCriteria = new SearchCriteria(ORIGIN,DEST,departureDate,returnDate,numTravellers,MAX,AIRLINE,preferredClass,nonstop,refundable);
    }

    @After
    public void tearDown() {
        searchCriteria = null;
    }

    @Test
    public void testMatches() {

        Airport originText = new Airport("YWG");
        Airport destText = new Airport("YVR");
        Date departtureDateTest = new Date(117,5,6,14,40);
        Date returnDateTest = new Date(117,5,16,14,40);
        Airline airlineTest = new Airline("WestJet", R.mipmap.ic_westjet);
        FlightClassEnum preferredClassTest = FlightClassEnum.FIRST_CLASS;

        System.out.println("\nStarting test searchCriteria");

        assertNotNull(searchCriteria);
        assertTrue(originText.equals(searchCriteria.getOrigin()));
        assertTrue(destText.equals(searchCriteria.getDestination()));
        assertTrue(departtureDateTest.equals(searchCriteria.getDepartureDate()));
        assertTrue(returnDateTest.equals(searchCriteria.getReturnDate()));
        assertTrue(3 == searchCriteria.getNumTravellers());
        assertTrue(1000 == searchCriteria.getMaxPrice());
        assertTrue(airlineTest.equals(searchCriteria.getPreferredAirlines()));
        assertTrue(preferredClassTest.equals(searchCriteria.getPreferredClass()));
        assertTrue(searchCriteria.isNonstop());
        assertTrue(searchCriteria.isRefundable());


    }
}
