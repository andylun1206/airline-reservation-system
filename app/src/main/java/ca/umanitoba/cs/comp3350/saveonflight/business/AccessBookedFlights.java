package ca.umanitoba.cs.comp3350.saveonflight.business;
import java.util.ArrayList;
import java.util.List;

import ca.umanitoba.cs.comp3350.saveonflight.application.Main;
import ca.umanitoba.cs.comp3350.saveonflight.application.Services;
import ca.umanitoba.cs.comp3350.saveonflight.objects.BookedFlight;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Flight;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Traveller;
import ca.umanitoba.cs.comp3350.saveonflight.persistence.DataAccessStub;

public class AccessBookedFlights {
    private DataAccessStub dataAccess;
    private List<BookedFlight> bookedFlights;

    // For iterating through all BookedFlights
    private BookedFlight currentBf;
    private int currentIndex;

    public AccessBookedFlights() {
        dataAccess = Services.getDataAccess(Main.DB_NAME);
        bookedFlights = null;
        currentBf = null;
        currentIndex = 0;
    }

    public List<BookedFlight> getFlights() {
        return dataAccess.getBookedFlights();
    }

    public BookedFlight getNext() {
        if (bookedFlights == null) {
            bookedFlights = dataAccess.getBookedFlights();
            currentIndex = 0;
        }

        if (currentIndex < bookedFlights.size()) {
            currentBf = bookedFlights.get(currentIndex++);
        } else {
            bookedFlights = null;
            currentBf = null;
            currentIndex = 0;
        }

        return currentBf;
    }

    public boolean addBookedFlight(BookedFlight bf) {
        return dataAccess.insert(bf);
    }

    public boolean updateBookedFlight(BookedFlight bf, Traveller t, Flight f) {
        return dataAccess.update(bf, t, f);
    }

    public boolean deleteFlight(BookedFlight bf) {
        return dataAccess.remove(bf);
    }

    public ArrayList<BookedFlight> getBookedFlightsOf(Traveller t){
        return dataAccess.getTravellersBookedFlights(t);
    }

    public ArrayList<BookedFlight> getTravellersOnFlight(Flight f){
        return dataAccess.getTravellersOnFlight(f);
    }

}
