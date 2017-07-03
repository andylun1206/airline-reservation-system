package ca.umanitoba.cs.comp3350.saveonflight.integration;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.umanitoba.cs.comp3350.saveonflight.application.Main;
import ca.umanitoba.cs.comp3350.saveonflight.business.AccessTravellers;
import ca.umanitoba.cs.comp3350.saveonflight.business.AccessTravellersImpl;

public class TravellerBusinessPersistenceSeamTest {
    private static AccessTravellers access;

    @BeforeClass
    public static void setUp() {
        Main.startUp(Main.DatabaseType.HSQL);
        access = new AccessTravellersImpl(Main.getTravellerAccess());
    }

    @AfterClass
    public static void tearDown() {
        Main.shutDown();
        access = null;
    }

    @Test
    public void testAddAndRemove() {

    }
}
