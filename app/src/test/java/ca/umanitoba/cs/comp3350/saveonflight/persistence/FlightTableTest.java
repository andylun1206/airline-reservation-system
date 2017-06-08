package ca.umanitoba.cs.comp3350.saveonflight.persistence;

/**
 * Created by zhengyugu on 2017-06-08.
 */

import org.junit.BeforeClass;
import org.junit.Test;
import static junit.framework.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import ca.umanitoba.cs.comp3350.saveonflight.objects.Airline;
import ca.umanitoba.cs.comp3350.saveonflight.objects.AirlineTest;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Airport;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Flight;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Traveller;

public class FlightTableTest {
    private static ArrayList<Flight> original;


    private static FlightTable flightTable;

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CANADA);
    private static Flight emptyNameCase;
    private static Flight emptyDateCase;
    private static Flight vaildCase;


    @BeforeClass
    public static void setUp() {
        flightTable = new FlightTable();
        flightTable.initialize();
        original = FlightTable.getFlights();
        new AirportTable().initialize();
        new AirlineTable().initialize();

        try {
            emptyNameCase = new Flight("",sdf.parse("2017-11-11 05:30"), sdf.parse("2017-11-11 08:51"), AirlineTable.findAirline("westjet"),AirportTable.findAirport("YYZ"),
                   AirportTable.findAirport("YWG"), 350.52, 200);
            vaildCase = new Flight("WJ 009",sdf.parse("2017-11-11 05:30"), sdf.parse("2017-11-11 08:51"), AirlineTable.findAirline("westjet"),AirportTable.findAirport("YYZ"),
                    AirportTable.findAirport("YWG"), 350.52, 200);
        }catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testExistence() {
        assertNotNull("Initialize is not work", flightTable);
    }

    @Test
    public void testInitialize() {
        assertEquals("Initialize is not work", original, FlightTable.getFlights());
    }

    @Test
    public void testAddNull() {
        flightTable.add(null);
        assertEquals("add null but actually add something", original, FlightTable.getFlights());
    }

    @Test
    public void testAddEmptyName() {
        flightTable.add(emptyNameCase);
        assertEquals("adding none since object but it shouldn't add", original, FlightTable.getFlights());
    }

    @Test
    public void testAddValid() {

        assertTrue("Failed to add Cathay Pacific to airlineTable.",flightTable.add(vaildCase));

        flightTable.remove(vaildCase);
    }
    @Test
    public void testAddDuplicate() {

        assertTrue("Failed to add unique airline 'dup'", flightTable.add(vaildCase));
        assertFalse("Succeeded adding a duplicate.", flightTable.add(vaildCase));

        flightTable.remove(vaildCase);
    }
    @Test
    public void testRemoveNull() {
        assertFalse("removed null?", flightTable.remove(null));
    }

    @Test
    public void testRemoveEmptyName() {
        assertFalse("cant remove EmptyName object", flightTable.remove(emptyNameCase));
    }

    @Test
    public void testRemoveValid() {
        flightTable.add(vaildCase);
        assertTrue("fail to remove Valid object", flightTable.remove(vaildCase));
    }

    @Test
    public void testUpdateNull(){
        assertFalse("update to null?", flightTable.update(null));
    }

    @Test
    public void testUpdateEmptyNameFlight(){
        assertFalse("should update a EmptyName object", flightTable.update(emptyNameCase));
    }

    @Test
    public void testUpdateValid() throws Exception{
        flightTable.add(vaildCase);

        Flight update=new Flight("WJ 009",sdf.parse("2017-11-11 05:30"), sdf.parse("2017-11-11 08:51"), AirlineTable.findAirline("westjet"),AirportTable.findAirport("YYZ"),
                AirportTable.findAirport("YWG"), 575.21, 500);
        assertTrue("should update a EmptyName object", flightTable.update(update));

        flightTable.remove(update);
    }
}
