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
import ca.umanitoba.cs.comp3350.saveonflight.business.SortFlights;
import ca.umanitoba.cs.comp3350.saveonflight.business.SortFlightsImpl;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Flight;
import ca.umanitoba.cs.comp3350.saveonflight.objects.ViewFlightsListViewEntry;

import java.util.ArrayList;

public class ViewFlightsFragment extends ListFragment {
	private ViewFlightsArrayAdapter flightAdapter;
	private ArrayList<ViewFlightsListViewEntry> flightList;
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
		
		flights = getArguments().getParcelableArrayList("flights");
		
		return view;
	}
	
	@Override
	public void onViewCreated(View view, @Nullable Bundle saveInstanceState) {
		super.onViewCreated(view, saveInstanceState);
		String title = getString(R.string.title_activity_view_flights);
		
		if (flights != null && flights.size() != 0) {
			title = getString(R.string.view_flights_flight_path, flights.get(0).getOrigin().toString(),
					flights.get(0).getDestination().toString());
			
			updateFlightList();
		}
		
		getActivity().setTitle(title);
		
		view.findViewById(R.id.button_view_flight_sort_duration).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				//ToastHandler.toastComingSoon(getActivity(), "Sort by duration");
                new SortFlightsImpl().sortFlightsBy(flights, SortFlights.SortParameter.DURATION);
                updateFlightList();
			}
		});
		
		view.findViewById(R.id.button_view_flight_sort_price).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				new SortFlightsImpl().sortFlightsBy(flights, SortFlights.SortParameter.PRICE);
				updateFlightList();
			}
		});
		
		view.findViewById(R.id.button_view_flight_sort_time).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				new SortFlightsImpl().sortFlightsBy(flights, SortFlights.SortParameter.DATE);
				updateFlightList();
			}
		});
	}
	
	private void updateFlightList() {
		flightList.clear();
		
		for (Flight f : flights) {
			flightList.add(new ViewFlightsListViewEntry(f.getFlightTime(), f.getPrice(), f.getAirline().getIcon(), f.getFlightID(), f.getFlightDuration()));
		}
		
		flightAdapter.notifyDataSetChanged();
	}
	
}
