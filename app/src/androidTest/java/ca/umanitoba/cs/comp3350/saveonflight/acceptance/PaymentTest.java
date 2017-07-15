package ca.umanitoba.cs.comp3350.saveonflight.acceptance;

import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;
import com.stripe.android.view.CardNumberEditText;
import com.stripe.android.view.ExpiryDateEditText;
import com.stripe.android.view.StripeEditText;

import ca.umanitoba.cs.comp3350.saveonflight.R;
import ca.umanitoba.cs.comp3350.saveonflight.presentation.FragmentNavigation;
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
        // Open home screen
        solo.waitForActivity("MainActivity");
        navigateToPaymentScreen();

        // Enter valid payment info
        StripeEditText[] editTexts = getCardInputFields();
        try {
            setCardInputField(editTexts[0], "4242424242424242");
            setCardInputField(editTexts[1], "08/18");
            setCardInputField(editTexts[2], "850");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        solo.enterText(3, "Bob Marley");
        solo.enterText(4, "474 Main Street");
        solo.enterText(5, "Winnipeg");
        solo.pressSpinnerItem(0, 2);
        solo.enterText(6, "R3P 7C8");

        solo.clickOnButton(0);

        assertTrue(solo.searchText("Payment Successful"));
        assertTrue(solo.searchText("View Booked Flights"));
        assertTrue(solo.searchText("Return to Homepage"));

        solo.clickOnText("Return to Homepage");
        solo.assertCurrentActivity("Expected Activity MainActivity", "MainActivity");
    }

    /**
     * Payment info entered is invalid so payment should not succeed.
     */
    public void testInvalidPayment() {
        solo.waitForActivity("MainActivity");
        navigateToPaymentScreen();

        // Enter invalid credit card number
        StripeEditText[] editTexts = getCardInputFields();
        try {
            setCardInputField(editTexts[0], "5415244644554449");
            setCardInputField(editTexts[1], "08/18");
            setCardInputField(editTexts[2], "850");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        solo.enterText(3, "Bob Marley");
        solo.enterText(4, "474 Main Street");
        solo.enterText(5, "Winnipeg");
        solo.pressSpinnerItem(0, 2);
        solo.enterText(6, "R3P 7C8");

        solo.clickOnButton(0);
        assertTrue(solo.waitForText("Invalid card", 1, 3000));

        // Enter invalid expiry date (in the past)
        try {
            setCardInputField(editTexts[0], "4242424242424242");
            setCardInputField(editTexts[1], "08/11");
            setCardInputField(editTexts[2], "850");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        solo.clickOnButton(0);
        assertTrue(solo.waitForText("Invalid card data", 1, 3000));

        // Enter invalid expiry date (not right format)
        try {
            setCardInputField(editTexts[0], "4242424242424242");
            setCardInputField(editTexts[1], "15/74");
            setCardInputField(editTexts[2], "850");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        solo.clickOnButton(0);
        assertTrue(solo.waitForText("Invalid card data", 1, 3000));

        solo.goBack();
        solo.goBack();
    }

    /**
     * Personal info entered in this test is invalid.
     */
    public void testMissingFields() {
        final String FIELD_REQUIRED_MSG = "This field is required";
        solo.waitForActivity("MainActivity");
        navigateToPaymentScreen();

        solo.clickOnButton(0);
        assertTrue(solo.searchText("Invalid card"));
        assertTrue(solo.searchText(FIELD_REQUIRED_MSG));

        StripeEditText[] editTexts = getCardInputFields();
        try {
            setCardInputField(editTexts[0], "4242424242424242");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        solo.clickOnButton(0);
        assertTrue(solo.searchText(FIELD_REQUIRED_MSG));

        try {
            setCardInputField(editTexts[1], "08/18");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        solo.clickOnButton(0);
        assertTrue(solo.searchText(FIELD_REQUIRED_MSG));

        try {
            setCardInputField(editTexts[2], "850");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        solo.clickOnButton(0);
        assertTrue(solo.searchText(FIELD_REQUIRED_MSG));

        solo.clickOnText("FULL NAME");
        assertTrue(solo.searchText(FIELD_REQUIRED_MSG));
        solo.enterText(3, "First Last");

        solo.clickOnText("ADDRESS LINE");
        assertTrue(solo.searchText(FIELD_REQUIRED_MSG));
        solo.enterText(4, "474 Mandalay Road");

        solo.clickOnText("CITY");
        assertTrue(solo.searchText(FIELD_REQUIRED_MSG));
        solo.enterText(5, "Winnipeg");

        solo.clickOnText("POSTAL CODE");
        assertTrue(solo.searchText(FIELD_REQUIRED_MSG));
        solo.enterText(6, "R4T 8D3");

        solo.clickOnButton(0);
        solo.clickOnText("Return to Homepage");

        solo.goBack();
    }

    private void navigateToPaymentScreen() {
        // Go to the search screen
        solo.clickOnButton("SEARCH FOR FLIGHTS");

        // Enter return trip info and proceed to the view flights screen
        solo.enterText(0, "Winnipeg YWG");
        solo.enterText(1, "Toronto YYZ");
        solo.clickOnText("Departure Date");
        solo.setDatePicker(0, 2017, 10, 11); // Passing a month of 10 maps to November (11th month)?
        solo.clickOnText("OK");
        solo.clickOnText("Return Date");
        solo.setDatePicker(0, 2017, 11, 11); // And passing a month of 11 maps to December???
        solo.clickOnText("OK");
        solo.clickOnButton(1);

        // Pick two flights to go to the trip summary screen
        solo.clickOnButton(4);
        solo.clickOnButton(4);

        // Proceed to the payment screen
        solo.clickOnButton(1);
    }

    /**
     * @return an array of the three StripeEditTexts that are part of the CardInputWidget
     */
    private StripeEditText[] getCardInputFields() {
        StripeEditText[] editTexts = new StripeEditText[3];
        editTexts[0] = (CardNumberEditText) solo.getView(R.id.et_card_number);
        editTexts[1] = (ExpiryDateEditText) solo.getView(R.id.et_expiry_date);
        editTexts[2] = (StripeEditText) solo.getView(R.id.et_cvc_number);
        return editTexts;
    }

    /**
     * Using solo.enterText() causes a runtime error when setting the text for EditTexts in
     * Stripe's CardInputWidget for some reason. Still not sure what the reason is... but setting
     * them with this method works.
     *
     * @param et    the StripeEditText to set
     * @param value what we want to set the StripeEditText's text to
     */
    private void setCardInputField(final StripeEditText et, final String value) throws Throwable {
        runTestOnUiThread(new Runnable() {
            @Override
            public void run() {
                et.setText(value);
            }
        });
    }

}
