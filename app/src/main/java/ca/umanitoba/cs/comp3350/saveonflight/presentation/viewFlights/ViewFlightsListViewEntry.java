package ca.umanitoba.cs.comp3350.saveonflight.presentation.viewFlights;

import ca.umanitoba.cs.comp3350.saveonflight.objects.Airline;

/**
 * ViewFlightsListViewEntry.java
 * <p>
 * Object representing an entry in the ListView on the view flights page.
 *
 * @author Andy Lun
 */

public class ViewFlightsListViewEntry {
    private String time;
    private double price;
    private Airline airline;
    private String flightId;
    private String duration;

    public ViewFlightsListViewEntry(String time, double price, Airline airline, String flightId, String duration) {
        this.time = time;
        this.price = price;
        this.airline = airline;
        this.flightId = flightId;
        this.duration = duration;
    }

    public String getTime() {
        return time;
    }

    public double getPrice() {
        return price;
    }

    public Airline getAirline() {
        return airline;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
    }

    public String getFlightId() {
        return flightId;
    }

    public String getDuration() {
        return duration;
    }
}
