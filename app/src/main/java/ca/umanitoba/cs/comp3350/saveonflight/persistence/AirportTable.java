package ca.umanitoba.cs.comp3350.saveonflight.persistence;

import java.util.ArrayList;

import ca.umanitoba.cs.comp3350.saveonflight.objects.Airport;

/**
 * Created by longyu on 2017-06-06.
 */

public class AirportTable extends DataAccessStub {
    private String dbName;
    private ArrayList<Airport> airports;
    private Airport vancouver, winnipeg, calgary;

    public AirportTable(String dbName) {
        this.dbName = dbName;
    }

    public void initialize() {

        airports = new ArrayList<Airport>();
        vancouver = new Airport("Vancouver YVR");
        airports.add(vancouver);
        winnipeg = new Airport("Winnipeg YWG");
        airports.add(winnipeg);
        calgary = new Airport("Calgary YYC");
        airports.add(calgary);
        System.out.println("Opened " + " database " + dbName);
    }

    @Override
    public ArrayList<Airport> getAirports() {
        ArrayList<Airport> result = new ArrayList<>();
        result.addAll(airports);
        return result;
    }

    public void setAirports(ArrayList<Airport> airports) {
        this.airports = airports;
    }

    public boolean insert(Object o) {
        return airports.add((Airport) o);
    }

    public boolean update(Object... o) {
        int index;

        index = airports.indexOf((Airport) o[0]);
        if (index >= 0) {
            airports.get(index).setAirportCode((String) o[1]);
            return true;
        }
        return false;
    }

    public boolean remove(Object o) {
        int index;
        index = airports.indexOf((Airport) o);
        if (index >= 0) {
            airports.remove(index);
            return true;
        }
        return false;
    }
    /*public void find(Object o){

    }*/
}
