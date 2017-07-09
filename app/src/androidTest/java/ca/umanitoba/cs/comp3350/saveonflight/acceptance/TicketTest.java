package ca.umanitoba.cs.comp3350.saveonflight.acceptance;

import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;

import ca.umanitoba.cs.comp3350.saveonflight.presentation.MainActivity;

public class TicketTest extends ActivityInstrumentationTestCase2<MainActivity> {
    private Solo solo;

    public TicketTest() {
        super(MainActivity.class);
    }

    public void setUp() throws Exception {
        solo = new Solo(getInstrumentation(), getActivity());
    }

    @Override
    public void tearDown() throws Exception {
        solo.finishOpenedActivities();
    }

    public void testTicketScreen() {
        solo.waitForActivity("MainActivity");
        solo.clickOnButton("BOOKED FLIGHTS");

        // Get flights that the passenger with id 0 has booked
        solo.enterText(0, "0");
        solo.clickOnButton(0);

        // Go to ticket screen for flight AC 256
        solo.clickOnText("AC 256");

        assertTrue(solo.searchText("Jack"));         // Passenger name displayed should be "Jack"
        assertTrue(solo.searchText("AC 256"));       // Flight code should be "AC 256
        assertTrue(solo.searchText("Winnipeg YWG")); // From Winnipeg
        assertTrue(solo.searchText("Toronto YYZ"));  // To Toronto
        assertTrue(solo.searchText("Nov 11 05:30")); // Departs at 5:30 am
        assertTrue(solo.searchText("Nov 11 08:51")); // Arrives at 8:51 am
        assertTrue(solo.searchText("3h 21m"));       // Flight duration is 3 hours and 21 minutes
        assertTrue(solo.searchText("Nov 11 04:45")); // Boarding time is 4:45 am
        assertTrue(solo.searchText("1A"));           // Seat 1A

        solo.goBack();

        // Go to ticket screen for flight AC 260
        solo.enterText(0, "0");
        solo.clickOnButton(0);
        solo.clickOnText("AC 260");

        assertTrue(solo.searchText("Jack"));
        assertTrue(solo.searchText("AC 260"));
        assertTrue(solo.searchText("Winnipeg YWG"));
        assertTrue(solo.searchText("Toronto YYZ"));
        assertTrue(solo.searchText("Nov 11 07:30"));
        assertTrue(solo.searchText("Nov 11 10:52"));
        assertTrue(solo.searchText("3h 22m"));
        assertTrue(solo.searchText("Nov 11 06:45"));
        assertTrue(solo.searchText("1A"));

        solo.goBack();

        // Get flights for passenger with id 1
        solo.enterText(0, "1");
        solo.clickOnButton(0);

        // Go to ticket screen for flight AC 264
        solo.clickOnText("AC 264");

        assertTrue(solo.searchText("Vicky"));
        assertTrue(solo.searchText("AC 264"));
        assertTrue(solo.searchText("Winnipeg YWG"));
        assertTrue(solo.searchText("Toronto YYZ"));
        assertTrue(solo.searchText("Nov 11 10:50"));
        assertTrue(solo.searchText("Nov 11 14:10"));
        assertTrue(solo.searchText("3h 20m"));
        assertTrue(solo.searchText("Nov 11 10:05"));
        assertTrue(solo.searchText("1A"));

        solo.goBack();
        solo.goBack();
    }
}
