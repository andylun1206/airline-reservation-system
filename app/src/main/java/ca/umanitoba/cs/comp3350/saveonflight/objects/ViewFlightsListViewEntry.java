package ca.umanitoba.cs.comp3350.saveonflight.objects;

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

    public void setTime(String time) {
        this.time = time;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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

    public void setFlightId(String flightId) {
        this.flightId = flightId;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
