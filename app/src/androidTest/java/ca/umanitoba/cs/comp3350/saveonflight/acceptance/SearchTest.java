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
        Assert.assertTrue(solo.searchText("2017-11-11"));
        Assert.assertTrue(solo.searchText("2017-12-11"));
        Assert.assertTrue(solo.searchText("Winnipeg YWG"));
        Assert.assertTrue(solo.searchText("Toronto YYZ"));
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
        Assert.assertTrue(solo.searchText("2017-11-11"));
        Assert.assertTrue(solo.searchText("2017-12-11"));
        Assert.assertTrue(solo.searchText("Winnipeg YWG"));
        Assert.assertTrue(solo.searchText("Toronto YYZ"));
        solo.clickOnButton("Search");
        solo.clickOnText("AC 206");

    }
}
