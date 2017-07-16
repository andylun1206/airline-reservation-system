package ca.umanitoba.cs.comp3350.saveonflight.acceptance;

import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;

import ca.umanitoba.cs.comp3350.saveonflight.presentation.MainActivity;

public class ViewBookedFlightsTest extends ActivityInstrumentationTestCase2<MainActivity> {
    private Solo solo;

    public ViewBookedFlightsTest() {
        super(MainActivity.class);
    }

    public void setUp() throws Exception {
        solo = new Solo(getInstrumentation(), getActivity());
    }

    @Override
    public void tearDown() throws Exception {
        solo.finishOpenedActivities();
    }

    /**
     * Passenger id's in this test all have booked flights associated with them. So, they should all
     * cause the screen to update with a list of flight(s).
     */
    public void testValidPassengerIds() {
        solo.waitForActivity("MainActivity");  // Open home page
        solo.clickOnButton("BOOKED FLIGHTS");  // Go to the view booked flights screen

        solo.enterText(0, "0");                // Enter a passenger id of 0
        solo.clickOnButton(0);                 // Click the 'View Flights' button
        assertTrue(solo.searchText("AC 256")); // Check that the right flight is shown
        assertTrue(solo.searchText("05:30 - 08:51"));
        assertTrue(solo.searchText("3h 21m"));
        assertTrue(solo.searchText("350.52"));
        assertTrue(solo.searchText("AC 260")); // This passenger has two flights; check that the second is shown as well
        assertTrue(solo.searchText("07:30 - 10:52"));
        assertTrue(solo.searchText("3h 22m"));
        assertTrue(solo.searchText("325.82"));

        solo.clearEditText(0);                 // Enter a passenger id of 1
        solo.enterText(0, "1");
        solo.clickOnButton(0);
        assertTrue(solo.searchText("AC 264"));
        assertTrue(solo.searchText("10:50 - 14:10"));
        assertTrue(solo.searchText("3h 20m"));
        assertTrue(solo.searchText("403.20"));

        solo.clearEditText(0);                 // Enter a passenger id of 2
        solo.enterText(0, "2");
        solo.clickOnButton(0);
        assertTrue(solo.searchText("AC 266"));
        assertTrue(solo.searchText("12:20 - 15:41"));
        assertTrue(solo.searchText("3h 21m"));
        assertTrue(solo.searchText("467.29"));

        // Clean up
        solo.clearEditText(0);
        solo.goBack();
    }

    /**
     * Passenger id's in this test do not have any booked flights associated with them. So, they
     * should not cause the screen to show any flights.
     */
    public void testInvalidPassengerIds() {
        solo.waitForActivity("MainActivity");  // Open home page
        solo.clickOnButton("BOOKED FLIGHTS");  // Go to the view booked flights screen

        // Passenger id 987654 should not have any flights associated with it
        solo.enterText(0, "987654");
        solo.clickOnButton(0);
        assertTrue(solo.waitForText("No booked flights were found for this passenger"));

        // Passenger id 456789 should also not have any flights associated with it
        solo.clearEditText(0);
        solo.enterText(0, "456789");
        solo.clickOnButton(0);
        assertTrue(solo.waitForText("No booked flights were found for this passenger"));

        // Clean up
        solo.clearEditText(0);
        solo.goBack();
    }

    /**
     * No passenger id is entered in this test (the EditText is left blank).
     */
    public void testNoPassengerId() {
        // Open home page
        solo.waitForActivity("MainActivity");

        // Open the view booked flights page
        solo.clickOnButton("BOOKED FLIGHTS");

        // Click the 'View Flights' button without inputting a passenger id
        solo.clickOnButton(0);
        assertTrue(solo.waitForText("Please enter a passenger ID"));

        solo.goBack();
    }
}
