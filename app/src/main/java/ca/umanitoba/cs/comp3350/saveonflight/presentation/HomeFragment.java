package ca.umanitoba.cs.comp3350.saveonflight.presentation;

/**
 * HomeFragment.java
 * <p>
 * Fragment for the home page of the application.
 *
 * @author Andy Lun
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ca.umanitoba.cs.comp3350.saveonflight.R;
import ca.umanitoba.cs.comp3350.saveonflight.presentation.searchFlights.SearchFragment;
import ca.umanitoba.cs.comp3350.saveonflight.presentation.viewBookedFlights.ViewBookedFlightFragment;

public class HomeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle saveInstanceState) {
        if (container != null) {
            container.removeAllViews();
        }
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle saveInstanceState) {
        super.onViewCreated(view, saveInstanceState);
        getActivity().setTitle(getString(R.string.app_name));
        ((NavigationView) getActivity().findViewById(R.id.nav_view)).setCheckedItem(R.id.nav_home);

        ((TextView) view.findViewById(R.id.textView_home_greeting)).setText(getString(R.string.home_greeting, "Guest"));

        view.findViewById(R.id.button_home_search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.content_frame, new SearchFragment());
                ft.commit();

                ((NavigationView) getActivity().findViewById(R.id.nav_view)).setCheckedItem(R.id.nav_search);
            }
        });

        view.findViewById(R.id.button_home_view_bookedflight).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.content_frame, new ViewBookedFlightFragment());
                ft.commit();

                ((NavigationView) getActivity().findViewById(R.id.nav_view)).setCheckedItem(R.id.nav_bookedflights);
            }
        });
    }
}
