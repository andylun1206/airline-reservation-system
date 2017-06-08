package ca.umanitoba.cs.comp3350.saveonflight.persistence;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ca.umanitoba.cs.comp3350.saveonflight.application.Main;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Airport;
import ca.umanitoba.cs.comp3350.saveonflight.objects.SearchCriteria;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Date;
import java.text.ParseException;

import static junit.framework.Assert.assertNotNull;

public class DataAccessStubTest {
    private DataAccessStub dataAccessStub;
    private SearchCriteria searchCriteria;

    @Before
    public void setUp() {
        Main.startUp();
        dataAccessStub = new DataAccessStub("test1");
        dataAccessStub.open("test1");
    }

    @After
    public void tearDown() {
        dataAccessStub = null;
        Main.shutDown();
    }

    @Test
    public void testExistence() {
        assertNotNull(dataAccessStub);
    }

    @Test
    public void testSearchByCriteria() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy, MM, dd, HH, mm", Locale.CANADA);
        searchCriteria = new SearchCriteria(new Airport("YVR"), new Airport("YWG"), simpleDateFormat.parse("2017, 11, 11, 22, 30"), 2, 0.0, null, null, false, false);
        assertNotNull(dataAccessStub.searchByCriteria(searchCriteria));
    }
}
