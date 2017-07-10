package ca.umanitoba.cs.comp3350.saveonflight.acceptance;

import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;

import junit.framework.Assert;

import ca.umanitoba.cs.comp3350.saveonflight.presentation.MainActivity;

/**
 * Created by Shenyun Wang on 2017-07-08.
 */

public class SearchTest extends ActivityInstrumentationTestCase2<MainActivity> {

    private Solo solo;

    public SearchTest(){
        super(MainActivity.class);
    }

    public void setUp() throws Exception{
        solo = new Solo(getInstrumentation(), getActivity());
    }

    @Override
    public void tearDown() throws Exception{
        solo.finishOpenedActivities();
    }

    public void testRoundTrip(){
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
        solo.clickOnText("AC 266");
        solo.clickOnText("AC 101");

        Assert.assertTrue(solo.searchText("2017-11-11"));
        Assert.assertTrue(solo.searchText("2017-12-11"));
        Assert.assertTrue(solo.searchText("Winnipeg YWG"));
        Assert.assertTrue(solo.searchText("Toronto YYZ"));
    }

    public void testOneWay(){
        solo.waitForActivity("MainActivity");
        solo.clickOnButton("SEARCH FOR FLIGHTS");
        solo.clickOnText("Return");
        solo.clickOnText("One Way");
        solo.enterText(0, "Winnipeg YWG");
        solo.enterText(1, "Toronto YYZ");
        solo.clickOnText("Departure Date");
        solo.setDatePicker(0, 2017, 10, 11);
        solo.clickOnText("OK");
        solo.clickOnButton("Search");
        solo.clickOnText("AC 260");

        Assert.assertTrue(solo.searchText("2017-11-11"));
        Assert.assertTrue(solo.searchText("2017-12-11"));
        Assert.assertTrue(solo.searchText("Winnipeg YWG"));
        Assert.assertTrue(solo.searchText("Toronto YYZ"));
        Assert.assertTrue(solo.searchText("AC 260"));

    }

//    public void testAdvancedSearch(){
//        solo.waitForActivity("MainActivity");
//        solo.clickOnButton("SEARCH FOR FLIGHTS");
//        solo.clickOnText("ADVANCED OPTIONS");
//        solo.enterText(0, "Winnipeg YWG");
//        solo.enterText(1, "Toronto YYZ");
//        solo.clickOnText("Departure Date");
//        solo.setDatePicker(0, 2017, 10, 11);
//        solo.clickOnText("OK");
//        solo.clickOnText("Return Date");
//        solo.setDatePicker(1,2017,11,11);
//        solo.clickOnText("OK");
//        solo.clickOnText("Maximum Price");
//        solo.enterText(2,"300");
//        solo.clickOnText("Preferred Airlines");
//        solo.enterText(3,"West Jet");
//        solo.clickOnText("Preferred Class");
//        solo.enterText(4,"Economy Class");
//        solo.clickOnButton("SEARCH");
//
//    }
}
