package ca.umanitoba.cs.comp3350.saveonflight.presentation;

/**
 * SearchCriteriaArrayAdapter.java
 *
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
import android.widget.*;
import ca.umanitoba.cs.comp3350.saveonflight.R;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Airline;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Airport;
import ca.umanitoba.cs.comp3350.saveonflight.objects.FlightClassEnum;
import ca.umanitoba.cs.comp3350.saveonflight.objects.SearchCriteria;
import ca.umanitoba.cs.comp3350.saveonflight.objects.SearchCriteriaListView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class SearchCriteriaArrayAdapter extends ArrayAdapter<SearchCriteriaListView> implements OnDateSetListener {
	private final Context context;
	private final int layoutResourceId;
	private ArrayList<SearchCriteriaListView> mandatoryCriteriaList;
	private ArrayList<SearchCriteriaListView> optionalCriteriaList;
	private ArrayList<SearchCriteriaListView> fullCriteriaList;
	private static SearchCriteria criteria;
	
	private EditText activeDateDisplay;
	
	public SearchCriteriaArrayAdapter(Context context, int layoutResourceId,
	                                  ArrayList<SearchCriteriaListView> mandatoryCriteriaList,
	                                  ArrayList<SearchCriteriaListView> optionalCriteriaList) {
		super(context, layoutResourceId, mandatoryCriteriaList);
		this.context = context;
		this.layoutResourceId = layoutResourceId;
		this.mandatoryCriteriaList = mandatoryCriteriaList;
		this.optionalCriteriaList = optionalCriteriaList;
		this.fullCriteriaList = new ArrayList<>();
		
		criteria = new SearchCriteria();
	}
	
	@Override
	public View getView(final int position, final View convertView, final ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		final View view = inflater.inflate(layoutResourceId, parent, false);
		final SearchCriteriaListView row = fullCriteriaList.get(position);
		
		((ImageView) view.findViewById(R.id.imageView_search_criteria_icon)).setImageResource(row.getIcon());
		final EditText input = (EditText) view.findViewById(R.id.editText_search_criteria_input);
		input.setHint(row.getTitle());
		setDefaults(input, row.getTitle());
		
		input.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) { }

			@Override
			public void onTextChanged(CharSequence charSequence, int start, int before, int count) { }

			@Override
			public void afterTextChanged(Editable editable) {
				String inputText = input.getText().toString().trim();
				if (!inputText.isEmpty()) {
					criteria.setField(view, inputText, row.getTitle());
				}
			}
		});

		switch(row.getIcon()) {
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
		
		return view;
	}
	
	@Override
	public int getCount() {
		return fullCriteriaList.size();
	}
	
	public void notifyDataSetChanged(boolean setOptional) {
		fullCriteriaList.clear();
		for (SearchCriteriaListView row : mandatoryCriteriaList) {
			fullCriteriaList.add(row.clone());
		}
		
		if (setOptional) {
			for (SearchCriteriaListView optionalRow : optionalCriteriaList) {
				fullCriteriaList.add(optionalRow.clone());
			}
		}
		
		this.notifyDataSetChanged();
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
	
	public static SearchCriteria getCriteria() {
		return criteria;
	}
	
	public static void setCriteria(SearchCriteria newCriteria) {
		criteria = newCriteria;
	}
	
	public boolean verifyCriteria(Activity activity) {
		boolean isValid = true;
		
		if (criteria.getOrigin() == null || criteria.getOrigin().toString().trim().isEmpty()) {
			isValid = missingRequiredField(activity, R.string.search_origin);
		} else if (criteria.getDestination() == null || criteria.getDestination().toString().trim().isEmpty()) {
			isValid = missingRequiredField(activity, R.string.search_destination);
		} else if (criteria.getDepartureDate() == null || criteria.getDepartureDate().toString().trim().isEmpty()) {
			isValid = missingRequiredField(activity, R.string.search_departure_date);
		/*} else if ((mandatoryCriteriaList == null || mandatoryCriteriaList.size() == 5) &&
				(criteria.getReturnDate() == null || criteria.getReturnDate().toString().trim().isEmpty())) {
			isValid = missingRequiredField(activity, R.string.search_return_date);*/
		} else if (criteria.getNumTravellers() == 0) {
			isValid = missingRequiredField(activity, R.string.search_num_travellers);
		}
			
		return isValid;
	}
	
	private boolean missingRequiredField(Activity activity, int string) {
		ToastActivity.toastMandatoryField(activity, activity.getString(string));
		return false;
	}
	
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
