package ca.umanitoba.cs.comp3350.saveonflight.presentation.searchFlights;

import android.app.Activity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import ca.umanitoba.cs.comp3350.saveonflight.R;
import ca.umanitoba.cs.comp3350.saveonflight.application.Main;
import ca.umanitoba.cs.comp3350.saveonflight.business.AccessAirportsImpl;
import ca.umanitoba.cs.comp3350.saveonflight.business.AccessFlights;
import ca.umanitoba.cs.comp3350.saveonflight.business.AccessFlightsImpl;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Airport;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Flight;
import ca.umanitoba.cs.comp3350.saveonflight.objects.SearchCriteria;
import ca.umanitoba.cs.comp3350.saveonflight.presentation.ToastHandler;

/**
 * Created by Shenyun Wang on 2017-06-27.
 */

public class SearchCriteriaHandler {
    public static boolean validate(Activity activity, SearchCriteria criteria) {
        boolean valid = true;

        if (!validateAirport(criteria.getOrigin())) {
            valid = missingRequiredField(activity, R.string.search_origin);
        } else if (!validateAirport(criteria.getDestination())) {
            valid = missingRequiredField(activity, R.string.search_destination);
        } else if (!validateDepartureDate(criteria.getDepartureDate())) {
            valid = missingRequiredField(activity, R.string.search_departure_date);
        } else if (criteria.isReturnTrip() && !validateReturnDate(criteria.getDepartureDate(), criteria.getReturnDate())) {
            valid = missingRequiredField(activity, R.string.search_return_date);
        }

        if (valid) {
            valid = checkThatDatabaseHasFlights(criteria);
            if (!valid) {
                ToastHandler.toastNoResults(activity);
            }
        }

        return valid;
    }

    public static SearchCriteria reverseFlightDirection(SearchCriteria criteria) {
        Airport temp = criteria.getOrigin();

        criteria.setOrigin(criteria.getDestination());
        criteria.setDestination(temp);
        criteria.setDepartureDate(criteria.getReturnDate());

        return criteria;
    }

    public static Date parseDate(String date) {
        Date parsedDate = null;

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CANADA);
            parsedDate = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return parsedDate;
    }

    private static boolean validateAirport(Airport airport) {
        boolean valid = false;

        if (airport != null && !airport.getAirportCode().trim().isEmpty()
                && new AccessAirportsImpl(Main.getAirportAccess()).findAirportByName(airport.getAirportCode()) != null) {
            valid = true;
        }

        return valid;
    }

    private static boolean validateDepartureDate(Date departureDate) {
        return (departureDate != null);
    }

    private static boolean validateReturnDate(Date departureDate, Date returnDate) {
        return (returnDate != null && departureDate.compareTo(returnDate) <= 0);
    }

    private static boolean checkThatDatabaseHasFlights(SearchCriteria criteria) {
        boolean valid = false;

        SearchCriteria copy = criteria.clone();
        AccessFlights access = new AccessFlightsImpl(Main.getFlightAccess());
        List<Flight> departingFlights = access.search(copy);
        if (!departingFlights.isEmpty()) {
            if (copy.isReturnTrip()) {
                reverseFlightDirection(copy);
                List<Flight> returnFlights = access.search(copy);
                if (!returnFlights.isEmpty()) {
                    valid = true;
                }
            } else {
                valid = true;
            }
        }

        return valid;
    }

    /**
     * Generates a toast message to notify the user that a field is mandatory
     * @param activity current activity
     * @param field field name
     * @return false
     */
    private static boolean missingRequiredField(Activity activity, int field) {
        ToastHandler.toastMandatoryField(activity, activity.getString(field));
        return false;
    }
}
