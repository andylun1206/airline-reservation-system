package ca.umanitoba.cs.comp3350.saveonflight.presentation;

/**
 * ViewFlightsArrayAdapter.java
 * <p>
 * ArrayAdapter for ViewFlights. Used to populate a listview in view.
 *
 * @author Andy Lun
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import ca.umanitoba.cs.comp3350.saveonflight.R;
import ca.umanitoba.cs.comp3350.saveonflight.objects.ViewFlightsListViewEntry;

import java.util.ArrayList;
import java.util.Locale;

public class ViewFlightsArrayAdapter extends ArrayAdapter<ViewFlightsListViewEntry> {
    private final Context context;
    private final int layoutResourceId;
    private final ArrayList<ViewFlightsListViewEntry> flightList;

    public ViewFlightsArrayAdapter(Context context, int layoutResourceId, ArrayList<ViewFlightsListViewEntry> flightList) {
        super(context, layoutResourceId, flightList);
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.flightList = flightList;
    }

    @Override
    public View getView(final int position, final View convertView, final ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(layoutResourceId, parent, false);
        final ViewFlightsListViewEntry row = flightList.get(position);

        ((TextView) view.findViewById(R.id.textView_view_flight_time)).setText(row.getTime());
        ((TextView) view.findViewById(R.id.textView_view_flight_price)).setText(String.format(Locale.CANADA, "$%.2f", row.getPrice()));
        ((ImageView) view.findViewById(R.id.imageView_view_flight_airline)).setImageResource(row.getAirline());
        ((TextView) view.findViewById(R.id.textView_view_flight_flightid)).setText(row.getFlightId());
        ((TextView) view.findViewById(R.id.textView_view_flight_duration)).setText(row.getDuration());

        view.findViewById(R.id.button_view_flight_next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastHandler.toastShowShortText((MainActivity) convertView.getContext(),
                        String.format(Locale.CANADA, convertView.getContext().getString(R.string.toast_flight_picked), row.getFlightId()));
                ViewFlightsFragment.addChosenFlight(row.getFlightId());
                ViewFlightsFragment.navgiateNextStep();
            }
        });

        return view;
    }
}
