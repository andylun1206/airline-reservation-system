package ca.umanitoba.cs.comp3350.saveonflight.persistence;

import java.util.ArrayList;

import ca.umanitoba.cs.comp3350.saveonflight.objects.Airline;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Airport;
import ca.umanitoba.cs.comp3350.saveonflight.objects.BookedFlight;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Flight;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Traveller;

/**
 * Created by zhang on 2017-05-25.
 */

interface DataAccess {

    public void open(String dbName);
        // TODO: Populate the 5 "tables" (ArrayLists) with fake data

    public void close();

    // AIRLINE TABLE METHDOS
    public ArrayList<Airline> getAirlines();
        // TODO: return all airlines

    public boolean insertAirline(Airline airline);

    // TODO: public ArrayList<Student> getStudentRandom(Student currentStudent)???

    public boolean updateAirline(Airline airline);
        // TODO: Not sure what the method signature for these update methods should be - kenny

    public boolean deleteAirline(Airline airline);
        // TODO: delete the specified airline from the Airlines table

    // AIRPORT TABLE METHODS
    public ArrayList<Airport> getAirports();
        // TODO: return ArrayList of all airports

    public boolean insertAirport(Airport airport);

    public boolean updateAirport(Airport airport);

    public boolean deleteAirport(Airport airport);


    // BOOKEDFLIGHTS TABLE METHODS
    public ArrayList<BookedFlight> getBookedFlights();

    public boolean insertBookedFlight(BookedFlight bookedFlight);

    public boolean updateBookedFlight(BookedFlight bookedFlight);

    public boolean deleteBookedFlight(BookedFlight bookedFlight);

    public ArrayList<BookedFlight> getTravellersBookedFlights(Traveller traveller);

    public ArrayList<BookedFlight> getTravellersOnFlight(Flight flight);

    // FLIGHTS TABLE METHODS
    public ArrayList<Flight> getFlights();

    public boolean insertFlight(Flight flight);

    public boolean updateFlight(Flight flight);

    public boolean deleteFlight(Flight flight);


    // TRAVELLERS TABLE METHODS
    public ArrayList<Traveller> getTravellers();
    public boolean insertTraveller(Traveller traveller);

    public boolean updateTraveller(Traveller traveller);

    public boolean deleteTraveller(Traveller traveller);

}
