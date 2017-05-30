package ca.umanitoba.cs.comp3350.saveonflight.objects;

public class Flight {
    private String flightID;
    private Airline airline;
    private String date;
    private DepartsAndArrives da;

    public Flight(String flightID, String date, Airline airline, DepartsAndArrives da) {
        this.flightID = flightID;
        this.date = date;
        this.airline=airline;
        this.da = da;
    }

    public Flight(String flightID, String date, String airline, String depart, String arrives) {
        this.flightID = flightID;
        this.date = date;
        this.airline = new Airline(airline);
        this.da = new DepartsAndArrives(depart,arrives);
    }

    public void setFlightID(String flightID) {
        this.flightID = flightID;
    }

    public Airline getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline.setName(airline);
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

    public String getFlightID() {
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
