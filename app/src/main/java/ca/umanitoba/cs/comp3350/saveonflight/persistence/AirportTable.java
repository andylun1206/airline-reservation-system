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

public class AirportTable implements DataAccess<Airport> {
    private static ArrayList<Airport> airports = null;

    public AirportTable() {
    }

    public void initialize() {
        if (airports == null) {
            airports = new ArrayList<Airport>();
            airports.add(new Airport("YVR","Vancouver"));
            airports.add(new Airport("YWG", "Winnipeg"));
            airports.add(new Airport("YYC","Calgary"));
            airports.add(new Airport("YYZ","Toronto"));
            airports.add(new Airport("YUL","Montréal"));
            airports.add(new Airport("YOW","Ottawa"));
            airports.add(new Airport("YQB","Québec"));
        }
    }

    public static ArrayList<Airport> getAirports() {
        return airports;
    }

    public static Airport findAirport(String city) {
        Airport result = null;
        for (Airport airport : airports) {
            if (airport.getCode().toLowerCase().contains(city.toLowerCase())) {
                result = airport;
            }
        }
        return result;
    }

    public boolean add(Airport airport) {
        boolean result = true;
        if (airport != null && !airport.getCode().isEmpty()) {
            for (Airport airport1 : airports) {
                if (airport.equals(airport1))
                    result = false;
            }
            if (result)
                result = airports.add(airport);
        } else {
            result = false;
        }
        return result;
    }

    public boolean update(Airport airport) {
        boolean isUpdated = false;
        if(airport!=null) {
            String code = airport.getCode();
            int index = 0;
            int changes = 0;
            Airport temp;
            for (int i = 0; i < airports.size(); i++) {
                temp = airports.get(i);
                if (temp.getCode().equals(code)) {
                    changes++;
                    index = i;
                }
            }
            if (changes != 0) {
                airports.get(index).setCode(code);
                isUpdated = true;
            }
        }
        return isUpdated;
    }

    public boolean remove(Airport airport) {
        boolean result = false;
        int index;
        index = airports.indexOf(airport);
        if (index >= 0) {
            airports.remove(index);
            result = true;
        }
        return result;
    }

}
