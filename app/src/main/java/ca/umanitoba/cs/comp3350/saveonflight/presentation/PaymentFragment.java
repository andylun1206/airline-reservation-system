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

import ca.umanitoba.cs.comp3350.saveonflight.R;

/**
 * Payment.java
 * <p>
 * Fragment for the payment screen of the application.
 *
 * @author Kenny Zhang
 */


public class PaymentFragment extends Fragment implements View.OnClickListener {
    // Payment info
    private EditText etCardNum;
    private EditText etExpMonth;
    private EditText etExpYear;
    private EditText etName;
    private EditText etCountryCode;
    private EditText etAreaCode;
    private EditText etPhoneNum;

    // Personal Info
    private EditText etAddress;
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
        View view = inflater.inflate(R.layout.fragment_payment, container, false);

        etCardNum = (EditText) view.findViewById(R.id.editText_card_number);
        etExpMonth = (EditText) view.findViewById(R.id.editText_month);
        etExpYear = (EditText) view.findViewById(R.id.editText_year);
        etName = (EditText) view.findViewById(R.id.editText_name);
        etCountryCode = (EditText) view.findViewById(R.id.editText_country_code);
        etAreaCode = (EditText) view.findViewById(R.id.editText_area_code);
        etPhoneNum = (EditText) view.findViewById(R.id.editText_phone_number);

        etAddress = (EditText) view.findViewById(R.id.editText_address);
        spinnerProvince = (Spinner) view.findViewById(R.id.spinner_province);
        spinnerCountry = (Spinner) view.findViewById(R.id.spinner_country);
        etPostalCode = (EditText) view.findViewById(R.id.editText_postal_code);

        buttonPurchase = (Button) view.findViewById(R.id.button_payment);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_payment:
                Bundle bundle = putInfoInBundle();
                // TODO: call business layer to validate info
                // TODO: call business layer to process payment
                // TODO: go to confirmation screen
                break;
        }
    }

    private Bundle putInfoInBundle() {
        Bundle bundle = new Bundle();

        



        return bundle;
    }
}
