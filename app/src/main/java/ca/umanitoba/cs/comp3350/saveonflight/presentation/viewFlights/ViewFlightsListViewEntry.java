package ca.umanitoba.cs.comp3350.saveonflight.presentation.viewFlights;

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
    private int airline;
    private String flightId;
    private String duration;

    public ViewFlightsListViewEntry(String time, double price, int airline, String flightId, String duration) {
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

    public int getAirline() {
        return airline;
    }

    public void setAirline(int airline) {
        this.airline = airline;
    }

    public String getFlightId() {
        return flightId;
    }

    public String getDuration() {
        return duration;
    }
}
