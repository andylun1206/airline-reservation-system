package ca.umanitoba.cs.comp3350.saveonflight.business;

import java.sql.SQLException;
import java.util.List;

import ca.umanitoba.cs.comp3350.saveonflight.objects.BookedFlight;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Traveller;
import ca.umanitoba.cs.comp3350.saveonflight.persistence.BookedFlightAccess;

/**
 * AccessBookedFlightImpl.java
 * <p>
 * Implementation of the database access object for the Flights table.
 *
 * @author Shenyun Wang
 */

public class AccessBookedFlightsImpl implements AccessBookedFlights {
    private static BookedFlightAccess bookedFlightsDB;

    public AccessBookedFlightsImpl(BookedFlightAccess access) {
        bookedFlightsDB = access;
    }

    /**
     * Adds a BookedFlight to the database.
     *
     * @param bf the BookedFlight to insertBookedFlight
     * @return true if the BookedFlight was added; false if not
     */
    @Override
    public boolean insertBookedFlight(BookedFlight bf) {
        boolean added = false;
        try {
            added = bookedFlightsDB.add(bf);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return added;
    }

    /**
     * Removes the specified BookedFlights from the table.
     *
     * @param bf specifies which BookedFlights to removeBookedFlight
     * @return true if 1 or more rows were removed; false otherwise
     */
    @Override
    public boolean removeBookedFlight(BookedFlight bf) {
        return bookedFlightsDB.remove(bf);
    }

    /**
     * Searches for BookedFlights that are associated with the specified Traveller.
     *
     * @param t the Traveller to search by
     * @return the List of BookedFlights that are associated with the given Traveller
     */
    public List<BookedFlight> searchByTraveller(Traveller t) {
        return bookedFlightsDB.searchByTraveller(t);
    }
    public String seatNumber(int seatTaken){
        String seatNumber=null;
        int number, character;
            number = seatTaken/4+1;
            character = seatTaken%4;
            switch(character) {
                case 0:
                    seatNumber=number+"D";
                    break;
                case 1:
                    seatNumber=number+"A";
                    break;
                case 2:
                    seatNumber=number+"B";
                    break;
                case 3:
                    seatNumber=number+"C";
                    break;
            }
        return seatNumber;
    }
}
