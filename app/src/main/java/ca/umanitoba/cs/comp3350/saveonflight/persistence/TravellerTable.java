package ca.umanitoba.cs.comp3350.saveonflight.persistence;

import java.util.ArrayList;

import ca.umanitoba.cs.comp3350.saveonflight.objects.Traveller;

/**
 * TravellerTable.java
 * <p>
 * Database table for TravellerTable
 *
 * @author Long Yu
 */

public class TravellerTable implements TravellerAccess {
    public static int nextId = 3;

    private static ArrayList<Traveller> travellers = null;

    public TravellerTable() {
    }

    public void initialize() {
        if (travellers == null) {
            travellers = new ArrayList<Traveller>();
            travellers.add(new Traveller(0, "Jack"));
            travellers.add(new Traveller(1, "Vicky"));
            travellers.add(new Traveller(2, "Amir"));
        }
    }

    public static ArrayList<Traveller> getTravellers() {
        return travellers;
    }

    public boolean add(Traveller traveller) {
        boolean result = true;
        if (traveller != null && traveller.getTravellerID() != 0) {

            for (Traveller traveller1 : travellers) {
                if (traveller.equals(traveller1))
                    result = false;
            }
            if (result)
                result = travellers.add(traveller);
        } else {
            result = false;
        }
        return result;
    }
}
