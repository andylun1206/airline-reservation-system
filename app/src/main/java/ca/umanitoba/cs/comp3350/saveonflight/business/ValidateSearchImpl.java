package ca.umanitoba.cs.comp3350.saveonflight.business;

import ca.umanitoba.cs.comp3350.saveonflight.objects.SearchCriteria;

/**
 * Created by HCI_Lab on 2017-06-27.
 */

public class ValidateSearchImpl implements ValidateSearch {
    public boolean validate(SearchCriteria s){
        boolean validate = false;
        if(s.isReturnTrip()){
            validate = roundTrip(s);
        }
        else {
            validate = oneWay(s);
        }

        return validate;
    }

    public boolean oneWay(SearchCriteria s){
        boolean check = false;
        if(s.)


        return check;
    }

    public boolean roundTrip(SearchCriteria s){
        return true;
    }
}

