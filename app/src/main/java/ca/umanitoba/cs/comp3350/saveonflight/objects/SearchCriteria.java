package ca.umanitoba.cs.comp3350.saveonflight.objects;

/**
 * SearchCriteria.java
 * <p>
 * Object representing a search for flights.
 *
 * @author Andy Lun
 */

import android.view.View;
import ca.umanitoba.cs.comp3350.saveonflight.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class SearchCriteria {
    private Airport origin;
    private Airport destination;
    private Date departureDate;
    private Date returnDate;
    private int numTravellers;
    private double maxPrice;
    private Airline preferredAirline;
    private FlightClassEnum preferredClass;
    private boolean nonstop;
    private boolean refundable;

    public SearchCriteria() {
    }

    public SearchCriteria(Airport origin, Airport destination, Date departureDate, Date returnDate,
                          int numTravellers, double maxPrice, Airline preferredAirline,
                          FlightClassEnum preferredClass, boolean nonstop, boolean refundable) {
        this.origin = origin;
        this.destination = destination;
        this.departureDate = departureDate;
        this.returnDate = returnDate;
        this.numTravellers = numTravellers;
        this.maxPrice = maxPrice;
        this.preferredAirline = preferredAirline;
        this.preferredClass = preferredClass;
        this.nonstop = nonstop;
        this.refundable = refundable;
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

    public Airline getPreferredAirline() {
        return preferredAirline;
    }

    public void setPreferredAirline(Airline preferredAirline) {
        this.preferredAirline = preferredAirline;
    }

    public FlightClassEnum getPreferredClass() {
        return preferredClass;
    }

    public void setPreferredClass(FlightClassEnum preferredClass) {
        this.preferredClass = preferredClass;
    }

    public boolean isNonstop() {
        return nonstop;
    }

    public void setNonstop(boolean nonstop) {
        this.nonstop = nonstop;
    }

    public boolean isRefundable() {
        return refundable;
    }

    public void setRefundable(boolean refundable) {
        this.refundable = refundable;
    }

    public void setField(View row, String inputText, String title) {
        if (row.getResources().getString(R.string.search_origin).equals(title)) {
            setOrigin(new Airport(inputText));
        } else if (row.getResources().getString(R.string.search_destination).equals(title)) {
            setDestination(new Airport(inputText));
        } else if (row.getResources().getString(R.string.search_departure_date).equals(title)) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CANADA);
                setDepartureDate(sdf.parse(inputText));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else if (row.getResources().getString(R.string.search_return_date).equals(title)) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CANADA);
                setReturnDate(sdf.parse(inputText));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else if (row.getResources().getString(R.string.search_num_travellers).equals(title)) {
            setNumTravellers(Integer.parseInt(inputText));
        } else if (row.getResources().getString(R.string.search_max_price).equals(title)) {
            setMaxPrice(Double.parseDouble(inputText));
        } else if (row.getResources().getString(R.string.search_airlines).equals(title)) {
            setPreferredAirline(new Airline(inputText));
        } else if (row.getResources().getString(R.string.search_class).equals(title)) {
            setPreferredClass(FlightClassEnum.FIRST_CLASS);
        }
    }
}
