package ca.umanitoba.cs.comp3350.saveonflight.presentation;

/**
 * MainActivity.java
 * <p>
 * Activity for the home, search, and view flights screens of the application.
 *
 * @author Andy Lun
 */

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.NavigationView.OnNavigationItemSelectedListener;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.util.ArrayList;
import java.util.List;

import ca.umanitoba.cs.comp3350.saveonflight.R;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Flight;
import ca.umanitoba.cs.comp3350.saveonflight.presentation.SearchFragment.ViewFlightsListener;

public class MainActivity extends AppCompatActivity
        implements OnNavigationItemSelectedListener, ViewFlightsListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            }
        };
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        final NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View headerView = navigationView.getHeaderView(0);

        headerView.findViewById(R.id.button_header_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastHandler.toastComingSoon(MainActivity.this, getString(R.string.common_login));
            }
        });

        headerView.findViewById(R.id.button_header_signup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastHandler.toastComingSoon(MainActivity.this, getString(R.string.common_signup));
            }
        });

        displaySelectedScreen(R.id.nav_home);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            Fragment f = getVisibleFragment();

            if (f instanceof ViewFlightsFragment) {
                displaySelectedScreen(R.id.nav_search);
            } else if (f instanceof SearchFragment) {
                displaySelectedScreen(R.id.nav_home);
            } else if (f instanceof PaymentFragment) {
                displaySelectedScreen(R.id.nav_home); // TODO: change this to go back to the view flights screen (after it is fully implemented)
            } else {
                super.onBackPressed();
            }
        }
    }

    /**
     * Returns the Fragment currently defining the Activity's UI.
     *
     * @return the Fragment currently defining the Activity's UI
     */
    private Fragment getVisibleFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        List<Fragment> fragments = fragmentManager.getFragments();
        if (fragments != null) {
            for (Fragment f : fragments) {
                if (f != null && f.isVisible()) {
                    return f;
                }
            }
        }
        return null;
    }

    /**
     * Switches fragments based on the navigation drawer item selected.
     *
     * @param itemId Id of drawer item selected
     */
    private void displaySelectedScreen(int itemId) {
        Fragment fragment = null;

        switch (itemId) {
            case R.id.nav_home:
                fragment = new HomeFragment();
                break;
            case R.id.nav_search:
                fragment = new SearchFragment();
                break;
            default:
                fragment = new HomeFragment();
                break;
        }

        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content_frame, fragment)
                    .commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        displaySelectedScreen(item.getItemId());
        return true;
    }

    /**
     * Switch context from searching to viewing flights
     *
     * @param flights result of search
     */
    @Override
    public void viewFlights(ArrayList<Flight> flights) {
        Fragment viewFragment = new ViewFlightsFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList("flights", flights);
        viewFragment.setArguments(args);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_frame, viewFragment)
                .commit();
    }
}
