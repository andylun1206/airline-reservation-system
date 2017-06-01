package ca.umanitoba.cs.comp3350.saveonflight.presentation;

/**
 * ViewFlightsFragment.java
 *
 * Fragment for the view flights page of the application
 *
 * @author Andy Lun
 */

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import ca.umanitoba.cs.comp3350.saveonflight.R;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Flight;

import java.util.List;

public class ViewFlightsFragment extends Fragment {
	List<Flight> flights;
	
	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
	                         @Nullable Bundle saveInstanceState) {
		if (container != null) {
			container.removeAllViews();
		}
		return inflater.inflate(R.layout.fragment_view_flights, container, false);
	}
	
	@Override
	public void onViewCreated(View view, @Nullable Bundle saveInstanceState) {
		super.onViewCreated(view, saveInstanceState);
		getActivity().setTitle("Flights");
		
		flights = getArguments().getParcelableArrayList("flights");
		((TextView) view.findViewById(R.id.textView)).setText(flights.get(0).toString());
	}
}
