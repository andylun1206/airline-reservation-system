package ca.umanitoba.cs.comp3350.saveonflight.presentation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import ca.umanitoba.cs.comp3350.saveonflight.R;

import java.util.ArrayList;

public class SearchFragment extends ListFragment implements OnItemSelectedListener{
	private SearchCriteriaArrayAdapter criteriaAdapter;
	private ArrayList<SearchCriteria> criterias;
	
	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
	                         @Nullable Bundle saveInstanceState) {
		View view = inflater.inflate(R.layout.fragment_search_return, container, false);
		criterias = new ArrayList<>();
		criteriaAdapter = new SearchCriteriaArrayAdapter(getActivity(), R.layout.list_search_criteria, criterias);
		setListAdapter(criteriaAdapter);
		return view;
	}
	
	@Override
	public void onViewCreated(View view, @Nullable Bundle saveInstanceState) {
		super.onViewCreated(view, saveInstanceState);
		getActivity().setTitle(getString(R.string.title_activity_search));
		
		Spinner spinner = (Spinner) view.findViewById(R.id.spinner_trip_type);
		spinner.setOnItemSelectedListener(this);
		
		view.findViewById(R.id.button_search_return_advance).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				setAdvancedCriterias();
				criteriaAdapter.notifyDataSetChanged();
			}
		});
	}
	
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
		getView().findViewById(R.id.search_return_advanced_settings_checkboxes).setVisibility(View.GONE);
	}
	
	public void onNothingSelected(AdapterView<?> arg0) { }
	
	private void setReturnCriterias() {
		criterias.clear();
		setOneWayCriterias();
		criterias.add(3, new SearchCriteria(R.drawable.ic_clock, getString(R.string.search_return_date)));
	}
	
	private void setOneWayCriterias() {
		criterias.clear();
		criterias.add(new SearchCriteria(R.drawable.ic_takeoff, getString(R.string.search_origin)));
		criterias.add(new SearchCriteria(R.drawable.ic_landing, getString(R.string.search_destination)));
		criterias.add(new SearchCriteria(R.drawable.ic_clock, getString(R.string.search_departure_date)));
		criterias.add(new SearchCriteria(R.drawable.ic_person, getString(R.string.search_num_travellers)));
	}
	
	private void setAdvancedCriterias() {
		View checkboxes = getView().findViewById(R.id.search_return_advanced_settings_checkboxes);
		
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
			criterias.add(new SearchCriteria(R.drawable.ic_dollar_sign, getString(R.string.search_max_price)));
			criterias.add(new SearchCriteria(R.drawable.ic_plane, getString(R.string.search_airlines)));
			criterias.add(new SearchCriteria(R.drawable.ic_seat, getString(R.string.search_class)));
			checkboxes.setVisibility(View.VISIBLE);
		}
	}
}
