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
        mockedList.add(new Airline("WinnipegAirline"));
        mockedList.findAirline("AirCanada");
        mockedList.close();

        verify(mockedList).add(null);
        verify(mockedList).add(new Airline(""));
        verify(mockedList).add(new Airline("WinnipegAirline"));
        verify(mockedList).findAirline(null);
        verify(mockedList).findAirline("");
        verify(mockedList).findAirline("WinnipegAirline");
        verify(mockedList).findAirline("AirCanada");
        verify(mockedList).close();

        when(mockedList.add(null)).thenReturn(false);
        when(mockedList.add(new Airline(""))).thenReturn(true);
        when(mockedList.add(new Airline("WinnipegAirline"))).thenReturn(true);


        when(mockedList.findAirline(null)).thenReturn(null);
        when(mockedList.findAirline("")).thenReturn(null);
        when(mockedList.findAirline("WinnipegAirline")).thenReturn(null);
        when(mockedList.findAirline("AirCanada")).thenReturn(new Airline("AirCanada"));

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
        assertTrue("add null but actually add something", travellerTable.add(null) < 0);
    }

    @Test
    public void testAddEmptyName() {
        travellerTable.add(emptyNameCase);
        assertEquals("adding none since object but it shouldn't add", original, travellerTable.getTravellers());
    }

    @Test
    public void testAddValid() {
        assertTrue(travellerTable.add(validCase) > 0);
    }
}
