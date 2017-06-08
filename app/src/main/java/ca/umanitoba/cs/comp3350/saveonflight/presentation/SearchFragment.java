package ca.umanitoba.cs.comp3350.saveonflight.presentation;

/**
 * SearchFragment.java
 * <p>
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
import ca.umanitoba.cs.comp3350.saveonflight.business.AccessFlightsImpl;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Flight;
import ca.umanitoba.cs.comp3350.saveonflight.objects.SearchCriteria;
import ca.umanitoba.cs.comp3350.saveonflight.objects.SearchCriteriaListViewEntry;

import java.util.ArrayList;

public class SearchFragment extends ListFragment {
    private SearchCriteriaArrayAdapter criteriaAdapter;
    private ArrayList<SearchCriteriaListViewEntry> mandatoryCriteriaList;
    private ArrayList<SearchCriteriaListViewEntry> optionalCriteriaList;

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
        mandatoryCriteriaList = new ArrayList<>();
        optionalCriteriaList = new ArrayList<>();
        criteriaAdapter = new SearchCriteriaArrayAdapter(getActivity(), R.layout.list_item_search_criteria,
                mandatoryCriteriaList, optionalCriteriaList);
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
                criteriaAdapter.notifyDataSetChanged(false);
                getView().findViewById(R.id.search_advanced_settings_checkboxes).setVisibility(View.GONE);
                SearchCriteriaArrayAdapter.setCriteria(new SearchCriteria());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
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
            }
        });

        view.findViewById(R.id.button_search_search).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (criteriaAdapter.verifyCriteria(getActivity())) {
                    ArrayList<Flight> flightList = new AccessFlightsImpl().search(SearchCriteriaArrayAdapter.getCriteria());
                    if (flightList != null && !flightList.isEmpty()) {
                        viewFlightsListener.viewFlights(flightList);
                    } else {
                        ToastHandler.toastNoResults(getActivity());
                    }
                }
            }
        });
    }

    /**
     * Sets all on screen criteria to represent return trips.
     */
    private void setReturnCriterias() {
        if (mandatoryCriteriaList.size() == 0) {
            setOneWayCriterias();
        }
        mandatoryCriteriaList.add(3, new SearchCriteriaListViewEntry(R.drawable.ic_clock, getString(R.string.search_return_date)));
    }

    /**
     * Sets all on screen criteria to represent one way trips.
     */
    private void setOneWayCriterias() {
        if (mandatoryCriteriaList.size() == 0) {
            mandatoryCriteriaList.add(new SearchCriteriaListViewEntry(R.drawable.ic_takeoff, getString(R.string.search_origin)));
            mandatoryCriteriaList.add(new SearchCriteriaListViewEntry(R.drawable.ic_landing, getString(R.string.search_destination)));
            mandatoryCriteriaList.add(new SearchCriteriaListViewEntry(R.drawable.ic_clock, getString(R.string.search_departure_date)));
            mandatoryCriteriaList.add(new SearchCriteriaListViewEntry(R.drawable.ic_person, getString(R.string.search_num_travellers)));
        } else {
            mandatoryCriteriaList.remove(3);
        }
    }

    /**
     * Toggles advance options to on screen criterias.
     */
    private void setAdvancedCriterias() {
        View checkboxes = getView().findViewById(R.id.search_advanced_settings_checkboxes);
        boolean isVisible = (checkboxes.getVisibility() == View.VISIBLE);

        if (isVisible) {
            checkboxes.setVisibility(View.GONE);
        } else {
            if (optionalCriteriaList.size() == 0) {
                optionalCriteriaList.add(new SearchCriteriaListViewEntry(R.drawable.ic_dollar_sign, getString(R.string.search_max_price)));
                optionalCriteriaList.add(new SearchCriteriaListViewEntry(R.drawable.ic_plane, getString(R.string.search_airlines)));
                optionalCriteriaList.add(new SearchCriteriaListViewEntry(R.drawable.ic_seat, getString(R.string.search_class)));
            }
            checkboxes.setVisibility(View.VISIBLE);
        }

        criteriaAdapter.notifyDataSetChanged(!isVisible);
    }
}
