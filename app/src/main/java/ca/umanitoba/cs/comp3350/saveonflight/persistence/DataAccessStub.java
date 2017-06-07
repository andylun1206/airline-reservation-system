package ca.umanitoba.cs.comp3350.saveonflight.persistence;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


import ca.umanitoba.cs.comp3350.saveonflight.R;
import ca.umanitoba.cs.comp3350.saveonflight.application.Main;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Airline;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Airport;
import ca.umanitoba.cs.comp3350.saveonflight.objects.BookedFlight;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Flight;
import ca.umanitoba.cs.comp3350.saveonflight.objects.FlightClassEnum;
import ca.umanitoba.cs.comp3350.saveonflight.objects.SearchCriteria;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Traveller;

public abstract class DataAccessStub implements DataAccess {
    private String dbName;
    private String dbType = "stub";
    private AirlineTable airlineTable;
    private AirportTable airportTable;
    private TravellerTable travellerTable;
    private BookedFlightTable bookedFlightTable;
    private FlightTable flightTable;

    public DataAccessStub(String dbName) {
        this.dbName = dbName;
    }

    public DataAccessStub() {
        this(Main.DB_NAME);
    }

    public void open(String dbName) {
        airlineTable = new AirlineTable("Airline");
        airlineTable.initialize();
        airportTable = new AirportTable("Airport");
        airportTable.initialize();
        travellerTable = new TravellerTable("Traveller");
        travellerTable.initialize();
        bookedFlightTable = new BookedFlightTable("BookedFlight");
        bookedFlightTable.initialize();
        flightTable = new FlightTable("Flight");
        flightTable.initialize();
        System.out.println("Opened " + dbType + " database " + dbName);
    }

    public void close() {
        System.out.println("Closed " + dbType + " database " + dbName);
    }

    abstract boolean update(Object... o);

    abstract boolean insert(Object o);

    abstract boolean remove(Object o);

    //abstract void find(Object o);//add, update, remove, find

    // AIRLINE TABLE METHODS
    public ArrayList<Airline> getAirlines() {}

    public boolean insertAirline(Airline airline) {}

    public boolean updateAirline(Airline airline, String name) {}

    public boolean deleteAirline(Airline airline) {}


    // AIRPORT TABLE METHODS
    public ArrayList<Airport> getAirports() {}

    public boolean insertAirport(Airport airport) {}

    public boolean updateAirport(Airport airport, String airportCode) {}

    public boolean deleteAirport(Airport airport) {}


    // BOOKEDFLIGHTS TABLE METHODS
    public ArrayList<BookedFlight> getBookedFlights() {}

    public boolean insertBookedFlight(BookedFlight bookedFlight) {}

    public boolean updateBookedFlight(BookedFlight bookedFlight, Traveller t, Flight f) {}

    public boolean deleteBookedFlight(BookedFlight bookedFlight) {}

    public ArrayList<BookedFlight> getTravellersBookedFlights(Traveller traveller) {
        ArrayList<BookedFlight> result = new ArrayList<BookedFlight>();

        for (int i = 0; i < bookedFlightTable.getBookedFlights().size(); i++) {
            BookedFlight term = bookedFlightTable.getBookedFlights().get(i);
            if (term.getTraveller().getTravellerID() == traveller.getTravellerID()) {
                result.add(term);
            }
        }
        return result;
    }

    public ArrayList<BookedFlight> getTravellersOnFlight(Flight flight) {
        ArrayList<BookedFlight> result = new ArrayList<BookedFlight>();

        for (int i = 0; i < bookedFlightTable.getBookedFlights().size(); i++) {
            BookedFlight term = bookedFlightTable.getBookedFlights().get(i);
            if (term.getFlight().getFlightID().equals(flight.getFlightID())) {
                result.add(term);
            }
        }
        return result;
    }

    // FLIGHTS TABLE METHODS
    public ArrayList<Flight> getFlights() {}

    public boolean insertFlight(Flight flight) {}

    public boolean updateFlight(Flight flight, String flightID, Date departDate, Date arriveDate, Airline airline, Airport origin,
                                Airport dest, double price, int capacity, int seatsTaken, FlightClassEnum flightClass) {}

    public boolean deleteFlight(Flight flight) {}

    public ArrayList<Flight> searchByCriteria(SearchCriteria criteria) {
        ArrayList<Flight> table;
        table = createTableByOriginAndDestination(new ArrayList<Flight>(), criteria.getOrigin(), criteria.getDestination());
        table = removeByDepartureDate(table, criteria.getDepartureDate());
        table = removeByNumTravellers(table, criteria.getNumTravellers());
        if (!(criteria.getMaxPrice() == 0.0))
            table = removeByMaxPrice(table, criteria.getMaxPrice());
        if (!(criteria.getPreferredAirlines() == null))
            table = removeByPreferredAirlines(table, criteria.getPreferredAirlines());
        if (!(criteria.getPreferredClass() == null))
            table = removeByPreferredClass(table, criteria.getPreferredClass());
        table = removeByNonstop(table);
        table = removeByRefundable(table);

        return table;
    }


    // TRAVELLERS TABLE METHODS
    public ArrayList<Traveller> getTravellers() {
        ArrayList<Traveller> result = new ArrayList<>();
        result.addAll(travellerTable.getTravellers());
        return result;
    }

    public boolean insertTraveller(Traveller traveller) {
        return travellerTable.getTravellers().add(traveller);
    }

    public boolean updateTraveller(Traveller traveller, int newID, String newName) {
        boolean result = false;

        int index = travellerTable.getTravellers().indexOf(traveller);
        if (index >= 0) {
            Traveller toUpdate = travellerTable.getTravellers().get(index);
            toUpdate.setTravellerID(newID);
            toUpdate.setName(newName);
            result = true;
        }

        return result;
    }

    public boolean deleteTraveller(Traveller traveller) {
        int index;
        index = travellerTable.getTravellers().indexOf(traveller);
        if (index >= 0) {
            travellerTable.getTravellers().remove(index);
            return true;
        }
        return false;
    }

    private ArrayList<Flight> createTableByOriginAndDestination(ArrayList<Flight> table, Airport origin, Airport destination) {
        Flight temp;
        for (int i = 0; i < flightTable.getFlights().size(); i++) {
            temp = flightTable.getFlights().get(i);
            if (temp.getOrigin().equals(origin) && temp.getDestination().equals(destination)) {
                table.add(temp);
            }
        }
        if (table.isEmpty())
            return new ArrayList<Flight>();
        return table;
    }

    private ArrayList<Flight> removeByDepartureDate(ArrayList<Flight> table, Date departureDate) {
        Calendar calFilterBy = Calendar.getInstance();
        calFilterBy.setTime(departureDate);

        if (!table.isEmpty()) {
            Flight temp;
            Calendar calTemp = Calendar.getInstance();
            for (int i = 0; i < table.size(); i++) {
                temp = table.get(i);
                calTemp.setTime(temp.getDepartureTime());
                //if (!temp.getDepartureTime().equals(departureDate)) {
                if (calTemp.get(Calendar.YEAR) != calFilterBy.get(Calendar.YEAR) ||
                        calTemp.get(Calendar.DAY_OF_YEAR) != calFilterBy.get(Calendar.DAY_OF_YEAR)) {
                    table.remove(temp);
                    i--;
                }
            }
        }
        return table;
    }

    private ArrayList<Flight> removeByNumTravellers(ArrayList<Flight> table, int numTravellers) {
        if (!table.isEmpty()) {
            Flight temp;
            for (int i = 0; i < table.size(); i++) {
                temp = table.get(i);
                if (temp.getCapacity() < numTravellers) {
                    table.remove(temp);
                    i--;
                }
            }
        }
        return table;
    }

    private ArrayList<Flight> removeByMaxPrice(ArrayList<Flight> table, double maxPrice) {
        if (!table.isEmpty()) {
            Flight temp;
            for (int i = 0; i < table.size(); i++) {
                temp = table.get(i);
                if (temp.getPrice() > maxPrice) {
                    table.remove(temp);
                    i--;
                }
            }
        }
        return table;
    }

    private ArrayList<Flight> removeByPreferredAirlines(ArrayList<Flight> table, Airline preferredAirlines) {
        if (!table.isEmpty()) {
            Flight temp;
            for (int i = 0; i < table.size(); i++) {
                temp = table.get(i);
                if (!temp.getAirline().equals(preferredAirlines)) {
                    table.remove(temp);
                    i--;
                }
            }
        }
        return table;
    }

    private ArrayList<Flight> removeByPreferredClass(ArrayList<Flight> table, FlightClassEnum preferredClass) {
        if (!table.isEmpty()) {
            Flight temp;
            for (int i = 0; i < table.size(); i++) {
                temp = table.get(i);
                if (!temp.getFlightClass().equals(preferredClass)) {
                    table.remove(temp);
                    i--;
                }
            }
        }
        return table;
    }

    private ArrayList<Flight> removeByNonstop(ArrayList<Flight> table) {
        /*if (table.isEmpty())
            return null;
         */
        // Adding more stuff later
        return table;
    }

    private ArrayList<Flight> removeByRefundable(ArrayList<Flight> table) {
        /*if (table.isEmpty())
            return null;
         */
        // Adding more stuff later
        return table;
    }
}
