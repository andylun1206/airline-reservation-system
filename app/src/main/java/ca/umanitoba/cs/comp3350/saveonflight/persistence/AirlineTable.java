package ca.umanitoba.cs.comp3350.saveonflight.persistence;

import java.util.ArrayList;

import ca.umanitoba.cs.comp3350.saveonflight.objects.Airline;
import ca.umanitoba.cs.comp3350.saveonflight.R;

/**
 * AirlineTable.java
 * <p>
 * Database table for Airline.
 *
 * @author Long Yu
 */

public class AirlineTable implements DataAccessStub<Airline> {
    private String dbName;
    private static ArrayList<Airline> airlines;

    public AirlineTable(String dbName) {
        this.dbName = dbName;
    }

    public void initialize() {
        airlines = new ArrayList<Airline>();
        airlines.add(new Airline("WestJet", R.mipmap.ic_westjet));
        airlines.add(new Airline("Air Canada", R.mipmap.ic_aircanada);
    }

    public static ArrayList<Airline> getAirlines() {
        return airlines;
    }

    public static Airline findAirline(String airlineName) {
        Airline result = null;

        for (Airline airline : airlines) {
            if (airline.getName().toLowerCase().contains(airlineName.toLowerCase())) {
                result = airline;
            }
        }

        return result;
    }

    public boolean add(Airline airline) {
        return airlines.add(airline);
    }

    public boolean update(Airline airline) {
        boolean isUpdated = false;
        String name = airline.getName();
        int icon = airline.getIcon();
        int index = 0;
        int changes = 0;
        Airline temp;
        for (int i = 0; i < airlines.size(); i++) {
            temp = airlines.get(i);
            if (temp.getName().equals(name)) {
                changes++;
                index = i;
            }
        }
        if (changes != 0) {
            airlines.get(index).setIcon(icon);
            isUpdated = true;

        }
        return isUpdated;
    }

    public boolean remove(Airline airline) {
        int index;
        index = airlines.indexOf(airline);
        if (index >= 0) {
            airlines.remove(index);
            return true;
        }
        return false;
    }

}
