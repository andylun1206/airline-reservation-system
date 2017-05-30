package ca.umanitoba.cs.comp3350.saveonflight.persistence;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

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
        Airline westJet, airCanada, winnipegAir;
        Airport vancouver, winnipeg, calgary;
        Traveller jack, vicky, amir;

        BookedFlight bookedFlight;
        Flight ywgToYvr, yvrToYwg, ywgToYyc, waYwgToYvr;

        airlines = new ArrayList<Airline>();
        westJet = new Airline("WestJet");
        airlines.add(westJet);
        airCanada = new Airline("Air Canada");
        airlines.add(airCanada);
        winnipegAir = new Airline("Winnipeg Air");
        airlines.add(winnipegAir);

        airports = new ArrayList<Airport>();
        vancouver = new Airport("YVR");
        airports.add(vancouver);
        winnipeg = new Airport("YWG");
        airports.add(winnipeg);
        calgary = new Airport("YYC");
        airports.add(calgary);

        travellers = new ArrayList<Traveller>();
        jack = new Traveller(0, "Jack");
        travellers.add(jack);
        vicky = new Traveller(1, "Vicky");
        travellers.add(vicky);
        amir = new Traveller(2, "Amir");
        travellers.add(amir);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy, MM, dd", Locale.CANADA);

        flights = new ArrayList<Flight>();
        try {
            ywgToYvr = new Flight("WJ 001", simpleDateFormat.parse("2017, 11, 11"), westJet, winnipeg,
                    vancouver, 200.00, 200, 0, Flight.FlightClass.ECONOMY);
            flights.add(ywgToYvr);
            yvrToYwg = new Flight("WJ 001", simpleDateFormat.parse("2017, 12, 12"), westJet, vancouver,
                    winnipeg, 350.00, 200, 0, Flight.FlightClass.ECONOMY);
            flights.add(yvrToYwg);
            ywgToYyc = new Flight("AC 001", simpleDateFormat.parse("2017, 9, 11"), airCanada, winnipeg,
                    calgary, 400.00, 150, 0, Flight.FlightClass.BUSINESS);
            flights.add(ywgToYyc);
            waYwgToYvr = new Flight("WA 001", simpleDateFormat.parse("2017, 10, 11"), winnipegAir, winnipeg,
                    vancouver, 500.00, 250, 0, Flight.FlightClass.ECONOMY);
            flights.add(waYwgToYvr);

            bookedFlights = new ArrayList<BookedFlight>();
            bookedFlight = new BookedFlight(jack, ywgToYvr);
            bookedFlights.add(bookedFlight);
            bookedFlight = new BookedFlight(jack, yvrToYwg);
            bookedFlights.add(bookedFlight);
            bookedFlight = new BookedFlight(vicky, ywgToYyc);
            bookedFlights.add(bookedFlight);
            bookedFlight = new BookedFlight(amir, waYwgToYvr);
            bookedFlights.add(bookedFlight);
        } catch (ParseException e) {
            e.printStackTrace();
        }

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
