package ca.umanitoba.cs.comp3350.saveonflight.business;

import ca.umanitoba.cs.comp3350.saveonflight.objects.Airline;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Airport;
import ca.umanitoba.cs.comp3350.saveonflight.objects.SearchCriteria;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Shenyun Wang on 2017-06-27.
 */

public class SearchCriteriaHandler {
    public static boolean validate(SearchCriteria criteria) {
        boolean valid = false;

        if (validateAirport(criteria.getOrigin())
                && validateAirport(criteria.getDestination())
                && validateTripDate(criteria.getDepartureDate(), criteria.getReturnDate())
                && validatePassengers(criteria.getNumTravellers())
                && validateAirline(criteria.getPreferredAirline())
                && validatePrice(criteria.getMaxPrice())) {
            valid = true;
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
                && new AccessAirportsImpl().findAirportByName(airport.getAirportCode()) != null) {
            valid = true;
        }

        return valid;
    }

    private static boolean validateTripDate(Date departureDate, Date returnDate) {
        return (departureDate != null && (returnDate == null || departureDate.compareTo(returnDate) <= 0));
    }

    private static boolean validatePassengers(int numPassengers) {
        return numPassengers > 0;
    }

    private static boolean validateAirline(Airline airline) {
        boolean valid = false;

        if (airline != null && !airline.getName().trim().isEmpty()
                && new AccessAirlinesImpl().getAirlineByName(airline.getName()) != null) {
            valid = true;
        }

        return valid;
    }

    private static boolean validatePrice(double price) {
        return price > 0;
    }
}
