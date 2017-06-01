package ca.umanitoba.cs.comp3350.saveonflight.presentation;

/**
 * ViewFlightsFragment.java
 *
 * Fragment for the view flights page of the application
 *
 * @author Andy Lun
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import ca.umanitoba.cs.comp3350.saveonflight.R;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Flight;
import ca.umanitoba.cs.comp3350.saveonflight.objects.ViewFlightsListView;

import java.util.ArrayList;

public class ViewFlightsFragment extends ListFragment {
	private ViewFlightsArrayAdapter flightAdapter;
	private ArrayList<ViewFlightsListView> flightList;
	private ArrayList<Flight> flights;
	
	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
	                         @Nullable Bundle saveInstanceState) {
		if (container != null) {
			container.removeAllViews();
		}
		
		View view = inflater.inflate(R.layout.fragment_view_flights, container, false);
		flightList = new ArrayList<>();
		flightAdapter = new ViewFlightsArrayAdapter(getActivity(), R.layout.list_item_view_flights, flightList);
		setListAdapter(flightAdapter);
		
		return view;
	}
	
	@Override
	public void onViewCreated(View view, @Nullable Bundle saveInstanceState) {
		super.onViewCreated(view, saveInstanceState);
		getActivity().setTitle("Flights");
		
		flights = getArguments().getParcelableArrayList(getString(R.string.title_activity_view_flights));
		flightList.add(new ViewFlightsListView("05:30am - 08:51am", 190, R.mipmap.ic_aircanada, "AC256", "2h 21m"));
		flightAdapter.notifyDataSetChanged();
	}
}