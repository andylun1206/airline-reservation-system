package ca.umanitoba.cs.comp3350.saveonflight.persistence;

import java.util.ArrayList;

import ca.umanitoba.cs.comp3350.saveonflight.objects.Airline;
import ca.umanitoba.cs.comp3350.saveonflight.R;

/**
 * Created by longyu on 2017-06-06.
 */

public class AirlineTable extends DataAccessStub {
    private String dbName;
    private ArrayList<Airline> airlines;
    private Airline westJet, airCanada, winnipegAir;

    public AirlineTable(String dbName) {
        this.dbName = dbName;
    }

    public void initialize() {

        airlines = new ArrayList<Airline>();
        westJet = new Airline("WestJet", R.mipmap.ic_westjet);
        airlines.add(westJet);
        airCanada = new Airline("Air Canada", R.mipmap.ic_aircanada);
        airlines.add(airCanada);
        winnipegAir = new Airline("Winnipeg Air", 0);
        airlines.add(winnipegAir);

        System.out.println("Opened " + " database " + dbName);
    }

    @Override
    public ArrayList<Airline> getAirlines() {
        ArrayList<Airline> result = new ArrayList<>();
        result.addAll(airlines);
        return result;
    }

    public void setAirlines(ArrayList<Airline> airlines) {
        this.airlines = airlines;
    }

    public boolean insertAirline(Airline airline) {
        return airlines.add(airline);
    }

    public boolean insert(Object o) {

        return airlines.add((Airline) o);
    }

    public boolean update(Object... o) {
        int index;

        index = airlines.indexOf((Airline) o[0]);
        if (index >= 0) {
            airlines.get(index).setName((String) o[1]);
            return true;
        }
        return false;
    }

    public boolean remove(Object o) {
        int index;
        index = airlines.indexOf((Airline) o);
        if (index >= 0) {
            airlines.remove(index);
            return true;
        }
        return false;
    }

    /*public void find(Object o) {

    }*/
}
