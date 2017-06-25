package ca.umanitoba.cs.comp3350.saveonflight.presentation;

/**
 * SearchCriteriaArrayAdapter.java
 * <p>
 * ArrayAdapter for SearchCriteria. Used to populate a listview in search.
 *
 * @author Andy Lun
 */

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Context;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import ca.umanitoba.cs.comp3350.saveonflight.R;
import ca.umanitoba.cs.comp3350.saveonflight.objects.SearchCriteria;
import ca.umanitoba.cs.comp3350.saveonflight.objects.SearchCriteriaListViewEntry;

public class SearchCriteriaArrayAdapter extends ArrayAdapter<SearchCriteriaListViewEntry> implements OnDateSetListener {
    private final Context context;
    private final int layoutResourceId;
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
        this.layoutResourceId = layoutResourceId;
        this.mandatoryCriteriaList = mandatoryCriteriaList;
        this.optionalCriteriaList = optionalCriteriaList;
        this.fullCriteriaList = new ArrayList<>();

        minDate = new GregorianCalendar();

        criteria = new SearchCriteria();
    }

    @Override
    public View getView(final int position, final View convertView, final ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View view = inflater.inflate(layoutResourceId, parent, false);
        final SearchCriteriaListViewEntry row = fullCriteriaList.get(position);

        ((ImageView) view.findViewById(R.id.imageView_search_criteria_icon)).setImageResource(row.getIcon());
        final EditText input = (EditText) view.findViewById(R.id.editText_search_criteria_input);
        input.setHint(row.getTitle());
//		setDefaults(input, row.getTitle());

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
                    criteria.setField(view, inputText, row.getTitle());
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
                            showDatePickerDialog(null);
                        }
                    });
                } else {
                    input.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            activeDateDisplay = input;
                            showDatePickerDialog(minDate);
                        }
                    });
                }

                break;
            case R.drawable.ic_person:
                input.setInputType(InputType.TYPE_CLASS_NUMBER);
                break;
            case R.drawable.ic_dollar_sign:
                input.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
                break;
            default:
                input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_AUTO_COMPLETE);
                break;
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
     * Initializes a date picker to the date passed in. If no date passed in, sets the minimum date to
     * the current date.
     */
    private void showDatePickerDialog(Calendar minDate) {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog dialog = new DatePickerDialog(this.getContext(), this, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        if (minDate == null) {
            dialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        } else {
            dialog.getDatePicker().setMinDate(minDate.getTimeInMillis());
        }
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
     * Verifies that the set of criterias match the set requirements
     * @param activity current activity
     * @return True if the requirements are met, else false
     */
    public boolean verifyCriteria(Activity activity) {
        boolean isValid = true;

        if (criteria.getOrigin() == null || criteria.getOrigin().toString().trim().isEmpty()) {
            isValid = missingRequiredField(activity, R.string.search_origin);
        } else if (criteria.getDestination() == null || criteria.getDestination().toString().trim().isEmpty()) {
            isValid = missingRequiredField(activity, R.string.search_destination);
        } else if (criteria.getDepartureDate() == null || criteria.getDepartureDate().toString().trim().isEmpty()) {
            isValid = missingRequiredField(activity, R.string.search_departure_date);
        } else if (criteria.getNumTravellers() == 0) {
            isValid = missingRequiredField(activity, R.string.search_num_travellers);
        }

        return isValid;
    }

    /**
     * Generates a toast message to notify the user that a field is mandatory
     * @param activity current activity
     * @param field field name
     * @return false
     */
    private boolean missingRequiredField(Activity activity, int field) {
        ToastHandler.toastMandatoryField(activity, activity.getString(field));
        return false;
    }

    /**
     * Set default text for marker
     * @param input input field
     * @param title row hint
     */
    private void setDefaults(final EditText input, String title) {

        if (title.equals("Origin")) {
            input.setText("Winnipeg");
        } else if (title.equals("Destination")) {
            input.setText("Vancouver");
        } else if (title.equals("Departure Date")) {
            input.setText("2017-11-11");
        } else if (title.equals("Return Date")) {
            input.setText("2017-11-11");
        } else if (title.equals("Number of Travellers")) {
            input.setText("1");
        }
    }
}
