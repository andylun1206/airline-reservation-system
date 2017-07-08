package ca.umanitoba.cs.comp3350.saveonflight.objects;

/**
 * SearchCriteria.java
 * <p>
 * Object representing a search for flights.
 *
 * @author Andy Lun
 */

import java.util.Date;

public class SearchCriteria {
    private boolean returnTrip;
    private Airport origin;
    private Airport destination;
    private Date departureDate;
    private Date returnDate;
    private double maxPrice;
    private Airline preferredAirlines;
    private FlightClassEnum preferredClass;

    public SearchCriteria() {
    }

    public int getPreferredClassInt() {
        return preferredClass.ordinal();
    }

    public Airport getOrigin() {
        return origin;
    }
    public String getOriginString() {
        return origin.getAirportCode();
    }

    public void setOrigin(Airport origin) {
        this.origin = origin;
    }

    public Airport getDestination() {
        return destination;
    }
    public String getDestinationString() {return destination.getAirportCode();}

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

    public double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public Airline getPreferredAirline() {
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
}
