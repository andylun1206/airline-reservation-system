package ca.umanitoba.cs.comp3350.saveonflight.persistence;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;

import ca.umanitoba.cs.comp3350.saveonflight.application.Main;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Traveller;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.*;

/**
 * Created by longyu on 2017-06-29.
 */

public class TravellerTableSqlTest {
    private static ArrayList<Traveller> original;
    private static TravellerTableSql travellerTable;
    private static TravellerTableSql mockedList;
    private Traveller emptyNameCase = new Traveller(0, "");
    private Traveller validCase = new Traveller(10, "Amir");

    @BeforeClass
    public static void setUp() {
        Main.startUp(Main.DatabaseType.HSQL);
        travellerTable = new TravellerTableSql();
        travellerTable.initialize(Main.getDBPathName());
        original = travellerTable.getTravellers();
        mockedList = mock(TravellerTableSql.class);
        mockedList.add(null);
        mockedList.add(new Traveller(0, ""));
        mockedList.add(new Traveller(100, "CNM"));
        mockedList.remove(null);
        mockedList.remove(new Traveller(0, ""));
        mockedList.remove(new Traveller(0, "Jack"));
        mockedList.findTraveller(10000);
        mockedList.findTraveller(0);
        mockedList.close();

        verify(mockedList).add(null);
        verify(mockedList).add(new Traveller(0, ""));
        verify(mockedList).add(new Traveller(100, "CNM"));
        verify(mockedList).remove(null);
        verify(mockedList).remove(new Traveller(0, ""));
        verify(mockedList).remove(new Traveller(0, "Jack"));
        verify(mockedList).findTraveller(10000);
        verify(mockedList).findTraveller(0);
        verify(mockedList).close();

        when(mockedList.add(null)).thenReturn(false);
        when(mockedList.add(new Traveller(0, ""))).thenReturn(true);
        when(mockedList.add(new Traveller(100, "CNM"))).thenReturn(true);

        when(mockedList.remove(null)).thenReturn(false);
        when(mockedList.remove(new Traveller(0, ""))).thenReturn(false);
        when(mockedList.remove(new Traveller(0, "Jack"))).thenReturn(true);

        when(mockedList.findTraveller(10000)).thenReturn(null);
        when(mockedList.findTraveller(0)).thenReturn(new Traveller(0, "Jack"));
    }

    @Test
    public void testExistence() {
        assertNotNull("Initialize is not work ALL NULL", travellerTable);
    }

    @Test
    public void testInitialize() {
        assertEquals("Initialize is not work", original, travellerTable.getTravellers());
    }

    @Test
    public void testAddNull() {
        assertFalse("Test add null fail", mockedList.add(null));
    }

    @Test
    public void testAddEmptyName() {
        assertTrue("Test add empty fail", mockedList.add(new Traveller(0, "")));
    }

    @Test
    public void testAddValid() {
        assertTrue("Test add valid fail", mockedList.add(new Traveller(100, "CNM")));
    }

    @Test
    public void testRemoveNull() {
        assertFalse("Test remove null fail", mockedList.remove(null));
    }

    @Test
    public void testRemoveEmpty() {
        assertFalse("Test remove empty fail", mockedList.remove(new Traveller(0, "")));
    }

    @Test
    public void testRemoveValid() {
        assertFalse("Test remove valid fail", mockedList.remove(new Traveller(0, "Jack")));
    }

    @Test
    public void testFindTravellerNotIn() {
        assertEquals("Test find traveller not in table fail", null, mockedList.findTraveller(10000));
    }

    @Test
    public void testFindTravellerIn() {
        assertEquals("Test find traveller in table fail", new Traveller(0, "Jack"), mockedList.findTraveller(0));
    }
}
