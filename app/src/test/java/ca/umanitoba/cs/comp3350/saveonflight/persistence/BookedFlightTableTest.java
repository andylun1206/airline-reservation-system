package ca.umanitoba.cs.comp3350.saveonflight.persistence;

/**
 * Created by zhengyugu on 2017-06-08.
 */

import org.junit.BeforeClass;
import org.junit.Test;
import static junit.framework.Assert.*;
import java.util.ArrayList;

import ca.umanitoba.cs.comp3350.saveonflight.objects.BookedFlight;

public class BookedFlightTableTest {
    private static ArrayList<BookedFlight> original;
    private static BookedFlightTable bookedFlightTable;
    private BookedFlight emptyNameCase = new BookedFlight("");
    private BookedFlight vaildCase = new BookedFlight("myAirport MAT");
    @BeforeClass
    public static void setUp() {
        bookedFlightTable = new AirportTable();
        bookedFlightTable.initialize();
        original = AirportTable.getAirports();
    }

    @Test
    public void testExistence() {
        assertNotNull("Initialize is not work", bookedFlightTable);
    }

    @Test
    public void testInitialize() {
        assertEquals("Initialize is not work", original, AirportTable.getAirports());
    }

    @Test
    public void testAddNull() {
        bookedFlightTable.add(null);
        assertEquals("add null but actually add something", original, AirportTable.getAirports());
    }

    @Test
    public void testAddEmptyName() {
        bookedFlightTable.add(emptyNameCase);
        assertEquals("adding none since object but it shouldn't add", original, AirportTable.getAirports());
    }

    @Test
    public void testAddValid() {
        bookedFlightTable.add(vaildCase);
        assertEquals("Failed to add Cathay Pacific to airlineTable.", vaildCase, AirportTable.findAirport("myAirport MAT"));

        bookedFlightTable.remove(vaildCase);
    }
    @Test
    public void testAddDuplicate() {

        assertTrue("Failed to add unique airline 'dup'", bookedFlightTable.add(vaildCase));
        assertFalse("Succeeded adding a duplicate.", bookedFlightTable.add(vaildCase));

        bookedFlightTable.remove(vaildCase);
    }
    @Test
    public void testRemoveNull() {
        assertFalse("removed null?", bookedFlightTable.remove(null));
    }

    @Test
    public void testRemoveEmptyName() {
        assertFalse("cant remove EmptyName object", bookedFlightTable.remove(emptyNameCase));
    }

    @Test
    public void testRemoveValid() {
        bookedFlightTable.add(vaildCase);
        assertTrue("fail to remove Valid object", bookedFlightTable.remove(vaildCase));
    }

    @Test
    public void testUpdateNull(){
        assertFalse("update to null?", bookedFlightTable.update(null));
    }

    @Test
    public void testUpdateEmptyNameFlight(){
        assertFalse("should update a EmptyName object", bookedFlightTable.update(emptyNameCase));
    }

    @Test
    public void testUpdateValid(){
        bookedFlightTable.add(vaildCase);
        // well done it at next teration
        assertFalse("should update a EmptyName object", bookedFlightTable.update(new BookedFlight("America UMA")));

        bookedFlightTable.remove(vaildCase);
    }
}
