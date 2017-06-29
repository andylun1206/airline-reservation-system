package ca.umanitoba.cs.comp3350.saveonflight.business;

import ca.umanitoba.cs.comp3350.saveonflight.objects.Airport;
import ca.umanitoba.cs.comp3350.saveonflight.objects.SearchCriteria;

/**
 * Created by Shenyun Wang on 2017-06-27.
 */

public class SearchCriteriaHandler {
    public boolean validate(SearchCriteria s){
        return true;
    }

    public static SearchCriteria reverseFlightDirection(SearchCriteria criteria) {
        Airport temp = criteria.getOrigin();

        criteria.setOrigin(criteria.getDestination());
        criteria.setDestination(temp);
        criteria.setDepartureDate(criteria.getReturnDate());

        return criteria;
    }
}
