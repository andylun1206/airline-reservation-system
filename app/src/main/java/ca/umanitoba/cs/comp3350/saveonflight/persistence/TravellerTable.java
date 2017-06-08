package ca.umanitoba.cs.comp3350.saveonflight.persistence;

import ca.umanitoba.cs.comp3350.saveonflight.objects.Traveller;

import java.util.ArrayList;

/**
 * TravellerTable.java
 * <p>
 * Database table for TravellerTable
 *
 * @author Long Yu
 */

public class TravellerTable implements DataAccessStub<Traveller> {
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
        if (traveller == null) {
            return false;
        }
        for (Traveller traveller1 : travellers) {
            if (traveller.equals(traveller1))
                return false;
        }
        return travellers.add(traveller);
    }

    public boolean update(Traveller traveller) {
        boolean isUpdated = false;
        int id = traveller.getTravellerID();
        String name = traveller.getName();
        int index = 0;
        int changes = 0;
        Traveller temp;
        for (int i = 0; i < travellers.size(); i++) {
            temp = travellers.get(i);
            if (temp.getTravellerID() == id) {
                changes++;
                index = i;
            }
        }
        if (changes != 0) {
            travellers.get(index).setName(name);
            isUpdated = true;
        }
        return isUpdated;
    }

    public boolean remove(Traveller traveller) {
        int index;
        index = travellers.indexOf(traveller);
        if (index >= 0) {
            travellers.remove(index);
            return true;
        }
        return false;
    }

}
