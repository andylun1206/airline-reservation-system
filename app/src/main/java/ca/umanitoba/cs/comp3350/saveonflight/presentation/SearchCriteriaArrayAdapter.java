package ca.umanitoba.cs.comp3350.saveonflight.presentation;

/**
 * SearchCriteriaArrayAdapter.java
 *
 * ArrayAdapter for SearchCriteria. Used to populate a listview in search.
 *
 * @author Andy Lun
 */

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Context;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import ca.umanitoba.cs.comp3350.saveonflight.R;
import ca.umanitoba.cs.comp3350.saveonflight.objects.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class SearchCriteriaArrayAdapter extends ArrayAdapter<SearchCriteriaListView> implements OnDateSetListener {
	private final Context context;
	private final int layoutResourceId;
	private final ArrayList<SearchCriteriaListView> criteriaListList;
	private static SearchCriteria criteria;
	
	private EditText activeDateDisplay;
	
	public SearchCriteriaArrayAdapter(Context context, int layoutResourceId, ArrayList<SearchCriteriaListView> criteriaListList) {
		super(context, layoutResourceId, criteriaListList);
		this.context = context;
		this.layoutResourceId = layoutResourceId;
		this.criteriaListList = criteriaListList;
		
		criteria = new SearchCriteria();
	}
	
	@Override
	public View getView(final int position, final View convertView, final ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		final View row = inflater.inflate(layoutResourceId, parent, false);
		final SearchCriteriaListView criteriaList = criteriaListList.get(position);
		
		((ImageView) row.findViewById(R.id.imageView_search_criteria_icon)).setImageResource(criteriaList.getIcon());
		final EditText input = (EditText) row.findViewById(R.id.editText_search_criteria_input);
		input.setHint(criteriaList.getTitle());
		input.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) { }
			
			@Override
			public void onTextChanged(CharSequence charSequence, int start, int before, int count) { }
			
			@Override
			public void afterTextChanged(Editable editable) {
				String inputText = input.getText().toString().trim();
				String title = criteriaList.getTitle();
				
				if (!inputText.isEmpty()) {
					if (row.getResources().getString(R.string.search_origin).equals(title)) {
						criteria.setOrigin(new Airport(inputText));
					} else if (row.getResources().getString(R.string.search_destination).equals(title)) {
						criteria.setDestination(new Airport(inputText));
					} else if (row.getResources().getString(R.string.search_departure_date).equals(title)) {
						try {
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CANADA);
							criteria.setDepartureDate(sdf.parse(inputText));
						} catch (ParseException e) {
							e.printStackTrace();
						}
					} else if (row.getResources().getString(R.string.search_return_date).equals(title)) {
						try {
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CANADA);
							criteria.setReturnDate(sdf.parse(inputText));
						} catch (ParseException e) {
							e.printStackTrace();
						}
					} else if (row.getResources().getString(R.string.search_num_travellers).equals(title)) {
						criteria.setNumTravellers(Integer.parseInt(inputText));
					} else if (row.getResources().getString(R.string.search_max_price).equals(title)) {
						criteria.setMaxPrice(Double.parseDouble(inputText));
					} else if (row.getResources().getString(R.string.search_airlines).equals(title)) {
						criteria.setPreferredAirlines(new Airline(inputText));
					} else if (row.getResources().getString(R.string.search_class).equals(title)) {
						criteria.setPreferredClass(Flight.FlightClass.FIRST_CLASS);
					}
				}
			}
		});
		
		switch(criteriaList.getIcon()) {
			case R.drawable.ic_clock:
				input.setInputType(InputType.TYPE_CLASS_DATETIME);
				input.setFocusable(false);
				input.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						activeDateDisplay = input;
						showDatePickerDialog();
					}
				});
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
		
		return row;
	}
	
	@Override
	public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
		this.activeDateDisplay.setText(String.format(Locale.CANADA, "%04d-%02d-%02d", year, month + 1, dayOfMonth));
		this.activeDateDisplay = null;
	}
	
	private void showDatePickerDialog() {
		Calendar calendar = Calendar.getInstance();
		DatePickerDialog dialog = new DatePickerDialog(this.getContext(), this, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
		dialog.show();
	}
	
	public static SearchCriteria getCriteria() { return criteria; }
	public static void setCriteria(SearchCriteria newCriteria) { criteria = newCriteria; }
	public static void resetCriteria() { criteria = new SearchCriteria(); }
	
}
