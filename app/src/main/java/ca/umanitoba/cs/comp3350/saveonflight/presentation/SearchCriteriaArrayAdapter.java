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
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
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
		final View view = inflater.inflate(layoutResourceId, parent, false);
		final SearchCriteriaListView row = criteriaListList.get(position);
		
		((ImageView) view.findViewById(R.id.imageView_search_criteria_icon)).setImageResource(row.getIcon());
		final EditText input = (EditText) view.findViewById(R.id.editText_search_criteria_input);
		input.setHint(row.getTitle());
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
