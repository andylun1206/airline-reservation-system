package ca.umanitoba.cs.comp3350.saveonflight.objects;

public class Flight {
    private int flightID;
    private Airline airline;
    private String date;
    private DepartsAndArrives da;

    public Flight(int flightID, String date, Airline airline, DepartsAndArrives da) {
        this.flightID = flightID;
        this.date = date;
        this.airline = airline;
        this.da = da;
    }

    public void setFlightID(int flightID) {
        this.flightID = flightID;
    }

    public Airline getAirline() {
        return airline;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public DepartsAndArrives getDa() {
        return da;
    }

    public void setDa(DepartsAndArrives da) {
        this.da = da;
    }

    public int getFlightID() {
        return flightID;
    }

    public String toString() {
        return "Flight: " + flightID + " " + date + " " + airline + " " + da;
    }

    public boolean equals(Object object) {
        boolean result = false;

        if (object instanceof Flight) {
            Flight other = (Flight) object;
            if (other.flightID == flightID) {
                result = true;
            }
        }

        return result;
    }
}
