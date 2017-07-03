package ca.umanitoba.cs.comp3350.saveonflight.persistence;

import java.util.ArrayList;

import ca.umanitoba.cs.comp3350.saveonflight.objects.BookedFlight;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Flight;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Traveller;

public interface BookedFlightAccess extends DataAccess<BookedFlight> {
    boolean add(BookedFlight bf);

    boolean remove(BookedFlight bf);

    ArrayList<BookedFlight> searchByTraveller(Traveller t);
}
