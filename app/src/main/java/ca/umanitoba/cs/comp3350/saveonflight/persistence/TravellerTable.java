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

public class TravellerTable implements DataAccessStub<Traveller> {
    private String dbName;
    private static ArrayList<Traveller> travellers = null;

    public TravellerTable(String dbName) {
        this.dbName = dbName;
    }

    public void initialize() {
        Traveller jack, vicky, amir;
        travellers = new ArrayList<Traveller>();
        jack = new Traveller(0, "Jack");
        travellers.add(jack);
        vicky = new Traveller(1, "Vicky");
        travellers.add(vicky);
        amir = new Traveller(2, "Amir");
        travellers.add(amir);
        System.out.println("Opened " + " database " + dbName);
    }

    public static ArrayList<Traveller> getTravellers() {
        return travellers;
    }

    public boolean add(Traveller traveller) {
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
