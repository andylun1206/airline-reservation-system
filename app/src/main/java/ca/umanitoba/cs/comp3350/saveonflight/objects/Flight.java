package ca.umanitoba.cs.comp3350.saveonflight.objects;

import java.util.Date;

public class Flight {
    private String flightID;
    private Airline airline;
    private Date date;
    private Airport origin;
    private Airport destination;
    private double price;
    private int capacity;
    private int seatsTaken;
    private FlightClass flightClass;

    public enum FlightClass {
        ECONOMY, BUSINESS, FIRST_CLASS;
    }

    public Flight(String flightID, Date date, Airline airline, Airport origin, Airport destination,
                  double price, int capacity) {
        this(flightID, date, airline, origin, destination, price, capacity, 0, FlightClass.ECONOMY);
    }

    public Flight(String flightID, Date date, Airline airline, Airport origin, Airport destination,
                  double price, int capacity, int seatsTaken, FlightClass flightClass) {
        this.flightID = flightID;
        this.date = date;
        this.airline = airline;
        this.origin = origin;
        this.destination = destination;
        this.price = price;
        this.capacity = capacity;
        this.seatsTaken = seatsTaken;
        this.flightClass = flightClass;
    }

    public boolean hasSpace() {
        return capacity > seatsTaken;
    }

    public int seatsRemaining() {
        return capacity - seatsTaken;
    }

    public boolean sellSeats(int numSold) {
        boolean result = false;

        if (seatsRemaining() >= numSold) {
            seatsTaken += numSold;
            result = true;
        } else {
            System.out.println("Trying to sell more seats than are available");
        }

        return result;
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

    public Date getDate() {
        return date;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getSeatsTaken() {
        return seatsTaken;
    }

    public void setSeatsTaken(int seatsTaken) {
        this.seatsTaken = seatsTaken;
    }

    public FlightClass getFlightClass() {
        return flightClass;
    }

    public void setFlightClass(FlightClass flightClass) {
        this.flightClass = flightClass;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getFlightID() {
        return flightID;
    }

    public String toString() {
        return "Flight: " + flightID + " " + date + " " + airline + " " + "from " + origin + " to " + destination;
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
