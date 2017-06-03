package ca.umanitoba.cs.comp3350.saveonflight.persistence;

import junit.framework.TestCase;

import ca.umanitoba.cs.comp3350.saveonflight.objects.Airport;
import ca.umanitoba.cs.comp3350.saveonflight.objects.SearchCriteria;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Date;
import java.text.ParseException;

/**
 * Created by longyu on 2017-06-02.
 */

public class DataAccessStubTest extends TestCase {
    private DataAccessStub dataAccessStub;
    private SearchCriteria searchCriteria;

    public DataAccessStubTest(String arg0) {
        super(arg0);
    }

    public void setUp() {
        dataAccessStub = new DataAccessStub("test1");
    }

    public void tearDown() {
        dataAccessStub = null;
    }

    public void testExistence() {
        assertNotNull(dataAccessStub);
    }

    public void testSearchByCriteria() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy, MM, dd, HH, mm", Locale.CANADA);
        searchCriteria = new SearchCriteria(new Airport("YVR"), new Airport("YWG"), simpleDateFormat.parse("2017, 11, 11, 22, 30"), simpleDateFormat.parse("2017, 11, 12, 22, 30"), 2, 0.0, null, null, false, false);
        /*Airport origin, Airport destination, Date departureDate, Date returnDate,
                          int numTravellers, double maxPrice, Airline preferredAirlines,
	                      Flight.FlightClass preferredClass, boolean nonstop, boolean refundable*/
        assertNotNull(dataAccessStub.searchByCriteria(searchCriteria));
    }
}
