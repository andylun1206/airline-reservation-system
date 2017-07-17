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

    public SearchTest() {
        super(MainActivity.class);
    }

    public void setUp() throws Exception {
        solo = new Solo(getInstrumentation(), getActivity());
    }

    @Override
    public void tearDown() throws Exception {
        solo.finishOpenedActivities();
    }

    public void testRoundTrip() {
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
        Assert.assertTrue(solo.searchText("AC 266"));
        solo.clickOnText("AC 266");
        Assert.assertTrue(solo.searchText("AC 101"));
        solo.clickOnText("AC 101");

        Assert.assertTrue(solo.searchText("2017-11-11"));
        Assert.assertTrue(solo.searchText("2017-12-11"));
        Assert.assertTrue(solo.searchText("Winnipeg YWG"));
        Assert.assertTrue(solo.searchText("Toronto YYZ"));
    }

    public void testOneWay() {
        solo.waitForActivity("MainActivity");
        solo.clickOnButton("SEARCH FOR FLIGHTS");
        solo.clickOnText("Return");
        solo.clickOnText("One Way");
        Assert.assertFalse(solo.searchText("Return Date"));
        solo.enterText(0, "Winnipeg YWG");
        solo.enterText(1, "Toronto YYZ");
        solo.clickOnText("Departure Date");
        solo.setDatePicker(0, 2017, 10, 11);
        solo.clickOnText("OK");
        solo.clickOnButton("Search");

        Assert.assertTrue(solo.searchText("AC 260"));
        solo.clickOnText("AC 260");

        Assert.assertTrue(solo.searchText("2017-11-11"));
        Assert.assertFalse(solo.searchText("2017-12-11"));
        Assert.assertTrue(solo.searchText("Winnipeg YWG"));
        Assert.assertTrue(solo.searchText("Toronto YYZ"));
        Assert.assertTrue(solo.searchText("AC 260"));
    }

    public void testNoResults() {
        solo.waitForActivity("MainActivity");
        solo.clickOnButton("SEARCH FOR FLIGHTS");

        solo.clickOnButton(1);
        assertTrue(solo.waitForText("Origin is invalid!"));

        solo.enterText(0, "Winnipeg YWG");
        solo.clickOnButton(1);
        assertTrue(solo.waitForText("Destination is invalid!"));

        solo.enterText(1, "Toooooronto YYZ");
        solo.clickOnButton(1);
        assertTrue(solo.waitForText("Destination is invalid!"));

        solo.clearEditText(1);
        solo.enterText(1, "Toronto YYZ");
        solo.clickOnButton(1);
        assertTrue(solo.waitForText("Departure Date is invalid!"));

        solo.clickOnText("Departure Date");
        solo.setDatePicker(0, 2017, 10, 11);
        solo.clickOnText("OK");
        solo.clickOnButton(1);
        assertTrue(solo.waitForText("Return Date is invalid!"));

        solo.clickOnText("Return Date");
        solo.setDatePicker(0, 2017, 11, 12);
        solo.clickOnText("OK");
        solo.clickOnButton(1);
        assertTrue(solo.waitForText("No flights were found with these criterias!"));
    }

    public void testAdvancedRoundTrip(){
        //west jet
        solo.waitForActivity("MainActivity");
        solo.clickOnButton("SEARCH FOR FLIGHTS");
        solo.clickOnText("Advanced Options");
        solo.enterText(0, "Winnipeg YWG");
        solo.enterText(1, "Toronto YYZ");
        solo.clickOnText("Departure Date");
        solo.setDatePicker(0, 2017, 10, 11);
        solo.clickOnText("OK");
        solo.clickOnText("Return Date");
        solo.setDatePicker(0, 2017, 11, 11);
        solo.clickOnText("OK");
        solo.enterText(4,"400.00");
        solo.enterText(5,"WestJet");
        solo.clickOnButton("Search");
        Assert.assertTrue(solo.searchText("WJ 258"));
        Assert.assertFalse(solo.searchText("AC"));
        solo.clickOnText("WJ 258");
        Assert.assertTrue(solo.searchText("WJ 491"));
        Assert.assertFalse(solo.searchText("AC"));
        solo.clickOnText("WJ 491");
        Assert.assertTrue(solo.searchText("Return Flight"));
        Assert.assertFalse(solo.searchText("AC"));

        //air canada
        solo.clickOnButton("Modify Search");
        solo.clickOnText("Advanced Options");
        solo.enterText(0, "Winnipeg YWG");
        solo.enterText(1, "Toronto YYZ");
        solo.clickOnText("Departure Date");
        solo.setDatePicker(0, 2017, 10, 11);
        solo.clickOnText("OK");
        solo.clickOnText("Return Date");
        solo.setDatePicker(0, 2017, 11, 11);
        solo.clickOnText("OK");
        solo.enterText(4,"500");
        solo.enterText(5,"Air Canada");
        solo.clickOnButton("Search");

        Assert.assertFalse(solo.searchText("WJ"));
        Assert.assertTrue(solo.searchText("AC 260"));
        solo.clickOnText("AC 260");
        Assert.assertFalse(solo.searchText("WJ"));
        Assert.assertTrue(solo.searchText("AC 100"));
        solo.clickOnText("AC 100");
        Assert.assertTrue(solo.searchText("Return Flight"));
        Assert.assertFalse(solo.searchText("WJ"));
    }

    public void testAdvancedOneway(){
        solo.waitForActivity("MainActivity");
        solo.clickOnButton("SEARCH FOR FLIGHTS");
        solo.clickOnText("Return");
        solo.clickOnText("One Way");
        solo.clickOnText("Advanced Options");
        Assert.assertFalse(solo.searchText("Return Date"));
        solo.enterText(0, "Winnipeg YWG");
        solo.enterText(1, "Toronto YYZ");
        solo.clickOnText("Departure Date");
        solo.setDatePicker(0, 2017, 10, 11);
        solo.clickOnText("OK");
        solo.enterText(3,"400");
        solo.enterText(4,"Air Canada");
        solo.clickOnButton("Search");
        Assert.assertTrue(solo.searchText("AC 268"));
        Assert.assertFalse(solo.searchText("WJ"));
        solo.clickOnText("AC 268");
        Assert.assertFalse(solo.searchText("WJ"));
        //westjet advanced search
        solo.clickOnButton("Modify Search");
        solo.clickOnText("Return");
        solo.clickOnText("One Way");
        solo.clickOnText("Advanced Options");
        solo.enterText(0, "Winnipeg YWG");
        solo.enterText(1, "Toronto YYZ");
        solo.clickOnText("Departure Date");
        solo.setDatePicker(0, 2017, 10, 11);
        solo.clickOnText("OK");
        solo.enterText(3,"200");
        solo.enterText(4,"WestJet");
        solo.clickOnButton("Search");
        Assert.assertFalse(solo.searchText("AC"));
        Assert.assertTrue(solo.searchText("WJ 546"));
        solo.clickOnText("WJ 546");
        Assert.assertFalse(solo.searchText("AC"));
    }
}
