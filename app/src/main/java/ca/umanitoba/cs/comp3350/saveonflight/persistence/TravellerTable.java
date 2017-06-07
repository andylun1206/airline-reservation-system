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
        return travellers;
    }

    public void setTravellers(ArrayList<Traveller> travellers) {
        this.travellers = travellers;
    }
    public void insert(Object o){

    }
    public void update(Object o){

    }
    public void remove(Object o){

    }
    public void find(Object o){

    }
}
