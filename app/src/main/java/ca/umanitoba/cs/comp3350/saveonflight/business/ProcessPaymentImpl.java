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
    private ProcessPaymentListener listener;

    public ProcessPaymentImpl(ProcessPaymentListener listener) {
        this.listener = listener;
    }

    @Override
    public void process(Card card, final Context context) {
        Stripe stripe = new Stripe(context, "pk_test_kweQjhDjrpBrs8KcOho48gxE");
        stripe.createToken(
                card,
                new TokenCallback() {
                    public void onSuccess(Token token) {
                        // Would send token to server if we had one... but we don't
                        listener.paymentSuccess();
                    }

                    public void onError(Exception error) {
                        Toast.makeText(context,
                                error.getLocalizedMessage(),
                                Toast.LENGTH_LONG
                        ).show();
                        listener.paymentFailure();
                    }
                }
        );
    }
}
