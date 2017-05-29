package ca.umanitoba.cs.comp3350.saveonflight.objects;

public class Flight {
    private String flightID;
    private Airline airline;
    private String date;
    private Airport depart;
    private Airport arrival;

    public static enum TravelClass {
        ECONOMY, BUSINESS, FIRST_CLASS
    }

    public Flight(String flightID, String date, String airline, String depart, String arrival) {
        this.flightID = flightID;
        this.date = date;
        this.airline = new Airline(airline);
        this.depart = new Airport(depart);
        this.arrival = new Airport(arrival);
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

    public Airport getDepart() {
        return depart;
    }

    public void setDepart(Airport depart) {
        this.depart = depart;
    }

    public Airport getArrival() {
        return arrival;
    }

    public void setArrival(Airport arrival) {
        this.arrival = arrival;
    }

    public String getFlightID() {
        return flightID;
    }

    public String toString() {
        return "Flight: " + flightID + " " + date + " " + airline + " " + "from " + depart + " to " + arrival;
    }

    public boolean equals(Object object) {
        boolean result = false;

        if (object instanceof Flight) {
            Flight other = (Flight) object;
            if (other.flightID.equals(flightID)) {
                result = true;
            }
        }

        return result;
    }
}
