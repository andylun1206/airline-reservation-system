package ca.umanitoba.cs.comp3350.saveonflight.persistence;

import java.util.ArrayList;
import java.util.Date;

import ca.umanitoba.cs.comp3350.saveonflight.objects.Airline;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Airport;
import ca.umanitoba.cs.comp3350.saveonflight.objects.BookedFlight;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Flight;
import ca.umanitoba.cs.comp3350.saveonflight.objects.FlightClassEnum;
import ca.umanitoba.cs.comp3350.saveonflight.objects.SearchCriteria;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Traveller;

interface DataAccess {
    public void open(String dbName);

    public void close();

    public void initialize();

    public boolean update(Object... o);

    public boolean insert(Object o);

    public boolean remove(Object o);

    public ArrayList<Airline> getAirlines();

    public ArrayList<Airport> getAirports();

    public ArrayList<BookedFlight> getBookedFlights();

    public ArrayList<Flight> getFlights();

    public ArrayList<Traveller> getTravellers();

    public ArrayList<BookedFlight> getTravellersBookedFlights(Traveller traveller);

    public ArrayList<BookedFlight> getTravellersOnFlight(Flight flight);

    public ArrayList<Flight> searchByCriteria(SearchCriteria criteria);

    /*// AIRLINE TABLE METHDOS


    public boolean insertAirline(Airline airline);

    public boolean updateAirline(Airline airline, String name);

    public boolean deleteAirline(Airline airline);

    // AIRPORT TABLE METHODS

  
    public boolean insertAirport(Airport airport);

    public boolean updateAirport(Airport airport, String airportCode);

    public boolean deleteAirport(Airport airport);

    // BOOKEDFLIGHTS TABLE METHODS

  
    public boolean insertBookedFlight(BookedFlight bookedFlight);

    public boolean updateBookedFlight(BookedFlight bookedFlight, Traveller t, Flight f);

    public boolean deleteBookedFlight(BookedFlight bookedFlight);
  */


    /*// FLIGHTS TABLE METHODS

  
    public boolean insertFlight(Flight flight);

    public boolean updateFlight(Flight flight, String flightID, Date departDate, Date arriveDate, Airline airline, Airport origin,
                                Airport dest, double price, int capacity, int seatsTaken, FlightClassEnum flightClass);

    public boolean deleteFlight(Flight flight);

    // TRAVELLERS TABLE METHODS

  
    public boolean insertTraveller(Traveller traveller);

    public boolean updateTraveller(Traveller traveller, int newID, String newName);

    public boolean deleteTraveller(Traveller traveller);*/
}
