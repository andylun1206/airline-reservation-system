package ca.umanitoba.cs.comp3350.saveonflight.persistence;

import java.util.ArrayList;

import ca.umanitoba.cs.comp3350.saveonflight.objects.Airport;

/**
 * AirportTable.java
 * <p>
 * Database table for Airports.
 *
 * @author Long Yu
 */

public class AirportTable implements AirportAccess {
    private static ArrayList<Airport> airports = null;

    public AirportTable() {
    }

    public void initialize(String dbPath) {
        if (airports == null) {
            airports = new ArrayList<Airport>();
            airports.add(new Airport("Vancouver YVR"));
            airports.add(new Airport("Winnipeg YWG"));
            airports.add(new Airport("Calgary YYC"));
            airports.add(new Airport("Toronto YYZ"));
            airports.add(new Airport("Montréal YUL"));
            airports.add(new Airport("Ottawa YOW"));
            airports.add(new Airport("Québec YQB"));
        }
    }

    public ArrayList<Airport> getAirports() {
        return airports;
    }

    public Airport findAirport(String city) {
        Airport result = null;
        for (Airport airport : airports) {
            if (airport.getAirportCode().toLowerCase().contains(city.toLowerCase())) {
                result = airport;
            }
        }
        return result;
    }
    public void close()
    {
        System.out.println("Closed  database " );
    }

}
