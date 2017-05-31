package ca.umanitoba.cs.comp3350.saveonflight.objects;

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

    public String toString() {
        return airportCode;
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
}
