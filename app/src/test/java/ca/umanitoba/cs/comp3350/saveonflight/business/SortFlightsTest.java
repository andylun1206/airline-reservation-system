package ca.umanitoba.cs.comp3350.saveonflight.business;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import ca.umanitoba.cs.comp3350.saveonflight.objects.Airline;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Airport;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Flight;

import static junit.framework.Assert.*;

public class SortFlightsTest {
    private ArrayList<Flight> flights;

    @Before
    public void setUp() {
        String flightID1 = "WJ101";
        String flightID2 = "WJ102";
        String flightID3 = "AC1";

        Date d1, d2;
        try {
            d1 = Flight.SIMPLE_DATE_FORMAT.parse("2017, 08, 07");
            d2 = Flight.SIMPLE_DATE_FORMAT.parse("2017, 08, 08");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Airline a1 = new Airline("WestJet");
        Airline a2 = new Airline("Air Canada");

        Airport ywg = new Airport("YWG");
        Airport yvr = new Airport("YVR");
        Airport yyc = new Airport ("YYC");

        flights.add(flightID1, d1, a1, ywg, yvr, )


    }

    @After
    public void tearDown() {

    }

    @Test
    public void testSortDate() {

    }
}
