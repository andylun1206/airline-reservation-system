package ca.umanitoba.cs.comp3350.saveonflight.objects;

import android.os.Parcel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ca.umanitoba.cs.comp3350.saveonflight.R;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AirlineTest {
    private final String AC = "Air Canada";
    private final String WJ = "WestJet";
    private final Airline AIRLINE = new Airline(AC, R.mipmap.ic_aircanada);
    private Airline airline2;
    private String wrongObject;

    @Before
    public void setUp() {
        airline2 = new Airline(WJ, R.mipmap.ic_westjet);
        wrongObject = "Air Canada";
    }

    @After
    public void tearDown() {
        airline2 = null;
    }

    @Test
    public void testName() {
        assertEquals(AC, AIRLINE.getName());
        assertEquals(WJ, airline2.getName());
        airline2.setName(AC);
        assertEquals(AC, airline2.getName());
    }
    @Test
    public void testGetIcon(){
        assertEquals(airline2.getIcon(),R.mipmap.ic_westjet);
        assertFalse(AIRLINE.getIcon()==R.mipmap.ic_westjet);
    }
    @Test
    public void testEqualsMethod() {
        assertFalse(AIRLINE.equals(airline2));    //westjet != air canada
        airline2.setName(AC);
        assertTrue(AIRLINE.equals(airline2));     //air canada = air canada
        airline2.setName("");
        assertFalse(AIRLINE.equals(airline2));    //air canada !=
        assertFalse(AIRLINE.equals(wrongObject)); //air canada != "air canada"
        airline2 = null;
        assertFalse(AIRLINE.equals(airline2));    //air canada != null
    }

    @Test
    public void testCompareTo() {
        assertTrue(AIRLINE.compareTo(AIRLINE) == 0);
        assertTrue(AIRLINE.compareTo(airline2) < 0);
        assertTrue(airline2.compareTo(AIRLINE) > 0);
        assertTrue(AIRLINE.compareTo(wrongObject) == 0);
        assertTrue(AIRLINE.compareTo(null) == 0);
    }

    @Test
    public void testParcelable() {
        Parcel parcel = mock(Parcel.class);
        when(parcel.readString()).thenReturn(AIRLINE.getName());
        assertNotNull(parcel);
        AIRLINE.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        Airline parceledAirline = (Airline) Airline.CREATOR.createFromParcel(parcel);
        assertEquals(parceledAirline, AIRLINE);
        parcel.recycle();
    }
}
