package ca.umanitoba.cs.comp3350.saveonflight.acceptance;

/**
 * Created by Shenyun Wang on 2017-07-08.
 */

import junit.framework.Assert;
import android.test.ActivityInstrumentationTestCase2;
import com.robotium.solo.Solo;

import ca.umanitoba.cs.comp3350.saveonflight.R;
import ca.umanitoba.cs.comp3350.saveonflight.presentation.MainActivity;

public class ViewTripSummaryTest extends ActivityInstrumentationTestCase2<MainActivity>{
    private Solo solo;

    public ViewTripSummaryTest(){
        super(MainActivity.class);
    }

    public void setUp() throws Exception{
        solo=new Solo(getInstrumentation(), getActivity());
    }

    @Override
    public void tearDown() throws Exception{
        solo.finishOpenedActivities();
    }

    public void testTripSummary(){
        solo.waitForActivity("MainActivity");
        solo.clickOnButton("SEARCH FOR FLIGHTS");
        solo.enterText(0, "Winnipeg YWG");
        solo.enterText(1, "Toronto YYZ");
        solo.clickOnText("Departure Date");
        solo.setDatePicker(0, 2017, 10, 11); // Passing a month of 10 maps to November (11th month)?
        solo.clickOnText("OK");
        solo.clickOnText("Return Date");
        solo.setDatePicker(0, 2017, 11, 11); // And passing a month of 11 maps to December???
        solo.clickOnText("OK");



        solo.clickOnButton("Search");
        //check departure flight to be chosen
        Assert.assertTrue(solo.searchText("AC 256"));
        Assert.assertTrue(solo.searchText("350.52"));
        solo.clickOnText("AC 256");
        //check return flight to be chosen
        Assert.assertTrue(solo.searchText("AC 100"));
        Assert.assertTrue(solo.searchText("420.12"));
        solo.clickOnText("AC 100");
        //check chosen flights
        Assert.assertTrue(solo.searchText("AC 256"));
        Assert.assertTrue(solo.searchText("AC 100"));
        Assert.assertTrue(solo.searchText("Winnipeg YWG"));
        Assert.assertTrue(solo.searchText("Toronto YYZ"));
        Assert.assertTrue(solo.searchText("2017-11-11"));
        Assert.assertTrue(solo.searchText("2017-12-11"));
        Assert.assertTrue(solo.searchText(Double.toString(420.12+350.52)));

    }
    public void testOneWayTrips(){
        solo.waitForActivity("MainActivity");
        solo.clickOnButton("SEARCH FOR FLIGHTS");
        //choose one way search
        solo.clickOnText("Return");
        solo.clickOnText("One Way");
        solo.enterText(0, "Winnipeg YWG");
        solo.enterText(1, "Toronto YYZ");
        solo.clickOnText("Departure Date");
        solo.setDatePicker(0, 2017, 10, 11); // Passing a month of 10 maps to November (11th month)?
        solo.clickOnText("OK");
        solo.clickOnButton("Search");
        //check flight to be chosen
        Assert.assertTrue(solo.searchText("AC 260"));
        Assert.assertTrue(solo.searchText("325.82"));
        Assert.assertTrue(solo.searchText("3h 22m"));
        solo.clickOnText("AC 260");

        //check chosen flight
        Assert.assertTrue(solo.searchText("AC 260"));
        Assert.assertTrue(solo.searchText("325.82"));
        Assert.assertTrue(solo.searchText("3h 22m"));
        Assert.assertTrue(solo.searchText("2017-11-11"));

        //make sure no return flight was chosen
        Assert.assertFalse(solo.searchText("AC 100"));
        Assert.assertFalse(solo.searchText("AC 101"));
        Assert.assertFalse(solo.searchText("WJ 491"));
        Assert.assertFalse(solo.searchText("2017-12-11"));
    }


}
