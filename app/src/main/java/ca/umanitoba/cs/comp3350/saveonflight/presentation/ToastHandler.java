package ca.umanitoba.cs.comp3350.saveonflight.presentation;

import android.app.Activity;
import android.widget.Toast;

import ca.umanitoba.cs.comp3350.saveonflight.R;

/**
 * ToastHandler.java
 * <p>
 * Collection of all toast message activity.
 *
 * @author Andy Lun
 */
public class ToastHandler {
    /**
     * Toast handler for missing mandatory fields
     *
     * @param activity current activity
     * @param field    missing field name
     */
    public static void toastMandatoryField(Activity activity, String field) {
        toastShowShortText(activity, activity.getString(R.string.toast_mandatory_field, field));
    }

    /**
     * Toast handler for no results from search
     *
     * @param activity current activity
     */
    public static void toastNoResults(Activity activity) {
        toastShowShortText(activity, activity.getString(R.string.toast_no_result));
    }

    /**
     * Generic toast handler for showing any text a brief amount of time
     *
     * @param activity current activity
     * @param text     text to show in the Toast
     */
    public static void toastShowShortText(Activity activity, String text) {
        Toast.makeText(activity, text, Toast.LENGTH_SHORT).show();
    }
}
