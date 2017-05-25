package ca.umanitoba.cs.comp3350.saveonflight.objects;

public class Airport {
    private String AirportCode;

    public Airport(String airportCode) {
        AirportCode = airportCode;
    }

    public String getAirportCode() {
        return AirportCode;
    }

    public void setAirportCode(String airportCode) {
        AirportCode = airportCode;
    }

    public String toString() {
        return "Airport{" + "AirportCode='" + AirportCode + '\'' + '}';
    }

    public boolean equals(Object object) {
        boolean result = false;
        Airport a;

        if (object instanceof Airport) {

            Airport other = (Airport) object;

            if (other.AirportCode.equals(AirportCode)) {
                result = true;
            }
        }

        return result;
    }
}
