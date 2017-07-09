package ca.umanitoba.cs.comp3350.saveonflight.persistence;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import ca.umanitoba.cs.comp3350.saveonflight.application.Main;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Airport;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.*;

/**
 * Created by longyu on 2017-06-29.
 */

public class AirportTableSqlTest {
    private static List<Airport> original;
    private static AirportTableSql airportTable;
    private static AirportTableSql mockedList;

    @BeforeClass
    public static void setUp() {
        Main.startUp(Main.DatabaseType.HSQL);
        airportTable = new AirportTableSql();
        airportTable.initialize(Main.getDBPathName());
        original = airportTable.getAirports();
        mockedList = mock(AirportTableSql.class);
        mockedList.findAirport("Vancouver YVR");
        mockedList.close();

        verify(mockedList).findAirport(null);
        verify(mockedList).findAirport("");
        verify(mockedList).findAirport("Vancouver YVR");
        verify(mockedList).findAirport("China cnm");
        verify(mockedList).close();

        when(mockedList.findAirport(null)).thenReturn(null);
        when(mockedList.findAirport("")).thenReturn(null);
        when(mockedList.findAirport("Vancouver YVR")).thenReturn(new Airport("Vancouver YVR"));
        when(mockedList.findAirport("China cnm")).thenReturn(null);
    }

    @Test
    public void testFindAirportNull() {
        assertEquals("Test null fail", null, mockedList.findAirport(null));
    }

    @Test
    public void testFindAirportEmpty() {
        assertEquals("Test empty fail", null, mockedList.findAirport(""));
    }

    @Test
    public void testFindAirportSample() {
        assertEquals("Test sample fail", new Airport("Vancouver YVR"), mockedList.findAirport("Vancouver YVR"));
        assertEquals("Test sample fail", null, mockedList.findAirport("China cnm"));
    }

    @Test
    public void testExistence() {
        assertNotNull("Initialize is not work", airportTable);
    }

}
