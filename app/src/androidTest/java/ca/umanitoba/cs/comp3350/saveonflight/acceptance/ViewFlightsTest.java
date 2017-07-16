package ca.umanitoba.cs.comp3350.saveonflight.acceptance;

import android.graphics.Point;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.ListView;

import com.robotium.solo.Solo;

import java.util.List;

import ca.umanitoba.cs.comp3350.saveonflight.presentation.MainActivity;

public class ViewFlightsTest extends ActivityInstrumentationTestCase2<MainActivity> {
    private Solo solo;

    public ViewFlightsTest() {
        super(MainActivity.class);
    }

    public void setUp() throws Exception {
        solo = new Solo(getInstrumentation(), getActivity());
    }

    @Override
    public void tearDown() throws Exception {
       solo.finishOpenedActivities();
    }

    public void testReturnTrip() {
        solo.waitForActivity("MainActivity");
        solo.clickOnButton(0);

        solo.enterText(0, "Winnipeg YWG");
        solo.enterText(1, "Toronto YYZ");
        solo.clickOnText("Departure");
        solo.setDatePicker(0, 2017, 10, 11);
        solo.clickOnText("OK");
        solo.clickOnText("Return Date");
        solo.setDatePicker(0, 2017, 11, 11);
        solo.clickOnText("OK");
        solo.clickOnButton("Search");

        // Now picking departing flight
        assertTrue(solo.searchText("Winnipeg YWG - Toronto YYZ"));
        solo.clickOnButton("Price");
        assertTrue(solo.searchText("WJ 546", true));
        solo.clickOnButton("Time");
        assertTrue(solo.searchText("WJ 520", true));
        solo.clickOnButton("Duration");
        assertTrue(solo.searchText("WJ 520", true));

        // Choose a departing flight
        solo.clickOnText("WJ 520");

        // Now picking return flight
        assertTrue(solo.searchText("Toronto YYZ - Winnipeg YWG"));
        solo.clickOnButton("Price");
        solo.clickOnButton("Time");
        solo.clickOnButton("Duration");
        assertTrue(solo.searchText("WJ 491"));
        assertTrue(solo.searchText("AC 100"));
        assertTrue(solo.searchText("AC 101"));

        // Choose the return flight, which should bring us to the trip summary screen
        solo.clickOnText("WJ 491");
        assertTrue(solo.searchText("Trip Summary"));

        solo.goBack();
        solo.goBack();
    }

    public void testOneWayTrip() {
        solo.waitForActivity("MainActivity");
        solo.clickOnButton(0);

        solo.pressSpinnerItem(0, 1);
        solo.enterText(0, "Winnipeg YWG");
        solo.enterText(1, "Calgary YYC");
        solo.clickOnText("Departure Date");
        solo.setDatePicker(0, 2017, 7, 11);
        solo.clickOnText("OK");

        solo.clickOnButton("Search");

        solo.clickOnButton("Price");
        solo.clickOnButton("Time");
        solo.clickOnButton("Duration");

        assertTrue(solo.searchText("Winnipeg YWG - Calgary YYC"));
        assertTrue(solo.searchText("AC 054"));
        assertTrue(solo.searchText("AC 061"));
        assertTrue(solo.searchText("AC 071"));
        assertTrue(solo.searchText("AC 081"));

        // Choosing the flight should bring us to the trip summary screen
        solo.clickOnText("AC 054");
        assertTrue(solo.searchText("Trip Summary"));

        solo.goBack();
        solo.goBack();
    }

    public void testNoFlightSelected() {
        solo.waitForActivity("MainActivity");
        solo.clickOnButton(0);

        solo.pressSpinnerItem(0, 1);
        solo.enterText(0, "Winnipeg YWG");
        solo.enterText(1, "Ottawa YOW");
        solo.clickOnText("Departure Date");
        solo.setDatePicker(0, 2017, 7, 11);
        solo.clickOnText("OK");

        solo.clickOnButton("Search");

        solo.clickOnButton("Price");
        solo.clickOnButton("Time");
        solo.clickOnButton("Duration");

        assertTrue(solo.searchText("Winnipeg YWG - Ottawa YOW"));
        assertTrue(solo.searchText("WJ 044"));
        assertTrue(solo.searchText("WJ 064"));

        Point size = new Point();
        solo.getCurrentActivity().getWindow().getWindowManager().getDefaultDisplay().getSize(size);
        solo.clickOnScreen(size.x, size.y);

        assertTrue(solo.searchText("Winnipeg YWG - Ottawa YOW")); // Should still be on the view flights screen

        solo.goBack();
        solo.goBack();
    }
}
