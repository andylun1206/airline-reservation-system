package ca.umanitoba.cs.comp3350.saveonflight.persistence;

import java.util.ArrayList;

import ca.umanitoba.cs.comp3350.saveonflight.objects.BookedFlight;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Flight;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Traveller;

/**
 * Created by longyu on 2017-06-06.
 */

public class BookedFlightTable extends DataAccessStub {
    private String dbName;
    private BookedFlight bookedFlight;
    private ArrayList<BookedFlight> bookedFlights;

    public BookedFlightTable(String dbName) {
        this.dbName = dbName;
    }

    public void initialize() {
        bookedFlights = new ArrayList<BookedFlight>();
        ArrayList<Traveller> travellers=super.getTravellers();
        ArrayList<Flight> flights=super.getFlights();
        bookedFlight = new BookedFlight(travellers.get(0), flights.get(0));
        bookedFlights.add(bookedFlight);
        bookedFlight = new BookedFlight(travellers.get(0), flights.get(1));
        bookedFlights.add(bookedFlight);
        bookedFlight = new BookedFlight(travellers.get(1), flights.get(2));
        bookedFlights.add(bookedFlight);
        bookedFlight = new BookedFlight(travellers.get(2), flights.get(3));
        bookedFlights.add(bookedFlight);
        System.out.println("Opened " + " database " + dbName);
    }

    @Override
    public ArrayList<BookedFlight> getBookedFlights() {
        ArrayList<BookedFlight> result = new ArrayList<>();
        result.addAll(bookedFlights);
        return result;
    }

    public boolean insert(Object o){
        return bookedFlights.add((BookedFlight) o);
    }
    public boolean update(Object... o){
        int index;

        index = bookedFlights.indexOf((BookedFlight) o[0]);
        if (index >= 0) {
            BookedFlight temp = bookedFlights.get(index);
            temp.setTraveller((Traveller) o[1]);
            temp.setFlight((Flight) o[2]);
            return true;
        }

        return false;
    }
    public boolean remove(Object o){
        int index;
        index = bookedFlights.indexOf((BookedFlight) o);
        if (index >= 0) {
            bookedFlights.remove(index);
            return true;
        }
        return false;

    }
    public void find(Object o){

    }
}
