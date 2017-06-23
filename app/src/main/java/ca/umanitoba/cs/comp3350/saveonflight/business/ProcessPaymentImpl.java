package ca.umanitoba.cs.comp3350.saveonflight.business;

import android.content.Context;
import android.widget.Toast;

import com.stripe.android.Stripe;
import com.stripe.android.TokenCallback;
import com.stripe.android.model.Card;
import com.stripe.android.model.Token;

/**
 * ProcessPayment.java
 * <p>
 * Implementation for the business logic of processing a payment.
 *
 * @author Kenny Zhang
 */

public class ProcessPaymentImpl implements ProcessPayment {
    @Override
    public boolean process(Card card, final Context context) {
        /*
        Stripe stripe = new Stripe(context, "pk_test_kweQjhDjrpBrs8KcOho48gxE");
        stripe.createToken(
                card,
                new TokenCallback() {
                    public void onSuccess(Token token) {
                        // Would send token to server if we had one
                        Toast.makeText(context,
                                "Token created successfully",
                                Toast.LENGTH_LONG
                        ).show();
                    }

                    public void onError(Exception error) {
                        Toast.makeText(context,
                                error.getLocalizedMessage(),
                                Toast.LENGTH_LONG
                        ).show();
                    }
                }
        );*/

        // Just pretend that the payment processing was successful...
        return true;
    }
}
