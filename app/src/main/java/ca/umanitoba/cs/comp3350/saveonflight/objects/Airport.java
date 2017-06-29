package ca.umanitoba.cs.comp3350.saveonflight.objects;

/**
 * Airport.java
 * <p>
 * Object mapped to the airport table DB
 *
 * @author Andy Lun
 */

public class Airport {
    private String airportCode;

    public Airport(String airportCode) {
        this.airportCode = airportCode;
    }

    public String getAirportCode() {
        return airportCode;
    }

    public void setAirportCode(String airportCode) {
        this.airportCode = airportCode;
    }

    public boolean equals(Object object) {
        boolean result = false;

        if (object instanceof Airport) {
            Airport other = (Airport) object;
            if (other.airportCode.equals(airportCode)) {
                result = true;
            }
        }

        return result;
    }

    public boolean contains(Object object) {
        boolean result = false;

        if (object instanceof Airport) {
            Airport airport = (Airport) object;
            if (airportCode.toLowerCase().contains(airport.getAirportCode().toLowerCase())) {
                result = true;
            }
        }
        return result;
    }
}
