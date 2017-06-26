package ca.umanitoba.cs.comp3350.saveonflight.presentation;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

import ca.umanitoba.cs.comp3350.saveonflight.R;
import ca.umanitoba.cs.comp3350.saveonflight.objects.ViewFlightsListViewEntry;

public class ViewBookedFlightArrayAdapter extends ArrayAdapter<ViewFlightsListViewEntry> {
    private final Context context;
    private final int layoutResourceId;
    private final ArrayList<ViewFlightsListViewEntry> flightList;

    public ViewBookedFlightArrayAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull ArrayList<ViewFlightsListViewEntry> flights) {
        super(context, resource, flights);
        this.context = context;
        this.layoutResourceId = resource;
        this.flightList = flights;
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

        return view;
    }
}

