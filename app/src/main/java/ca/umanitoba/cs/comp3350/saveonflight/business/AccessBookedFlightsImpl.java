package ca.umanitoba.cs.comp3350.saveonflight.business;

import java.util.ArrayList;
import java.util.List;

import ca.umanitoba.cs.comp3350.saveonflight.application.Main;
import ca.umanitoba.cs.comp3350.saveonflight.application.Services;
import ca.umanitoba.cs.comp3350.saveonflight.objects.BookedFlight;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Flight;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Traveller;
import ca.umanitoba.cs.comp3350.saveonflight.persistence.DataAccessStub;

public class AccessBookedFlightsImpl implements AccessBookedFlights {
    private DataAccessStub dataAccess;

    public AccessBookedFlightsImpl() {
        dataAccess = Services.getDataAccess(Main.DB_NAME);
    }

    @Override
    public List<BookedFlight> getFlights() {
        return dataAccess.getBookedFlights();
    }

    @Override
    public boolean addBookedFlight(BookedFlight bf) {
        return dataAccess.insertBookedFlight(bf);
    }

    @Override
    public boolean updateBookedFlight(BookedFlight bf, Traveller t, Flight f) {
        return dataAccess.updateBookedFlight(bf, t, f);
    }

    @Override
    public boolean deleteFlight(BookedFlight bf) {
        return dataAccess.deleteBookedFlight(bf);
    }

    @Override
    public ArrayList<BookedFlight> getBookedFlightsOf(Traveller t) {
        return dataAccess.getTravellersBookedFlights(t);
    }

    @Override
    public ArrayList<BookedFlight> getTravellersOnFlight(Flight f) {
        return dataAccess.getTravellersOnFlight(f);
    }

}
