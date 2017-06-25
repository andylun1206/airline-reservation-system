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

public class TravellerTable implements DataAccess<Traveller> {
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

    public boolean update(Traveller traveller) {
        boolean isUpdated = false;
        if(traveller != null) {
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
        }
        return isUpdated;
    }

    public boolean remove(Traveller traveller) {
        boolean result = false;
        int index;
        index = travellers.indexOf(traveller);
        if (index >= 0) {
            travellers.remove(index);
            result = true;
        }
        return result;
    }

}
