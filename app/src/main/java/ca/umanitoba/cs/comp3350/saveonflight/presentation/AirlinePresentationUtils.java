package ca.umanitoba.cs.comp3350.saveonflight.presentation;

import ca.umanitoba.cs.comp3350.saveonflight.R;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Airline;

public class AirlinePresentationUtils {
    private static final String WESTJET = "WestJet";
    private static final String AIR_CANADA = "Air Canada";

    public static int getUiIconCode(Airline airline) {
        int uiIconCode = -1;
        switch (airline.getName()) {
            case WESTJET:
                uiIconCode = R.mipmap.ic_westjet;
                break;
            case AIR_CANADA:
                uiIconCode = R.mipmap.ic_aircanada;
                break;
        }
        return uiIconCode;
    }
}
