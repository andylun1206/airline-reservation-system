package ca.umanitoba.cs.comp3350.saveonflight.business;


import android.content.Context;

import com.stripe.android.model.Card;

/**
 * ProcessPayment.java
 * <p>
 * Interface for the business logic of processing a payment.
 *
 * @author Kenny Zhang
 */

public interface ProcessPayment {
    boolean process(Card card, Context context);
}
