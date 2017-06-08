package ca.umanitoba.cs.comp3350.saveonflight.persistence;

/**
 * Created by zhengyugu on 2017-06-08.
 */

import org.junit.BeforeClass;
import org.junit.Test;
import static junit.framework.Assert.*;
import java.util.ArrayList;

import ca.umanitoba.cs.comp3350.saveonflight.objects.Traveller;

public class TravellerTableTest {
    private static ArrayList<Traveller> original;
    private static TravellerTable travellerTable;
    private Traveller emptyNameCase = new Traveller(0,"");
    private Traveller vaildCase = new Traveller(10,"Amir");
    @BeforeClass
    public static void setUp() {
        travellerTable = new TravellerTable();
        travellerTable.initialize();
        original = TravellerTable.getTravellers();
    }

    @Test
    public void testExistence() {
        assertNotNull("Initialize is not work ALL NULL", travellerTable);
    }

    @Test
    public void testInitialize() {
        assertEquals("Initialize is not work", original,TravellerTable.getTravellers());
    }

    @Test
    public void testAddNull() {
        travellerTable.add(null);
        assertEquals("add null but actually add something", original, TravellerTable.getTravellers());
    }

    @Test
    public void testAddEmptyName() {
        travellerTable.add(emptyNameCase);
        assertEquals("adding none since object but it shouldn't add", original, TravellerTable.getTravellers());
    }

    @Test
    public void testAddValid() {
        travellerTable.add(vaildCase);
        //need find
        assertEquals("Failed to add Cathay Pacific to airlineTable.", vaildCase,vaildCase );

        travellerTable.remove(vaildCase);
    }
    @Test
    public void testAddDuplicate() {

        assertTrue("Failed to add unique airline 'dup'", travellerTable.add(vaildCase));
        assertFalse("Succeeded adding a duplicate.", travellerTable.add(vaildCase));

        travellerTable.remove(vaildCase);
    }
    @Test
    public void testRemoveNull() {
        assertFalse("removed null?", travellerTable.remove(null));
    }

    @Test
    public void testRemoveEmptyName() {
        assertTrue("cant remove EmptyName object", travellerTable.remove(emptyNameCase));
    }

    @Test
    public void testRemoveValid() {
        travellerTable.add(vaildCase);
        assertTrue("fail to remove Valid object", travellerTable.remove(vaildCase));
    }

    @Test
    public void testUpdateNull(){
        assertFalse("update to null?", travellerTable.update(null));
    }

    @Test
    public void testUpdateEmptyNameFlight(){
        assertFalse("should update a EmptyName object", travellerTable.update(emptyNameCase));
    }

    @Test
    public void testUpdateValid(){
        travellerTable.add(vaildCase);
        assertTrue("should update a EmptyName object", travellerTable.update(new Traveller(10,"Jack")));

        travellerTable.remove(vaildCase);
    }
}
