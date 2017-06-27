package ca.umanitoba.cs.comp3350.saveonflight;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import ca.umanitoba.cs.comp3350.saveonflight.business.DepartureTimeComparatorTest;
import ca.umanitoba.cs.comp3350.saveonflight.business.DurationComparatorTest;
import ca.umanitoba.cs.comp3350.saveonflight.business.PriceComparatorTest;
import ca.umanitoba.cs.comp3350.saveonflight.business.SortFlightsTest;
import ca.umanitoba.cs.comp3350.saveonflight.business.comparators.DepartureTimeComparator;
import ca.umanitoba.cs.comp3350.saveonflight.objects.AirlineTest;
import ca.umanitoba.cs.comp3350.saveonflight.objects.AirportTest;
import ca.umanitoba.cs.comp3350.saveonflight.objects.BookedFlightTest;
import ca.umanitoba.cs.comp3350.saveonflight.objects.FlightBuilderTest;
import ca.umanitoba.cs.comp3350.saveonflight.objects.FlightTest;
import ca.umanitoba.cs.comp3350.saveonflight.objects.SearchCriteriaListViewEntryTest;
import ca.umanitoba.cs.comp3350.saveonflight.objects.SearchCriteriaTest;
import ca.umanitoba.cs.comp3350.saveonflight.objects.TravellerTest;
import ca.umanitoba.cs.comp3350.saveonflight.objects.ViewFlightsListViewEntryTest;
import ca.umanitoba.cs.comp3350.saveonflight.persistence.AirlineTableTest;
import ca.umanitoba.cs.comp3350.saveonflight.persistence.AirportTableTest;
import ca.umanitoba.cs.comp3350.saveonflight.persistence.BookedFlightTableTest;
import ca.umanitoba.cs.comp3350.saveonflight.persistence.FlightTableTest;
import ca.umanitoba.cs.comp3350.saveonflight.persistence.TravellerTableTest;


@RunWith(Suite.class)
@Suite.SuiteClasses({
        AirlineTest.class,
        AirportTest.class,
        BookedFlightTest.class,
        FlightTest.class,
        FlightBuilderTest.class,
        SearchCriteriaTest.class,
        TravellerTest.class,
        SearchCriteriaListViewEntryTest.class,
        ViewFlightsListViewEntryTest.class,
        SortFlightsTest.class,
        DepartureTimeComparatorTest.class,
        DurationComparatorTest.class,
        PriceComparatorTest.class,
        AirlineTableTest.class,
        AirportTableTest.class,
        TravellerTableTest.class,
        BookedFlightTableTest.class,
        FlightTableTest.class

})

public class AllTests {
}

