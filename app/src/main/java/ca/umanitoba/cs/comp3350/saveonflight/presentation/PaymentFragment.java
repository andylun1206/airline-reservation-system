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

import java.util.ArrayList;

import ca.umanitoba.cs.comp3350.saveonflight.R;
import ca.umanitoba.cs.comp3350.saveonflight.business.AccessBookedFlights;
import ca.umanitoba.cs.comp3350.saveonflight.business.AccessBookedFlightsImpl;
import ca.umanitoba.cs.comp3350.saveonflight.business.ProcessPayment;
import ca.umanitoba.cs.comp3350.saveonflight.business.ProcessPaymentImpl;
import ca.umanitoba.cs.comp3350.saveonflight.objects.BookedFlight;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Flight;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Traveller;
import ca.umanitoba.cs.comp3350.saveonflight.persistence.TravellerTable;

/**
 * Payment.java
 * <p>
 * Fragment for the payment screen of the application.
 *
 * @author Kenny Zhang
 */


public class PaymentFragment extends Fragment implements View.OnClickListener, ProcessPayment.ProcessPaymentListener {
    private CardInputWidget mCardInputWidget;
    private EditText etName;
    private EditText etAddress;
    private EditText etCity;
    private Spinner spinnerProvince;
    private Spinner spinnerCountry;
    private EditText etPostalCode;
    private Button buttonPurchase;

    private ArrayList<Flight> flights;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (container != null) {
            container.removeAllViews();
        }
        View view = inflater.inflate(R.layout.fragment_payment_stripe, container, false);
        getActivity().setTitle("Payment");

        mCardInputWidget = (CardInputWidget) view.findViewById(R.id.card_input_widget);
        etName = (EditText) view.findViewById(R.id.editText_name);
        etAddress = (EditText) view.findViewById(R.id.editText_address);
        etCity = (EditText) view.findViewById(R.id.editText_city);
        spinnerProvince = (Spinner) view.findViewById(R.id.spinner_province);
        spinnerCountry = (Spinner) view.findViewById(R.id.spinner_country);
        etPostalCode = (EditText) view.findViewById(R.id.editText_postal_code);

        buttonPurchase = (Button) view.findViewById(R.id.button_payment);
        buttonPurchase.setOnClickListener(this);

        flights = getArguments().getParcelableArrayList("flights_to_book");

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_payment:
                //PaymentInfo info = createPaymentInfo();
                Card card = createCard();

                if (card != null) {
                    ProcessPayment processPayment = new ProcessPaymentImpl(this);
                    processPayment.process(card, getContext());
                } else {
                    ToastHandler.toastInvalidCardInfo(getActivity());
                }

                break;
        }
    }

    @Override
    public void paymentSuccess() {
        Toast.makeText(getContext(), "Payment succeeded", Toast.LENGTH_SHORT).show();
        // Since we're not actually processing any payments... just add the BookedFlight to the database

        AccessBookedFlights accessBookedFlights = new AccessBookedFlightsImpl();

        // First, create a new Traveller
        int id = TravellerTable.nextId++;
        Traveller traveller = new Traveller(id, etName.getText().toString());

        // Then, create the BookedFlight(s) objects
        ArrayList<BookedFlight> bfs = new ArrayList<>();
        for (Flight f : flights) {
            bfs.add(new BookedFlight(traveller, f));
        }

        // Finally, add all the BookedFlight(s) to the database
        for (BookedFlight bf : bfs) {
            accessBookedFlights.addBookedFlight(bf);
        }

        FragmentNavigation.bookingConfirmation(id);
        // TODO: go to confirmation screen (include their passenger id so that they can view their booked flights)

    }

    @Override
    public void paymentFailure() {
        Toast.makeText(getContext(), "Payment failed", Toast.LENGTH_SHORT).show();
    }

    private Card createCard() {
        Card cardToSave = mCardInputWidget.getCard();

        if (cardToSave != null) {
            cardToSave.setName(etName.getText().toString());
            cardToSave.setAddressLine1(etAddress.getText().toString());
            cardToSave.setAddressCity(etCity.getText().toString());
            cardToSave.setAddressState(spinnerProvince.getSelectedItem().toString());
            cardToSave.setAddressCountry(spinnerCountry.getSelectedItem().toString());
            cardToSave.setAddressZip(etPostalCode.getText().toString());
        }

        return cardToSave;
    }

}
