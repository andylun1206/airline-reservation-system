package ca.umanitoba.cs.comp3350.saveonflight.persistence;

/**
 * Created by zhengyugu on 2017-06-08.
 */

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;

import ca.umanitoba.cs.comp3350.saveonflight.objects.Traveller;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

public class TravellerTableTest {
    private static ArrayList<Traveller> original;
    private static DataAccess<Traveller> travellerTable;
    private Traveller emptyNameCase = new Traveller(0,"");
    private Traveller validCase = new Traveller(10,"Amir");
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

        assertTrue("Failed to add Cathay Pacific to airlineTable.", travellerTable.add(validCase) );

        travellerTable.remove(validCase);
    }
    @Test
    public void testAddDuplicate() {

        assertTrue("Failed to add unique airline 'dup'", travellerTable.add(validCase));
        assertFalse("Succeeded adding a duplicate.", travellerTable.add(validCase));

        travellerTable.remove(validCase);
    }
    @Test
    public void testRemoveNull() {
        assertFalse("removed null?", travellerTable.remove(null));
    }


    @Test
    public void testRemoveValid() {
        travellerTable.add(validCase);
        assertTrue("fail to remove Valid object", travellerTable.remove(validCase));
    }

    @Test
    public void testUpdateNull(){
        assertFalse("update to null?", travellerTable.update(null));
    }


    @Test
    public void testUpdateValid(){
        travellerTable.add(validCase);
        assertTrue("should update a EmptyName object", travellerTable.update(new Traveller(10,"Jack")));

        travellerTable.remove(new Traveller(10,"Jack"));
    }
}
