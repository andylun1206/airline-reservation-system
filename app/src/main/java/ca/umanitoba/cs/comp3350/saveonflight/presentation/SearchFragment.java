package ca.umanitoba.cs.comp3350.saveonflight.presentation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import ca.umanitoba.cs.comp3350.saveonflight.R;

import java.util.ArrayList;

public class SearchFragment extends ListFragment{
	private SearchCriteriaArrayAdapter adapter;
	private ArrayList<SearchCriteria> criterias;
	
	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
	                         @Nullable Bundle saveInstanceState) {
		View view = inflater.inflate(R.layout.fragment_search_return, container, false);
		setDefaultCriterias();
		adapter = new SearchCriteriaArrayAdapter(getActivity(), R.layout.list_search_criteria, criterias);
		setListAdapter(adapter);
		return view;
	}
	
	@Override
	public void onViewCreated(View view, @Nullable Bundle saveInstanceState) {
		super.onViewCreated(view, saveInstanceState);
		getActivity().setTitle(getString(R.string.title_activity_search));
		
		getView().findViewById(R.id.button_search_return_advance).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				if (criterias.size() == 5) {
					criterias.add(new SearchCriteria(R.drawable.ic_plane, getString(R.string.search_airlines)));
					criterias.add(new SearchCriteria(R.drawable.ic_seat, getString(R.string.search_class)));
					getView().findViewById(R.id.search_return_advanced_settings_checkboxes).setVisibility(View.VISIBLE);
				} else {
					criterias.remove(6);
					criterias.remove(5);
					getView().findViewById(R.id.search_return_advanced_settings_checkboxes).setVisibility(View.GONE);
				}
				adapter.notifyDataSetChanged();
			}
		});
	}
	
	private void setDefaultCriterias() {
		criterias = new ArrayList<>();
		criterias.add(new SearchCriteria(R.drawable.ic_takeoff, getString(R.string.search_origin)));
		criterias.add(new SearchCriteria(R.drawable.ic_landing, getString(R.string.search_destination)));
		criterias.add(new SearchCriteria(R.drawable.ic_clock, getString(R.string.search_departure_date)));
		criterias.add(new SearchCriteria(R.drawable.ic_clock, getString(R.string.search_arrival_date)));
		criterias.add(new SearchCriteria(R.drawable.ic_person, getString(R.string.search_num_travellers)));
	}
}
