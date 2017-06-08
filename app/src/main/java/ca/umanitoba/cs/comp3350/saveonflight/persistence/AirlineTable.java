package ca.umanitoba.cs.comp3350.saveonflight.persistence;

import ca.umanitoba.cs.comp3350.saveonflight.R;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Airline;

import java.util.ArrayList;

/**
 * AirlineTable.java
 * <p>
 * Database table for Airline.
 *
 * @author Long Yu
 */

public class AirlineTable implements DataAccess<Airline> {
    private static ArrayList<Airline> airlines = null;

    public AirlineTable() {
    }

    public void initialize() {
        if (airlines == null) {
            airlines = new ArrayList<Airline>();
            airlines.add(new Airline("WestJet", R.mipmap.ic_westjet));
            airlines.add(new Airline("Air Canada", R.mipmap.ic_aircanada));
        }
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
        boolean result = true;
        if (airline != null && !airline.getName().isEmpty() && airline.getIcon() != 0) {
            for (Airline airline1 : airlines) {
                if (airline.equals(airline1)) {
                    result = false;
                }
            }
            if (result) {
                result = airlines.add(airline);
            }
        } else {
            result = false;
        }
        return result;
    }

    public boolean update(Airline airline) {
        boolean isUpdated = false;
        if(airline != null){
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
        }
        return isUpdated;
    }

    public boolean remove(Airline airline) {
        boolean result = false;
        int index;
        index = airlines.indexOf(airline);
        if (index >= 0) {
            airlines.remove(index);
            result = true;
        }
        return result;
    }

}
