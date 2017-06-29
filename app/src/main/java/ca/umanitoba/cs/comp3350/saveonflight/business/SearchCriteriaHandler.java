package ca.umanitoba.cs.comp3350.saveonflight.business;

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
    public static boolean validate(SearchCriteria criteria){
        return true;
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
}
