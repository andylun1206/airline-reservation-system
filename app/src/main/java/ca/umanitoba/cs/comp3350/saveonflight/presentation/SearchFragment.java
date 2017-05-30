package ca.umanitoba.cs.comp3350.saveonflight.presentation;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import ca.umanitoba.cs.comp3350.saveonflight.R;

import java.util.Calendar;
import java.util.Locale;

public class SearchFragment extends Fragment implements OnDateSetListener{
	private EditText departureDate;
	private EditText arrivalDate;
	private Button advancedSetting;
	
	private EditText activeDateDisplay;
	
	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
	                         @Nullable Bundle saveInstanceState) {
		return inflater.inflate(R.layout.fragment_search_return, container, false);
	}
	
	@Override
	public void onViewCreated(View view, @Nullable Bundle saveInstanceState) {
		super.onViewCreated(view, saveInstanceState);
		getActivity().setTitle(getString(R.string.title_activity_search));
		
		this.departureDate = (EditText) getView().findViewById(R.id.editText_search_return_departure_date);
		this.departureDate.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				activeDateDisplay = departureDate;
				showDatePickerDialog();
			}
		});
		
		this.arrivalDate = (EditText) getView().findViewById(R.id.editText_search_return_arrival_date);
		this.arrivalDate.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				activeDateDisplay = arrivalDate;
				showDatePickerDialog();
			}
		});
		
		this.advancedSetting = (Button) getView().findViewById(R.id.button_search_return_advance);
		this.advancedSetting.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				View advancedSettingView = getView().findViewById(R.id.search_return_advanced_settings_checkboxes);
				
				if (advancedSettingView.getVisibility() == View.GONE) {
					getView().findViewById(R.id.search_return_advanced_settings_airline).setVisibility(View.VISIBLE);
					getView().findViewById(R.id.search_return_advanced_settings_class).setVisibility(View.VISIBLE);
					advancedSettingView.setVisibility(View.VISIBLE);
				} else {
					getView().findViewById(R.id.search_return_advanced_settings_airline).setVisibility(View.GONE);
					getView().findViewById(R.id.search_return_advanced_settings_class).setVisibility(View.GONE);
					advancedSettingView.setVisibility(View.GONE);
				}
			}
		});
	}
	
	private void showDatePickerDialog() {
		Calendar calendar = Calendar.getInstance();
		DatePickerDialog dialog = new DatePickerDialog(this.getContext(), this, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
		dialog.show();
	}
	
	@Override
	public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
		this.activeDateDisplay.setText(String.format(Locale.CANADA, "%04d-%02d-%02d", year, month + 1, dayOfMonth));
		this.activeDateDisplay = null;
	}
	
}
