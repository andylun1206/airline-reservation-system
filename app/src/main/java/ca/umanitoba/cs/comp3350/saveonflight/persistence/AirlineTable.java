package ca.umanitoba.cs.comp3350.saveonflight.persistence;

import java.util.ArrayList;

import ca.umanitoba.cs.comp3350.saveonflight.objects.Airline;

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

    public void initialize(String dbPath) {
        if (airlines == null) {
            airlines = new ArrayList<Airline>();
            airlines.add(new Airline("WestJet"));
            airlines.add(new Airline("Air Canada"));
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
    public void close()
    {
        System.out.println("Closed  database " );
    }

}
