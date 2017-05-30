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
    private Flight flight;
    private int currentFlight;

    public AccessBookedFlights() {
        dataAccess = Services.getDataAccess(Main.DB_NAME);
        bookedFlights = null;
        flight = null;
        currentFlight = 0;
    }

    public List<BookedFlight> getFlights() {
        return dataAccess.getBookedFlights();
    }

    public boolean addBookedFlight(BookedFlight f) {
        return dataAccess.insertBookedFlight(f);
    }


    public BookedFlight deleteFlight(BookedFlight f) {
        BookedFlight deleted = null;
        if (dataAccess.deleteBookedFlight(f))
            deleted = f;
        return deleted;
    }

    public ArrayList<BookedFlight> getBookedFlightsOf(Traveller t){
        return dataAccess.getTravellersBookedFlights(t);
    }

    public ArrayList<BookedFlight> getTravellersOnFlight(Flight f){
        return dataAccess.getTravellersOnFlight(f);
    }

}
