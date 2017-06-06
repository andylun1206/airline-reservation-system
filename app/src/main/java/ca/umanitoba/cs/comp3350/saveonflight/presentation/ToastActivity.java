package ca.umanitoba.cs.comp3350.saveonflight.presentation;

import android.app.Activity;
import android.widget.Toast;
import ca.umanitoba.cs.comp3350.saveonflight.R;

/**
 * ToastActivity.java
 *
 * Collection of all toast message activity.
 *
 * @author Andy Lun
 */
public class ToastActivity {
	
	/**
	 * Generic toast handler for features coming soon
	 * @param activity current activity
	 * @param feature feature name
	 */
	public static void toastComingSoon(Activity activity, String feature) {
		Toast.makeText(activity, activity.getString(R.string.toast_coming_soon, feature),
				Toast.LENGTH_SHORT).show();
	}
	
	/**
	 * Generic toast handler for missing mandatory fields
	 * @param activity current activity
	 * @param field missing field name
	 */
	public static void toastMandatoryField(Activity activity, String field) {
		Toast.makeText(activity, activity.getString(R.string.toast_mandatory_field, field),
				Toast.LENGTH_SHORT).show();
	}
	
	/**
	 * Generic toast handler for no results from search
	 * @param activity current activity
	 */
	public static void toastNoResults(Activity activity) {
		Toast.makeText(activity, activity.getString(R.string.toast_no_result), Toast.LENGTH_SHORT).show();
	}
	
}
