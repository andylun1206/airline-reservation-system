package ca.umanitoba.cs.comp3350.saveonflight.persistence;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import ca.umanitoba.cs.comp3350.saveonflight.objects.Airline;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Airport;
import ca.umanitoba.cs.comp3350.saveonflight.objects.BookedFlight;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Flight;
import ca.umanitoba.cs.comp3350.saveonflight.objects.FlightClassEnum;

/**
 * Created by longyu on 2017-06-06.
 */

public class FlightTable extends DataAccessStub {
    private String dbName;
    private ArrayList<Flight> flights;
    private Flight ywgToYvr, yvrToYwg, ywgToYyc, waYwgToYvr;

    public FlightTable(String dbName) {
        this.dbName = dbName;
    }

    public void initialize() {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy, MM, dd, HH, mm", Locale.CANADA);
        flights = new ArrayList<Flight>();
        try {
            ArrayList<Airport> airports = super.getAirports();
            ArrayList<Airline> airlines = super.getAirlines();

            ywgToYvr = new Flight("WJ 001", simpleDateFormat.parse("2017, 11, 11, 22, 30"), simpleDateFormat.parse("2017, 11, 12, 01, 30"), airlines.get(0), airports.get(1),
                    airports.get(0), 200.00, 200, 0, FlightClassEnum.ECONOMY);
            flights.add(ywgToYvr);
            yvrToYwg = new Flight("WJ 001", simpleDateFormat.parse("2017, 10, 10, 22, 30"), simpleDateFormat.parse("2017, 10, 12, 22, 30"), airlines.get(0), airports.get(0),
                    airports.get(1), 350.00, 200, 0, FlightClassEnum.ECONOMY);
            flights.add(yvrToYwg);
            ywgToYyc = new Flight("AC 001", simpleDateFormat.parse("2017, 9, 11, 22, 30"), simpleDateFormat.parse("2017, 9, 12, 22, 30"), airlines.get(1), airports.get(1),
                    airports.get(2), 400.00, 150, 0, FlightClassEnum.BUSINESS);
            flights.add(ywgToYyc);
            waYwgToYvr = new Flight("WA 001", simpleDateFormat.parse("2017, 10, 11, 12, 30"), simpleDateFormat.parse("2017, 10, 11, 22, 30"), airlines.get(2), airports.get(1),
                    airports.get(0), 500.00, 250, 0, FlightClassEnum.ECONOMY);
            flights.add(waYwgToYvr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("Opened " + " database " + dbName);
    }

    @Override
    public ArrayList<Flight> getFlights() {
        ArrayList<Flight> result = new ArrayList<>();
        result.addAll(flights);
        return result;
    }

    public boolean insert(Object o) {
        return flights.add((Flight) o);
    }

    public boolean update(Object... o) {
        int index;

        index = flights.indexOf((Flight) o[0]);
        if (index >= 0) {
            Flight f = flights.get(index);
            f.setFlightID((String) o[1]);
            f.setDepartureTime((Date) o[2]);
            f.setArrivalTime((Date) o[3]);
            f.setAirline((Airline) o[4]);
            f.setOrigin((Airport) o[5]);
            f.setDestination((Airport) o[6]);
            f.setPrice((double) o[7]);
            f.setCapacity((int) o[8]);
            f.setSeatsTaken((int) o[9]);
            f.setFlightClass((FlightClassEnum) o[10]);
            return true;
        }
        return false;
    }

    public boolean remove(Object o) {
        int index;
        index = flights.indexOf((Flight) o);
        if (index >= 0) {
            flights.remove(index);
            return true;
        }
        return false;
    }

    public void find(Object o) {

    }
}
