package ca.umanitoba.cs.comp3350.saveonflight.presentation;

/**
 * SearchFragment.java
 *
 * Fragment for the search page of the application.
 *
 * @author Andy Lun
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;
import ca.umanitoba.cs.comp3350.saveonflight.R;
import ca.umanitoba.cs.comp3350.saveonflight.business.FindFlightsImpl;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Flight;
import ca.umanitoba.cs.comp3350.saveonflight.objects.SearchCriteria;
import ca.umanitoba.cs.comp3350.saveonflight.objects.SearchCriteriaListView;

import java.util.ArrayList;

public class SearchFragment extends ListFragment {
	private SearchCriteriaArrayAdapter criteriaAdapter;
	private ArrayList<SearchCriteriaListView> criteriaLists;
	
	ViewFlightsListener viewFlightsListener;
	public interface ViewFlightsListener {
		void viewFlights(ArrayList<Flight> flights);
	}
	
	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
	                         @Nullable Bundle saveInstanceState) {
		if (container != null) {
			container.removeAllViews();
		}
		
		View view = inflater.inflate(R.layout.fragment_search, container, false);
		criteriaLists = new ArrayList<>();
		criteriaAdapter = new SearchCriteriaArrayAdapter(getActivity(), R.layout.list_item_search_criteria, criteriaLists);
		setListAdapter(criteriaAdapter);
		
		viewFlightsListener = (ViewFlightsListener) getActivity();
		
		return view;
	}
	
	@Override
	public void onViewCreated(View view, @Nullable Bundle saveInstanceState) {
		super.onViewCreated(view, saveInstanceState);
		getActivity().setTitle(getString(R.string.title_activity_search));
		
		((Spinner) view.findViewById(R.id.spinner_trip_type)).setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				switch (parent.getItemAtPosition(position).toString()) {
					case "Return":
						setReturnCriterias();
						break;
					case "One Way":
						setOneWayCriterias();
						break;
				}
				criteriaAdapter.notifyDataSetChanged();
				getView().findViewById(R.id.search_advanced_settings_checkboxes).setVisibility(View.GONE);
				SearchCriteriaArrayAdapter.resetCriteria();
			}
			@Override
			public void onNothingSelected(AdapterView<?> adapterView) { }
		});
		
		((CheckBox) view.findViewById(R.id.checkBox_search_nonstop)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
				SearchCriteria criteria = SearchCriteriaArrayAdapter.getCriteria();
				criteria.setNonstop(isChecked);
				SearchCriteriaArrayAdapter.setCriteria(criteria);
			}
		});

		((CheckBox) view.findViewById(R.id.checkBox_search_refundable)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
				SearchCriteria criteria = SearchCriteriaArrayAdapter.getCriteria();
				criteria.setRefundable(isChecked);
				SearchCriteriaArrayAdapter.setCriteria(criteria);
			}
		});
		
		view.findViewById(R.id.button_search_advance).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				setAdvancedCriterias();
				criteriaAdapter.notifyDataSetChanged();
			}
		});
		
		view.findViewById(R.id.button_search_search).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				viewFlightsListener.viewFlights(new FindFlightsImpl().search(SearchCriteriaArrayAdapter.getCriteria()));
			}
		});
	}
	
	private void setReturnCriterias() {
		criteriaLists.clear();
		setOneWayCriterias();
		criteriaLists.add(3, new SearchCriteriaListView(R.drawable.ic_clock, getString(R.string.search_return_date)));
	}
	
	private void setOneWayCriterias() {
		criteriaLists.clear();
		criteriaLists.add(new SearchCriteriaListView(R.drawable.ic_takeoff, getString(R.string.search_origin)));
		criteriaLists.add(new SearchCriteriaListView(R.drawable.ic_landing, getString(R.string.search_destination)));
		criteriaLists.add(new SearchCriteriaListView(R.drawable.ic_clock, getString(R.string.search_departure_date)));
		criteriaLists.add(new SearchCriteriaListView(R.drawable.ic_person, getString(R.string.search_num_travellers)));
	}
	
	private void setAdvancedCriterias() {
		View checkboxes = getView().findViewById(R.id.search_advanced_settings_checkboxes);
		
		if (checkboxes.getVisibility() == View.VISIBLE) {
			switch (((Spinner) getView().findViewById(R.id.spinner_trip_type)).getSelectedItem().toString()) {
				case "Return":
					setReturnCriterias();
					break;
				case "One Way":
					setOneWayCriterias();
					break;
			}
			checkboxes.setVisibility(View.GONE);
		} else {
			criteriaLists.add(new SearchCriteriaListView(R.drawable.ic_dollar_sign, getString(R.string.search_max_price)));
			criteriaLists.add(new SearchCriteriaListView(R.drawable.ic_plane, getString(R.string.search_airlines)));
			criteriaLists.add(new SearchCriteriaListView(R.drawable.ic_seat, getString(R.string.search_class)));
			checkboxes.setVisibility(View.VISIBLE);
		}
	}
}
