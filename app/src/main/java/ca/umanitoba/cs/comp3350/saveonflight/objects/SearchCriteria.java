package ca.umanitoba.cs.comp3350.saveonflight.objects;

/**
 * SearchCriteria.java
 * <p>
 * Object representing a search for flights.
 *
 * @author Andy Lun
 */

import android.view.View;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import ca.umanitoba.cs.comp3350.saveonflight.R;
import ca.umanitoba.cs.comp3350.saveonflight.business.AccessAirportsImpl;

import static ca.umanitoba.cs.comp3350.saveonflight.business.AccessAirportsImpl.airportExists;

public class SearchCriteria {
    private boolean returnTrip;
    private Airport origin;
    private Airport destination;
    private Date departureDate;
    private Date returnDate;
    private int numTravellers;
    private double maxPrice;
    private Airline preferredAirlines;
    private FlightClassEnum preferredClass;


    public SearchCriteria() {
    }

    public SearchCriteria(Airport origin, Airport destination, Date departureDate,
                          int numTravellers, double maxPrice, Airline preferredAirlines,
                          FlightClassEnum preferredClass) {
        this.origin = origin;
        this.destination = destination;
        this.departureDate = departureDate;
        this.numTravellers = numTravellers;
        this.maxPrice = maxPrice;
        this.preferredAirlines = preferredAirlines;
        this.preferredClass = preferredClass;

    }

    public Airport getOrigin() {
        return origin;
    }

    public void setOrigin(Airport origin) {
        this.origin = origin;
    }

    public Airport getDestination() {
        return destination;
    }

    public void setDestination(Airport destination) {
        this.destination = destination;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public int getNumTravellers() {
        return numTravellers;
    }

    public void setNumTravellers(int numTravellers) {
        this.numTravellers = numTravellers;
    }

    public double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public Airline getPreferredAirlines() {
        return preferredAirlines;
    }

    public void setPreferredAirlines(Airline preferredAirlines) {
        this.preferredAirlines = preferredAirlines;
    }

    public FlightClassEnum getPreferredClass() {
        return preferredClass;
    }

    public void setPreferredClass(FlightClassEnum preferredClass) {
        this.preferredClass = preferredClass;
    }


    public boolean isReturnTrip() {
        return this.returnTrip;
    }

    public void setReturnTrip(boolean isReturnTrip) {
        this.returnTrip = isReturnTrip;
    }

    public void setField(View row, String inputText, String title) {
        if (row.getResources().getString(R.string.search_origin).equals(title)) {
            setOrigin(new Airport(inputText));
        } else if (row.getResources().getString(R.string.search_destination).equals(title)) {
            setDestination(new Airport(inputText));
        } else if (row.getResources().getString(R.string.search_departure_date).equals(title)) {
            setDepartureDate(parseDate(inputText));
        } else if (row.getResources().getString(R.string.search_return_date).equals(title)) {
            setReturnDate(parseDate(inputText));
        } else if (row.getResources().getString(R.string.search_num_passengers).equals(title)) {
            setNumTravellers(Integer.parseInt(inputText));
        } else if (row.getResources().getString(R.string.search_max_price).equals(title)) {
            setMaxPrice(Double.parseDouble(inputText));
        } else if (row.getResources().getString(R.string.search_airlines).equals(title)) {
            setPreferredAirlines(new Airline(inputText, 0));
        } else if (row.getResources().getString(R.string.search_class).equals(title)) {
            setPreferredClass(FlightClassEnum.FIRST_CLASS);
        }
    }

    public void reverseFlightDirection() {
        Airport temp = getOrigin();
        setOrigin(getDestination());
        setDestination(temp);

        setDepartureDate(getReturnDate());
    }

    private Date parseDate(String date) {
        Date parsedDate = null;

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CANADA);
            parsedDate = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return parsedDate;
    }
}
