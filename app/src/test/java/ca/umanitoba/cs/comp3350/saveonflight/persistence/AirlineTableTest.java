package ca.umanitoba.cs.comp3350.saveonflight.persistence;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;

import ca.umanitoba.cs.comp3350.saveonflight.R;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Airline;

import static junit.framework.Assert.*;


/**
 * Created by zhengyugu on 2017-06-08.
 */

public class AirlineTableTest {
    private static ArrayList<Airline> original;
    private static AirlineTable airlineTable;

    @BeforeClass
    public static void setUp() {
        airlineTable = new AirlineTable();
        airlineTable.initialize();
        original = AirlineTable.getAirlines();
    }

    @Test
    public void testExistence() {
        assertNotNull(airlineTable);
    }

    @Test
    public void testInitialize() {
        assertEquals("Initialize is not work", original, AirlineTable.getAirlines());
    }

    @Test
    public void testAddNull() {
        airlineTable.add(null);
        assertEquals("add null but actually add something", original, AirlineTable.getAirlines());
    }

    @Test
    public void testAddEmptyName() {
        airlineTable.add(new Airline("", R.drawable.ic_plane));
        assertEquals("adding none since object but it shouldn't add", original, AirlineTable.getAirlines());
    }

    @Test
    public void testAddEmptyImage() {
        airlineTable.add(new Airline("Cathay Pacific", 0));
        assertEquals("adding none since object but it shouldn't add", original, AirlineTable.getAirlines());
    }

    @Test
    public void testAddValid() {
        Airline cathayPacific = new Airline("Cathay Pacific", R.drawable.ic_plane);
        airlineTable.add(cathayPacific);
        assertEquals("Failed to add Cathay Pacific to airlineTable.", cathayPacific, airlineTable.findAirline("Cathay Pacific"));

        airlineTable.remove(cathayPacific);
    }
    @Test
    public void testAddDuplicate() {
        Airline dup = new Airline("dup", R.mipmap.ic_aircanada);
        assertTrue("Failed to add unique airline 'dup'", airlineTable.add(dup));
        assertFalse("Succeeded adding a duplicate.",airlineTable.add(dup));

        airlineTable.remove(dup);
    }
    @Test
    public void testRemoveNull() {
        assertFalse("removed null?",airlineTable.remove(null));
    }

    @Test
    public void testRemoveEmptyName() {
        assertFalse("cant remove EmptyName object",airlineTable.remove(new Airline("", R.drawable.ic_plane)));
    }

    @Test
    public void testRemoveEmptyImage() {
        assertFalse("cant remove EmptyImage object",airlineTable.remove(new Airline("Cathay Pacific", 0)));
    }

    @Test
    public void testRemoveValid() {
        Airline cathay = new Airline("Cathay Pacific", R.drawable.ic_plane);
        airlineTable.add(cathay);
        assertTrue("fail to remove Valid object",airlineTable.remove(cathay));
    }

    @Test
    public void testUpdateNull(){
        assertFalse("update to null?",airlineTable.update(null));
    }

    @Test
    public void testUpdateEmptyNameFlight(){
        assertFalse("should update a EmptyName object",airlineTable.update(new Airline("", R.drawable.ic_plane)));
    }

    @Test
    public void testUpdateValid(){
        Airline cathay = new Airline("Cathay Pacific", R.drawable.ic_plane);
        airlineTable.add(cathay);
        assertTrue("should update a EmptyName object",airlineTable.update(new Airline("Cathay Pacific", R.mipmap.ic_aircanada)));

        airlineTable.remove(cathay);
    }
}
