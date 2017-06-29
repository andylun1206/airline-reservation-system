package ca.umanitoba.cs.comp3350.saveonflight.presentation.searchFlights;

/**
 * SearchCriteriaArrayAdapter.java
 * <p>
 * ArrayAdapter for SearchCriteria. Used to populate a listview in search.
 *
 * @author Andy Lun
 */

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Context;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import ca.umanitoba.cs.comp3350.saveonflight.R;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Airline;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Airport;
import ca.umanitoba.cs.comp3350.saveonflight.objects.FlightClassEnum;
import ca.umanitoba.cs.comp3350.saveonflight.objects.SearchCriteria;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class SearchCriteriaArrayAdapter extends ArrayAdapter<SearchCriteriaListViewEntry> implements OnDateSetListener {
    private final Context context;
    private ArrayList<SearchCriteriaListViewEntry> mandatoryCriteriaList;
    private ArrayList<SearchCriteriaListViewEntry> optionalCriteriaList;
    private ArrayList<SearchCriteriaListViewEntry> fullCriteriaList;
    private static SearchCriteria criteria;

    private EditText activeDateDisplay;
    private Calendar minDate;

    public SearchCriteriaArrayAdapter(Context context, int layoutResourceId,
                                      ArrayList<SearchCriteriaListViewEntry> mandatoryCriteriaList,
                                      ArrayList<SearchCriteriaListViewEntry> optionalCriteriaList) {
        super(context, layoutResourceId, mandatoryCriteriaList);
        this.context = context;
        this.mandatoryCriteriaList = mandatoryCriteriaList;
        this.optionalCriteriaList = optionalCriteriaList;
        this.fullCriteriaList = new ArrayList<>();

        minDate = new GregorianCalendar();

        criteria = new SearchCriteria();
    }

    @Override
    public View getView(final int position, final View convertView, final ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        int layoutResourceId;

        if ((criteria.isReturnTrip() && position == 4) ||
                (!criteria.isReturnTrip() && position == 3)) {
            layoutResourceId = R.layout.list_item_search_criteria_drop_down;
        } else {
            layoutResourceId = R.layout.list_item_search_criteria_text;

        }

        final View view = inflater.inflate(layoutResourceId, parent, false);
        final SearchCriteriaListViewEntry row = fullCriteriaList.get(position);

        ((ImageView) view.findViewById(R.id.imageView_search_criteria_icon)).setImageResource(row.getIcon());

        if (layoutResourceId == R.layout.list_item_search_criteria_drop_down) {
            ((Spinner) view.findViewById(R.id.spinner_search_criteria_input)).setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    setField(view, Integer.toString(position + 1), row.getTitle());
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });
        } else if (layoutResourceId == R.layout.list_item_search_criteria_text) {
            final AutoCompleteTextView input = (AutoCompleteTextView) view.findViewById(R.id.editText_search_criteria_input);
            input.setHint(row.getTitle());
            input.setThreshold(1);

            input.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                }

                @Override
                public void afterTextChanged(Editable editable) {
                    String inputText = input.getText().toString().trim();
                    if (!inputText.isEmpty()) {
                        setField(view, inputText, row.getTitle());
                    }
                }
            });

            switch (row.getIcon()) {
                case R.drawable.ic_clock:
                    input.setInputType(InputType.TYPE_CLASS_DATETIME);
                    input.setFocusable(false);
                    if (input.getHint().toString().equals("Departure Date")) {
                        input.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                activeDateDisplay = input;
                                showDatePickerDialog(System.currentTimeMillis() - 1000);
                            }
                        });
                    } else {
                        input.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                activeDateDisplay = input;
                                showDatePickerDialog(minDate.getTimeInMillis());
                            }
                        });
                    }
                    break;
                case R.drawable.ic_dollar_sign:
                    input.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
                    input.setFilters(new InputFilter[] {new InputFilter.LengthFilter(8)});
                    break;
                case R.drawable.ic_takeoff:
                case R.drawable.ic_landing:
                    input.setAdapter(new ArrayAdapter<>(context, android.R.layout.simple_dropdown_item_1line, view.getResources().getStringArray(R.array.airport_list)));
                    input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_AUTO_COMPLETE);
                    break;
                case R.drawable.ic_plane:
                    input.setAdapter(new ArrayAdapter<>(context, android.R.layout.simple_dropdown_item_1line, view.getResources().getStringArray(R.array.airline_list)));
                    input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_AUTO_COMPLETE);
                    break;
                case R.drawable.ic_seat:
                    input.setAdapter(new ArrayAdapter<>(context, android.R.layout.simple_dropdown_item_1line, view.getResources().getStringArray(R.array.class_list)));
                    input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_AUTO_COMPLETE);
                    break;
            }
        }

        return view;
    }

    @Override
    public int getCount() {
        return fullCriteriaList.size();
    }

    /**
     * Notifies search fragment that the criteria dataset has changed
     * @param setOptional True if advance options are togged on, else false
     */
    public void notifyDataSetChanged(boolean setOptional) {
        fullCriteriaList.clear();
        for (SearchCriteriaListViewEntry row : mandatoryCriteriaList) {
            fullCriteriaList.add(row.clone());
        }

        if (setOptional) {
            for (SearchCriteriaListViewEntry optionalRow : optionalCriteriaList) {
                fullCriteriaList.add(optionalRow.clone());
            }
        }

        this.notifyDataSetChanged();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        this.activeDateDisplay.setText(String.format(Locale.CANADA, "%04d-%02d-%02d", year, month + 1, dayOfMonth));

        // Make sure users can only pick return dates that are after the departure date
        if (this.activeDateDisplay.getHint().toString().equals("Departure Date")) {
            minDate.set(year, month, dayOfMonth);
        }

        this.activeDateDisplay = null;
    }

    /**
     * Initializes a date picker to the date passed in. 
     */
    private void showDatePickerDialog(long date) {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog dialog = new DatePickerDialog(this.getContext(), this, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        dialog.getDatePicker().setMinDate(date);
        dialog.show();
    }

    /**
     * Get the search criteria used to search for flights
     * @return search criteria
     */
    public static SearchCriteria getCriteria() {
        return criteria;
    }

    /**
     * Set the search criteria used to search for flights
     * @param newCriteria new set of search criterias to be assigned
     */
    public static void setCriteria(SearchCriteria newCriteria) {
        criteria = newCriteria;
    }

    /**
     * Maps the input to the correct search criteria based on row key
     * @param row row of the search criteria
     * @param inputText user input text
     * @param key key to map field
     */
    private void setField(View row, String inputText, String key) {
        if (row.getResources().getString(R.string.search_origin).equals(key)) {
            criteria.setOrigin(new Airport(inputText));
        } else if (row.getResources().getString(R.string.search_destination).equals(key)) {
            criteria.setDestination(new Airport(inputText));
        } else if (row.getResources().getString(R.string.search_departure_date).equals(key)) {
            criteria.setDepartureDate(SearchCriteriaHandler.parseDate(inputText));
        } else if (row.getResources().getString(R.string.search_return_date).equals(key)) {
            criteria.setReturnDate(SearchCriteriaHandler.parseDate(inputText));
        } else if (row.getResources().getString(R.string.search_num_passengers).equals(key)) {
            criteria.setNumTravellers(Integer.parseInt(inputText));
        } else if (row.getResources().getString(R.string.search_max_price).equals(key)) {
            criteria.setMaxPrice(Double.parseDouble(inputText));
        } else if (row.getResources().getString(R.string.search_airlines).equals(key)) {
            criteria.setPreferredAirlines(new Airline(inputText));
        } else if (row.getResources().getString(R.string.search_class).equals(key)) {
            criteria.setPreferredClass(FlightClassEnum.FIRST_CLASS);
        }
    }
}
