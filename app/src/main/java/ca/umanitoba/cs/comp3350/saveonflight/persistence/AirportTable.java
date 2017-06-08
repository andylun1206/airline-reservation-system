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

public class AirportTable implements DataAccessStub<Airport> {
    private String dbName;
    private static ArrayList<Airport> airports = null;

    public AirportTable(String dbName) {
        this.dbName = dbName;
    }

    public void initialize() {

        airports = new ArrayList<Airport>();
        airports.add(new Airport("Vancouver YVR"));
        airports.add(new Airport("Winnipeg YWG"));
        airports.add(new Airport("Calgary YYC"));
        airports.add(new Airport("Toronto YYZ"));
        airports.add(new Airport("Montréal YUL"));
        airports.add(new Airport("Ottawa YOW"));
        airports.add(new Airport("Québec YQB"));

    }

    public static ArrayList<Airport> getAirports() {
        return airports;
    }

    public static Airport findAirport(String city) {
        Airport result = null;
        for (Airport airport : airports) {
            if (airport.getAirportCode().toLowerCase().contains(city.toLowerCase())) {
                result = airport;
            }
        }
        return result;
    }

    public boolean add(Airport airport) {
        return airports.add(airport);
    }

    public boolean update(Airport airport) {
        boolean isUpdated = false;
        String code = airport.getAirportCode();
        int index = 0;
        int changes = 0;
        Airport temp;
        for (int i = 0; i < airports.size(); i++) {
            temp = airports.get(i);
            if (temp.getAirportCode().equals(code)) {
                changes++;
                index = i;
            }
        }
        if (changes != 0) {
            airports.get(index).setAirportCode(code);
            isUpdated = true;
        }
        return isUpdated;
    }

    public boolean remove(Airport airport) {
        int index;
        index = airports.indexOf(airport);
        if (index >= 0) {
            airports.remove(index);
            return true;
        }
        return false;
    }

}