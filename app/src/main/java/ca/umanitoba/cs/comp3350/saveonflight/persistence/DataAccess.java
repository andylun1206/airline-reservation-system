package ca.umanitoba.cs.comp3350.saveonflight.persistence;

import java.util.ArrayList;

import ca.umanitoba.cs.comp3350.saveonflight.objects.Airline;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Airport;
import ca.umanitoba.cs.comp3350.saveonflight.objects.BookedFlight;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Flight;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Traveller;

interface DataAccess {
    public void open(String dbName);
    public void close();

    // AIRLINE TABLE METHDOS
    public ArrayList<Airline> getAirlines();
    public boolean insertAirline(Airline airline);
    public boolean updateAirline(Airline airline);
    public boolean deleteAirline(Airline airline);

    // AIRPORT TABLE METHODS
    public ArrayList<Airport> getAirports();
    public boolean insertAirport(Airport airport);
    public boolean updateAirport();
    public boolean deleteAirport(Airport airport);

    // BOOKEDFLIGHTS TABLE METHODS
    public ArrayList<BookedFlight> getBookedFlights();
    public boolean insertBookedFlight(BookedFlight bookedFlight);
    public boolean updateBookedFlight();
    public boolean deleteBookedFlight(BookedFlight bookedFlight);
    public ArrayList<BookedFlight> getTravellersBookedFlights(Traveller traveller);
    public ArrayList<BookedFlight> getTravellersOnFlight(Flight flight);

    // FLIGHTS TABLE METHODS
    public ArrayList<Flight> getFlights();
    public boolean insertFlight(Flight flight);
    public boolean updateFlight();
    public boolean deleteFlight(Flight flight);

    // TRAVELLERS TABLE METHODS
    public ArrayList<Traveller> getTravellers();
    public boolean insertTraveller(Traveller traveller);
    public boolean updateTraveller();
    public boolean deleteTraveller(Traveller traveller);
}
