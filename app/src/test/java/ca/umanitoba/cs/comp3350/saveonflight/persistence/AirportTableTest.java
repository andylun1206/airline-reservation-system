package ca.umanitoba.cs.comp3350.saveonflight.persistence;

/**
 * Created by zhengyugu on 2017-06-08.
 */

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;

import ca.umanitoba.cs.comp3350.saveonflight.objects.Airport;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;



public class AirportTableTest {
    private static ArrayList<Airport> original;
    private static AirportTable airportTable;
    private Airport emptyNameCase = new Airport("");
    private Airport vaildCase = new Airport("myAirport MAT");
    @BeforeClass
    public static void setUp() {
        airportTable = new AirportTable();
        airportTable.initialize();
        original = AirportTable.getAirports();
    }

    @Test
    public void testExistence() {
        assertNotNull("Initialize is not work",airportTable);
    }

    @Test
    public void testInitialize() {
        assertEquals("Initialize is not work", original, AirportTable.getAirports());
    }

    @Test
    public void testAddNull() {
        airportTable.add(null);
        assertEquals("add null but actually add something", original, AirportTable.getAirports());
    }

    @Test
    public void testAddEmptyName() {
        airportTable.add(emptyNameCase);
        assertEquals("adding none since object but it shouldn't add", original, AirportTable.getAirports());
    }

    @Test
    public void testAddValid() {
        airportTable.add(vaildCase);
        assertEquals("Failed to add Cathay Pacific to airlineTable.", vaildCase, AirportTable.findAirport("myAirport MAT"));

        airportTable.remove(vaildCase);
    }
    @Test
    public void testAddDuplicate() {

        assertTrue("Failed to add unique airline 'dup'", airportTable.add(vaildCase));
        assertFalse("Succeeded adding a duplicate.",airportTable.add(vaildCase));

        airportTable.remove(vaildCase);
    }
    @Test
    public void testRemoveNull() {
        assertFalse("removed null?",airportTable.remove(null));
    }

    @Test
    public void testRemoveEmptyName() {
        assertFalse("cant remove EmptyName object",airportTable.remove(emptyNameCase));
    }

    @Test
    public void testRemoveValid() {
        airportTable.add(vaildCase);
        assertTrue("fail to remove Valid object",airportTable.remove(vaildCase));
    }

    @Test
    public void testUpdateNull(){
        assertFalse("update to null?",airportTable.update(null));
    }

    @Test
    public void testUpdateEmptyNameFlight(){
        assertFalse("should update a EmptyName object",airportTable.update(emptyNameCase));
    }

    @Test
    public void testUpdateValid(){
        airportTable.add(vaildCase);
        // well done it at next teration
        assertFalse("should update a EmptyName object",airportTable.update(new Airport("America UMA")));

        airportTable.remove(vaildCase);
    }
}
