package ca.umanitoba.cs.comp3350.saveonflight.persistence;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import ca.umanitoba.cs.comp3350.saveonflight.R;
import ca.umanitoba.cs.comp3350.saveonflight.application.Main;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Airline;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.*;

/**
 * Created by longyu on 2017-06-29.
 */

public class AirlineTableSqlTest {

    private static ArrayList<Airline> original;
    private static AirlineTableSql airlineTable;
    private static AirlineTableSql mockedList;

    @BeforeClass
    public static void setUp() {
        Main.startUp(Main.DatabaseType.HSQL);
        airlineTable = new AirlineTableSql();
        airlineTable.initialize(Main.getDBPathName());
        original = airlineTable.getAirlines();
        mockedList = mock(AirlineTableSql.class);
        mockedList.add(null);
        mockedList.add(new Airline(""));
        mockedList.add(new Airline("WinnipegAirline"));
        mockedList.findAirline(null);
        mockedList.findAirline("");
        mockedList.findAirline("WinnipegAirline");
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
    public void testFindAirlineNull() {
        assertEquals("Test null fail", null, mockedList.findAirline(null));
    }

    @Test
    public void testFindAirlineEmpty() {
        assertEquals("Test empty fail", null, mockedList.findAirline(""));
    }

    @Test
    public void testFindAirlineSample() {
        assertEquals("Test sample fail", new Airline("AirCanada"), mockedList.findAirline("AirCanada"));
        assertEquals("Test sample fail", null, mockedList.findAirline("WinnipegAirline"));

    }

    @Test
    public void testAddNull() {
        assertFalse("Test null fail", mockedList.add(null));
    }

    @Test
    public void testAddEmpty() {
        assertTrue("Test empty fail", mockedList.add(new Airline("")));
        //airlineTable.remove(new Airline(""));
    }

    @Test
    public void testAddSample() {
        assertTrue("Test sample fail", mockedList.add(new Airline("WinnipegAirline")));
        //airlineTable.remove(new Airline("VancouverAirline"));
    }

    @Test
    public void testExistence() {
        assertNotNull(airlineTable);
    }

    @Test
    public void testInitialize() {
        assertEquals("Initialize is not work", original, airlineTable.getAirlines());
    }

}
