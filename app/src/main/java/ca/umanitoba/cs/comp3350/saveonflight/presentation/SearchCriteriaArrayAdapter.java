package ca.umanitoba.cs.comp3350.saveonflight.presentation;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Context;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import ca.umanitoba.cs.comp3350.saveonflight.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class SearchCriteriaArrayAdapter extends ArrayAdapter<SearchCriteria> implements OnDateSetListener {
	private final Context context;
	private final int layoutResourceId;
	private final ArrayList<SearchCriteria> criterias;
	
	private EditText activeDateDisplay;
	
	public SearchCriteriaArrayAdapter(Context context, int layoutResourceId, ArrayList<SearchCriteria> criterias) {
		super(context, layoutResourceId, criterias);
		this.context = context;
		this.layoutResourceId = layoutResourceId;
		this.criterias = criterias;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View row = inflater.inflate(layoutResourceId, parent, false);
		SearchCriteria criteria = criterias.get(position);
		((ImageView) row.findViewById(R.id.imageView_search_criteria_icon)).setImageResource(criteria.getIcon());
		final EditText input = (EditText) row.findViewById(R.id.editText_search_criteria_input);
		input.setHint(criteria.getTitle());
		
		switch(criteria.getIcon()) {
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
}
