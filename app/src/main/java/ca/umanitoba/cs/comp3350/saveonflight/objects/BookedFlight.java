package ca.umanitoba.cs.comp3350.saveonflight.objects;

/**
 * BookedFlight.java
 * <p>
 * Object mapped to the BookedFlight table in the database.
 */

public class BookedFlight {
    private Traveller traveller;
    private Flight flight;

    public BookedFlight(Traveller traveller, Flight flight) {
        this.traveller = traveller;
        this.flight = flight;
    }

    public Traveller getTraveller() {
        return traveller;
    }

    public void setTraveller(Traveller traveller) {
        this.traveller = traveller;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public boolean equals(Object object) {
        boolean result = false;

        if (object instanceof BookedFlight) {
            BookedFlight other = (BookedFlight) object;
            if (other.traveller.equals(traveller) && other.flight.equals(flight)) {
                result = true;
            }
        }

        return result;
    }
}
