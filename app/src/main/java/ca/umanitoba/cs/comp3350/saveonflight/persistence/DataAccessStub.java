package ca.umanitoba.cs.comp3350.saveonflight.persistence;


import java.util.ArrayList;

import ca.umanitoba.cs.comp3350.saveonflight.application.Main;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Airline;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Airport;
import ca.umanitoba.cs.comp3350.saveonflight.objects.BookedFlight;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Flight;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Traveller;

public class DataAccessStub {
    private String dbName;
    private String dbType = "stub";

    private ArrayList<Airline> airlines;
    private ArrayList<Airport> airports;
    private ArrayList<BookedFlight> bookedFlights;
    private ArrayList<Flight> flights;
    private ArrayList<Traveller> travellers;

    public DataAccessStub(String dbName) {
        this.dbName = dbName;
    }

    public DataAccessStub() {
        this(Main.DB_NAME);
    }

    public void open() {
        // TODO: Populate the 5 "tables" (ArrayLists) with fake data

        System.out.println("Opened " + dbType + " database " + dbName);
    }

    public void close() {

        System.out.println("Closed " + dbType + " database " + dbName);
    }

    // AIRLINE TABLE METHDOS
    public ArrayList<Airline> getAirlines() {
        // TODO: return all airlines
        return null;
    }

    public boolean insertAirline(Airline airline) {
        return airlines.add(airline);
    }

    public boolean updateAirline() {
        // TODO: Not sure what the method signature for these update methods should be - kenny
        return false;
    }

    public boolean deleteAirline(Airline airline) {
        // TODO: delete the specified airline from the Airlines table
        return false;
    }


    // AIRPORT TABLE METHODS
    public ArrayList<Airport> getAirports() {
        // TODO: return ArrayList of all airports
        return null;
    }

    public boolean insertAirport(Airport airport) {
        return airports.add(airport);
    }

    public boolean updateAirport() {
        // TODO
        return false;
    }

    public boolean deleteAirport(Airport airport) {
        // TODO
        return false;
    }


    // BOOKEDFLIGHTS TABLE METHODS
    public ArrayList<BookedFlight> getBookedFlights() {
        // TODO
        return null;
    }

    public boolean insertBookedFlight(BookedFlight bookedFlight) {
        return bookedFlights.add(bookedFlight);
    }

    public boolean updateBookedFlight() {
        // TODO
        return false;
    }

    public boolean deleteBookedFlight(BookedFlight bookedFlight) {
        // TODO
        return false;
    }

    // FLIGHTs TABLE METHODS
    public ArrayList<Flight> getFlights() {
        // TODO
        return null;
    }

    public boolean insertFlight(Flight flight) {
        return flights.add(flight);
    }

    public boolean updateFlight() {
        // TODO
        return false;
    }

    public boolean deleteFlight(Flight flight) {
        // TODO
        return false;
    }


    // TRAVELLERS TABLE METHODS
    public ArrayList<Traveller> getTravellers() {
        // TODO
        return null;
    }

    public boolean insertTraveller(Traveller traveller) {
        return travellers.add(traveller);
    }

    public boolean updateTraveller() {
        // TODO
        return false;
    }

    public boolean deleteTraveller(Traveller traveller) {
        // TODO
        return false;
    }

}
