package ca.umanitoba.cs.comp3350.saveonflight.presentation.payment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
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
import ca.umanitoba.cs.comp3350.saveonflight.application.Main;
import ca.umanitoba.cs.comp3350.saveonflight.business.AccessBookedFlights;
import ca.umanitoba.cs.comp3350.saveonflight.business.AccessBookedFlightsImpl;
import ca.umanitoba.cs.comp3350.saveonflight.business.AccessFlightsImpl;
import ca.umanitoba.cs.comp3350.saveonflight.business.AccessTravellers;
import ca.umanitoba.cs.comp3350.saveonflight.business.AccessTravellersImpl;
import ca.umanitoba.cs.comp3350.saveonflight.objects.BookedFlight;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Flight;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Traveller;
import ca.umanitoba.cs.comp3350.saveonflight.presentation.FragmentNavigation;
import ca.umanitoba.cs.comp3350.saveonflight.presentation.ToastHandler;

/**
 * PaymentFragment.java
 * <p>
 * Fragment for the payment screen of the application.
 *
 * @author Kenny Zhang
 */

public class PaymentFragment extends Fragment implements View.OnClickListener {
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

        AccessFlightsImpl flightAccess = new AccessFlightsImpl(Main.getFlightAccess());
        flights = new ArrayList<>();

        ArrayList<String> flightCodes = getArguments().getStringArrayList("flights_to_book");
        if (flightCodes != null) {
            for (String f : flightCodes) {
                flights.add(flightAccess.getFlightByCode(f));
            }
        } else {
            ToastHandler.toastShowShortText(getActivity(), "Error: no flights to book");
        }

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_payment:
                Card card = createCard();

                if (card != null) {
                    paymentSuccess();
                } else {
                    paymentFailure();
                }

                break;
        }
    }

    /**
     * Stores the new traveller and booked flight information in the database upon a successful payment.
     */
    public void paymentSuccess() {
        // Since we're not actually processing any payments... just insertBookedFlight the BookedFlight(s) to the database
        AccessBookedFlights accessBookedFlights = new AccessBookedFlightsImpl(Main.getBookedFlightAccess());

        // First, create a new Traveller and store it in the database
        AccessTravellers accessTravellers = new AccessTravellersImpl(Main.getTravellerAccess());
        Traveller traveller = new Traveller(accessTravellers.getMaxId() + 1, etName.getText().toString());
        boolean added = accessTravellers.insertTraveller(traveller);

        if (added) {
            // Then, create the BookedFlight(s) objects
            ArrayList<BookedFlight> bfs = new ArrayList<>();
            for (Flight f : flights) {
                // flightAccess.updateFlight(flight, new seats taken)
                bfs.add(new BookedFlight(traveller, f,accessBookedFlights.seatNumber(f.getSeatsTaken())));
            }

            // Finally, insertBookedFlight all the BookedFlight(s) to the database
            for (BookedFlight bf : bfs) {
                accessBookedFlights.insertBookedFlight(bf);
            }

            showConfirmationDialog(traveller.getTravellerID());
        } else {
            ToastHandler.toastShowShortText(getActivity(), "Please enter your name");
        }
    }

    /**
     * Notify the user that payment has failed.
     */
    public void paymentFailure() {
        ToastHandler.toastShowShortText(getActivity(), "Invalid card data");
    }

    /**
     * Creates a Card object from the information in the input fields on the screen. The credit card
     * number, expiry card, and security code must be valid for a Card object to be created. Otherwise,
     * the method returns null.
     *
     * @return the Card object that was created
     */
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

    /**
     * Creates a Dialog that tells the passenger their ID. Gives them the option
     * of going to the view booked flights screen or returning to the homepage.
     *
     * @param passengerID the passenger's ID
     */
    private void showConfirmationDialog(int passengerID) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Payment Successful")
                .setMessage("Your passenger ID is " + passengerID + ".\nUse this ID to view your booked flights.");
        builder.setPositiveButton("View Booked Flights", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                FragmentNavigation.viewBookedFlights();
                ((NavigationView) getActivity().findViewById(R.id.nav_view)).setCheckedItem(R.id.nav_bookedflights);
            }
        });
        builder.setNegativeButton("Return to Homepage", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                FragmentNavigation.homepage();
                ((NavigationView) getActivity().findViewById(R.id.nav_view)).setCheckedItem(R.id.nav_home);
            }
        });
        builder.create().show();
    }

}
