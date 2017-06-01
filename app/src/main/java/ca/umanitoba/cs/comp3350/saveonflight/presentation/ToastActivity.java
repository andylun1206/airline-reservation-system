package ca.umanitoba.cs.comp3350.saveonflight.presentation;

import android.support.v4.app.FragmentActivity;
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
	
	public static void toastComingSoon(FragmentActivity activity, String feature) {
		Toast.makeText(activity, activity.getString(R.string.toast_coming_soon, feature),
				Toast.LENGTH_SHORT).show();
	}
	
	
}
