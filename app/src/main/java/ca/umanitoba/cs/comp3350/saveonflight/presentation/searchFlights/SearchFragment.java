package ca.umanitoba.cs.comp3350.saveonflight.presentation.searchFlights;

/**
 * SearchFragment.java
 * <p>
 * Fragment for the search page of the application.
 *
 * @author Andy Lun
 */

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;
import android.widget.AdapterView.OnItemSelectedListener;

import java.util.ArrayList;

import ca.umanitoba.cs.comp3350.saveonflight.R;
import ca.umanitoba.cs.comp3350.saveonflight.business.AccessFlightsImpl;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Flight;
import ca.umanitoba.cs.comp3350.saveonflight.objects.SearchCriteria;
import ca.umanitoba.cs.comp3350.saveonflight.presentation.FragmentNavigation;
import ca.umanitoba.cs.comp3350.saveonflight.presentation.ToastHandler;

public class SearchFragment extends ListFragment {
    private SearchCriteriaArrayAdapter criteriaAdapter;
    private ArrayList<SearchCriteriaListViewEntry> mandatoryCriteriaList;
    private ArrayList<SearchCriteriaListViewEntry> optionalCriteriaList;
    private boolean showAdvanced = false;


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
        criteriaAdapter = new SearchCriteriaArrayAdapter(getActivity(), R.layout.list_item_search_criteria_text,
                mandatoryCriteriaList, optionalCriteriaList);
        setListAdapter(criteriaAdapter);

        if (! (view instanceof EditText)) {
            view.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
                    return false;
                }
            });
        }

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle saveInstanceState) {
        super.onViewCreated(view, saveInstanceState);
        getActivity().setTitle(getString(R.string.title_activity_search));

        ((Spinner) view.findViewById(R.id.spinner_trip_type)).setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SearchCriteria newCriteria = new SearchCriteria();

                switch (parent.getItemAtPosition(position).toString()) {
                    case "Return":
                        setReturnCriteria();
                        newCriteria.setReturnTrip(true);
                        break;
                    case "One Way":
                        setOneWayCriteria();
                        newCriteria.setReturnTrip(false);
                        break;
                }
                criteriaAdapter.notifyDataSetChanged(false);
                SearchCriteriaArrayAdapter.setCriteria(newCriteria);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        view.findViewById(R.id.button_search_advance).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                setAdvancedCriteria();
            }
        });

        view.findViewById(R.id.button_search_search).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SearchCriteriaHandler.validate(getActivity(), SearchCriteriaArrayAdapter.getCriteria())) {
                    //ArrayList<Flight> flightList = new AccessFlightsImpl().search(SearchCriteriaArrayAdapter.getCriteria());
                    ArrayList<Flight> flightList = new AccessFlightsImpl().search(SearchCriteriaArrayAdapter.getCriteria());
                    if (flightList != null && !flightList.isEmpty()) {
                        FragmentNavigation.viewFlights();
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
    private void setReturnCriteria() {
        if (mandatoryCriteriaList.size() == 0) {
            setOneWayCriteria();
        }
        mandatoryCriteriaList.add(3, new SearchCriteriaListViewEntry(R.drawable.ic_clock, getString(R.string.search_return_date)));
    }

    /**
     * Sets all on screen criteria to represent one way trips.
     */
    private void setOneWayCriteria() {
        if (mandatoryCriteriaList.size() == 0) {
            mandatoryCriteriaList.add(new SearchCriteriaListViewEntry(R.drawable.ic_takeoff, getString(R.string.search_origin)));
            mandatoryCriteriaList.add(new SearchCriteriaListViewEntry(R.drawable.ic_landing, getString(R.string.search_destination)));
            mandatoryCriteriaList.add(new SearchCriteriaListViewEntry(R.drawable.ic_clock, getString(R.string.search_departure_date)));
            mandatoryCriteriaList.add(new SearchCriteriaListViewEntry(R.drawable.ic_person, getString(R.string.search_num_passengers)));
        } else {
            mandatoryCriteriaList.remove(3);
        }
    }

    /**
     * Toggles advance options to on screen criterias.
     */
    private void setAdvancedCriteria() {
        if (optionalCriteriaList.size() == 0) {
            optionalCriteriaList.add(new SearchCriteriaListViewEntry(R.drawable.ic_dollar_sign, getString(R.string.search_max_price)));
            optionalCriteriaList.add(new SearchCriteriaListViewEntry(R.drawable.ic_plane, getString(R.string.search_airlines)));
            optionalCriteriaList.add(new SearchCriteriaListViewEntry(R.drawable.ic_seat, getString(R.string.search_class)));
        }

        criteriaAdapter.notifyDataSetChanged(!showAdvanced);
        showAdvanced = !showAdvanced;
    }


}
