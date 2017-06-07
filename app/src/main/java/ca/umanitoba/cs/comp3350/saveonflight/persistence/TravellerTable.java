package ca.umanitoba.cs.comp3350.saveonflight.persistence;

import java.util.ArrayList;

import ca.umanitoba.cs.comp3350.saveonflight.objects.Traveller;

/**
 * Created by longyu on 2017-06-06.
 */

public class TravellerTable extends DataAccessStub {
    private String dbName;
    private ArrayList<Traveller> travellers;

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

    @Override
    public ArrayList<Traveller> getTravellers() {
        ArrayList<Traveller> result = new ArrayList<>();
        result.addAll(travellers);
        return result;
    }

    public boolean insert(Object o) {
        return travellers.add((Traveller) o);
    }

    public boolean update(Object... o) {
        boolean result = false;

        int index = travellers.indexOf((Traveller) o[0]);
        if (index >= 0) {
            Traveller toUpdate = travellers.get(index);
            toUpdate.setTravellerID((int) o[1]);
            toUpdate.setName((String) o[2]);
            result = true;
        }

        return result;
    }

    public boolean remove(Object o) {
        int index;
        index = travellers.indexOf((Traveller) o);
        if (index >= 0) {
            travellers.remove(index);
            return true;
        }
        return false;
    }

    /*public void find(Object o) {

    }*/
}
