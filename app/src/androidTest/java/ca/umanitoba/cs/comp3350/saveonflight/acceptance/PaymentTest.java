package ca.umanitoba.cs.comp3350.saveonflight.acceptance;

import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;

import ca.umanitoba.cs.comp3350.saveonflight.presentation.MainActivity;

public class PaymentTest extends ActivityInstrumentationTestCase2<MainActivity> {
    private Solo solo;

    public PaymentTest() {
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
     * Payment info entered here is valid so payment should succeed.
     */
    public void testValidPayment() {

    }
}
