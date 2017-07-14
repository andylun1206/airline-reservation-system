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

    public ArrayList<Traveller> getTravellers() {
        return travellers;
    }

    public boolean add(Traveller traveller) {
        boolean added = false;
        if (traveller != null) {
            traveller.setTravellerId(nextId++);
            travellers.add(traveller);
            added = true;
        }
        return added;
    }

    public int getMaxId() {
        return nextId - 1;
    }

    public boolean remove(Traveller traveller) {
        boolean removed = false;

        int i = 0;
        while (!removed && i < travellers.size()) {
            if (traveller.equals(travellers.get(i))) {
                travellers.remove(i);
                removed = true;
            }
        }

        return removed;
    }

    public void close()
    {
        System.out.println("Closed  database " );
    }

}
