package ca.umanitoba.cs.comp3350.saveonflight.acceptance;

import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.robotium.solo.Solo;
import com.stripe.android.view.CardInputWidget;
import com.stripe.android.view.CardNumberEditText;
import com.stripe.android.view.ExpiryDateEditText;
import com.stripe.android.view.StripeEditText;

import ca.umanitoba.cs.comp3350.saveonflight.R;
import ca.umanitoba.cs.comp3350.saveonflight.presentation.MainActivity;
import ca.umanitoba.cs.comp3350.saveonflight.presentation.payment.PaymentFragment;

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
        CardNumberEditText etCardNum = (CardNumberEditText) solo.getView(R.id.et_card_number);
        ExpiryDateEditText etExp = (ExpiryDateEditText) solo.getView(R.id.et_expiry_date);
        StripeEditText etCvc = (StripeEditText) solo.getView(R.id.et_cvc_number);
        try {
            setCardInputField(etCardNum, "4242424242424242");
            setCardInputField(etExp, "08/18");
            setCardInputField(etCvc, "850");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        solo.enterText(3, "Bob Marley");
        solo.enterText(4, "474 Main Street");
        solo.enterText(5, "Winnipeg");
        solo.pressSpinnerItem(0, 2);
        solo.enterText(6, "R3P 7C8");

        solo.clickOnButton(0);
        solo.clickOnText("Return to Homepage");
        solo.assertCurrentActivity("Expected Activity MainActivity", "MainActivity");
    }

    private void navigateToPaymentScreen() {
        // Go to the search screen
        solo.clickOnButton("Search for Flights");

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
     * Using solo.enterText() causes a NullPointerException when setting  the text for EditTexts in
     * Stripe's CardInputWidget for some reason. Still not sure what the reason is... but setting
     * them with this method works.
     *
     * @param et the StripeEditText to set
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
