package ca.umanitoba.cs.comp3350.saveonflight.business;

import ca.umanitoba.cs.comp3350.saveonflight.objects.PaymentInfo;

/**
 * ValidatePayments.java
 * <p>
 * Interface for the business logic of validating payments.
 *
 * @author Kenny Zhang
 */

public interface ValidatePayments {
    boolean validate(PaymentInfo paymentInfo);
}
