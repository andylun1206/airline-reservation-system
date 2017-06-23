package ca.umanitoba.cs.comp3350.saveonflight.presentation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.stripe.android.model.Card;
import com.stripe.android.view.CardInputWidget;

import ca.umanitoba.cs.comp3350.saveonflight.R;
import ca.umanitoba.cs.comp3350.saveonflight.business.AccessBookedFlights;
import ca.umanitoba.cs.comp3350.saveonflight.business.AccessBookedFlightsImpl;
import ca.umanitoba.cs.comp3350.saveonflight.business.ProcessPayment;
import ca.umanitoba.cs.comp3350.saveonflight.business.ProcessPaymentImpl;
import ca.umanitoba.cs.comp3350.saveonflight.objects.PaymentInfo;

/**
 * Payment.java
 * <p>
 * Fragment for the payment screen of the application.
 *
 * @author Kenny Zhang
 */


public class PaymentFragment extends Fragment implements View.OnClickListener {
    // Payment info
    //private EditText etCardNum;
    //private EditText etExpMonth;
    //private EditText etExpYear;
    private CardInputWidget mCardInputWidget;
    private EditText etName;
    //private EditText etCountryCode;
    //private EditText etAreaCode;
    //private EditText etPhoneNum;

    // Personal Info
    private EditText etAddress;
    private EditText etCity;
    private Spinner spinnerProvince;
    private Spinner spinnerCountry;
    private EditText etPostalCode;

    private Button buttonPurchase;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (container != null) {
            container.removeAllViews();
        }
        View view = inflater.inflate(R.layout.fragment_payment_stripe, container, false);

        //etCardNum = (EditText) view.findViewById(R.id.editText_card_number);
        //etExpMonth = (EditText) view.findViewById(R.id.editText_month);
        //etExpYear = (EditText) view.findViewById(R.id.editText_year);
        mCardInputWidget = (CardInputWidget) view.findViewById(R.id.card_input_widget);
        etName = (EditText) view.findViewById(R.id.editText_name);
        //etCountryCode = (EditText) view.findViewById(R.id.editText_country_code);
        //etAreaCode = (EditText) view.findViewById(R.id.editText_area_code);
        //etPhoneNum = (EditText) view.findViewById(R.id.editText_phone_number);

        etAddress = (EditText) view.findViewById(R.id.editText_address);
        etCity = (EditText) view.findViewById(R.id.editText_city);
        spinnerProvince = (Spinner) view.findViewById(R.id.spinner_province);
        spinnerCountry = (Spinner) view.findViewById(R.id.spinner_country);
        etPostalCode = (EditText) view.findViewById(R.id.editText_postal_code);

        buttonPurchase = (Button) view.findViewById(R.id.button_payment);
        buttonPurchase.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_payment:
                //PaymentInfo info = createPaymentInfo();
                Card card = createCard();

                if (card != null) {
                    ProcessPayment processPayment = new ProcessPaymentImpl();
                    boolean paymentSuccess = processPayment.process(card, getContext());

                    if (paymentSuccess) {
                        // Put BookedFlight into database
                        AccessBookedFlights accessBookedFlights = new AccessBookedFlightsImpl();
                        // TODO: Add the BookedFlight object (Received form previous screen - view flights)
                        //accessBookedFlights.addBookedFlight();
                    } else {
                        // Make a Toast
                        Toast.makeText(getContext(), "Payment failed", Toast.LENGTH_SHORT).show();
                    }

                    // TODO: go to confirmation screen
                } else {
                    ToastHandler.toastInvalidCardInfo(getActivity());
                }

                break;
        }
    }

    private Card createCard() {
        Card cardToSave = mCardInputWidget.getCard();

        if (cardToSave != null) {
            cardToSave.setName(etName.getText().toString());
            cardToSave.setAddressCity(etCity.getText().toString());
            cardToSave.setAddressCountry(spinnerCountry.getSelectedItem().toString());
            cardToSave.setAddressZip(etPostalCode.getText().toString());
        }

        return cardToSave;
    }

    /**
     * Creates a PaymentInfo object from the values input into the EditTexts on the screen.
     *
     * @return the PaymentInfo object created.
     */
    private PaymentInfo createPaymentInfo() {
        /*
        String cardNum = etCardNum.getText().toString();
        String expiryDate = etExpMonth.getText().toString() + "/" + etExpYear.getText().toString();

        PaymentInfo.PaymentInfoBuilder builder = new PaymentInfo.PaymentInfoBuilder(cardNum, expiryDate);
        builder.setName(etName.getText().toString())
                .setPhoneNumber(etCountryCode.getText().toString() + etAreaCode.getText().toString() + etPhoneNum.getText().toString())
                .setAddress(etAddress.getText().toString())
                .setProvince(spinnerProvince.getSelectedItem().toString())
                .setCountry(spinnerCountry.getSelectedItem().toString())
                .setPostalCode(etPostalCode.getText().toString());

        return builder.build();*/
        return null;
    }
}
