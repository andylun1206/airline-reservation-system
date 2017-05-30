package ca.umanitoba.cs.comp3350.saveonflight.persistence;


import java.util.ArrayList;

import ca.umanitoba.cs.comp3350.saveonflight.application.Main;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Airline;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Airport;
import ca.umanitoba.cs.comp3350.saveonflight.objects.BookedFlight;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Flight;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Traveller;

public class DataAccessStub implements DataAccess {
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

    public void open(String dbName) {
        Airline airline;
        Airport airport;
        BookedFlight bookedFlight;
        Flight flight;
        Traveller traveller;

        airlines = new ArrayList<Airline>();
        airline = new Airline("WestJet");
        airlines.add(airline);
        airline = new Airline("Air Canada");
        airlines.add(airline);
        airline = new Airline("Winnipeg Air");
        airlines.add(airline);

        airports = new ArrayList<Airport>();
        airport = new Airport("YVR");
        airports.add(airport);
        airport = new Airport("YWG");
        airports.add(airport);
        airport = new Airport("YYC");
        airports.add(airport);

        travellers = new ArrayList<Traveller>();
        traveller = new Traveller(0, "Jack");
        travellers.add(traveller);
        traveller = new Traveller(1, "Vicky");
        travellers.add(traveller);
        traveller = new Traveller(2, "Amir");
        travellers.add(traveller);

        flights = new ArrayList<Flight>();
        flight = new Flight("WJ 001", "2017/11/11", "WestJet", "YWG", "YVR");
        flights.add(flight);
        flight = new Flight("WJ 001", "2017/12/12", "WestJet", "YVR", "YWG");
        flights.add(flight);
        flight = new Flight("AC 001", "2017/9/11", "Air Canada", "YWG", "YYC");
        flights.add(flight);
        flight = new Flight("WA 001", "2017/10/11", "Winnpeg Air", "YWG", "YVR");
        flights.add(flight);

        bookedFlights = new ArrayList<BookedFlight>();
        bookedFlight = new BookedFlight(0, "Jack", "WJ 001", "2017/11/11", "WestJet", "YWG", "YVR");
        bookedFlights.add(bookedFlight);
        bookedFlight = new BookedFlight(0, "Jack", "WJ 001", "2017/12/12", "WestJet", "YVR", "YWG");
        bookedFlights.add(bookedFlight);
        bookedFlight = new BookedFlight(1, "Vicky", "AC 001", "2017/9/11", "Air Canada", "YWG", "YYC");
        bookedFlights.add(bookedFlight);
        bookedFlight = new BookedFlight(2, "Amir", "WA 001", "2017/10/11", "Winnpeg Air", "YWG", "YVR");
        bookedFlights.add(bookedFlight);

        System.out.println("Opened " + dbType + " database " + dbName);
    }

    public void close() {
        System.out.println("Closed " + dbType + " database " + dbName);
    }

    // AIRLINE TABLE METHODS
    public ArrayList<Airline> getAirlines() {
        ArrayList<Airline> result = new ArrayList<>();
        result.addAll(airlines);
        return result;
    }

    public boolean insertAirline(Airline airline) {
        return airlines.add(airline);
    }

    public boolean updateAirline(Airline airline) {
        // TODO: Not sure what the method signature for these update methods should be - kenny

        return false;
    }

    public boolean deleteAirline(Airline airline) {
        int index;
        index = airlines.indexOf(airline);
        if (index >= 0) {
            airlines.remove(index);
            return true;
        }
        return false;
    }


    // AIRPORT TABLE METHODS
    public ArrayList<Airport> getAirports() {
        // Return a copy of List
        ArrayList<Airport> result = new ArrayList<>();
        result.addAll(airports);
        return result;
    }

    public boolean insertAirport(Airport airport) {
        return airports.add(airport);
    }

    public boolean updateAirport() {
        // TODO
        return false;
    }

    public boolean deleteAirport(Airport airport) {
        int index;
        index = airports.indexOf(airport);
        if (index >= 0) {
            airports.remove(index);
            return true;
        }
        return false;
    }


    // BOOKEDFLIGHTS TABLE METHODS
    public ArrayList<BookedFlight> getBookedFlights() {
        ArrayList<BookedFlight> result = new ArrayList<>();
        result.addAll(bookedFlights);
        return result;
    }

    public boolean insertBookedFlight(BookedFlight bookedFlight) {
        return bookedFlights.add(bookedFlight);
    }

    public boolean updateBookedFlight() {
        // TODO
        return false;
    }

    public boolean deleteBookedFlight(BookedFlight bookedFlight) {
        int index;
        index = bookedFlights.indexOf(bookedFlight);
        if (index >= 0) {
            bookedFlights.remove(index);
            return true;
        }
        return false;

    }

    public ArrayList<BookedFlight> getTravellersBookedFlights(Traveller traveller) {
        ArrayList<BookedFlight> result = new ArrayList<BookedFlight>();

        for (int i = 0; i < bookedFlights.size(); i++) {
            BookedFlight term = bookedFlights.get(i);
            if (term.getTraveller().getTravellerID() == traveller.getTravellerID()) {
                result.add(term);
            }
        }
        return result;
    }

    public ArrayList<BookedFlight> getTravellersOnFlight(Flight flight) {
        ArrayList<BookedFlight> result = new ArrayList<BookedFlight>();

        for (int i = 0; i < bookedFlights.size(); i++) {
            BookedFlight term = bookedFlights.get(i);
            if (term.getFlight().getFlightID().equals(flight.getFlightID())) {
                result.add(term);
            }
        }
        return result;
    }

    // FLIGHTS TABLE METHODS
    public ArrayList<Flight> getFlights() {
        ArrayList<Flight> result = new ArrayList<>();
        result.addAll(flights);
        return result;
    }

    public boolean insertFlight(Flight flight) {
        return flights.add(flight);
    }

    public boolean updateFlight() {
        //// TODO: 2017-05-25
        return false;
    }

    public boolean deleteFlight(Flight flight) {
        int index;
        index = flights.indexOf(flight);
        if (index >= 0) {
            flights.remove(index);
            return true;
        }
        return false;
    }


    // TRAVELLERS TABLE METHODS
    public ArrayList<Traveller> getTravellers() {
        ArrayList<Traveller> result = new ArrayList<>();
        result.addAll(travellers);
        return result;
    }

    public boolean insertTraveller(Traveller traveller) {
        return travellers.add(traveller);
    }

    public boolean updateTraveller(Traveller traveller, int newID, String newName) {
        boolean result = false;

        int index = travellers.indexOf(traveller);
        if (index >= 0) {
            Traveller toUpdate = travellers.get(index);
            toUpdate.setTravellerID(newID);
            toUpdate.setName(newName);
            result = true;
        }

        return result;
    }

    public boolean deleteTraveller(Traveller traveller) {
        int index;
        index = travellers.indexOf(traveller);
        if (index >= 0) {
            travellers.remove(index);
            return true;
        }
        return false;
    }

}
